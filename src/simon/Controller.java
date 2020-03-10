package simon;

import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

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
        GamePane.getInstance().disableButton("red", false);

        String[] secuencia = {"red", "green", "yellow", "blue"};

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int index = 0;
            @Override
            public void run() {
                next(secuencia, index);
                index++;

                if(index == secuencia.length + 1)
                    timer.cancel();
            }
        }, 0, 1000);
    }

    private void terminar() {
        GamePane.getInstance().disableButtons(true);
        GameGrid.getInstance().terminarReaccion();
    }

    private void next(String[] secuencia, int index) {
        if(index > 0)
            GamePane.getInstance().disableButton(secuencia[index - 1], true);
        if(index < secuencia.length)
            GamePane.getInstance().disableButton(secuencia[index], false);
        else
            terminar();
    }
}
