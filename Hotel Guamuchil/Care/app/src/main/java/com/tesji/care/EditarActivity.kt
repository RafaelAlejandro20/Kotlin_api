package com.tesji.care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditarActivity : AppCompatActivity() {
    private val urlBase = "http://10.0.2.2:5000/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        val btn_buscar = findViewById<Button>(R.id.buscar)
        val btn_editar = findViewById<Button>(R.id.editar)
        val btn_eliminar = findViewById<Button>(R.id.eliminar)
        val et_dato = findViewById<EditText>(R.id.dato)
        val et_nombre = findViewById<EditText>(R.id.nombre)
        val et_contenido = findViewById<EditText>(R.id.contenido)

        val btn_inicio = findViewById<Button>(R.id.btn_inicio)
        val btn_anadir = findViewById<Button>(R.id.btn_anadir)
        val btn_ed = findViewById<Button>(R.id.btn_editar)

        btn_inicio.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java).apply {}
            startActivity(homeIntent)
        }
        btn_anadir.setOnClickListener {
            val homeIntent = Intent(this, MainActivity2::class.java).apply {}
            startActivity(homeIntent)
        }
        btn_ed.setOnClickListener {
            val homeIntent = Intent(this, EditarActivity::class.java).apply {}
            startActivity(homeIntent)
        }

        btn_buscar.setOnClickListener {
            buscar(et_dato.text.toString())
        }
        btn_editar.setOnClickListener {
            editar(et_dato.text.toString(), et_nombre.text.toString(), et_contenido.text.toString())
        }
        btn_eliminar.setOnClickListener {
            eliminar(et_dato.text.toString())
        }
    }
    private fun buscar(id:String){
        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.getUserById(id)
            if (response.isSuccessful){
                runOnUiThread {
                    val et_nombre = findViewById<EditText>(R.id.nombre)
                    val et_contenido = findViewById<EditText>(R.id.contenido)
                    et_nombre.setText("${response.body()?.Nombre}")
                    et_contenido.setText("${response.body()?.Nota}")
                }
            }else{
                Toast.makeText(applicationContext, "Error en Retrofit", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun editar(id:String, nombre:String, nota:String){
        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.editUser(id,nombre,nota)
            if (response.isSuccessful){
                runOnUiThread {
                    Toast.makeText(applicationContext, "${response.body()?.Resultado}", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext, "Error en Retrofit", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun eliminar(id:String){
        val service = retrofit.create(PostApi::class.java)
        lifecycleScope.launch {
            val response = service.deleteUser(id)
            if (response.isSuccessful){
                runOnUiThread {
                    Toast.makeText(applicationContext, "${response.body()?.Resultado}", Toast.LENGTH_SHORT).show()
                    val et_dato = findViewById<EditText>(R.id.dato)
                    val et_nombre = findViewById<EditText>(R.id.nombre)
                    val et_contenido = findViewById<EditText>(R.id.contenido)
                    et_dato.setText("")
                    et_nombre.setText("")
                    et_contenido.setText("")
                }
            }else{
                Toast.makeText(applicationContext, "Error en Retrofit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}