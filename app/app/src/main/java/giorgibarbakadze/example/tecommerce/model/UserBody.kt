package giorgibarbakadze.example.tecommerce.model

data class UserBody(
    val email: String, val firstName: String, val lastName: String,
    val city: Int?, val address: String, val zipCode: String, val password: String
)