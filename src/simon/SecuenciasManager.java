package simon;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SecuenciasManager {

    private static SecuenciasManager thisSecuenciasManager;

    private LinkedList<String> secuenciaActual;

    /**
     * Patron singleton
     * @return instancia unica de clase
     */
    public static SecuenciasManager getInstance() {
        if(thisSecuenciasManager == null)
            thisSecuenciasManager = new SecuenciasManager();
        return thisSecuenciasManager;
    }

    /**
     * Constructor de clase
     */
    private SecuenciasManager() {
        this.secuenciaActual = new LinkedList<>();
    }

    /**
     * Muestra una secuencia de colores random
     * @param size tamaño de secuencia
     */
    public void showSecuenciaRandom(int size) {
        this.secuenciaActual = getSecuenciaRandom(size);
        Timer timer0;

        timer0 = new Timer();
        timer0.scheduleAtFixedRate(new TimerTask() {
            int index = 0;
            int secuenciaIndex = 0;
            @Override
            public void run() {
                boolean flag = false;
                if(index % 2 != 0) {
                    next(secuenciaActual, secuenciaIndex);
                    flag = true;
                }
                else {
                    GamePane.getInstance().disableButtons(true);
                }

                if(index == secuenciaActual.size() * 2 + 1) {
                    timer0.cancel();
                    Controller.getInstance().finShow();
                }

                if(flag)
                    secuenciaIndex++;
                index++;
            }
        }, 0, 700);
    }

    /**
     * Generacion de secuencia random
     * @param size tamaño de secuencia
     * @return lista de string con los colores
     */
    private LinkedList<String> getSecuenciaRandom(int size) {
        String[] opciones = {"r", "g", "y", "b"};
        LinkedList<String> secuencia = new LinkedList<>();

        for(int i = 0; i < size; i++) {
            Random random = new Random();
            secuencia.add(i, opciones[random.nextInt(opciones.length)]);
        }
        return secuencia;
    }

    /**
     * Muestra siguiente color
     * @param secuencia secuencia de colores
     * @param index indice del color a mostrar
     */
    private void next(LinkedList<String> secuencia, int index) {
        if(index < secuencia.size()) {
            GamePane.getInstance().disableButton(secuencia.get(index), false);
        }
    }

    /**
     * Indica si la respuesta ingresada es correcta o no
     * @param respuesta lista con la respuesta
     * @return true para respuesta correcta, de lo contrario false
     */
    public boolean setRespuesta(LinkedList<String> respuesta) {
        for(int i = 0; i < respuesta.size(); i++) {
            if(!respuesta.get(i).equalsIgnoreCase(secuenciaActual.get(i))) {
                return false;
            }
        }
        return true;
    }

}
