package com.tesji.rafael_work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.tesji.rafael_work.model.ModelPago
import com.tesji.rafael_work.model.PagoModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtnombre = findViewById<EditText>(R.id.nombre)
        val txtfecha = findViewById<EditText>(R.id.fecha)
        val txtene = findViewById<EditText>(R.id.ene)
        val txtfeb = findViewById<EditText>(R.id.feb)
        val txtmar = findViewById<EditText>(R.id.mar)
        val txtabr = findViewById<EditText>(R.id.abr)
        val txtmay = findViewById<EditText>(R.id.may)
        val txtjun = findViewById<EditText>(R.id.jun)
        val txtjul = findViewById<EditText>(R.id.jul)
        val txtago = findViewById<EditText>(R.id.ago)
        val txtsep = findViewById<EditText>(R.id.sep)
        val txtoct = findViewById<EditText>(R.id.oct)
        val txtnov = findViewById<EditText>(R.id.nov)
        val txtdic = findViewById<EditText>(R.id.dic)
        val txtsueldo = findViewById<EditText>(R.id.sueldo)
        val boton = findViewById<Button>(R.id.boton)
        var res:Double = 0.0

        boton.setOnClickListener{
            if(txtfecha.text.toString().toDouble() > 0.0){
                var fec:Double = txtfecha.text.toString().toDouble() % 4
                if(fec == 0.0){
                    res = 29.0
                }else{
                    res = 28.0
                }
            }
            if(txtnombre.text.toString().trim().isEmpty()){
                txtnombre.setError("Ingresa un nombre")
            }else if(txtfecha.text.toString().trim().isEmpty() || txtfecha.text.toString().toDouble() == 0.0){
                txtfecha.setError("Comprueba el valor ingresado")
            }else if(txtene.text.toString().trim().isEmpty() || txtene.text.toString().toDouble() > 31.0 || txtene.text.toString().toDouble() == 0.0){
                txtene.setError("Comprueba el valor ingresado")
            }else if(txtfeb.text.toString().trim().isEmpty() || txtfeb.text.toString().toDouble() > res || txtfeb.text.toString().toDouble() == 0.0){
                txtfeb.setError("Comprueba el valor ingresado")
            }else if(txtmar.text.toString().trim().isEmpty() || txtmar.text.toString().toDouble() > 31.0 || txtmar.text.toString().toDouble() == 0.0){
                txtmar.setError("Comprueba el valor ingresado")
            }else if(txtabr.text.toString().trim().isEmpty() || txtabr.text.toString().toDouble() > 30.0 || txtabr.text.toString().toDouble() == 0.0){
                txtabr.setError("Comprueba el valor ingresado")
            }else if(txtmay.text.toString().trim().isEmpty() || txtmay.text.toString().toDouble() > 31.0 || txtmay.text.toString().toDouble() == 0.0){
                txtmay.setError("Comprueba el valor ingresado")
            }else if(txtjun.text.toString().trim().isEmpty() || txtjun.text.toString().toDouble() > 30.0 || txtjun.text.toString().toDouble() == 0.0){
                txtjun.setError("Comprueba el valor ingresado")
            }else if(txtjul.text.toString().trim().isEmpty() || txtjul.text.toString().toDouble() > 31.0 || txtjul.text.toString().toDouble() == 0.0){
                txtjul.setError("Comprueba el valor ingresado")
            }else if(txtago.text.toString().trim().isEmpty() || txtago.text.toString().toDouble() > 31.0 || txtago.text.toString().toDouble() == 0.0){
                txtago.setError("Comprueba el valor ingresado")
            }else if(txtsep.text.toString().trim().isEmpty() || txtsep.text.toString().toDouble() > 30.0 || txtsep.text.toString().toDouble() == 0.0){
                txtsep.setError("Comprueba el valor ingresado")
            }else if(txtoct.text.toString().trim().isEmpty() || txtoct.text.toString().toDouble() > 31.0 || txtoct.text.toString().toDouble() == 0.0){
                txtoct.setError("Comprueba el valor ingresado")
            }else if(txtnov.text.toString().trim().isEmpty() || txtnov.text.toString().toDouble() > 30.0 || txtnov.text.toString().toDouble() == 0.0){
                txtnov.setError("Comprueba el valor ingresado")
            }else if(txtdic.text.toString().trim().isEmpty() || txtdic.text.toString().toDouble() > 31.0 || txtdic.text.toString().toDouble() == 0.0){
                txtdic.setError("Comprueba el valor ingresado")
            }else if(txtsueldo.text.toString().trim().isEmpty()){
                txtsueldo.setError("Ingresa un sueldo")
            }else {
                //CONECTAR CON EL MODELO
                val terreno = ModelPago()
                terreno.nombre = txtnombre.text.toString()
                terreno.sueldo = txtsueldo.text.toString().toDouble()
                terreno.fecha = txtfecha.text.toString().toDouble()
                terreno.ene = txtene.text.toString().toDouble()
                terreno.feb = txtfeb.text.toString().toDouble()
                terreno.mar = txtmar.text.toString().toDouble()
                terreno.abr = txtabr.text.toString().toDouble()
                terreno.may = txtmay.text.toString().toDouble()
                terreno.jun = txtjun.text.toString().toDouble()
                terreno.jul = txtjul.text.toString().toDouble()
                terreno.ago = txtago.text.toString().toDouble()
                terreno.sep = txtsep.text.toString().toDouble()
                terreno.oct = txtoct.text.toString().toDouble()
                terreno.nov = txtnov.text.toString().toDouble()
                terreno.dic = txtdic.text.toString().toDouble()

                Toast.makeText(
                    applicationContext,
                    terreno.calcularVenta(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}