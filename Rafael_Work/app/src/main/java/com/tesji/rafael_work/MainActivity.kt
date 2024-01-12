package com.tesji.rafael_work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val urlBase = "https://rafaelalejandro.pythonanywhere.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buscar_valor = findViewById<EditText>(R.id.buscar)
        val boton = findViewById<Button>(R.id.boton)

        boton.setOnClickListener {
            buscar(buscar_valor.text.toString())
        }

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
    fun buscar(id:String){
        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.getUserPostById(id)
            val id = findViewById<TextView>(R.id.id)
            val nombre = findViewById<TextView>(R.id.nombre)
            if(response.isSuccessful){
                runOnUiThread {
                    id.text = "${response.body()?.Id}"
                    nombre.text = "${response.body()?.Nombre}"
                    showAlert()
                }
            }else{
                showAlert()
            }
        }
    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Por favor comprueba lo valores ingresados.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}