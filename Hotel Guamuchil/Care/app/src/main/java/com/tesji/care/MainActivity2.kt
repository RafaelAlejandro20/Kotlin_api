package com.tesji.care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {
    private val urlBase = "http://10.0.2.2:5000/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btn_insertar = findViewById<Button>(R.id.insertar)
        val et_nombre = findViewById<EditText>(R.id.nombre)
        val et_contenido = findViewById<EditText>(R.id.contenido)

        btn_insertar.setOnClickListener {
            insertar(et_nombre.text.toString(),et_contenido.text.toString())
        }

        val btn_inicio = findViewById<Button>(R.id.btn_inicio)
        val btn_anadir = findViewById<Button>(R.id.btn_anadir)
        val btn_editar = findViewById<Button>(R.id.btn_editar)

        btn_inicio.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java).apply {}
            startActivity(homeIntent)
        }
        btn_anadir.setOnClickListener {
            val homeIntent = Intent(this, MainActivity2::class.java).apply {}
            startActivity(homeIntent)
        }
        btn_editar.setOnClickListener {
            val homeIntent = Intent(this, EditarActivity::class.java).apply {}
            startActivity(homeIntent)
        }
    }
    private fun insertar(nombre:String,contenido:String){
        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.insertUser(nombre,contenido)
            if (response.isSuccessful){
                Toast.makeText(applicationContext, "Dato ingresado con exito", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Error en Retrofit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}