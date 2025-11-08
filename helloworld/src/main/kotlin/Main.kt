package org.example
import com.diacht.ktest.*
import org.example.helloworld.BuildConfig
import com.diacht.ktest.compose.startTestUi

fun seed(): String = "NikitaPoddubnyi"

fun labNumber(): Int = 1

fun main() {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    var kitty = "Васько"
    kitty += " \uD83D\uDC31"
    var age = 7
    println("Кошеня №1 - $kitty віком $age років")

    val catName: String = "Мурзик \uD83D\uDC08"
    val weight1: Float = 3.5f
    println("Кошеня №2 - $catName з вагою $weight1 кг")

    var secCat = "Рудий"
    secCat += " \uD83D\uDC06"
    age = 6
    val weight2: Float = 8.2f
    println("Кошеня №3 - $secCat з вагою $weight2 кг та віком $age років!")
}
