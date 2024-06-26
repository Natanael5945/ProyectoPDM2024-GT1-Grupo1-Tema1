package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView

class InformarcionHotelAdapter(private val listener: OnButtonClickListener): RecyclerView.Adapter<InformarcionHotelAdapter.ViewHolder>() {


    private val tipoHabitacionTitulo = arrayOf("Habitación Platinum", "Habitación Silver", "Habitación Gold", "Habitación Ruby", "Habitación Sapphire")

    private val precioHabit = arrayOf("$500.00", "$450.00", "$400.00", "$350.00", "$300.00")

    private val calificacionHabit = arrayOf("Calificacióm: 5.0", "Califcación: 4.5", "Calificación: 4.0", "Califación: 3.5", "Califacación: 3.0")

    private val imgHabitacion = intArrayOf(R.drawable.habitacion_uno, R.drawable.habitacion_dos, R.drawable.habitacion_tres, R.drawable.habitacion_cuatro, R.drawable.habitacion_cinco)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_layout_habitaciones_sin_reservar, viewGroup, false)

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
        private val cardView: CardView = itemView.findViewById(R.id.cardview_habitaciones_sin_reservar)

        init {
            tipoHabitacion = itemView.findViewById(R.id.txt_titulo_tipo_habitacion_sin_reservar)
            precioHabitacion = itemView.findViewById(R.id.txt_precio_habitacion_sin_reservar)
            calNumHabitacion = itemView.findViewById(R.id.txt_calificacion_numerica_habitacion_sin_reservar)
            imagenHabitacion = itemView.findViewById(R.id.imgv_imagen_habitacion_sin_reservar)

            cardView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onCardClick(position)
                }

            }

        }


    }

}