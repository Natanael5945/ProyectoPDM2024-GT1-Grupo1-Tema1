package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReservasActivas.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReservasActivas : Fragment(), OnButtonClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reservas_activas, container, false)

        // Encontrar el RecyclerView utilizando la vista inflada
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_reservas_activas)

        // Configurar el LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Configurar el Adaptador
        val adapter = ReservasActivasAdapter(this)
        recyclerView.adapter = adapter

        return view
    }

    override fun onButtonClick(position: Int) {
        TODO("Not yet implemented")
    }


    override fun onCardClick(position: Int) {
        // Lógica para abrir el nuevo fragmento
        val fragment = InfoHabitacionReservaActiva()
        fragmentManager?.beginTransaction()?.replace(R.id.frame_contenedor, fragment)?.addToBackStack(null)?.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReservasActivas.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReservasActivas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}