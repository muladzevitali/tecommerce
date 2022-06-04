package giorgibarbakadze.example.tecommerce.repository


import android.util.Log
import giorgibarbakadze.example.tecommerce.api.ApiInterface
import giorgibarbakadze.example.tecommerce.model.LoginResponse
import giorgibarbakadze.example.tecommerce.model.SignInBody
import giorgibarbakadze.example.tecommerce.model.UserBody
import retrofit2.HttpException


class Repository(private val apiInterface: ApiInterface) {
    var errorMessage: String? = null

    suspend fun signIn(signInInfo: SignInBody): LoginResponse {
        var loginResponse = LoginResponse("")
        try {

            loginResponse = apiInterface.signIn(signInInfo)
        } catch (e: HttpException) {
            errorMessage = "${e.message()}${e.code()}"
        }
        return loginResponse
    }

    suspend fun signUp(registerInfo: UserBody) {
        try {
            apiInterface.registerUser(registerInfo)
        } catch (e: HttpException) {
            errorMessage = "${e.message()}${e.code()}"
        }
    }

}