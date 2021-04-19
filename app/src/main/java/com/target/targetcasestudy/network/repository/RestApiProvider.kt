package com.target.targetcasestudy.network.repository

import com.target.targetcasestudy.network.api.ApiConstants
import com.target.targetcasestudy.network.api.RestService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestApiProvider {
    companion object {
        fun getRestApi(): RestService? {
            val okHttpClient =
                getOkHttpClient()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.REPO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(RestService::class.java)
        }

        fun getOkHttpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.interceptors().add(httpLoggingInterceptor)
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            return builder.build()
        }
    }
}