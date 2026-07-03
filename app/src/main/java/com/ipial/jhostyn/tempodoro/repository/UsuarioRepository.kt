package com.ipial.jhostyn.tempodoro.repository

import com.ipial.jhostyn.tempodoro.database.UsuarioDao
import com.ipial.jhostyn.tempodoro.model.Usuario

class UsuarioRepository(
    private val dao: UsuarioDao
) {

    suspend fun insertar(usuario: Usuario) {
        dao.insertar(usuario)
    }

    suspend fun login(correo: String, password: String): Usuario? {
        return dao.login(correo, password)
    }
}