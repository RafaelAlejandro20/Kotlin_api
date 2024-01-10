package com.tesji.care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RestablecerActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restablecer)

        val email = findViewById<EditText>(R.id.email)
        val cambiar = findViewById<Button>(R.id.cambiar)
        val volver = findViewById<Button>(R.id.volver)

        cambiar.setOnClickListener {
            if(email.text.toString().trim().isEmpty()){
                email.setError("Por favor ingresa un correo")
            }else{
                cambiarContrasena(email.text.toString())
            }
        }
        firebaseAuth = Firebase.auth

        volver.setOnClickListener {
            val newIntent = Intent(this, AuthActivity::class.java).apply {}
            startActivity(newIntent)
        }
    }
    private fun cambiarContrasena(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener() {task ->
                if(task.isSuccessful){
                    alertas("Correo enviado","Verifica tu correo para restablecer tu conraseña")
                }else{
                    alertas("Error de verificación","Por favor verifica tu correo")
                }
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