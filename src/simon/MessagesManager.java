package simon;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class MessagesManager {

    /**
     * Constructor vacio de clase
     */
    public MessagesManager() {}

    /**
     * Muestra de dialogo de error
     * @param msg mensaje a mostrar en dialogo
     */
    public static void showErrorAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("");
        alert.setContentText(msg);
        alert.getDialogPane().setPrefWidth(140 + msg.length() * 5);
        fix(alert);
        alert.showAndWait();
    }

    private static void fix(Alert alert) {
        Platform.runLater(() -> alert.getDialogPane().getScene().getWindow().sizeToScene());
    }
}
