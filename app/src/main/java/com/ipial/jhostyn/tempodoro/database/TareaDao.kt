package com.ipial.jhostyn.tempodoro.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ipial.jhostyn.tempodoro.model.Tarea

@Dao
interface TareaDao {

    @Insert
    suspend fun insertar(tarea: Tarea)

    @Update
    suspend fun actualizar(tarea: Tarea)

    @Delete
    suspend fun eliminar(tarea: Tarea)

    @Query("SELECT * FROM tareas ORDER BY id DESC")
    fun obtenerTodas(): LiveData<List<Tarea>>

    @Query("SELECT * FROM tareas")
    suspend fun obtenerTodasSync(): List<Tarea>

    @Query("SELECT * FROM tareas WHERE id = :id")
    suspend fun obtenerPorId(id: Int): Tarea?

    // ---------- ESTADÍSTICAS ----------

    @Query("SELECT COUNT(*) FROM tareas")
    fun totalTareas(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM tareas WHERE estado = 'Completada'")
    fun tareasCompletadas(): LiveData<Int>

    @Query("SELECT IFNULL(SUM(pomodorosCompletados),0) FROM tareas")
    fun pomodorosCompletados(): LiveData<Int>

    @Query("SELECT IFNULL(SUM(pomodorosEstimados),0) FROM tareas")
    fun pomodorosEstimados(): LiveData<Int>
}