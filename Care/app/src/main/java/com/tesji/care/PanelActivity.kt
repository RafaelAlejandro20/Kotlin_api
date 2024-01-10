package com.tesji.care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class PanelActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val tuColeccion = db.collection("plantas")
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : Adaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel)

        val datos_agua = arrayOf("1 vez a la semana","2 veces a la semana","3 veces a la semana","4 veces a la semana","5 veces a la semana","6 veces a la semana","Diariamente")
        val datos_sol = arrayOf("Sol directo","Sol indirecto","Sol filtrado","Sombra","Media sombra","Luz artificial")

        recyclerView = findViewById(R.id.lista_muestra)
        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter = Adaptador()
        recyclerView.adapter=adapter
        consultarColeccion()

        val guardar = findViewById<Button>(R.id.guardar)
        val salir = findViewById<Button>(R.id.salir)
        val nombre = findViewById<EditText>(R.id.nombre)
        val nombre_cientifico = findViewById<EditText>(R.id.nombre_cientifico)
        val agua = findViewById<Spinner>(R.id.agua)
        val sol = findViewById<Spinner>(R.id.sol)
        val cuidados = findViewById<EditText>(R.id.cuidados)
        val recuperar = findViewById<Button>(R.id.recuperar)
        val eliminar = findViewById<Button>(R.id.eliminar)
        val caja_agua = findViewById<TextView>(R.id.caja_agua)
        val caja_sol = findViewById<TextView>(R.id.caja_sol)

        val adaptador_agua = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            datos_agua)
        adaptador_agua.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
            agua.adapter = adaptador_agua

        val adaptador_sol = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            datos_sol)
        adaptador_sol.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
            sol.adapter = adaptador_sol

        agua.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    caja_agua.text = agua.selectedItem.toString()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        sol.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                caja_sol.text = sol.selectedItem.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        guardar.setOnClickListener {
            if(nombre.text.toString().trim().isEmpty()){
                nombre.setError("Ingresa un valor")
            }else if(caja_agua.text.toString().trim().isEmpty()){
                caja_agua.setError("Ingresa un valor")
            }else if(caja_sol.text.toString().trim().isEmpty()){
                caja_sol.setError("Ingresa un valor")
            }else{
                if(nombre_cientifico.text.toString().trim().isEmpty()){
                    nombre_cientifico.setText("Desconocido")
                }
                if(cuidados.text.toString().trim().isEmpty()){
                    cuidados.setText("Ninguno")
                }
                db.collection("plantas").document(nombre.text.toString()).set(
                    hashMapOf("nombre_cientifico" to nombre_cientifico.text.toString(),
                        "agua" to caja_agua.text.toString(),
                        "sol" to caja_sol.text.toString(),
                        "cuidados" to cuidados.text.toString())
                )
                consultarColeccion()
            }
        }
        recuperar.setOnClickListener {
            if(nombre.text.toString().trim().isEmpty()){
                nombre.setError("Coloca un nombre para buscar")
            }else{
                db.collection("plantas").document(nombre.text.toString()).get().addOnSuccessListener {
                    if(it.get("nombre_cientifico") == null && it.get("agua") == null && it.get("sol") == null && it.get("cuidados") == null){
                        alertas("Registro no encontrado","No encontramos ningun registro asociado a ese nombre.")
                    }else{
                        nombre_cientifico.setText(it.get("nombre_cientifico") as String ?)
                        caja_agua.setText(it.get("agua") as String ?)
                        caja_sol.setText(it.get("sol") as String ?)
                        cuidados.setText(it.get("cuidados") as String ?)
                    }
                }
            }
        }
        eliminar.setOnClickListener {
            if(nombre.text.trim().isEmpty()){
                nombre.setError("Coloca el nombre")
            }else{
                db.collection("plantas").document(nombre.text.toString()).delete()
                consultarColeccion()
                nombre.setText("")
                nombre_cientifico.setText("")
                cuidados.setText("")
            }
        }
        salir.setOnClickListener {
            val panelIntent = Intent(this, HomeActivity::class.java).apply {
            }
            startActivity(panelIntent)
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
    private fun alertas(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}