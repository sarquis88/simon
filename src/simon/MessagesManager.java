package simon;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class MessagesManager {

    /**
     * Constructor vacio de clase
     */
    public MessagesManager() {}

    /**
     * Muestra de dialogo de informacion
     * @param msg mensaje a mostrar en dialogo
     */
    public static void showInformationAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText("");
        alert.setContentText(msg);
        alert.getDialogPane().setPrefWidth(250);
        fix(alert);
        alert.showAndWait();
    }

    private static void fix(Alert alert) {
        Platform.runLater(() -> alert.getDialogPane().getScene().getWindow().sizeToScene());
    }
}
