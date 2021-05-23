# Nombre del proyecto

Money Life (Aplicación para Android)
## Introducción

Este proyecto es un videojuego de finanzas personales, el cual consiste en simular de una forma interactiva la vida de una persona con base en las decisiones económicas que se presenten, tratando de imitar las oportunidades que comúnmente se presentan en la vida cotidiana de una persona de nivel socioeconómico medio. El juego tendrá como variables eventos de tipio micro y macro, aparte de toma de decisiones en forma de inversiones, gastos personales y modificaciones de tu estado económico actual, todas estas variables tendrán un efecto tanto positivo como negativo en tu ganancias y deudas diarias, aparte de evaluar el nivel de comodidad o felicidad con el cual cuenta tu personaje con las decisiones y eventos previos.

El juego comenzará con una persona estudiante de aproximadamente 18 años y se ira incrementando la edad con forme pase los turnos, cada turno será lo equivalente a un periodo de 2 semanas o 1 mes, y en cada uno de estos sucederán los eventos y las decisiones previamente dichas.


## Tabla de contenidos

* [Detalle de Cliente](#client-details)
* [Ambiente URLS](#environment-urls)
* [Miembros del equipo](#team-members)
* [Tecnología utilizada](#technology-stack)
* [Recursos de gestión](#management-resources)
* [Configuración del proyecto](#setup-the-project)
* [Ejecución y Depuración](#exec-and-debugging)
* [Ejecutar pruebas](#running-tests)
* [Crear versión para distribución](#deployment-build)


### Detalle de Cliente

| Nombre                         | Email                | Rol                                                                                |
| ------------------------------ | -------------------- | ---------------------------------------------------------------------------------- |
| Rubén Cantu Damas | ruben.aguilar@tec.mx | Profesor de finanzas ITESM Campus Mty. |

### Ambiente URLS

Backend: https://moneylifev1.azurewebsites.net

Base de Datos: moneylifedbserver.mysql.database.azure.com

Servicio de Almacenamiento: https://moneylifestorage.blob.core.windows.net/

### Miembros del equipo

Versión 1.0
| Nombre                            | Email                   | Rol                                               |
| --------------------------------- | ----------------------- | ------------------------------------------------- |
| Carlos Ivan Cardenas Cardenas     | A00820062@itesm.mx      | Desarrollador - Administrador de Proyecto         |
| Diego Ivan Valadez Lozano         | A00817562@itesm.mx      | Desarrollador - Scrum Master                      |
| Fernando Limon Flores             | A00819824@itesm.mx      | Desarrollador - Product Owner                     |
| Salvador Barboza Portillo         | A01187752@itesm.mx      | Desarrollador - Administrador de Configuración    |


### Tecnológia utilizada

| Tecnológia      | Versión      |
| --------------- | ------------ |
| Kotlin          | 1.3.72       |
| AndroidX        | 1.3.2        |
| Room            | 2.2.6        |
| Hilt            | 2.33-beta    |
| Groupie         | 2.8.1        |
| Shimmer         | 0.5.0        |
| Retrofit        | 2.9.0        |
| Android Coroutines | 1.3.9     |
| Moshi           | 1.11.0       |
| GraphView       | 4.2.2        |
| MockK           | 1.11.0       |
| AssertK         | 0.23.1       |
| Espresso        | 3.3.0        |


### Recursos de gestión

Microsoft Teams

GitHub Projects

### Configuración del proyecto  
Se recomienda utilizar Android Studio para ejecutar y depurar el proyecto. Es posible compilar y ejecutar la aplicación desde la consola de comandos utilizando AVD, ADB y Gradle. Sin embargo, Android Studio contiene herramientas que facilitan el desarrollo en esta tecnología.
Para probar la aplicación, se necesita conectar un dispositivo con Android con la opción de [modo de desarrollador encendida](https://developer.android.com/studio/debug/dev-options#enable) o [crear un dispositivo virtual](https://developer.android.com/studio/run/managing-avds)

### Ejecución y Depuración
- Para ejecutar la aplicación, una vez que el dispositivo conectado se encuentra activo, seleccionar el icono de ejecutar. Si no se esta utilizando Android Studio, esto mismo se puede realizar ejecutando la tarea 'app:assemble' con Gradle desde la consola de comandos. Esto, generará un archivo .apk dentro de la carpeta 'build' que puede instalarse en el dispositivo ejecutando la tarea 'app:installDebug'.
- Para ejecutar la aplicación en modo de depuración, una vez que el dispositivo conectado se encuentra activo, seleccionar el icono de depurar. Si no se esta utilizando Android Studio, esto se puede realizar ejecutando la tarea 'app:assembleDebug' con con Gradle desde la consola de comandos. Esto, generara un archivo .apk dentro de la carpeta 'build' que puede instalarse en el dispositivo ejecutando la tarea 'app:installDebug'. Para comenzar la depuración, se ver punto siguiente.
- Para conectar la herramienta de depuración a un dispositivo que ya esta ejecutando la aplicación, seleccionar el icono de conectar herramienta de depuración.

Para detener el proyecto, basta con cerrar la aplicación en el dispositivo o bien, seleccionando la opción de detener desde Android Studio.

### Ejecutar pruebas
Para ejecutar las pruebas, ejecutar la tarea 'app:check' con Gradle en la línea de comandos, o bien hacer click derecho sobre el nombre del proyecto en Android Studio y seleccionar 'correr todas las pruebas'. Al terminar de ejecutarse este proceso, se mostrará una lista con las pruebas encontradas y su resultado.

### Crear versión para distribución
Para crear una versión para distribución:
- Opción desde Android Studio: Seleccionar Build -> Build Bundle(s) / APK (s) -> Build APK (s).
- Opcion desde Gradle: ejecutar desde línea de comandos 
Este archivo se puede distribuir directamente con los usuarios que deseen probar la aplicación
