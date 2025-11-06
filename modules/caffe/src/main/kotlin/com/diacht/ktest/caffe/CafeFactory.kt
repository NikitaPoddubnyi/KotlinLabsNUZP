package com.diacht.ktest.caffe

import com.diacht.ktest.FactoryItf
import com.diacht.ktest.Product
import com.diacht.ktest.ProductType
import com.diacht.ktest.NONE

class CafeFactory : FactoryItf() {
    private val storage = CafeStorage()
    private val machine = CafeMachine(storage)
    private val orderStatistics = mutableMapOf<ProductType, Int>() // <тип, кількість замовлень>

    override fun resetSimulation() {
        storage.resetSimulation()
        orderStatistics.clear()
    }

    override fun loadProducts(productsFromSupplier: List<Product>) {
        productsFromSupplier.forEach { storage.addProduct(it) }
    }

    override fun order(order: List<Pair<ProductType, Int>>): List<Product> {
        val result = mutableListOf<Product>()
        for ((type, count) in order) {
            repeat(count) {
                val product = machine.make(type)
                result.add(product)
                orderStatistics[type] = (orderStatistics[type] ?: 0) + 1
            }
        }
        return result
    }

    override fun getLeftovers(): List<Product> = storage.getLeftovers()

    override fun getEarnings(): Int {
        return orderStatistics.entries.sumOf { (type, count) ->
            getPriceForProduct(type) * count
        }
    }

    override fun getOrderStatistics(): List<Product> =
        orderStatistics.map { (type, count) -> Product(type, count) }

    override fun getPopularDrink(): Product {
        if (orderStatistics.isEmpty()) return Product(NONE, 0)
        val (type, count) = orderStatistics.maxByOrNull { it.value }!!
        return Product(type, count)
    }

    override fun getUnpopularDrink(): Product {
        if (orderStatistics.isEmpty()) return Product(NONE, 0)
        val (type, count) = orderStatistics.minByOrNull { it.value }!!
        return Product(type, count)
    }

    override fun getMostEarnings(): Pair<ProductType, Int> {
        if (orderStatistics.isEmpty()) return Pair(NONE, 0)

        val mostProfitable = orderStatistics.entries
            .map { (type, count) ->
                type to (getPriceForProduct(type) * count)
            }
            .maxByOrNull { it.second }!!

        return mostProfitable
    }

    private fun getPriceForProduct(type: ProductType): Int {
        return when (type) {
            ESPRESSO -> 25
            AMERICANO -> 30
            CAPPUCCINO -> 30
            AMERICANO_WI_MILK -> 35
            LATE -> 40
            CACAO_DRINK -> 40
            else -> 0
        }
    }
}