package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ServiciosNoContratadosAdapter: RecyclerView.Adapter<ServiciosNoContratadosAdapter.ViewHolder>() {

    private val nombreServicio = arrayOf("Servicio de Concierge", "Servicio de Habitaciones", "Servicios de Spa y Bienestar")

    private val descripcionServicio = arrayOf("El servicio de concierge proporciona a los huéspedes asistencia e información sobre atracciones locales, restaurantes, transporte y otros servicios. Pueden ayudar con la reserva de restaurantes, entradas y tours.",
        "El servicio de habitaciones permite a los huéspedes ordenar comida y bebidas para ser entregadas directamente a su habitación. Este servicio a menudo está disponible las 24 horas y puede incluir una amplia variedad de opciones de menú.",
        "Muchos hoteles ofrecen instalaciones de spa y bienestar en el lugar, proporcionando servicios como masajes, tratamientos faciales, tratamientos corporales y clases de fitness. Estos servicios ayudan a los huéspedes a relajarse y rejuvenecer durante su estancia.")

    private val imgServicio = intArrayOf(R.drawable.hotel_uno, R.drawable.hotel_dos, R.drawable.hotel_tres)


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_layout_servicios_no_contratados, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return nombreServicio.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.nombreServicioNoContratado.text = nombreServicio[i]
        viewHolder.descripcionServicioNoContratado.text = descripcionServicio[i]
        viewHolder.imagenServicioNoContratado.setImageResource(imgServicio[i])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        //creamos variables de los tipos de los componentes de los cuales vamos a pasarles un array de datos
        val nombreServicioNoContratado: TextView
        val descripcionServicioNoContratado: TextView
        val imagenServicioNoContratado: ImageView
        init {
            nombreServicioNoContratado = itemView.findViewById(R.id.txt_nombre_servicio)
            descripcionServicioNoContratado = itemView.findViewById(R.id.txt_descripcion_servicio)
            imagenServicioNoContratado = itemView.findViewById(R.id.imgv_servicio_habitacion)


        }

    }

}