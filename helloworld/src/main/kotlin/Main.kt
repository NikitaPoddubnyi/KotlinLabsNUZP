import com.diacht.ktest.*
import org.example.helloworld.BuildConfig
import com.diacht.ktest.compose.startTestUi
import com.diacht.ktest.caffe.CafeFactory
import com.diacht.ktest.caffe.*
import kotlinx.coroutines.*
import kotlin.math.*
import java.math.BigInteger

fun seed(): String = "NikitaPoddubnyi"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

fun getSimulationObject(): FactoryItf {
    return CafeFactory()
}

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {
    val deferred = strList.map { str ->
        async {
            // For MD5 strings, convert them to numerical values properly
            sendToServer(str)
        }
    }

    val results = deferred.awaitAll()

    // Calculate sum of squares using BigInteger to avoid overflow
    var sumOfSquares = BigInteger.ZERO
    results.forEach { result ->
        val bigResult = BigInteger.valueOf(result.toLong())
        sumOfSquares += bigResult * bigResult
    }

    // Calculate square root
    return@coroutineScope sqrt(sumOfSquares.toDouble())
}

suspend fun sendToServer(data: String): Int {
    delay(100)

    // Convert MD5 hex string to integer value
    // Take first 8 characters to avoid overflow and convert from hex
    val hexSubstring = data.substring(0, 8)
    return BigInteger(hexSubstring, 16).toInt()
}

// Alternative implementation that processes the entire MD5 string:
suspend fun serverDataCalculateAlternative(strList: List<String>): Double = coroutineScope {
    val deferred = strList.map { str ->
        async {
            // Process the MD5 string by summing character codes
            // This ensures we get consistent positive values
            str.fold(0L) { acc, char ->
                acc + char.code.toLong()
            }.toInt()
        }
    }

    val results = deferred.awaitAll()

    // Use Double for calculations to avoid overflow
    val sumOfSquares = results.fold(0.0) { acc, value ->
        acc + (value.toDouble() * value.toDouble())
    }

    return@coroutineScope sqrt(sumOfSquares)
}

fun demonstrateCoffee() {
    println("\n=== ☕ ДЕМОНСТРАЦІЯ КАВ'ЯРНІ ===")

    val factory = CafeFactory()

    val initialProducts = listOf(
        Product(MILK, 1000),
        Product(COFFEE, 500),
        Product(SUGAR, 1000),
        Product(WATER, 5000),
        Product(CACAO_POWDER, 200)
    )
    factory.loadProducts(initialProducts)

    println("📦 Початкові продукти завантажено:")
    initialProducts.forEach { product ->
        println("- ${product.type}: ${product.count}${getUnit(product.type)}")
    }

    val order = listOf(
        ESPRESSO to 3,
        CAPPUCCINO to 2,
        LATE to 4,
        AMERICANO to 1,
        AMERICANO_WI_MILK to 2,
        CACAO_DRINK to 1
    )

    println("\n🧾 Замовлення:")
    order.forEach { (type, count) ->
        println("- $type: $count шт.")
    }

    val result = factory.order(order)

    println("\n✅ Приготовано напої:")
    result.groupBy { it.type }.forEach { (type, list) ->
        println("- $type: ${list.size} шт.")
    }

    println("\n📊 Статистика:")
    println("- Загальний дохід: ${factory.getEarnings()} грн")

    val popular = factory.getPopularDrink()
    println("- Найпопулярніший напій: ${popular.type} (${popular.count} замовлень)")

    val unpopular = factory.getUnpopularDrink()
    println("- Найменш популярний напій: ${unpopular.type} (${unpopular.count} замовлень)")

    val mostEarnings = factory.getMostEarnings()
    println("- Найприбутковіший напій: ${mostEarnings.first} (${mostEarnings.second} грн)")

    println("\n📦 Залишки на складі:")
    factory.getLeftovers().forEach {
        println("- ${it.type}: ${it.count}${getUnit(it.type)}")
    }

    println("\n📈 Детальна статистика замовлень:")
    factory.getOrderStatistics().forEach {
        println("- ${it.type}: ${it.count} замовлень")
    }
}

// Helper function to get appropriate units
private fun getUnit(type: ProductType): String {
    return when (type) {
        MILK, WATER -> " мл"
        else -> " г"
    }
}

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    demonstrateCoffee()

    startTestUi(seed(), labNumber())
}