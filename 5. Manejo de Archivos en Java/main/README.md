# Check-in Aula con Persistencia

Proyecto educativo en Java para registrar inscripciones de estudiantes al inicio de clase.

## Funcionalidades
- Registro, búsqueda y resumen de inscripciones.
- Ejecución por consola o GUI (`--gui`).
- Persistencia automática en archivo CSV (`data/inscripciones.csv`).

## Compilación y ejecución


javac -d out $(find src -name "*.java")
java -cp out edu.cerp.checkin.App         # modo consola
java -cp out edu.cerp.checkin.App --gui   # modo GUI



## Persistencia
Se utiliza un archivo **CSV** porque es simple, legible y compatible con planillas de cálculo.
Los datos se cargan al iniciar y se guardan automáticamente al registrar nuevas inscripciones.
