package com.tesji.rafael_work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val urlBase = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.getUserPost()
            response.forEach {
                println(it)
            }
            runOnUiThread {
                val json = findViewById<TextView>(R.id.json)
                json.text = response.first().Nombre
            }
        }
    }
}