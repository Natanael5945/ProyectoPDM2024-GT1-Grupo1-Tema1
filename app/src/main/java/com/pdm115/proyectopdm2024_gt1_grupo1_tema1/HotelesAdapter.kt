package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelesAdapter(private val listener: OnButtonClickListener): RecyclerView.Adapter<HotelesAdapter.ViewHolder>(){

    private val hoteles = arrayOf("Hotel Costa del Sol", "Hotel la playita", "Hotel la palma")

    private val numHabitaciones = arrayOf("15 habitaciones", "$15 habitaciones", "14 habitaciones")

    private val calificHotel = arrayOf("5.0", "4.5", "4.0")

    private val ubicacionHotel = arrayOf("Ubicado en la hermosa playa de Costa del Sol, en el departamento de La Paz, El Salvador, el Hotel Costa del Sol ofrece una experiencia inolvidable frente al mar. Este hotel se encuentra en la Carretera Litoral, kilómetro 75, rodeado de palmeras y con acceso directo a la playa. Los visitantes pueden disfrutar de la tranquilidad del océano Pacífico y de las impresionantes puestas de sol.",
        "Situado en la encantadora localidad de El Tunco, en el departamento de La Libertad, el Hotel La Playita es un destino popular entre los surfistas y turistas que buscan relajarse junto al mar. Este hotel se encuentra en la Avenida El Tunco, número 12, a pocos pasos de la playa y de una vibrante escena de restaurantes y bares. Con su ambiente acogedor y vistas panorámicas al océano, es el lugar perfecto para unas vacaciones relajantes.",
        "Enclavado en las montañas del departamento de Chalatenango, el Hotel La Palma ofrece una escapada tranquila en medio de la naturaleza. Este encantador hotel está ubicado en la Carretera a La Palma, kilómetro 87, rodeado de exuberante vegetación y aire fresco de montaña. La Palma es conocida por su arte popular y sus pintorescas vistas, lo que la convierte en un destino ideal para quienes buscan una experiencia cultural y relajante.")

    private val imagenHotel = intArrayOf(R.drawable.hotel_uno, R.drawable.hotel_dos, R.drawable.hotel_tres)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_layout_hoteles, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return hoteles.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.nombreHotel.text = hoteles[i]
        viewHolder.numeroHabitaciones.text = numHabitaciones[i]
        viewHolder.imgHotel.setImageResource(imagenHotel[i])
        viewHolder.direccionHotel.text = ubicacionHotel[i]
        viewHolder.calificacionHotel.text = calificHotel[i]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //creamos variables de los tipos de los componentes de los cuales vamos a pasarles un array de datos
        val nombreHotel: TextView
        val numeroHabitaciones: TextView
        val imgHotel: ImageView
        val direccionHotel: TextView
        val calificacionHotel: TextView
        val botonVerHabitaciones: Button

        init {
            nombreHotel = itemView.findViewById(R.id.txt_nombre_hotel)
            numeroHabitaciones = itemView.findViewById(R.id.txt_numero_habitaciones_hotel)
            imgHotel = itemView.findViewById(R.id.imgv_hotel)
            direccionHotel = itemView.findViewById(R.id.txt_direccion_hotel)
            calificacionHotel = itemView.findViewById(R.id.txt_numero_estrellas_hotel)
            botonVerHabitaciones = itemView.findViewById(R.id.btn_ver_habitaciones_hotel)

            botonVerHabitaciones.setOnClickListener {
                listener.onButtonClick(adapterPosition)
            }
        }
    }
}