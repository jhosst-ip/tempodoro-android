package com.ipial.jhostyn.tempodoro.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ipial.jhostyn.tempodoro.databinding.ActivityEditarTareaBinding
import com.ipial.jhostyn.tempodoro.model.Tarea
import com.ipial.jhostyn.tempodoro.viewmodel.TareaViewModel
import com.ipial.jhostyn.tempodoro.utils.NotificationHelper
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

        id = intent.getIntExtra("id",0)

        binding.edtTitulo.setText(intent.getStringExtra("titulo"))
        binding.edtDescripcion.setText(intent.getStringExtra("descripcion"))
        binding.edtCategoria.setText(intent.getStringExtra("categoria"))
        binding.edtPomodoros.setText(intent.getIntExtra("estimados",0).toString())

        completados = intent.getIntExtra("completados",0)
        fecha = intent.getStringExtra("fecha")!!

        binding.btnActualizar.setOnClickListener {

            val tarea = Tarea(
                id,
                binding.edtTitulo.text.toString(),
                binding.edtDescripcion.text.toString(),
                binding.edtCategoria.text.toString(),
                binding.edtPomodoros.text.toString().toInt(),
                completados,
                "Pendiente",
                fecha
            )

            viewModel.actualizar(tarea)
            NotificationHelper(this)
                .mostrarNotificacion(
                    "Tarea actualizada",
                    "Los cambios se guardaron correctamente."
                )

            finish()
        }

        binding.btnEliminar.setOnClickListener {

            val tarea = Tarea(
                id,
                binding.edtTitulo.text.toString(),
                binding.edtDescripcion.text.toString(),
                binding.edtCategoria.text.toString(),
                binding.edtPomodoros.text.toString().toInt(),
                completados,
                "Pendiente",
                fecha
            )

            viewModel.eliminar(tarea)
            NotificationHelper(this)
                .mostrarNotificacion(
                    "Tarea eliminada",
                    "La tarea fue eliminada correctamente."
                )

            finish()
        }

    }

}