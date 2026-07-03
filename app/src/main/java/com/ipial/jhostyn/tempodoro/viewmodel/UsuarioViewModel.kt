package com.ipial.jhostyn.tempodoro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ipial.jhostyn.tempodoro.database.AppDatabase
import com.ipial.jhostyn.tempodoro.model.Usuario
import com.ipial.jhostyn.tempodoro.repository.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioRepository

    init {
        repository = UsuarioRepository(
            AppDatabase.getDatabase(application).usuarioDao()
        )
    }

    fun registrar(usuario: Usuario) {
        viewModelScope.launch {
            repository.insertar(usuario)
        }
    }

    suspend fun login(correo: String, password: String): Usuario? {
        return repository.login(correo, password)
    }
}