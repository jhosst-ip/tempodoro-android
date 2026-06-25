package com.ipial.jhostyn.tempodoro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}