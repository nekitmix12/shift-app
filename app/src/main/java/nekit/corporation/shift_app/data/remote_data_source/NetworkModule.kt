package nekit.corporation.shift_app.data.remote_data_source

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {
    private const val BASE_URL = "https://randomuser.me/"

    @Provides
    @Reusable
    fun provideConnectionPool(): ConnectionPool = ConnectionPool(
        maxIdleConnections = 15, keepAliveDuration = 1, timeUnit = TimeUnit.MINUTES
    )



    @Provides
    @Reusable
    fun provideOkHttpClient(
         pool: ConnectionPool
    ): OkHttpClient =
        OkHttpClient.Builder().readTimeout(10, TimeUnit.MINUTES).connectionPool(pool)
            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())
                response.newBuilder().header("Cache-Control", "public, max-age=60").build()
            }.connectTimeout(10, TimeUnit.MINUTES).build()


    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    @Provides
    @Reusable
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient).build()


    @Provides
    @Reusable
    fun provideUserService(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

}