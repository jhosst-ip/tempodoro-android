package com.ipial.jhostyn.tempodoro.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ipial.jhostyn.tempodoro.databinding.ActivityEditarTareaBinding
import com.ipial.jhostyn.tempodoro.model.Tarea
import com.ipial.jhostyn.tempodoro.utils.NotificationHelper
import com.ipial.jhostyn.tempodoro.viewmodel.TareaViewModel

class EditarTareaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarTareaBinding

    private val viewModel: TareaViewModel by viewModels()

    private var id = 0
    private var completados = 0
    private lateinit var fecha: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditarTareaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra("id", 0)

        binding.edtTitulo.setText(intent.getStringExtra("titulo"))
        binding.edtDescripcion.setText(intent.getStringExtra("descripcion"))
        binding.edtCategoria.setText(intent.getStringExtra("categoria"))
        binding.edtPomodoros.setText(
            intent.getIntExtra("estimados", 0).toString()
        )

        completados = intent.getIntExtra("completados", 0)
        fecha = intent.getStringExtra("fecha") ?: ""

        binding.btnActualizar.setOnClickListener {

            val titulo = binding.edtTitulo.text.toString().trim()
            val descripcion = binding.edtDescripcion.text.toString().trim()
            val categoria = binding.edtCategoria.text.toString().trim()
            val pomodorosTexto =
                binding.edtPomodoros.text.toString().trim()

            if (titulo.isEmpty()) {
                binding.edtTitulo.error = "Ingrese un título"
                binding.edtTitulo.requestFocus()
                return@setOnClickListener
            }

            if (descripcion.isEmpty()) {
                binding.edtDescripcion.error =
                    "Ingrese una descripción"
                binding.edtDescripcion.requestFocus()
                return@setOnClickListener
            }

            if (categoria.isEmpty()) {
                binding.edtCategoria.error =
                    "Ingrese una categoría"
                binding.edtCategoria.requestFocus()
                return@setOnClickListener
            }

            if (pomodorosTexto.isEmpty()) {
                binding.edtPomodoros.error =
                    "Ingrese la cantidad de pomodoros"
                binding.edtPomodoros.requestFocus()
                return@setOnClickListener
            }

            val pomodoros = pomodorosTexto.toInt()

            if (pomodoros <= 0) {
                binding.edtPomodoros.error =
                    "Debe ser al menos 1 pomodoro"
                binding.edtPomodoros.requestFocus()
                return@setOnClickListener
            }

            val tarea = Tarea(
                id = id,
                titulo = titulo,
                descripcion = descripcion,
                categoria = categoria,
                pomodorosEstimados = pomodoros,
                pomodorosCompletados = completados,
                estado = "Pendiente",
                fechaCreacion = fecha
            )

            viewModel.actualizar(tarea)

            NotificationHelper(this)
                .mostrarNotificacion(
                    "Tarea actualizada",
                    "Los cambios se guardaron correctamente."
                )

            Toast.makeText(
                this,
                "Tarea actualizada",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }

        binding.btnEliminar.setOnClickListener {

            val tarea = Tarea(
                id = id,
                titulo = binding.edtTitulo.text.toString(),
                descripcion = binding.edtDescripcion.text.toString(),
                categoria = binding.edtCategoria.text.toString(),
                pomodorosEstimados = binding.edtPomodoros.text.toString().toIntOrNull() ?: 1,
                pomodorosCompletados = completados,
                estado = "Pendiente",
                fechaCreacion = fecha
            )

            viewModel.eliminar(tarea)

            NotificationHelper(this)
                .mostrarNotificacion(
                    "Tarea eliminada",
                    "La tarea fue eliminada correctamente."
                )

            Toast.makeText(
                this,
                "Tarea eliminada",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }
}