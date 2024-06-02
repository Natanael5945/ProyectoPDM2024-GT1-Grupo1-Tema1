package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservasActivasAdapter: RecyclerView.Adapter<ReservasActivasAdapter.ViewHolder>() {

    val tipoHabitacionTitulo = arrayOf("Habitación Platinum", "Habitación Silver", "Habitación Gold", "Habitación Ruby", "Habitación Sapphire")

    val precioHabit = arrayOf("$500.00", "$450.00", "$400.00", "$350.00", "$300.00")

    val calificacionHabit = arrayOf("Calificacióm: 5.0", "Califcación: 4.5", "Calificación: 4.0", "Califación: 3.5", "Califacación: 3.0")

    val imgHabitacion = intArrayOf(R.drawable.habitacion_uno, R.drawable.habitacion_dos, R.drawable.habitacion_tres, R.drawable.habitacion_cuatro, R.drawable.habitacion_cinco)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_layout_reservas_activas, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return tipoHabitacionTitulo.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tipoHabitacion.text = tipoHabitacionTitulo[i]
        viewHolder.precioHabitacion.text = precioHabit[i]
        viewHolder.calNumHabitacion.text = calificacionHabit[i]
        viewHolder.imagenHabitacion.setImageResource(imgHabitacion[i])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        //creamos variables de los tipos de los componentes de los cuales vamos a pasarles un array de datos
        val tipoHabitacion: TextView
        val precioHabitacion: TextView
        val calNumHabitacion: TextView
        val imagenHabitacion: ImageView

        init {
            tipoHabitacion = itemView.findViewById(R.id.txt_titulo_reserva_habitacion)
            precioHabitacion = itemView.findViewById(R.id.txt_precio_habitacion)
            calNumHabitacion = itemView.findViewById(R.id.txt_calificacion_numerica_habitacion)
            imagenHabitacion = itemView.findViewById(R.id.imgv_imagen_habitacion)
        }

    }
}