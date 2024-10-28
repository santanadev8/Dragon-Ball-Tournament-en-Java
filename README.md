# Dragon Ball Tournament en Java

Este proyecto es una simulación de un torneo de lucha basado en los personajes de Dragon Ball. El programa permite crear luchadores, organizar torneos y ver los resultados de las batallas.

## Características

- Crear luchadores personalizados.
- Organizar torneos con un número de luchadores que sea una potencia de 2.
- Ver la lista de luchadores disponibles.
- Ver el progreso y los resultados del torneo.

## Requisitos

- Java 8 o superior.
- Biblioteca `com.coti.tools.Esdia` para la entrada de datos.

## Uso

### Menú Principal

Al ejecutar el programa, se mostrará el siguiente menú:

--- Dragon Ball Tournament Menu ---

Crear luchador
Crear torneo
Ver luchadores
Ver torneo
Salir
Introduce una opción:

### Opciones del Menú

1. **Crear luchador**: Permite crear un nuevo luchador ingresando su nombre, velocidad, ataque y defensa.
2. **Crear torneo**: Inicia un nuevo torneo. Se debe ingresar un número de luchadores que sea una potencia de 2.
3. **Ver luchadores**: Muestra la lista de todos los luchadores disponibles.
4. **Ver torneo**: Muestra el progreso y los resultados del torneo actual.
5. **Salir**: Sale del programa.

### Ejemplo de Uso

1. **Crear un luchador**:
   - Selecciona la opción 1.
   - Ingresa el nombre del luchador.
   - Ingresa la velocidad, ataque y defensa del luchador.

2. **Crear un torneo**:
   - Selecciona la opción 2.
   - Ingresa el número de luchadores (debe ser una potencia de 2, por ejemplo, 4, 8, 16, etc.).
   - El torneo se iniciará automáticamente y se mostrarán los resultados de las batallas.

3. **Ver luchadores**:
   - Selecciona la opción 3 para ver la lista de luchadores disponibles.

4. **Ver torneo**:
   - Selecciona la opción 4 para ver el progreso y los resultados del torneo actual.

## Clase Luchadores

Representa a un luchador con atributos como nombre, velocidad, ataque, defensa y salud.

## Clase Batalla

Representa una batalla entre dos luchadores. Se comparan las velocidades de los luchadores para determinar el orden de ataque y se calcula el daño de cada ataque.

## Clase Torneo

Gestiona la creación de torneos, la validación del número de luchadores y el progreso del torneo.

## Clase RandomUtils

Proporciona métodos utilitarios para generar números aleatorios y seleccionar elementos aleatorios de una lista.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que te gustaría hacer. Ten en cuenta que soy estudiante de segundo curso en Ingenieria Informatica y soy consciente de que el codigo puede ser muy mejorable.
