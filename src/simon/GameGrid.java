package simon;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GameGrid implements SimonParameters {

    private static GameGrid thisGameGrid;

    private GridPane thisGrid;
    private Pane statusPane;
    private Button jugar;
    private Label status;
    private Rectangle rectangle;

    /**
     * Patron singleton
     * @return instancia unica de clase
     */
    public static GameGrid getInstance() {
        if(thisGameGrid == null)
            thisGameGrid = new GameGrid();
        return thisGameGrid;
    }

    /**
     * Constructor de clase
     */
    private GameGrid() {

        thisGrid = new GridPane();
        thisGrid.setHgap(10);
        thisGrid.setVgap(10);
        thisGrid.setPadding(new Insets(vPadding, hPadding, vPadding, hPadding));

        jugar = new Button("Jugar");
        jugar.setPrefSize(buttonsWidth, buttonsHeight);
        jugar.setStyle(buttonsStyle);
        jugar.setOnAction(e -> jugarReaccion());

        Button salir = new Button("Salir");
        salir.setPrefSize(buttonsWidth, buttonsHeight);
        salir.setStyle(buttonsStyle);
        salir.setOnAction(e -> Controller.getInstance().exit(0));

        statusPane = new Pane();
        statusPane.setPrefSize(buttonsWidth, buttonsHeight);

        rectangle = new Rectangle(0,
                0,
                buttonsWidth * 2,
                buttonsHeight * 1.5);
        rectangle.setArcHeight(arcHeight);
        rectangle.setArcWidth(arcWidth);
        rectangle.setFill(rectangleColor);

        statusPane.getChildren().add(rectangle);

        setStatus("SIMON");

        thisGrid.add(jugar, 0, 0);
        thisGrid.add(statusPane, 1, 0);
        thisGrid.add(salir, 10, 0);

    }

    /**
     * Seteo de posicion de la grid
     * @param x layout x
     * @param y layout y
     */
    public void setLayout(double x, double y) {
        this.thisGrid.setLayoutX(x);
        this.thisGrid.setLayoutY(y);
    }

    /**
     * Retorna el grid para que las vistas lo utilicen
     * @return objeto nodo GridPane
     */
    public GridPane getGridPane() {
        return this.thisGrid;
    }

    /**
     * Desactivacion del boton de jugar
     * Comienzo de juego
     */
    private void jugarReaccion() {
        jugar.setDisable(true);
        Controller.getInstance().jugar();
    }

    /**
     * Activacion del boton de jugar
     */
    public void finRonda() {
        jugar.setDisable(false);
    }

    /**
     * Cambia el mensaje del margen superior
     * @param status mensaje a mostrar
     */
    public void setStatus(String status) {

        if(this.status != null)
            this.statusPane.getChildren().remove(this.status);
        this.status = new Label(status);
        this.status.setStyle(statusStyle);
        this.status.setLayoutX(rectangle.getWidth() * 0.33);
        this.status.setLayoutY(vPadding / 2.00);
        this.statusPane.getChildren().add(this.status);
    }
}
