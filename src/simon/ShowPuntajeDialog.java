package simon;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

public class ShowPuntajeDialog extends SimonDialog implements SimonParameters {

    private ButtonType volver;
    private ButtonType reestablecer;

    /**
     * Constructor de la clase
     */
    public ShowPuntajeDialog(LinkedList<String> puntajes) {
        thisDialog = new Dialog<>();

        thisDialog.setTitle("Puntajes");
        thisDialog.setHeaderText("");
        thisDialog.getDialogPane().setPrefWidth(275);

        volver = new ButtonType("Volver");
        reestablecer = new ButtonType("Reestablecer");

        thisDialog.getDialogPane().getButtonTypes().addAll(reestablecer, volver);

        GridPane grid = new GridPane();
        grid.setHgap(hPadding);
        grid.setVgap(vPadding);
        grid.setPadding(new Insets(vPadding, hPadding, vPadding, hPadding));

        Label jugadorLabel = new Label("JUGADOR");
        jugadorLabel.setStyle("-fx-font-weight: bold;");
        grid.add(jugadorLabel, 0, 0);

        Label rondaLabel = new Label("RONDA");
        rondaLabel.setStyle("-fx-font-weight: bold;");
        grid.add(rondaLabel, 1, 0);

        Label velocidadLabel = new Label("VELOCIDAD");
        velocidadLabel.setStyle("-fx-font-weight: bold;");
        grid.add(velocidadLabel, 2, 0);

        for(int i = 0; i < puntajes.size(); i++) {
            Label jugador = new Label(puntajes.get(i).split("-")[0]);
            grid.add(jugador, 0, i + 1);

            Label ronda = new Label(puntajes.get(i).split("-")[1]);
            grid.add(ronda, 1, i + 1);

            Label velocidad = new Label(puntajes.get(i).split("-")[2] + "%");
            grid.add(velocidad, 2, i + 1);
        }

        thisDialog.getDialogPane().setContent(grid);
    }

    /**
     * Muestra la ventana del dialogo e interpreta los botones apretados
     * por el usuario
     */
    public void show() {

        super.show();

        if(this.thisDialog.getResult() == this.volver)
            return;
        if(this.thisDialog.getResult() == this.reestablecer)
            Controller.getInstance().reestablecerPuntajes();
    }
}
