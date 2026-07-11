package com.ipial.jhostyn.tempodoro.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ipial.jhostyn.tempodoro.databinding.ActivityEstadisticasBinding
import com.ipial.jhostyn.tempodoro.viewmodel.TareaViewModel

class EstadisticasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstadisticasBinding

    private val viewModel: TareaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityEstadisticasBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnVolver.setOnClickListener {
            finish()
        }

        viewModel.totalTareas.observe(this) {

            binding.txtTotalTareas.text =
                "📋 Tareas creadas: $it"

        }

        viewModel.tareasCompletadas.observe(this) {

            binding.txtCompletadas.text =
                "✅ Tareas completadas: $it"

        }

        viewModel.pomodorosCompletados.observe(this) {

            binding.txtPomodoros.text =
                "🍅 Pomodoros realizados: $it"

        }

        viewModel.pomodorosEstimados.observe(this) {

            val completados =
                viewModel.pomodorosCompletados.value ?: 0

            val pendientes =
                it - completados

            binding.txtPendientes.text =
                "⏳ Pomodoros pendientes: $pendientes"

            val minutos =
                completados * 25

            val horas =
                minutos / 60

            val minutosRestantes =
                minutos % 60

            binding.txtTiempo.text =
                "🕒 Tiempo estudiado: ${horas}h ${minutosRestantes}m"

        }
    }
}