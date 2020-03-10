package simon;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameGrid implements SimonParameters {

    private static GameGrid thisGameGrid;

    private GridPane thisGrid;
    private Button jugar;

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

        Button salir = new Button("Salir");
        salir.setPrefSize(buttonsWidth, buttonsHeight);
        salir.setStyle(buttonsStyle);
        salir.setOnAction(e -> Controller.getInstance().exit(0));

        thisGrid.add(jugar, 0, 0);
        thisGrid.add(salir, 12, 0);

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
        Controller.getInstance().jugar();
    }

    public void finRonda() {
        jugar.setDisable(false);
    }
}
