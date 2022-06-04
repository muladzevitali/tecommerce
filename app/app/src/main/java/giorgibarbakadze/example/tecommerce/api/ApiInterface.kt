package giorgibarbakadze.example.tecommerce.api

import giorgibarbakadze.example.tecommerce.model.LoginResponse
import giorgibarbakadze.example.tecommerce.model.SignInBody
import giorgibarbakadze.example.tecommerce.model.UserBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("token")
    suspend fun signIn(@Body info: SignInBody): LoginResponse

    @Headers("Content-Type:application/json")
    @POST("users/")
    suspend fun registerUser(
        @Body info: UserBody
    ): UserBody
}