# GestionAlumnos-Java-NIO
CRUD de estudiantes utilizando java
# Sistema de Gestión de Alumnos - Java NIO
Sistema de Gestión de Alumnos - Java NIO
Aplicación de consola desarrollada en Java para realizar operaciones CRUD sobre un archivo de texto utilizando el paquete java.nio.file.

Descripción
El programa permite administrar información básica de estudiantes mediante las siguientes operaciones:

Registrar un nuevo alumno.

Mostrar todos los alumnos.

Buscar un alumno por ID.

Actualizar el nombre de un alumno.

Eliminar un alumno.

Salir del programa.

Los datos se almacenan en el archivo alumnos.txt.

Formato del archivo
Cada alumno ocupa una línea utilizando el siguiente formato:

ID_Alumno - NombreAlumno

Por ejemplo:

101 - Juan Carlos Pérez
102 - María Fernanda López
103 - Roberto Gómez

Estructura del proyecto
GestionAlumnos/
├── src/
│   ├── Alumno.java
│   ├── AlumnoRepository.java
│   └── Main.java
├── alumnos.txt
└── README.md

Tecnologías utilizadas
Java

Java NIO

java.nio.file.Path

java.nio.file.Files

java.nio.file.StandardOpenOption

Programación Orientada a Objetos

Requisitos
Para ejecutar el proyecto se necesita:

Tener instalado Java JDK.

Se recomienda utilizar JDK 11 o una versión posterior.

Puedes comprobar la versión instalada mediante:

java -version
javac -version

Ejecución desde terminal
Entrar a la carpeta donde se encuentran los archivos .java:

cd src

Compilar el programa:

javac *.java

Ejecutar:

java Main

Funcionamiento
Al iniciar el programa, se comprueba si existe el archivo alumnos.txt.

Si el archivo no existe, el programa lo crea automáticamente.

El menú principal contiene las siguientes opciones:

==============================================
       MENÚ GESTIÓN DE ALUMNOS
             JAVA NIO
==============================================
1. Registrar nuevo alumno
2. Ver todos los alumnos
3. Buscar alumno por ID
4. Actualizar nombre de alumno
5. Eliminar alumno
6. Salir
==============================================

Persistencia
Los datos se almacenan directamente en el archivo alumnos.txt.

Crear un alumno
Para registrar un alumno se utiliza Files.writeString() junto con:

StandardOpenOption.APPEND

Esto permite agregar nuevos registros al archivo sin eliminar los existentes.

Leer alumnos
Para leer los registros almacenados se utiliza:

Files.readAllLines()

Actualizar y eliminar
Para actualizar o eliminar registros, primero se lee el contenido del archivo, se modifica la lista de líneas y posteriormente se escribe nuevamente utilizando:

StandardOpenOption.TRUNCATE_EXISTING

De esta manera, el archivo queda actualizado con los nuevos datos.

Manejo de errores
El programa controla las excepciones relacionadas con las operaciones de entrada y salida mediante IOException.

Ejemplo:

try {
    // Operación con archivos
} catch (IOException e) {
    // Manejo del error
}

También se realizan validaciones para:

IDs duplicados.

IDs no numéricos.

IDs menores o iguales a cero.

Nombres vacíos.

Búsquedas de alumnos inexistentes.

Actualizaciones de alumnos inexistentes.

Eliminaciones de alumnos inexistentes.

Operaciones CRUD
Operación	Método utilizado
Crear	Files.writeString() + APPEND
Leer	Files.readAllLines()
Buscar	Files.readAllLines()
Actualizar	Files.readAllLines() + Files.write()
Eliminar	Files.readAllLines() + Files.write()
Crear archivo	Files.exists() + Files.createFile()

Ejemplo de datos
Después de registrar tres alumnos, el archivo alumnos.txt podría contener:

101 - Juan Carlos Pérez
102 - María Fernanda López
103 - Roberto Gómez

Arquitectura
El proyecto está organizado siguiendo una separación sencilla de responsabilidades:

Alumno
   ↓
Modelo de datos

AlumnoRepository
   ↓
Gestión y persistencia de los alumnos

Main
   ↓
Menú e interacción con el usuario

Esta estructura permite separar el modelo, la lógica de acceso a los datos y la interfaz de consola.

Uso de Java NIO
Esta aplicación utiliza las clases de java.nio.file para trabajar con el archivo:

Path para representar la ubicación del archivo.

Files para crear, leer y modificar archivos.

StandardOpenOption para definir cómo se realizan las operaciones de escritura.

No se utilizan File, FileReader ni FileWriter para la persistencia de los datos.

Autor
Nombre del estudiante: ______________________

Curso: Programación 3

Fecha: ______________________
