package simon;

import java.util.LinkedList;

public class Controller {

    private static Controller thisController;

    private String status;
    private LinkedList<String> respuestaActual;

    private int ronda;


    /**
     * Patron singleton
     * @return instancia unica de clase
     */
    public static Controller getInstance() {
        if(thisController == null)
            thisController = new Controller();
        return thisController;
    }

    public Controller() {
        this.respuestaActual = new LinkedList<>();
        this.status = "INICIO";
    }

    public void exit(int status) {
        System.exit(status);
    }

    public void jugar() {
        // TODO ronda no avanza
        if(this.status.equalsIgnoreCase("INICIO"))
            this.ronda = 1;
        else if(this.status.equalsIgnoreCase("RESPUESTA"))
            this.ronda++;

        this.status = "MUESTRA";
        GamePane.getInstance().quitarAccion();
        SecuenciasManager.getInstance().showSecuenciaRandom(this.ronda);
    }

    public void finShow() {
        if(this.status.equalsIgnoreCase("MUESTRA")) {
            GamePane.getInstance().disableButtons(false);
            GamePane.getInstance().agregarAccion();
            this.status = "RESPUESTA";
        }
    }

    public void finRonda() {
        this.status = "INICIO";
        this.ronda = 0;
        GamePane.getInstance().disableButtons(true);
        GameGrid.getInstance().finRonda();
    }

    public void colorButtonReaccion(String colors) {
        this.respuestaActual.add(colors);
        if(this.respuestaActual.size() == this.ronda) {
            boolean ganado = SecuenciasManager.getInstance().setRespuesta(respuestaActual);
            GamePane.getInstance().disableButtons(true);
            this.respuestaActual.clear();

            if(ganado) {
                showMessage("GANADA RONDA " + this.ronda);
                jugar();
            }
            else {
                showMessage("PERDIDA RONDA " + this.ronda);
                finRonda();
            }
        }
    }

    private void showMessage(String message) {
        GameGrid.getInstance().setStatus(message);
    }
}
