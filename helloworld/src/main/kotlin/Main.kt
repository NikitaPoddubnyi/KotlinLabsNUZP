import com.diacht.ktest.*
import org.example.helloworld.BuildConfig
import com.diacht.ktest.compose.startTestUi
import kotlinx.coroutines.*
import kotlin.math.sqrt

fun seed(): String = "NikitaPoddubnyi"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

suspend fun sendToServer(str: String): Int {
    delay(10000)
    println("📤 Відправлено на сервер: '$str'")
    val result = str.toIntOrNull() ?: str.length
    println("📥 Отримано з сервера: $result")
    return result
}

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {
    println("\n🔄 Початок асинхронного завантаження даних...")
    val startTime = System.currentTimeMillis()

    val deferredResults = strList.map { str ->
        async { sendToServer(str) }
    }

    val results = deferredResults.awaitAll()

    // Розрахунок √(x0² + x1² + ... + xn²)
    val sumSquares = results.sumOf { it * it }
    val finalResult = sqrt(sumSquares.toDouble())

    val endTime = System.currentTimeMillis()
    val totalTime = (endTime - startTime) / 1000.0

    println("✅ Всі дані завантажено за ${totalTime} секунд")
    println("📊 Результат обчислень: $finalResult")

    finalResult
}

fun demonstrateCoroutines() = runBlocking {
    println("\n=== 🔄 ДЕМОНСТРАЦІЯ КОРУТИН ===")

    val testData = listOf("10", "hello", "42", "test", "5")
    println("📋 Тестові дані: $testData")
    println("⏰ Кожен запит до сервера займає 10 секунд...")
    println("🚀 Запускаємо асинхронне завантаження...")

    val result = serverDataCalculate(testData)
    println("\n🎯 Фінальний результат: $result")
}

fun main(args: Array<String>) = runBlocking {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    demonstrateCoroutines()

    startTestUi(seed(), labNumber())
}