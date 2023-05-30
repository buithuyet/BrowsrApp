package com.thryve.browrslib.common

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkFactory {

    private const val BASE_URL = "https://api.github.com"
    private const val API_TOKEN = "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"
    private var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getAppService(): AppService {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", "application/vnd.github+json")
                .addHeader("Authorization", "Bearer $API_TOKEN")
                .build()
            chain.proceed(request)
        }.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(AppService::class.java)
    }
}