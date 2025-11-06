package com.diacht.ktest.caffe

import com.diacht.ktest.*
import java.util.concurrent.TimeUnit

object EspressoReceipt : Receipt(
    products = listOf(
        Product(type = COFFEE, count = 7),
        Product(type = WATER, count = 25),
    ),
    time = 5,
    timeUnit = TimeUnit.SECONDS,
    outProductType = ESPRESSO,
    price = 25,
)

object AmericanoReceipt : Receipt(
    products = listOf(
        Product(type = COFFEE, count = 7),
        Product(type = SUGAR, count = 7),
        Product(type = WATER, count = 120),
    ),
    time = 6,
    timeUnit = TimeUnit.SECONDS,
    outProductType = AMERICANO,
    price = 30,
)

object CappuccinoReceipt : Receipt(
    products = listOf(
        Product(type = COFFEE, count = 9),
        Product(type = SUGAR, count = 7),
        Product(type = WATER, count = 110),
        Product(type = MILK, count = 50),
    ),
    time = 10,
    timeUnit = TimeUnit.SECONDS,
    outProductType = CAPPUCCINO,
    price = 30,
)

object AmericanoWithMilkReceipt : Receipt(
    products = listOf(
        Product(type = COFFEE, count = 7),
        Product(type = SUGAR, count = 14),
        Product(type = WATER, count = 90),
        Product(type = MILK, count = 30),
    ),
    time = 8,
    timeUnit = TimeUnit.SECONDS,
    outProductType = AMERICANO_WI_MILK,
    price = 35,
)

object LateReceipt : Receipt(
    products = listOf(
        Product(type = COFFEE, count = 10),
        Product(type = SUGAR, count = 14),
        Product(type = WATER, count = 50),
        Product(type = MILK, count = 150),
    ),
    time = 10,
    timeUnit = TimeUnit.SECONDS,
    outProductType = LATE,
    price = 40,
)

object CacaoDrinkReceipt : Receipt(
    products = listOf(
        Product(type = CACAO_POWDER, count = 13),
        Product(type = SUGAR, count = 25),
        Product(type = WATER, count = 30),
        Product(type = MILK, count = 180),
    ),
    time = 9,
    timeUnit = TimeUnit.SECONDS,
    outProductType = CACAO_DRINK,
    price = 40,
)

object CafeReceipts {
    fun getRecipe(type: ProductType): Receipt = when (type) {
        ESPRESSO -> EspressoReceipt
        AMERICANO -> AmericanoReceipt
        CAPPUCCINO -> CappuccinoReceipt
        AMERICANO_WI_MILK -> AmericanoWithMilkReceipt
        LATE -> LateReceipt
        CACAO_DRINK -> CacaoDrinkReceipt
        else -> throw IllegalArgumentException("Немає рецепта для $type")
    }
}