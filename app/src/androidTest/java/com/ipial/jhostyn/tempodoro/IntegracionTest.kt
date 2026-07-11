package com.ipial.jhostyn.tempodoro


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ipial.jhostyn.tempodoro.database.AppDatabase
import com.ipial.jhostyn.tempodoro.model.Tarea
import com.ipial.jhostyn.tempodoro.model.Usuario
import com.ipial.jhostyn.tempodoro.database.TareaDao
import com.ipial.jhostyn.tempodoro.database.UsuarioDao
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntegracionTest {

    private lateinit var db: AppDatabase
    private lateinit var tareaDao: TareaDao
    private lateinit var usuarioDao: UsuarioDao

    @Before
    fun crearDb() {
        // Base de datos en memoria (no toca la real del dispositivo)
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        tareaDao = db.tareaDao()
        usuarioDao = db.usuarioDao()
    }

    @After
    fun cerrarDb() {
        db.close()
    }

    // Prueba de integración 1: CRUD en Room
    @Test
    fun insertarYLeerTarea_retornaDatosCorrectos() = runBlocking {
        val tarea = Tarea(
            titulo = "Estudiar Kotlin",
            descripcion = "Repasar corutinas",
            categoria = "Estudio",
            pomodorosEstimados = 2,
            pomodorosCompletados = 0,
            estado = "Pendiente",
            fechaCreacion = "11/07/2026"
        )

        tareaDao.insertar(tarea)
        val tareas = tareaDao.obtenerTodasSync()

        Assert.assertEquals(1, tareas.size)
        Assert.assertEquals("Estudiar Kotlin", tareas[0].titulo)
    }

    // Prueba de integración 2: Validación completa de login
    @Test
    fun loginUsuario_credencialesCorrectas_retornaUsuario() = runBlocking {
        val usuario = Usuario(
            nombre = "Jhostyn",
            correo = "usuario@correo.com",
            password = "123456"
        )

        usuarioDao.insertar(usuario)

        val encontrado = usuarioDao.login("usuario@correo.com", "123456")
        Assert.assertNotNull(encontrado)
        Assert.assertEquals("Jhostyn", encontrado?.nombre)
    }

    @Test
    fun loginUsuario_credencialesIncorrectas_retornaNull() = runBlocking {
        val usuario = Usuario(
            nombre = "Jhostyn",
            correo = "usuario@correo.com",
            password = "123456"
        )

        usuarioDao.insertar(usuario)

        val encontrado = usuarioDao.login("usuario@correo.com", "wrongpass")
        Assert.assertNull(encontrado)
    }
}
