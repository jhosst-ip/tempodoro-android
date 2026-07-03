package com.ipial.jhostyn.tempodoro.repository

import androidx.lifecycle.LiveData
import com.ipial.jhostyn.tempodoro.database.TareaDao
import com.ipial.jhostyn.tempodoro.model.Tarea

class TareaRepository(
    private val dao: TareaDao
) {

    val listaTareas: LiveData<List<Tarea>> = dao.obtenerTodas()

    suspend fun insertar(tarea: Tarea) {
        dao.insertar(tarea)
    }

    suspend fun actualizar(tarea: Tarea) {
        dao.actualizar(tarea)
    }

    suspend fun eliminar(tarea: Tarea) {
        dao.eliminar(tarea)
    }
    suspend fun obtenerPorId(id:Int):Tarea?{

        return dao.obtenerPorId(id)

    }
}