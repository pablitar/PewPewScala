PewPewScala
===========

Este proyecto es un juego de ejemplo implementado en Vainilla.

La estructura incluye:
- [Vainilla](vainilla) | Versión de vainilla con la que fue hecho el juego
- [El Juego en Sí](PewPew) | Código del juego en Scala


Para correr desde consola:
-------------------------

    cd PewPew
    sbt run
    
O sino, es posible compilar un "runnable jar"

    cd PewPew
    sbt assembly
    java -jar target/scala-2.11/PewPew-assembly-0.1.jar

Para desarrollar:
----------------

Se incluyen archivos de SBT para compilar el proyecto y generar los archivos de los diversos IDEs.

### Para importar desde el scala IDE:

Import --> Existing Projects Into Workspace --> Navegar a la carpeta PewPew.

### Para actualizar los archivos de proyecto desde SBT:

    sbt eclipse
    
    
### Para correr desde el IDE:

Correr la aplicación de Scala [PewPew.scala](PewPew/src/main/scala/ar/edu/pdep/pewpew/PewPew.scala)