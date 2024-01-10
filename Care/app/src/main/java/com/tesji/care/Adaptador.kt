package com.tesji.care

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador : RecyclerView.Adapter<Adaptador.ViewHolder> () {
    private var datos: List<Plantas> = ArrayList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtnombre: TextView = itemView.findViewById(R.id.txt_nombre)
        val txtnombrecientifico: TextView = itemView.findViewById(R.id.txt_nombre_cientifico)
        val txtagua: TextView = itemView.findViewById(R.id.txt_agua)
        val txtsol: TextView = itemView.findViewById(R.id.txt_sol)
        val txtcuidados: TextView = itemView.findViewById(R.id.txt_cuidados)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    fun setDatos(datos: List<Plantas>){
        this.datos=datos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datos[position]
        holder.txtnombre.text = item.nombre.toString()
        holder.txtnombrecientifico.text = item.nombre_cientifico.toString()
        holder.txtagua.text = item.agua.toString()
        holder.txtsol.text = item.sol.toString()
        holder.txtcuidados.text = item.cuidados.toString()
    }
}