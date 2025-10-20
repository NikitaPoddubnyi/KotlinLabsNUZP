
import org.example.helloworld.BuildConfig
import com.diacht.ktest.compose.startTestUi
import kotlin.math.tan
import kotlin.math.sin

fun seed(): String = "NikitaPoddubnyi"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

/**
 * iCalculate()
 * Обчислює значення tan(x0 + x1 + x2 + x3).
 * Вхідні параметри – цілі числа з дефолтними значеннями:
 * x0 = 10, x1 = -52, x2 = -99, x3 = -111.
 */
fun iCalculate(
    x0: Int = 10,
    x1: Int = -52,
    x2: Int = -99,
    x3: Int = -111
): Double {
    return tan((x0 + x1 + x2 + x3).toDouble())
}

/**
 * dCalculate()
 * Обчислює значення sin(x0 * x1 * x2 * x3).
 * Вхідні параметри – числа з плаваючою крапкою (Double),
 * значення за замовчуванням:
 * x0 = 7.84, x1 = 51.66, x2 = -21.84, x3 = -10.78.
 */
fun dCalculate(
    x0: Double = 7.84,
    x1: Double = 51.66,
    x2: Double = -21.84,
    x3: Double = -10.78
): Double {
    return sin(x0 * x1 * x2 * x3)
}

/**
 * strCalculate()
 * Порівнює два рядки однакової парної довжини,
 * що складаються з символів A, T, G, C, J.
 * - Перевіряються тільки символи 'T' та 'C' з першого рядка.
 * - Якщо відповідні символи у другому рядку не співпадають:
 *   • якщо позиція у першій половині рядка → +2 до результату
 *   • якщо у другій половині → +1 до результату
 * Повертає кількість неспівпадінь.
 */
fun strCalculate(x0: String, x1: String): Int {
    require(x0.length == x1.length && x0.length % 2 == 0) {
        "Рядки повинні бути однакової парної довжини"
    }

    var result = 0
    val half = x0.length / 2

    for (i in x0.indices) {
        val c0 = x0[i]
        val c1 = x1[i]

        if (c0 == 'T' || c0 == 'C') {
            if (c0 != c1) {
                result += if (i < half) 2 else 1
            }
        }
    }
    return result
}

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")
    println("iCalculate(): ${iCalculate()}")
    println("dCalculate(): ${dCalculate()}")
    println("strCalculate(\"TACGTC\", \"TAGGCC\"): ${strCalculate("TACGTC", "TAGGCC")}")

    startTestUi(seed(), labNumber())

}
