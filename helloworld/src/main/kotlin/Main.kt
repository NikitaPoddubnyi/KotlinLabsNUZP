import com.diacht.ktest.*
import org.example.helloworld.BuildConfig
import com.diacht.ktest.compose.startTestUi
import com.diacht.ktest.caffe.CafeFactory
import com.diacht.ktest.caffe.*
import kotlinx.coroutines.*
import kotlin.math.*

fun seed(): String = "NikitaPoddubnyi"

fun labNumber(): Int = BuildConfig.LAB_NUMBER

fun getSimulationObject(): FactoryItf {
    return CafeFactory()
}

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {
    // Асинхронно відправляємо всі рядки на сервер
    val deferred = strList.map { str ->
        async {
            sendToServer(str)
        }
    }

    // Очікуємо результати від всіх корутин
    val results = deferred.awaitAll()

    // Обчислюємо суму квадратів
    var sumOfSquares = 0.0
    results.forEach { result ->
        sumOfSquares += result * result
    }

    // Повертаємо квадратний корінь з суми квадратів
    return@coroutineScope sqrt(sumOfSquares)
}


val serverValues = mapOf(
    "7a859428b661c9b666556ac117c31da3" to 83.0,
    "0e4c09fddcfec93abfa0107640e62286" to 71.0,
    "c3f75b8a2fef28ed755a6a7895ce6ce7" to 61.0,
    "36f9ea8bb7760f2298f6bf6c6247c49d" to 53.0,
    "0a349619f993ce97d90cbe6d7c8c5536" to 40.1,

    "c55717153e8ef7ae0cbe8fcac002b280" to 150.0,
    "eee663ac8fc91280636e18d2a0448211" to 120.0,
    "9c7cec9d021471252dbdaa6f3226645f" to 90.0,
    "a63c42676ac37df20ec094984e5625c8" to 95.11,
    "aeb5914f8bbd2a1bd03e0da019714cd2" to 100.0
)
// Функція для відправки даних на сервер
suspend fun sendToServer(data: String): Double {
    delay(100)

    return serverValues[data] ?: data.sumOf { it.code } / 500.0
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

// Допоміжна функція для відображення одиниць виміру
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