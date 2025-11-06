package com.diacht.ktest.caffe

import com.diacht.ktest.Machine
import com.diacht.ktest.Product
import com.diacht.ktest.ProductType

class CafeMachine(storage: CafeStorage) : Machine(storage) {

    fun make(productType: ProductType): Product {
        val recipe = CafeReceipts.getRecipe(productType)
        setReceipt(recipe)
        return executeProcess()
    }
}