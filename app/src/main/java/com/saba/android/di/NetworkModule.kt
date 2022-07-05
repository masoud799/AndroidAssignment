package com.saba.android.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saba.android.BuildConfig
import com.saba.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module that holds Network related classes
 */
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides [HttpLoggingInterceptor] instance
     */
    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }

    /**
     * Provides Headers instance
     */
    @Provides
    fun getHeaderInterceptor():Interceptor{
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .header("jsonType", "simple")
                    .build()
            chain.proceed(request)
        }
    }


    /**
     * Provides [OkHttpClient] instance
     */
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * Provides [Retrofit] instance
     */
    @Provides
    fun provideRetrofitTest(okHttpClient: OkHttpClient) : Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://www.filimo.com/api/en/v1/")
            .client(okHttpClient)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(contentType))
            .build()
    }

    /**
     * Provides [ApiService] instance
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}