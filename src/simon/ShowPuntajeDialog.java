package simon;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowPuntajeDialog extends SimonDialog implements SimonParameters {

    private ButtonType volver;
    private ButtonType reestablecer;

    /**
     * Constructor de la clase
     */
    public ShowPuntajeDialog() {
        thisDialog = new Dialog<>();

        thisDialog.setTitle("Puntajes");
        thisDialog.setHeaderText("");
        thisDialog.getDialogPane().setPrefWidth(paneWidth);
        thisDialog.getDialogPane().setPrefHeight(paneHeight);

        volver = new ButtonType("Volver");
        reestablecer = new ButtonType("Reestablecer");

        thisDialog.getDialogPane().getButtonTypes().addAll(reestablecer, volver);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefWidth(thisDialog.getDialogPane().getPrefWidth());
        scrollPane.setPrefHeight(thisDialog.getDialogPane().getPrefHeight());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        TableView<Puntaje> tableView = new TableView<>();
        tableView.setEditable(false);
        tableView.setPrefWidth(thisDialog.getDialogPane().getPrefWidth() - 5 * hPadding);
        tableView.setPrefHeight(thisDialog.getDialogPane().getPrefHeight());

        TableColumn<Puntaje, String> c0 = new TableColumn<>("Jugador");
        c0.setCellValueFactory(new PropertyValueFactory<>("jugador"));
        c0.setPrefWidth( tableView.getPrefWidth()  * 0.4);
        TableColumn<Puntaje, String> c1 = new TableColumn<>("Velocidad");
        c1.setCellValueFactory(new PropertyValueFactory<>("velocidad"));
        c1.setPrefWidth( tableView.getPrefWidth() * 0.3);
        TableColumn<Puntaje, Integer> c2 = new TableColumn<>("Ronda");
        c2.setCellValueFactory(new PropertyValueFactory<>("ronda"));
        c2.setPrefWidth( tableView.getPrefWidth() * 0.3);

        tableView.setItems(Controller.getPuntajes());

        tableView.getColumns().add(c0);
        tableView.getColumns().add(c1);
        tableView.getColumns().add(c2);

        scrollPane.setContent(tableView);

        thisDialog.getDialogPane().setContent(scrollPane);
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
