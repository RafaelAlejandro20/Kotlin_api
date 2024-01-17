package com.tesji.care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val urlBase = "http://10.0.2.2:5000/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_buscar = findViewById<Button>(R.id.buscar)
        val et_1entrada = findViewById<EditText>(R.id.obtener1)

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

        btn_buscar.setOnClickListener {
            buscar(et_1entrada.text.toString())
        }
        mostrar()
    }
    private fun mostrar(){
        var resultado:String = ""
        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.getUserPost()
            response.forEach {
                println(it)
                resultado += "${it.Id}: ${it.Nombre} - ${it.Nota}\n"
                val tv_datos = findViewById<TextView>(R.id.datos)
                tv_datos.text = resultado
            }
            /*runOnUiThread {
                val tv_datos = findViewById<TextView>(R.id.datos)
                tv_datos.text = response.first().Nombre
            }*/
        }
    }
    private fun buscar(id:String){
        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.getUserById(id)
            if (response.isSuccessful){
                runOnUiThread {
                    val et_nombre = findViewById<TextView>(R.id.nombre)
                    val et_contenido = findViewById<TextView>(R.id.contenido)
                    et_nombre.text = "${response.body()?.Nombre}"
                    et_contenido.text = "${response.body()?.Nota}"
                }
            }else{
                Toast.makeText(applicationContext, "Error en Retrofit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}