package com.ipial.jhostyn.tempodoro.repository

import androidx.lifecycle.LiveData
import com.ipial.jhostyn.tempodoro.database.TareaDao
import com.ipial.jhostyn.tempodoro.model.Tarea

class TareaRepository(
    private val dao: TareaDao
) {

    val listaTareas: LiveData<List<Tarea>> = dao.obtenerTodas()

    val totalTareas = dao.totalTareas()

    val tareasCompletadas = dao.tareasCompletadas()

    val pomodorosCompletados = dao.pomodorosCompletados()

    val pomodorosEstimados = dao.pomodorosEstimados()
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