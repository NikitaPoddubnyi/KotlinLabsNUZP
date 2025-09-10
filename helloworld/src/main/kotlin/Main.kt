package org.example

fun seed(): Double = 28.0

fun labNumber(): Int = 1

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    var kitty = "Васько"
    kitty += " \uD83D\uDC31"
    var age = 7
    println("Кошеня №1 - $kitty віком $age років")

    val catName: String = "Мурзик \uD83D\uDC08"
    val weight: Float = 3.5f
    println("Кошеня №2 - $catName з вагою $weight кг")

    var secCat = "Мурзик"
    secCat += " \uD83D\uDC06"
    age = 6
    val newWeight = 8.2f
    println("Кошеня №3 - $secCat з вагою $newWeight кг та віком $age років!")
}
