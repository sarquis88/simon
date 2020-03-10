package simon;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SecuenciasManager {

    private static SecuenciasManager thisSecuenciasManager;

    private LinkedList<String> secuenciaActual;
    private LinkedList<String> respuestaActual;

    /**
     * Patron singleton
     * @return instancia unica de clase
     */
    public static SecuenciasManager getInstance() {
        if(thisSecuenciasManager == null)
            thisSecuenciasManager = new SecuenciasManager();
        return thisSecuenciasManager;
    }

    private SecuenciasManager() {
        this.secuenciaActual = new LinkedList<>();
        this.respuestaActual = new LinkedList<>();
    }

    public void showSecuenciaRandom(int size) {
        this.secuenciaActual = getSecuenciaRandom(size);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int index = 0;
            @Override
            public void run() {
                next(secuenciaActual, index);
                index++;

                if(index == secuenciaActual.size() + 1) {
                    timer.cancel();
                    Controller.getInstance().finShow();
                }
            }
        }, 0, 1000);
    }

    private LinkedList<String> getSecuenciaRandom(int size) {
        String[] opciones = {"r", "g", "y", "b"};
        LinkedList<String> secuencia = new LinkedList<>();

        for(int i = 0; i < size; i++) {
            Random random = new Random();
            String opcion;
            do {
                opcion = opciones[random.nextInt(opciones.length)];
            }
            while(i > 0 && opcion.equalsIgnoreCase(secuencia.get(i - 1)));

            secuencia.add(i, opcion);
        }
        return secuencia;
    }

    private void next(LinkedList<String> secuencia, int index) {
        if(index > 0)
            GamePane.getInstance().disableButton(secuencia.get(index - 1), true);

        if(index < secuencia.size())
            GamePane.getInstance().disableButton(secuencia.get(index), false);
    }

    public void getRespuesta(String rta) {
        this.respuestaActual.add(rta);

        if(this.respuestaActual.size() == 3) {
            boolean bien = true;

            for(int i = 0; i < respuestaActual.size(); i++) {
                if(!respuestaActual.get(i).equalsIgnoreCase(secuenciaActual.get(i))) {
                    System.out.println("MAL");
                    bien = false;
                    break;
                }
            }
            if(bien)
                System.out.println("BIEN");
            Controller.getInstance().finRonda();
        }

    }

}
