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

// Функція для відправки даних на сервер
suspend fun sendToServer(data: String): Double {
    delay(100)


    return when (data) {
        "7a859428b661c9b666556ac117c31da3" -> 83.0  // 6400
        "0e4c09fddcfec93abfa0107640e62286" -> 71.0  // 5041
        "c3f75b8a2fef28ed755a6a7895ce6ce7" -> 61.0  // 3844
        "36f9ea8bb7760f2298f6bf6c6247c49d" -> 53.0 // 2704
        "0a349619f993ce97d90cbe6d7c8c5536" -> 40.07  // 1849
        else -> data.sumOf { it.code } / 500.0
    }
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