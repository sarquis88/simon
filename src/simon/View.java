package simon;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

public class View implements SimonParameters {

    private static View thisView = null;

    private Scene thisScene;

    /**
     * Patron singleton
     * @return instancia unica de clase
     */
    public static View getInstance() {
        if(thisView == null)
            thisView = new View();
        return thisView;
    }

    /**
     * Constructor de clase
     */
    private View() {
        Pane layout = new Pane();

        GameGrid gameGrid = GameGrid.getInstance();
        gameGrid.setLayout(hPadding, vPadding);

        GamePane gamePane = GamePane.getInstance();
        gamePane.setLayout(sceneWidth - paneWidth, sceneHeight - paneHeight);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setLayoutX(0);
        separator.setLayoutY(gamePane.getThisPane().getLayoutY() - vPadding);
        separator.setPrefWidth(sceneWidth);
        separator.setPrefHeight(separatorHeight);

        layout.getChildren().addAll(gameGrid.getGridPane(), separator, gamePane.getThisPane());

        this.thisScene = new Scene(layout, sceneWidth, sceneHeight);
    }

    /**
     * Devuelve ventana de vista
     * @return objeto Scene
     */
    public Scene getScene() {
        return this.thisScene;
    }
}
