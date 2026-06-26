# tempodoro-android
Aplicación Pomodoro para Android con Kotlin
# Tempodoro

Aplicación Pomodoro para Android desarrollada en **Kotlin** con **Material Design 3**.  
Su objetivo es ayudar a estudiantes y profesionales a mejorar la concentración y productividad mediante la técnica Pomodoro.

---

#Definición del problema
Muchas personas tienen dificultades para mantener la concentración durante largos periodos de estudio o trabajo.  
La técnica Pomodoro propone dividir el tiempo en intervalos de trabajo y descanso, pero no siempre es fácil gestionarlos manualmente.  
**Tempodoro** surge como solución para automatizar estos ciclos y ofrecer estadísticas de uso.

---

# Objetivo de la aplicación
- Facilitar la gestión del tiempo de estudio/trabajo.  
- Implementar la técnica Pomodoro de forma sencilla y personalizable.  
- Proporcionar métricas básicas para que el usuario evalúe su progreso.  

---

# Historias de usuario (MVP)
- **HU1:** Como estudiante quiero iniciar un temporizador de estudio para concentrarme en mis tareas.  
- **HU2:** Como usuario quiero ver estadísticas de mis sesiones para medir mi progreso.  
- **HU3:** Como usuario quiero ajustar la duración de estudio y descanso para personalizar mi flujo de trabajo.  

---

# Arquitectura y tecnología usada
- **Framework:** Android Studio  
- **Lenguaje:** Kotlin  
- **UI/UX:** Material Design 3  
- **Gestión de dependencias:** Gradle  
- **Prototipo:** Figma (capturas incluidas más abajo)  

---

# Instrucciones de instalación
1. Clonar el repositorio:  
   ```bash
   git clone https://github.com/jhosst-ip/tempodoro-android.git


docs: add Project README



## Funcionalidades implementadas ✅

### Login
- [x] Pantalla de inicio de sesión con campos de email y contraseña
- [x] Validación de formato de email
- [x] Validación de contraseña (mínimo 6 caracteres)
- [x] Mensajes de error en tiempo real
- [x] Navegación a pantalla de Registro
- [x] Navegación a pantalla principal tras autenticación exitosa

### Registro
- [x] Pantalla de registro con campos: email, contraseña, confirmar contraseña
- [x] Validación de formato de email
- [x] Validación de contraseña (mínimo 6 caracteres)
- [x] Validación de coincidencia de contraseñas
- [x] Almacenamiento de credenciales en SharedPreferences
- [x] Mensaje de confirmación de registro exitoso

### Pantalla Principal
- [x] Pantalla de bienvenida con título "Tempodoro"
- [x] Subtítulo "Tu asistente de concentración"
- [x] Diseño limpio y minimalista con Material Design

### Navegación
- [x] Flujo completo: Login → Registro → Pantalla Principal
- [x] Cierre automático de actividades al navegar
