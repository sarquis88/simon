package simon;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameGrid implements SimonParameters {

    private static GameGrid thisGameGrid;

    private GridPane thisGrid;
    private Button jugar;
    private Button terminar;

    /**
     * Patron singleton
     * @return instancia unica de clase
     */
    public static GameGrid getInstance() {
        if(thisGameGrid == null)
            thisGameGrid = new GameGrid();
        return thisGameGrid;
    }

    private GameGrid() {

        thisGrid = new GridPane();
        thisGrid.setHgap(10);
        thisGrid.setVgap(10);
        thisGrid.setPadding(new Insets(vPadding, hPadding, vPadding, hPadding));

        jugar = new Button("Jugar");
        jugar.setPrefSize(buttonsWidth, buttonsHeight);
        jugar.setStyle(buttonsStyle);
        jugar.setOnAction(e -> jugarReaccion());

        terminar = new Button("Terminar");
        terminar.setPrefSize(buttonsWidth, buttonsHeight);
        terminar.setStyle(buttonsStyle);
        terminar.setDisable(true);
        terminar.setOnAction(e -> terminarReaccion());

        Button salir = new Button("Salir");
        salir.setPrefSize(buttonsWidth, buttonsHeight);
        salir.setStyle(buttonsStyle);
        salir.setOnAction(e -> Controller.getInstance().exit(0));

        thisGrid.add(jugar, 0, 0);
        thisGrid.add(terminar, 1, 0);
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

    private void jugarReaccion() {
        jugar.setDisable(true);
        terminar.setDisable(false);

        Controller.getInstance().jugar();
    }

    public void terminarReaccion() {
        terminar.setDisable(true);
        jugar.setDisable(false);
    }
}
