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
    val urlBase = "https://rafaelalejandro.pythonanywhere.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mostrar()
    }
    fun mostrar(){
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
                val id = findViewById<TextView>(R.id.id)
                val nombre = findViewById<TextView>(R.id.nombre)
                id.text = "${response.first()?.Id}"//response.first().Id
                nombre.text = response.first().Nombre

            }
        }
    }
    fun buscar(){
        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.getUserPostById("2")
            if(response.isSuccessful){
                runOnUiThread {
                    val id = findViewById<TextView>(R.id.id)
                    id.text = "${response.body()?.Id} - ${response.body()?.Nombre}"
                }
            }else{
                Log.e("Error retrofit","${response.code()} - ${response.message()}")
            }
        }
    }
}