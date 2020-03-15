package simon;

import java.util.LinkedList;

public class Controller {

    private static Controller thisController;

    private static LinkedList<String> puntajes = new LinkedList<>();

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

    /**
     * Constructor de clase
     */
    public Controller() {
        this.respuestaActual = new LinkedList<>();
        this.status = "INICIO";
    }

    /**
     * Salir del juego
     * Cierre de ventana
     * @param status estado de salida
     */
    public void exit(int status) {
        System.exit(status);
    }

    /**
     * Comienzo de ronda
     * Muestra de colores
     * Respuesta de jugador
     */
    public void jugar() {
        if(this.status.equalsIgnoreCase("INICIO"))
            this.ronda = 1;
        else if(this.status.equalsIgnoreCase("RESPUESTA"))
            this.ronda++;

        this.status = "MUESTRA";
        GamePane.getInstance().quitarAccion();
        SecuenciasManager.getInstance().showSecuenciaRandom();
    }

    /**
     * Fin de muestra de colores de ronda
     */
    public void finShow() {
        if(this.status.equalsIgnoreCase("MUESTRA")) {
            GamePane.getInstance().disableButtons(false);
            GamePane.getInstance().agregarAccion();
            this.status = "RESPUESTA";
        }
    }

    /**
     * Fin de ronda
     * Ejecutado luego de responder
     */
    public void finRonda() {
        this.status = "INICIO";
        this.ronda = 0;
        GamePane.getInstance().disableButtons(true);
        GameGrid.getInstance().finRonda();
    }

    /**
     * Reaccion a los botones de colores
     * @param colors idenficador de color (ROJO = 'r')
     */
    public void colorButtonReaccion(String colors) {
        this.respuestaActual.add(colors);
        if(this.respuestaActual.size() == this.ronda) {
            boolean ganado = SecuenciasManager.getInstance().setRespuesta(respuestaActual);
            GamePane.getInstance().disableButtons(true);
            this.respuestaActual.clear();

            if(ganado) {
                showMessage("RONDA " + this.ronda);
                jugar();
            }
            else {
                showMessage("SIMON");
                if(MessagesManager.confirmation("PERDISTE\nRONDA "
                        + this.ronda + "\n\nDesea guardar su puntaje?")) {
                    InputSimpleDialog inputSimpleDialog = new InputSimpleDialog("Ingrese su nombre");
                    inputSimpleDialog.show();
                    String nombre = inputSimpleDialog.getResult();
                    if(nombre != null) {
                        SimonBDD.getInstance().insertarPuntaje(nombre, ronda);
                        puntajes.clear();
                        SimonBDD.getInstance().restorePuntajesFromBDD();
                    }
                }
                finRonda();
            }
        }
    }

    /**
     * Mostrar mensaje en el margen superior
     * @param message mensaje propiamente dicho
     */
    private void showMessage(String message) {
        GameGrid.getInstance().setStatus(message);
    }

    /**
     * Agregado de nuevo puntaje
     * @param puntaje puntaje a agregar
     */
    public static void addPuntaje(String puntaje) {
        puntajes.add(puntaje);
    }

    /**
     * Muestra de puntajes
     */
    public void verPuntajes() {
        if(puntajes.isEmpty())
            MessagesManager.showInformationAlert("No hay puntajes guardados", false);
        else {
            String puntajesParaMostrar = "JUGADOR --- RONDA";
            for(String puntaje : puntajes) {
                String jugador = puntaje.split("-")[0];
                int ronda = Integer.decode(puntaje.split("-")[1]);

                puntajesParaMostrar = puntajesParaMostrar.concat("\n" + jugador + " --- " + ronda);
            }
            MessagesManager.showInformationAlert(puntajesParaMostrar, true);
        }
    }
}
