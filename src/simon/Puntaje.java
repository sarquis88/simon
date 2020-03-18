package simon;

public class Puntaje {

    private String jugador;
    private int ronda;
    private String velocidad;

    public Puntaje() {

    }

    public Puntaje(String jugador, int ronda, int velocidad) {
        this.jugador = jugador;
        this.ronda = ronda;
        this.velocidad = velocidad + "%";
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad + "%";
    }
}
