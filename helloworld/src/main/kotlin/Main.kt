import com.diacht.ktest.compose.startTestUi
import org.example.helloworld.BuildConfig
import kotlin.math.*
import kotlinx.coroutines.*
import java.net.URL

fun seed(): String = "Piddubnyi_Nikita"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

suspend fun getNumberFromServer(message: String): Int {
    return withContext(Dispatchers.IO) {
        val url = URL("http://diacht.2vsoft.com/api/send-number?message=$message")
        val connection = url.openConnection()
        connection.connect()
        val input = connection.getInputStream()
        val buffer = ByteArray(128)
        val bytesRead = input.read(buffer)
        input.close()
        String(buffer, 0, bytesRead).toInt()
    }
}

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {
    val firstSix = strList.take(6)

    val deferredResults = firstSix.map { str ->
        async { getNumberFromServer(str) }
    }

    val results = deferredResults.awaitAll()

    val maxValue = results.maxOrNull() ?: throw IllegalArgumentException("Список порожній")

    tanh(maxValue.toDouble())
}

fun main() = runBlocking {
    val data = listOf("x0", "x1", "x2", "x3", "x4", "x5")
    val result = serverDataCalculate(data)
    println(result)
    startTestUi(seed(), labNumber())
}