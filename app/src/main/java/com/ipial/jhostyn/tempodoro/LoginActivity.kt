package com.ipial.jhostyn.tempodoro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.TextView
import android.util.Patterns

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val emailEditText = findViewById<TextInputEditText>(R.id.etEmail)
        val passwordEditText = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        val tvRegisterLink = findViewById<TextView>(R.id.tvRegisterLink)

        btnLogin.setOnClickListener {
            if (validateInputs(emailLayout, passwordLayout, emailEditText, passwordEditText)) {
                val prefs = getSharedPreferences("TempodoroPrefs", MODE_PRIVATE)
                val savedEmail = prefs.getString("user_email", null)
                val savedPassword = prefs.getString("user_password", null)

                val email = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (savedEmail == null || savedPassword == null) {
                    emailLayout.error = "No existe ninguna cuenta registrada"
                } else if (email != savedEmail || password != savedPassword) {
                    passwordLayout.error = "Correo o contraseña incorrectos"
                } else {
                    // ✅ Inicio de sesión exitoso
                    emailLayout.error = null
                    passwordLayout.error = null
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        tvRegisterLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateInputs(
        emailLayout: TextInputLayout,
        passwordLayout: TextInputLayout,
        emailEditText: TextInputEditText,
        passwordEditText: TextInputEditText
    ): Boolean {
        var isValid = true

        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty()) {
            emailLayout.error = "El correo no puede estar vacío"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.error = "Formato de correo inválido"
            isValid = false
        } else {
            emailLayout.error = null
        }

        if (password.isEmpty()) {
            passwordLayout.error = "La contraseña no puede estar vacía"
            isValid = false
        } else if (password.length < 6) {
            passwordLayout.error = "La contraseña debe tener al menos 6 caracteres"
            isValid = false
        } else {
            passwordLayout.error = null
        }

        return isValid
    }
}