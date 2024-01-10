package com.tesji.care

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val salir = findViewById<Button>(R.id.salir)
        val agregar = findViewById<Button>(R.id.agregar)
        val mostrar = findViewById<Button>(R.id.mostrar)

        salir.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java).apply {}
            startActivity(intent)
        }
        agregar.setOnClickListener {
            val intent = Intent(this, PanelActivity::class.java).apply {}
            startActivity(intent)
        }
        mostrar.setOnClickListener {
            val intent = Intent(this, MostrarActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}