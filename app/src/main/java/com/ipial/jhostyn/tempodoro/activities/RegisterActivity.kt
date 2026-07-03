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

    private val viewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener {

            val nombre = binding.edtNombre.text.toString().trim()
            val correo = binding.edtCorreo.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuario = Usuario(
                nombre = nombre,
                correo = correo,
                password = password
            )

            viewModel.registrar(usuario)

            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }

    }

}