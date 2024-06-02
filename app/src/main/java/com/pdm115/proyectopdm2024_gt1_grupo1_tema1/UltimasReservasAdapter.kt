package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UltimasReservasAdapter: RecyclerView.Adapter<UltimasReservasAdapter.ViewHolder>() {

    val reservacion = arrayOf("prueba1", "prueba2", "prueba3")

    val fecha = arrayOf("15/02/2024", "24/05/2024", "05/03/2024")
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_layout_ultimas_reservas, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return reservacion.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.reservaHabitacion.text = reservacion[i]
        viewHolder.reservaFecha.text = fecha[i]

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var reservaHabitacion: TextView
        var reservaFecha: TextView

        init {
            reservaHabitacion = itemView.findViewById(R.id.txt_reserva_habitacion)
            reservaFecha = itemView.findViewById(R.id.txt_reserva_fecha)
        }
    }

}