import com.diacht.ktest.*
import org.example.helloworld.BuildConfig
import com.diacht.ktest.compose.startTestUi
import kotlinx.coroutines.*
import kotlin.math.sqrt


fun seed(): String = "NikitaPoddubnyi"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

suspend fun sendToServer(str: String): Int {
    delay(300)
    println("📤 Відправлено на сервер: $str")
    val result = str.toIntOrNull() ?: str.length 
    println("📥 Отримано з сервера: $result")
    return result
}

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {
    val deferredResults = strList.map { str ->
        async { sendToServer(str) }
    }

    val results = deferredResults.awaitAll()

    val sumSquares = results.sumOf { it * it }
    sqrt(sumSquares.toDouble())
}

fun demonstrateCoroutines() = runBlocking {
    println("\n=== 🔄 ДЕМОНСТРАЦІЯ КОРУТИН ===")

    val testData = listOf("10", "hello", "42", "test", "5")
    println("📋 Тестові дані: $testData")

    val result = serverDataCalculate(testData)
    println("📊 Результат обчислень: $result")
}

fun main(args: Array<String>) = runBlocking {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")
    demonstrateCoroutines()
    startTestUi(seed(), labNumber())
}