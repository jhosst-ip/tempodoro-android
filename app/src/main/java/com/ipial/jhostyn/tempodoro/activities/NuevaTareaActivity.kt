package com.ipial.jhostyn.tempodoro.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ipial.jhostyn.tempodoro.databinding.ActivityNuevaTareaBinding
import com.ipial.jhostyn.tempodoro.model.Tarea
import com.ipial.jhostyn.tempodoro.utils.NotificationHelper
import com.ipial.jhostyn.tempodoro.viewmodel.TareaViewModel

class NuevaTareaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNuevaTareaBinding

    private val viewModel: TareaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNuevaTareaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            guardarTarea()
        }
    }

    private fun guardarTarea() {

        val titulo = binding.edtTitulo.text.toString().trim()
        val descripcion = binding.edtDescripcion.text.toString().trim()
        val categoria = binding.edtCategoria.text.toString().trim()
        val pomodorosTexto = binding.edtPomodoros.text.toString().trim()

        if (titulo.isEmpty()) {
            binding.edtTitulo.error = "Ingrese un título"
            binding.edtTitulo.requestFocus()
            return
        }

        if (descripcion.isEmpty()) {
            binding.edtDescripcion.error = "Ingrese una descripción"
            binding.edtDescripcion.requestFocus()
            return
        }

        if (categoria.isEmpty()) {
            binding.edtCategoria.error = "Ingrese una categoría"
            binding.edtCategoria.requestFocus()
            return
        }

        if (pomodorosTexto.isEmpty()) {
            binding.edtPomodoros.error =
                "Ingrese la cantidad de pomodoros"
            binding.edtPomodoros.requestFocus()
            return
        }

        val pomodoros = pomodorosTexto.toInt()

        if (pomodoros <= 0) {
            binding.edtPomodoros.error =
                "Debe ser al menos 1 pomodoro"
            binding.edtPomodoros.requestFocus()
            return
        }

        val tarea = Tarea(
            titulo = titulo,
            descripcion = descripcion,
            categoria = categoria,
            pomodorosEstimados = pomodoros,
            pomodorosCompletados = 0,
            estado = "Pendiente",
            fechaCreacion = java.text.SimpleDateFormat(
                "dd/MM/yyyy",
                java.util.Locale.getDefault()
            ).format(java.util.Date())
        )

        viewModel.insertar(tarea)

        NotificationHelper(this)
            .mostrarNotificacion(
                "Tarea creada",
                "La tarea \"$titulo\" fue creada correctamente."
            )

        Toast.makeText(
            this,
            "Tarea guardada correctamente",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}