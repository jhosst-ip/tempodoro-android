package com.ipial.jhostyn.tempodoro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ipial.jhostyn.tempodoro.database.AppDatabase
import com.ipial.jhostyn.tempodoro.model.Tarea
import com.ipial.jhostyn.tempodoro.repository.TareaRepository
import kotlinx.coroutines.launch

class TareaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TareaRepository

    val listaTareas: LiveData<List<Tarea>>
    val totalTareas: LiveData<Int>

    val tareasCompletadas: LiveData<Int>

    val pomodorosCompletados: LiveData<Int>

    val pomodorosEstimados: LiveData<Int>

    init {

        val dao = AppDatabase.getDatabase(application).tareaDao()

        repository = TareaRepository(dao)

        listaTareas = repository.listaTareas

        totalTareas = repository.totalTareas

        tareasCompletadas = repository.tareasCompletadas

        pomodorosCompletados = repository.pomodorosCompletados

        pomodorosEstimados = repository.pomodorosEstimados
    }

    fun insertar(tarea: Tarea) {

        viewModelScope.launch {

            repository.insertar(tarea)

        }

    }

    fun actualizar(tarea: Tarea) {

        viewModelScope.launch {

            repository.actualizar(tarea)

        }

    }

    fun eliminar(tarea: Tarea) {

        viewModelScope.launch {

            repository.eliminar(tarea)

        }

    }
    suspend fun obtenerPorId(id:Int):Tarea?{

        return repository.obtenerPorId(id)

    }

}