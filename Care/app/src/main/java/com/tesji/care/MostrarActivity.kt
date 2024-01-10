package com.tesji.care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class MostrarActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val tuColeccion = db.collection("plantas")
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : Adaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        recyclerView = findViewById(R.id.lista)
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter = Adaptador()
        recyclerView.adapter=adapter
        consultarColeccion()

        val salir_lista = findViewById<Button>(R.id.salir_lista)

        salir_lista.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {}
            startActivity(intent)
        }
    }
    private fun consultarColeccion(){
        tuColeccion.get()
            .addOnSuccessListener { querySnapshot ->
                val listaTuModelo = mutableListOf<Plantas>()
                for(document in querySnapshot){
                    val nombre = document.id
                    val nombre_cientifico = document.getString("nombre_cientifico")
                    val agua = document.getString("agua")
                    val sol = document.getString("sol")
                    val cuidados = document.getString("cuidados")
                    if(nombre_cientifico != null && agua != null && sol != null && cuidados != null){
                        val tuModelo = Plantas(nombre,nombre_cientifico,agua,sol,cuidados)
                        listaTuModelo.add((tuModelo))
                    }
                }
                adapter.setDatos(listaTuModelo)
            }
    }
}