package com.ipial.jhostyn.tempodoro

import org.junit.Assert.*
import org.junit.Test

class ValidacionesTest {

    // Funciones auxiliares que replican la lógica de tus Activities
    private fun validarCorreo(correo: String): Boolean {
        return correo.contains("@") && correo.contains(".")
    }

    private fun validarPassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun validarTarea(titulo: String, descripcion: String, categoria: String, pomodoros: Int): Boolean {
        return titulo.isNotEmpty() &&
                descripcion.isNotEmpty() &&
                categoria.isNotEmpty() &&
                pomodoros > 0
    }

    // Prueba 1: Validación de correo
    @Test
    fun validarCorreo_formatoCorrecto_retornaTrue() {
        val correoValido = "usuario@universidad.edu.ec"
        val resultado = validarCorreo(correoValido)
        assertTrue("Un correo con formato válido debe pasar", resultado)
    }

    @Test
    fun validarCorreo_formatoInvalido_retornaFalse() {
        val correoInvalido = "usuariosindominio"
        val resultado = validarCorreo(correoInvalido)
        assertFalse("Un correo sin @ no debe ser válido", resultado)
    }

    // Prueba 2: Validación de contraseña
    @Test
    fun validarPassword_menosDe6Caracteres_retornaFalse() {
        val passwordCorta = "12345"
        val resultado = validarPassword(passwordCorta)
        assertFalse("Contraseña menor a 6 caracteres debe ser rechazada", resultado)
    }

    @Test
    fun validarPassword_valida_retornaTrue() {
        val passwordValida = "123456"
        val resultado = validarPassword(passwordValida)
        assertTrue("Contraseña con 6 o más caracteres debe ser aceptada", resultado)
    }

    // Prueba 3: Lógica de negocio CRUD (Tarea)
    @Test
    fun validarTarea_tituloVacio_retornaFalse() {
        val resultado = validarTarea("", "desc", "cat", 1)
        assertFalse("Una tarea sin título no debe ser válida", resultado)
    }

    @Test
    fun validarTarea_datosCorrectos_retornaTrue() {
        val resultado = validarTarea("Estudiar Kotlin", "Repasar corutinas", "Estudio", 2)
        assertTrue("Una tarea con datos correctos debe ser válida", resultado)
    }
}
