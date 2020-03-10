package simon;

public class Controller {

    private static Controller thisController;

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

    }

    public void exit(int status) {
        System.exit(status);
    }

    public void jugar() {

        GamePane.getInstance().quitarAccion();
        mostrar();
    }

    public void mostrar() {
        SecuenciasManager.getInstance().showSecuenciaRandom(3);
    }

    public void finShow() {
        GamePane.getInstance().disableButtons(false);
        GamePane.getInstance().agregarAccion();
    }

    public void finRonda() {
        GamePane.getInstance().disableButtons(true);
        GameGrid.getInstance().finRonda();
    }

    public void colorButtonReaccion(String colors) {
        SecuenciasManager.getInstance().getRespuesta(colors);
    }
}
