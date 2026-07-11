package com.ipial.jhostyn.tempodoro.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipial.jhostyn.tempodoro.adapter.TareaAdapter
import com.ipial.jhostyn.tempodoro.databinding.ActivityHomeBinding
import com.ipial.jhostyn.tempodoro.viewmodel.TareaViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: TareaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TareaAdapter(

            emptyList(),

            { tarea ->

                val intent = Intent(
                    this,
                    EditarTareaActivity::class.java
                )

                intent.putExtra("id", tarea.id)
                intent.putExtra("titulo", tarea.titulo)
                intent.putExtra("descripcion", tarea.descripcion)
                intent.putExtra("categoria", tarea.categoria)
                intent.putExtra("estimados", tarea.pomodorosEstimados)
                intent.putExtra("completados", tarea.pomodorosCompletados)
                intent.putExtra("fecha", tarea.fechaCreacion)

                startActivity(intent)

            },

            { tarea ->

                val intent = Intent(
                    this,
                    PomodoroActivity::class.java
                )

                intent.putExtra("id", tarea.id)
                intent.putExtra("titulo", tarea.titulo)
                intent.putExtra("estimados", tarea.pomodorosEstimados)
                intent.putExtra(
                    "completados",
                    tarea.pomodorosCompletados
                )

                startActivity(intent)

            }

        )

        binding.recyclerTareas.layoutManager =
            LinearLayoutManager(this)

        binding.recyclerTareas.adapter = adapter

        viewModel.listaTareas.observe(this) { lista ->

            adapter.actualizarLista(lista)

        }

        binding.btnNuevaTarea.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    NuevaTareaActivity::class.java
                )
            )

        }
        binding.btnEstadisticas.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    EstadisticasActivity::class.java
                )
            )

        }
    }
}