
import org.example.helloworld.BuildConfig
import com.diacht.ktest.compose.startTestUi
import kotlin.math.tan
import kotlin.math.sin

fun seed(): String = "NikitaPoddubnyi"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

fun iCalculate(
    x0: Int = 10,
    x1: Int = -52,
    x2: Int = -99,
    x3: Int = -111
): Double {
    return tan((x0 + x1 + x2 + x3).toDouble())
}

fun dCalculate(
    x0: Double = 7.84,
    x1: Double = 51.66,
    x2: Double = -21.84,
    x3: Double = -10.78
): Double {
    return sin(x0 * x1 * x2 * x3)
}

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
