package com.pdm115.proyectopdm2024_gt1_grupo1_tema1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.pdm115.proyectopdm2024_gt1_grupo1_tema1.Data.Services.HotelService

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Hoteles.newInstance] factory method to
 * create an instance of this fragment.
 */
class Hoteles : Fragment(), OnButtonClickListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var hotelService: HotelService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        hotelService = HotelService(FirebaseDatabase.getInstance())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Mueve tu código que accede a la vista aquí
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hoteles, container, false)

        // Encontrar el RecyclerView utilizando la vista inflada
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_hoteles)

        // Configurar el LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Configurar el Adaptador
        val adapter = HotelesAdapter(this)
        recyclerView.adapter = adapter

        return view
    }

    override fun onButtonClick(position: Int) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_contenedor, InformacionHotel())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Hoteles.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Hoteles().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}