package giorgibarbakadze.example.tecommerce.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    companion object {
        private const val BASE_URL: String = "http://139.59.152.216:8002/rest/auth/"

        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        private fun getRetrofitInstance(): Retrofit {
            lateinit var retrofit: Retrofit
            try {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            } catch (E: Exception) {
                E.printStackTrace()
            }
            return retrofit
        }

        val apiInterface: ApiInterface = getRetrofitInstance().create(ApiInterface::class.java)
    }

}