package simon;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class SimonDialog {

    protected Dialog<ButtonType> thisDialog;

    /**
     * Muestra de ventana
     */
    public void show() {
        Platform.runLater(() -> thisDialog.getDialogPane().getScene().getWindow().sizeToScene());
        thisDialog.showAndWait();
    }
}
