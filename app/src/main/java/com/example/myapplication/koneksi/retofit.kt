package com.example.myapplication.koneksi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retofit {
    private var service: ApiEndPoint? = null

    fun getApiClient(): ApiEndPoint? {
        if (service == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.100.158/crud/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            service = retrofit.create(ApiEndPoint::class.java)
        }
        return service
    }

}