package simon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller implements SimonParameters {

    private static Controller thisController;
    private static ObservableList<Puntaje> puntajes = FXCollections.observableArrayList();

    private String status;

    private int ronda;
    private int c;
    private double slider;

    /**
     * Patron singleton
     *
     * @return instancia unica de clase
     */
    public static Controller getInstance() {
        if (thisController == null)
            thisController = new Controller();
        return thisController;
    }

    /**
     * Constructor de clase
     */
    public Controller() {
        this.status = "INICIO";
    }

    /**
     * Salir del juego
     * Cierre de ventana
     *
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
        if (this.status.equalsIgnoreCase("INICIO"))
            this.ronda = 1;
        else if (this.status.equalsIgnoreCase("RESPUESTA"))
            this.ronda++;

        this.status = "MUESTRA";
        this.slider = GameGrid.getInstance().getSlideValue();
        this.c = 0;

        GamePane.getInstance().quitarAccion();
        SecuenciasManager.getInstance().showSecuenciaRandom((int) Math.round(1000 - 800 * slider), ronda);
    }

    /**
     * Fin de muestra de colores de ronda
     */
    public void finShow() {
        if (this.status.equalsIgnoreCase("MUESTRA")) {
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
        GamePane.getInstance().disableButtons(true);
        GameGrid.getInstance().finRonda();
    }

    /**
     * Reaccion a los botones de colores
     * @param color idenficador de color (ROJO = 'r')
     */
    public void colorButtonReaccion(String color) {
        if(!SecuenciasManager.getInstance().addRespuesta(color, c)) {
            showMessage("SIMON");
            if (MessagesManager.confirmation("PERDISTE\nRONDA "
                    + this.ronda + "\n\nDesea guardar su puntaje?")) {
                InputNombreDialog inputNombreDialog = new InputNombreDialog("Ingrese su nombre");
                inputNombreDialog.show();
                String nombre = inputNombreDialog.getResult();
                int velocidad = (int) Math.round(this.slider * 100);
                if (nombre != null) {
                    SimonBDD.getInstance().insertarPuntaje(nombre, this.ronda, velocidad);
                    puntajes.clear();
                    SimonBDD.getInstance().restorePuntajesFromBDD();
                }
            }
            finRonda();
        }
        else {
            c++;
            GameGrid.getInstance().setProgress((double) c / (double) ronda);
            if (this.c == this.ronda) {
                GamePane.getInstance().disableButtons(true);
                showMessage("RONDA " + this.ronda);
                jugar();
            }
        }
    }

    /**
     * Mostrar mensaje en el margen superior
     *
     * @param message mensaje propiamente dicho
     */
    private void showMessage(String message) {
        GameGrid.getInstance().setStatus(message);
    }

    /**
     * Agregado de nuevo puntaje
     *
     * @param puntaje puntaje a agregar
     */
    public static void addPuntaje(Puntaje puntaje) {
        puntajes.add(puntaje);
    }

    /**
     * Muestra de puntajes
     */
    public void verPuntajes() {
        if (puntajes.isEmpty())
            MessagesManager.showInformationAlert("No hay puntajes guardados", false);
        else {
            ShowPuntajeDialog showPuntajeDialog = new ShowPuntajeDialog();
            showPuntajeDialog.show();
        }
    }

    /**
     * Borrado de todos los puntajes
     */
    public void reestablecerPuntajes() {
        if (MessagesManager.confirmation("Desea reestablecer los puntajes?")) {
            SimonBDD.getInstance().restablecerBDD();
            puntajes.clear();
        }
    }

    /**
     * Getter de puntajes
     * @return lista de puntajes
     */
    public static ObservableList<Puntaje> getPuntajes() {
        return puntajes;
    }
}
