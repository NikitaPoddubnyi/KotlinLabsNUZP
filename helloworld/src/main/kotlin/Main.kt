package org.example

fun seed(): String = "Piddubnyi_Nikita"
fun labNumber(): Int = 1

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    var kitty = "Васько"
    kitty += " \uD83D\uDC31"
    val age = 7
    println("Кошеня №1 - $kitty віком $age років")

    
    val catName: String = "Мурзик \uD83D\uDC08"
    val weight: Float = 3.5f
    println("Кошеня №2 - $catName з вагою $weight кг")

    val catName2: String = "Рудий \uD83D\uDC06"
    val weight2: Float = 8.2f
    val age2 = 6
    println("Кошеня №3 - $catName2 віком $age2 та вагою $weight2 кг")
}

