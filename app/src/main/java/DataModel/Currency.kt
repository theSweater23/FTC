package DataModel

data class Currency(
    val Name: String,
    val CharCode: String,
    val Nominal: Int,
    val PresentValue: Double,
    val PreviousValue: Double
)
