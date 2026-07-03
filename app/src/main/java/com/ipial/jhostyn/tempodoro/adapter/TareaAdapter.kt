package com.ipial.jhostyn.tempodoro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipial.jhostyn.tempodoro.R
import com.ipial.jhostyn.tempodoro.model.Tarea
import android.widget.Button
class TareaAdapter(
    private var lista: List<Tarea>,
    private val onClick: (Tarea) -> Unit,
    private val onPomodoro: (Tarea) -> Unit
) : RecyclerView.Adapter<TareaAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val descripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val categoria: TextView = itemView.findViewById(R.id.txtCategoria)
        val pomodoros: TextView = itemView.findViewById(R.id.txtPomodoros)
        val btnPomodoro = itemView.findViewById<Button>(R.id.btnPomodoro)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarea, parent, false)

        return ViewHolder(vista)

    }

    override fun getItemCount(): Int {

        return lista.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val tarea = lista[position]

        holder.titulo.text = tarea.titulo
        holder.descripcion.text = tarea.descripcion
        holder.categoria.text = tarea.categoria

        holder.pomodoros.text =
            "${tarea.pomodorosCompletados}/${tarea.pomodorosEstimados}"

        holder.itemView.setOnClickListener {

            onClick(tarea)

        }
        holder.btnPomodoro.setOnClickListener {
            onPomodoro(tarea)
        }
    }

    fun actualizarLista(nuevaLista: List<Tarea>) {

        lista = nuevaLista

        notifyDataSetChanged()

    }

}