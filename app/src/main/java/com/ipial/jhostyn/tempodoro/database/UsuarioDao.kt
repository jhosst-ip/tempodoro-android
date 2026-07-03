package com.ipial.jhostyn.tempodoro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ipial.jhostyn.tempodoro.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertar(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE correo=:correo AND password=:password LIMIT 1")
    suspend fun login(correo:String,password:String): Usuario?

}