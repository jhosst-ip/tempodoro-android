package com.ipial.jhostyn.tempodoro.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ipial.jhostyn.tempodoro.databinding.ActivityRegisterBinding
import com.ipial.jhostyn.tempodoro.model.Usuario
import com.ipial.jhostyn.tempodoro.viewmodel.UsuarioViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val usuarioViewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener {

            val nombre = binding.edtNombre.text.toString().trim()
            val correo = binding.edtCorreo.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (nombre.isEmpty()) {
                binding.edtNombre.error = "Ingrese su nombre"
                binding.edtNombre.requestFocus()
                return@setOnClickListener
            }

            if (correo.isEmpty()) {
                binding.edtCorreo.error = "Ingrese su correo"
                binding.edtCorreo.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = "Ingrese una contraseña"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.edtPassword.error =
                    "La contraseña debe tener mínimo 6 caracteres"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            val usuario = Usuario(
                nombre = nombre,
                correo = correo,
                password = password
            )

            usuarioViewModel.registrar(usuario)

            Toast.makeText(
                this,
                "Usuario registrado correctamente",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )

            finish()
        }
    }
}