package com.ipial.jhostyn.tempodoro

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val confirmLayout = findViewById<TextInputLayout>(R.id.confirmLayout)
        val emailEditText = findViewById<TextInputEditText>(R.id.etEmail)
        val passwordEditText = findViewById<TextInputEditText>(R.id.etPassword)
        val confirmEditText = findViewById<TextInputEditText>(R.id.etConfirmPassword)
        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirm = confirmEditText.text.toString().trim()

            var isValid = true

            if (email.isEmpty()) {
                emailLayout.error = "El correo no puede estar vacío"
                isValid = false
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "Formato de correo inválido"
                isValid = false
            } else {
                emailLayout.error = null
            }

            if (password.isEmpty()) {
                passwordLayout.error = "La contraseña no puede estar vacía"
                isValid = false
            } else if (password.length < 6) {
                passwordLayout.error = "Debe tener al menos 6 caracteres"
                isValid = false
            } else {
                passwordLayout.error = null
            }

            if (confirm != password) {
                confirmLayout.error = "Las contraseñas no coinciden"
                isValid = false
            } else {
                confirmLayout.error = null
            }

            if (!isValid) {
                return@setOnClickListener
            }

            // ✅ Guardar en SharedPreferences
            val prefs = getSharedPreferences("TempodoroPrefs", Context.MODE_PRIVATE)
            prefs.edit().putString("user_email", email)
                .putString("user_password", password)
                .apply()

            Toast.makeText(this, "✅ Registro exitoso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}