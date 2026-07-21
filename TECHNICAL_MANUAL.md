# Manual Técnico — Tempodoro v1.0

## 1. Descripción del sistema

### Descripción

Tempodoro es una aplicación móvil desarrollada para Android cuyo objetivo es mejorar la productividad de estudiantes y profesionales mediante la técnica Pomodoro. La aplicación permite gestionar tareas de estudio, controlar sesiones de trabajo y descanso, registrar estadísticas de productividad y visualizar el progreso del usuario.

### Problema que resuelve

Muchas personas tienen dificultades para administrar su tiempo de estudio o trabajo de forma organizada. Tempodoro ayuda a planificar las tareas, controlar el tiempo mediante sesiones Pomodoro y realizar un seguimiento del progreso alcanzado.

### Usuario objetivo

- Estudiantes universitarios.
- Profesionales.
- Personas que utilizan la técnica Pomodoro.

### Alcance del MVP

La versión 1.0 implementa las funcionalidades principales:

- Inicio de sesión.
- Registro de usuarios.
- CRUD de tareas.
- Temporizador Pomodoro.
- Estadísticas de productividad.
- Notificaciones al finalizar cada sesión.
- Almacenamiento local mediante Room.

---

# 2. Arquitectura de la aplicación

La aplicación sigue el patrón de arquitectura **MVVM (Model – View – ViewModel)**.

## Capa de Presentación (UI)

Contiene todas las Activities encargadas de interactuar con el usuario.

Ejemplos:

- LoginActivity
- RegisterActivity
- HomeActivity
- NuevaTareaActivity
- EditarTareaActivity
- PomodoroActivity
- EstadisticasActivity

---

## Capa ViewModel

Gestiona la lógica entre la interfaz gráfica y la base de datos.

Clase principal:

- TareaViewModel

Responsabilidades:

- Obtener información.
- Actualizar tareas.
- Insertar registros.
- Eliminar tareas.
- Exponer datos mediante LiveData.

---

## Capa de Datos

Está formada por:

- Room Database
- DAO
- Repository

Clases principales:

- AppDatabase
- TareaDao
- TareaRepository

---

## Flujo de funcionamiento

Usuario

↓

Activities

↓

ViewModel

↓

Repository

↓

Room Database

---

# 3. Modelo de datos

Entidad principal:

## Tarea

| Campo | Tipo |
|--------|------|
| id | Int |
| titulo | String |
| descripcion | String |
| categoria | String |
| pomodorosEstimados | Int |
| pomodorosCompletados | Int |
| estado | String |
| fechaCreacion | String |

La entidad Tarea representa cada actividad registrada por el usuario y almacena toda la información necesaria para controlar el avance mediante la técnica Pomodoro.

---

# 4. Tecnologías y librerías

## Framework

- Android Studio
- Kotlin

## Arquitectura

- MVVM

## Base de datos

- Room Database

## Componentes Android Jetpack

- LiveData
- ViewModel
- RecyclerView

## Otras librerías

- Material Design 3
- ViewBinding
- Kotlin Coroutines

---

# 5. Instrucciones para compilar

## Requisitos

- Android Studio (versión utilizada durante el desarrollo)
- JDK 11 o superior
- Gradle configurado automáticamente
- Android SDK 35 o superior

## Procedimiento

1. Clonar el repositorio desde GitHub.
2. Abrir el proyecto en Android Studio.
3. Esperar la sincronización de Gradle.
4. Ejecutar la aplicación en un dispositivo físico o emulador Android.

No se requieren API Keys ni archivos de configuración externos.

---

# 6. Estructura del repositorio

app/

├── activities/

├── adapter/

├── database/

├── model/

├── repository/

├── utils/

├── viewmodel/

├── res/

└── AndroidManifest.xml

Descripción:

- activities: interfaz gráfica.
- adapter: RecyclerView.
- database: configuración Room.
- model: entidades.
- repository: acceso a datos.
- utils: utilidades y notificaciones.
- viewmodel: lógica MVVM.
- res: recursos gráficos y layouts.

---

# 7. Historial de versiones

## v1.0

Fecha: Julio 2026

Funcionalidades implementadas:

- Inicio de sesión.
- Registro.
- CRUD de tareas.
- Temporizador Pomodoro.
- Estadísticas.
- Notificaciones.
- Persistencia con Room.
- Corrección de errores.
- Optimización para la presentación final.
