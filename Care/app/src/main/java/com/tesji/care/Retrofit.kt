package com.tesji.care

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rafaelalejandro.pythonanywhere.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val ConsumirApi = retrofit.create(ConsumirApi::class.java)
}