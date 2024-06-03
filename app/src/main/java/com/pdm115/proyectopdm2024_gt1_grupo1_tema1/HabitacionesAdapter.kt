package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitacionesAdapter: RecyclerView.Adapter<HabitacionesAdapter.ViewHolder>() {

    private val tipoHabitacionPopular = arrayOf("Habitación Doble", "Habitación Indivual", "Habitación Triple", "Habitación Ruby", "Habitación Sapphire")

    private val precioHabitPopular = arrayOf("$500.00", "$450.00", "$400.00", "$350.00", "$300.00")

    private val calificacionHabitPopular = arrayOf("5.0", "4.5", "4.0", "3.5", "3.0")

    private val imgHabitacionPopular = intArrayOf(R.drawable.habitacion_uno, R.drawable.habitacion_dos, R.drawable.habitacion_tres, R.drawable.habitacion_cuatro, R.drawable.habitacion_cinco)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_layout_habitaciones, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return tipoHabitacionPopular.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tipoHabitacionPopular.text = tipoHabitacionPopular[i]
        viewHolder.precioHabitacionPopular.text = precioHabitPopular[i]
        viewHolder.habitacionCalificacion.text = calificacionHabitPopular[i]
        viewHolder.imagenHabitacionPopular.setImageResource(imgHabitacionPopular[i])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        //creamos variables de los tipos de los componentes de los cuales vamos a pasarles un array de datos
        val tipoHabitacionPopular: TextView
        val precioHabitacionPopular: TextView
        val habitacionCalificacion: TextView
        val imagenHabitacionPopular: ImageView

        init {
            tipoHabitacionPopular = itemView.findViewById(R.id.txt_tipo_poupular_habitacion)
            precioHabitacionPopular = itemView.findViewById(R.id.txt_precio_habitacion_popular)
            habitacionCalificacion = itemView.findViewById(R.id.txt_numero_estrellas_habitaciones)
            imagenHabitacionPopular = itemView.findViewById(R.id.imgv_habitacion_popular)
        }
    }
}