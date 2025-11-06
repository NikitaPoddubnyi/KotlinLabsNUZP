package com.diacht.ktest.caffe

import com.diacht.ktest.Product
import com.diacht.ktest.ProductType
import com.diacht.ktest.Storage
import java.util.concurrent.ConcurrentHashMap

class CafeStorage : Storage {
    private val products = ConcurrentHashMap<ProductType, Int>()

    override fun addProduct(product: Product) {
        products.merge(product.type, product.count, Int::plus)
    }

    override fun checkProductCount(type: ProductType): Int {
        return products[type] ?: 0
    }

    override fun getProduct(productType: ProductType, count: Int): Product {
        val currentCount = checkProductCount(productType)
        if (currentCount < count) {
            throw IllegalStateException("Недостатньо продукту $productType. Запитувано: $count, є: $currentCount")
        }
        products[productType] = currentCount - count
        return Product(productType, count)
    }

    override fun getLeftovers(): List<Product> {
        return products.map { (type, count) -> Product(type, count) }
    }

    override fun resetSimulation() {
        products.clear()
    }
}