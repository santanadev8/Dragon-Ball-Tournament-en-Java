import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {
        Torneo torneo = new Torneo();
        torneo.inicializarLuchadores(); // Inicializar luchadores al arrancar el programa

        int opcion;
        do {
            mostrarMenu();
            opcion = Esdia.readInt("Introduce una opción: ");
            switch (opcion) {
                case 1 -> {
                    Luchadores nuevoLuchador = new Luchadores();
                    torneo.agregarLuchador(nuevoLuchador);
                }
                case 2 -> torneo.empezarTorneo();
                case 3 -> torneo.verLuchadores();
                case 4 -> torneo.verTorneo();
                case 5 -> System.out.println("Saliendo del juego...");
                default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("--- Dragon Ball Tournament Menu ---");
        System.out.println("");
        System.out.println("  1. Crear luchador");
        System.out.println("  2. Crear torneo");
        System.out.println("  3. Ver luchadores");
        System.out.println("  4. Ver torneo");
        System.out.println("  5. Salir");
        System.out.println("");
    }

    static class Luchadores {
        private String nombre;
        private int velocidad;
        private int ataque;
        private int defensa;
        private int salud;

        // Constructores
        public Luchadores() {
        }

        public Luchadores(String nombre, int velocidad, int ataque, int defensa) {
            this.nombre = nombre;
            this.velocidad = velocidad;
            this.ataque = ataque;
            this.defensa = defensa;
            this.salud = 100;
        }

        // Setters con validaciones
        public void setVelocidad(int velocidad) {
            if (velocidad < 0 || velocidad > 100) {
                throw new IllegalArgumentException("La velocidad debe estar entre 0 y 100.");
            }
            this.velocidad = velocidad;
        }

        public void setAtaque(int ataque) {
            if (ataque < 0 || ataque > 100) {
                throw new IllegalArgumentException("El ataque debe estar entre 0 y 100.");
            }
            this.ataque = ataque;
        }

        public void setDefensa(int defensa) {
            if (defensa < 0 || defensa > 100) {
                throw new IllegalArgumentException("La defensa debe estar entre 0 y 100.");
            }
            this.defensa = defensa;
        }

        public void setSalud(int salud) {
            if (salud < 0 || salud > 100) {
                throw new IllegalArgumentException("La salud debe estar entre 0 y 100.");
            }
            this.salud = salud;
        }

        public void setNombre(String nombre) {
            if (nombre == null || nombre.trim().equals("")) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            this.nombre = nombre;
        }

        // Getters
        public int getVelocidad() {
            return velocidad;
        }

        public int getAtaque() {
            return ataque;
        }

        public int getDefensa() {
            return defensa;
        }

        public int getSalud() {
            return salud;
        }

        public String getNombre() {
            return nombre;
        }
    }

    static class Batalla {
        private Luchadores luchador1, luchador2;

        // Constructor
        public Batalla(Luchadores luchador1, Luchadores luchador2) {
            this.luchador1 = luchador1;
            this.luchador2 = luchador2;
        }

        // Métodos
        public Luchadores combate() {
            if (luchador1.getVelocidad() > luchador2.getVelocidad())
                turnos(luchador1, luchador2);
            else
                turnos(luchador2, luchador1);

            if (luchador1.getSalud() > 0) {
                System.out.println(luchador1.getNombre() + " ha ganado la batalla contra " + luchador2.getNombre());
                return luchador1;
            } else {
                System.out.println(luchador2.getNombre() + " ha ganado la batalla contra " + luchador1.getNombre());
                return luchador2;
            }
        }

        private void turnos(Luchadores luchador1, Luchadores luchador2) {
            while (luchador1.getSalud() > 0 && luchador2.getSalud() > 0) {
                System.out.println("Turno de " + luchador1.getNombre());
                atacar(luchador1, luchador2);
                if (luchador2.getSalud() <= 0)
                    break;
                System.out.println("Turno de " + luchador2.getNombre());
                atacar(luchador2, luchador1);
            }
            System.out.println("El combate ha terminado!");
        }

        private void atacar(Luchadores atacante, Luchadores defensor) {
            int ataque = atacante.getAtaque() - defensor.getDefensa();
            if (ataque < 0)
                ataque = 0;
            if ((int) Math.floor(Math.random() * 10) >= 2) {
                // El defensor es golpeado
                if (defensor.getDefensa() > defensor.getAtaque()) {
                    System.out.println(defensor.getNombre() + " fue golpeado!");
                    int nuevaSalud = defensor.getSalud() - ((atacante.getAtaque() * 10) / 100);
                    defensor.setSalud(Math.max(nuevaSalud, 0)); // Asegurarse de que la salud no sea menor que 0
                } else {
                    System.out.println(defensor.getNombre() + " fue golpeado!");
                    int nuevaSalud = defensor.getSalud() - ataque;
                    defensor.setSalud(Math.max(nuevaSalud, 0)); // Asegurarse de que la salud no sea menor que 0
                }
            } else {
                // El defensor esquiva el ataque
                System.out.println(defensor.getNombre() + " esquivó el ataque!");
            }
            // Pausar la ejecución para mostrar los mensajes más lentamente
            try {
                Thread.sleep(1000); // Pausa de 1 segundo (1000 milisegundos) para poder ver mejor los combates.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
    }
        }
    }

    static class Torneo {
        ArrayList<Luchadores> luchadores = new ArrayList<>();
        ArrayList<Batalla> batallas = new ArrayList<>();
        private int numLuchadores;

        public void inicializarLuchadores() {
            String[] nombres = {
                    "Goku", "Vegeta", "Piccolo", "Gohan", "Freezer", "Cell",
                    "Majin Boo", "Trunks", "Krilin", "Ten Shin Han", "Yamcha",
                    "Broly", "Bills", "Whis", "Jiren", "Hit",
                    "Zamas", "Androide 18", "Androide 17", "Gotenks", "Raditz",
                    "Nappa", "Bardock", "Kid Boo", "Kaiosama", "Maestro Roshi",
                    "Dende", "Videl", "Pan", "Mr. Satán", "Chichi", "Bulma",
                    "Zarbon", "Dodoria", "Ginyu", "Recoome", "Burter", "Jeice", "Guldo",
                    "Shenlong", "Tapion", "Puar", "Oolong"
            };

            for (String nombre : nombres) {
                Luchadores luchador = new Luchadores();
                luchador.setNombre(nombre);
                luchador.setVelocidad(RandomUtils.getRandomInt(0, 100));
                luchador.setAtaque(RandomUtils.getRandomInt(0, 100));
                luchador.setDefensa(RandomUtils.getRandomInt(0, 100));
                luchador.setSalud(100);
                luchadores.add(luchador);
            }
        }

        public void agregarLuchador(Luchadores luchador) {
            luchador.setNombre(Esdia.readString_ne("Introduce el nombre del luchador: "));
            luchador.setVelocidad(Esdia.readInt("Introduce la velocidad del luchador: "));
            luchador.setAtaque(Esdia.readInt("Introduce el ataque del luchador: "));
            luchador.setDefensa(Esdia.readInt("Introduce la defensa del luchador: "));
            luchador.setSalud(100); // Salud inicial
            luchadores.add(luchador);
        }

        public void validarNumeroLuchadores() {
            do {
                numLuchadores = Esdia.readInt("Introduce el número de luchadores (debe ser una potencia de 2): ");
            } while (numLuchadores < 2 || (numLuchadores & (numLuchadores - 1)) != 0);
            System.out.println("Número de luchadores válido: " + numLuchadores);
        }

        public void verLuchadores() {
            System.out.println("--- Lista de Luchadores ---");
            for (Luchadores luchador : luchadores) {
                System.out.println("Nombre: " + luchador.getNombre() + ", Velocidad: " + luchador.getVelocidad()
                        + ", Ataque: " + luchador.getAtaque() + ", Defensa: " + luchador.getDefensa()
                        + ", Salud: " + luchador.getSalud());
            }
        }

        public void empezarTorneo() {
            validarNumeroLuchadores();
            if (numLuchadores > luchadores.size()) {
                System.out.println("No hay suficientes luchadores para el torneo.");
                return;
            }

            Collections.shuffle(luchadores); // Mezclar la lista de luchadores para emparejarlos al azar

            ArrayList<Luchadores> participantes = new ArrayList<>(luchadores.subList(0, numLuchadores));

            while (participantes.size() > 1) {
                ArrayList<Luchadores> ganadores = new ArrayList<>();
                for (int i = 0; i < participantes.size(); i += 2) {
                    Luchadores luchador1 = participantes.get(i);
                    Luchadores luchador2 = participantes.get(i + 1);
                    Batalla batalla = new Batalla(luchador1, luchador2);
                    Luchadores ganador = batalla.combate();
                    ganadores.add(ganador);
                }
                participantes = ganadores;
                System.out.println("Ronda completada. Luchadores restantes: " + participantes.size());
            }

            if (participantes.size() == 1) {
                System.out.println("El ganador del torneo es: " + participantes.get(0).getNombre());
            }
        }

        public void verTorneo() {
            System.out.println("--- Torneo ---");
            for (Batalla batalla : batallas) {
                System.out.println("Batalla entre " + batalla.luchador1.getNombre() + " y " + batalla.luchador2.getNombre());
                batalla.combate();
            }
        }
    }

    public static class RandomUtils {
        private static final Random RANDOM = new Random();

        public static int getRandomInt(int min, int max) {
            return RANDOM.nextInt((max - min) + 1) + min;
        }

        public static double getRandomDouble(double min, double max) {
            return min + (max - min) * RANDOM.nextDouble();
        }

        public static boolean getRandomBoolean() {
            return RANDOM.nextBoolean();
        }

        public static <T> T getRandomElement(T[] array) {
            int index = RANDOM.nextInt(array.length);
            return array[index];
        }

        public static <T> T getRandomElement(ArrayList<T> list) {
            int index = RANDOM.nextInt(list.size());
            return list.get(index);
        }
    }
}