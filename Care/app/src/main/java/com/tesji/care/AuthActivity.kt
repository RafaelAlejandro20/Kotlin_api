package com.tesji.care

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    Correo
}

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setup()
    }
    private fun setup() {
        title = "Autenticacion"

        val boton = findViewById<Button>(R.id.signup)
        val acceder = findViewById<Button>(R.id.acceder)
        val correo = findViewById<EditText>(R.id.correo)
        val contrasena = findViewById<EditText>(R.id.contrasena)
        val restablecer = findViewById<TextView>(R.id.restablecer)

        restablecer.setOnClickListener {
            val res = Intent(this, RestablecerActivity::class.java).apply {}
            startActivity(res)
        }

        boton.setOnClickListener {
            if(correo.text.toString().trim().isEmpty()){
                correo.setError("Ingresa un correo")
            }else if(contrasena.text.toString().trim().isEmpty()){
                contrasena.setError("Ingresa una contraseña")
            }else{
                if (correo.text.isNotEmpty() && contrasena.text.isNotEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo.text.toString(),
                        contrasena.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            sendMailVerification()
                            alertas("Correo de verificación","Por favor, revisa tu correo para verificar tu cuenta.")
                        }else{
                            alertas("Verificación","Por favor verifica tu correo, o ingresa uno diferente.")
                        }
                    }
                }
            }
        }
        acceder.setOnClickListener {
            if(correo.text.toString().trim().isEmpty()){
                correo.setError("Ingresa tu correo")
            }else if(contrasena.text.toString().trim().isEmpty()){
                contrasena.setError("Ingresa tu contraseña")
            }else{
                if (correo.text.isNotEmpty() && contrasena.text.isNotEmpty()){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(correo.text.toString(),
                        contrasena.text.toString()).addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            val user = FirebaseAuth.getInstance().currentUser
                            val verifica = user?.isEmailVerified
                            if(verifica==true){
                                val acceder = Intent(this, HomeActivity::class.java).apply {
                                }
                                startActivity(acceder)
                            }else{
                                alertas("Accesso denegado","Por favor verifica tus datos.")
                            }
                        }else{
                            alertas("Acceso denegado", "Por favor verifica tus datos.")
                        }
                    }
                }
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
    private fun sendMailVerification(){
        val user = FirebaseAuth.getInstance().currentUser!!
        user.sendEmailVerification().addOnCompleteListener(this){ task ->
            if(task.isSuccessful){

            }else{

            }
        }
    }
}