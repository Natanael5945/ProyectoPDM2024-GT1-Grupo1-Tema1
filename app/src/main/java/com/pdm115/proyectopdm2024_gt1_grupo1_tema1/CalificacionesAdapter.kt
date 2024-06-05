package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalificacionesAdapter: RecyclerView.Adapter<CalificacionesAdapter.ViewHolder>() {

    private val nomUsuario = arrayOf("Antonio", "Natanael", "Cristian", "Eva")

    private val tiempoCalif = arrayOf("5 m", "25 m", "1 h", "2h")

    private val contenidoCalif = arrayOf("¡Excelente experiencia! El personal fue muy amable y servicial. La habitación estaba impecable y tenía una vista increíble. Definitivamente recomendaría este hotel a mis amigos y familiares.",
        "Muy buen hotel en general. Las instalaciones son modernas y limpias. El desayuno buffet tiene una buena variedad de opciones. Lo único que podría mejorar es la velocidad del Wi-Fi en las habitaciones.",
        "El hotel está bien ubicado y el precio es razonable. Sin embargo, encontré que el servicio de limpieza podría ser más consistente y el aire acondicionado hacía un poco de ruido por la noche.",
        "No tuve la mejor experiencia en este hotel. El check-in fue lento y la habitación no estaba lista a tiempo. Además, había un problema con la ducha y el personal no fue muy eficiente en resolverlo. No creo que me quede aquí de nuevo.")

    private val calificaciones = arrayOf("Calificación: 5.0 pts", "Calificación: 4.0 pts", "Calificación: 3.0 pts", "Calificación: 2.0 pts")
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_layout_calificaciones_usuarios, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return nomUsuario.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.nombreUsuario.text = nomUsuario[i]
        viewHolder.tiempoCalificacion.text = tiempoCalif[i]
        viewHolder.contenidoCalificacion.text = contenidoCalif[i]
        viewHolder.califiacion.text = calificaciones[i]

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nombreUsuario: TextView
        var tiempoCalificacion: TextView
        var contenidoCalificacion: TextView
        var califiacion: TextView

        init {
            nombreUsuario = itemView.findViewById(R.id.txt_nombre_usuario_calificaciones)
            tiempoCalificacion = itemView.findViewById(R.id.txt_tiempo_realizada_calificaciones)
            contenidoCalificacion = itemView.findViewById(R.id.txt_contenido_calificaciones)
            califiacion = itemView.findViewById(R.id.txt_calificacion_usuario_calificaciones)
        }
    }

}