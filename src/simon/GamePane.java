package simon;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GamePane implements SimonParameters {

    private static GamePane thisGamePane;

    private Pane thisPane;
    private Button upRed;
    private Button rightGreen;
    private Button downYellow;
    private Button leftBlue;

    /**
     * Patron singleton
     * @return instancia unica de clase
     */
    public static GamePane getInstance() {
        if(thisGamePane == null)
            thisGamePane = new GamePane();
        return thisGamePane;
    }

    private GamePane() {
        thisPane = new Pane();
        thisPane.setPrefSize(paneWidth, paneHeight);

        this.upRed = new Button();
        this.upRed.setLayoutX((paneWidth - buttonsSimonWidth) / 2.00);
        this.upRed.setLayoutY(paneHeight * 0.25 - buttonsSimonHeight / 2.00);
        this.upRed.setPrefSize(buttonsSimonWidth, buttonsSimonHeight);
        this.upRed.setStyle(buttonsSimonStyle + " -fx-background-color: red;");

        this.rightGreen = new Button();
        this.rightGreen.setLayoutX(paneWidth * 0.75 - buttonsSimonWidth / 2.00 );
        this.rightGreen.setLayoutY((paneHeight - buttonsSimonHeight) / 2.00);
        this.rightGreen.setPrefSize(buttonsSimonWidth, buttonsSimonHeight);
        this.rightGreen.setStyle(buttonsSimonStyle + " -fx-background-color: green;");

        this.downYellow = new Button();
        this.downYellow.setLayoutX(upRed.getLayoutX());
        this.downYellow.setLayoutY(paneHeight * 0.75 - buttonsSimonHeight / 2.00);
        this.downYellow.setPrefSize(buttonsSimonWidth, buttonsSimonHeight);
        this.downYellow.setStyle(buttonsSimonStyle + " -fx-background-color: yellow;");

        this.leftBlue = new Button();
        this.leftBlue.setLayoutX(paneWidth * 0.25 - buttonsSimonWidth / 2.00);
        this.leftBlue.setLayoutY(rightGreen.getLayoutY());
        this.leftBlue.setPrefSize(buttonsSimonWidth, buttonsSimonHeight);
        this.leftBlue.setStyle(buttonsSimonStyle + " -fx-background-color: blue;");

        disableButtons(true);

        thisPane.getChildren().addAll(this.downYellow, this.leftBlue, this.upRed, this.rightGreen);
    }

    public void setLayout(double x, double y) {
        thisPane.setLayoutX(x);
        thisPane.setLayoutY(y);
    }

    public Pane getThisPane() {
        return this.thisPane;
    }

    public void disableButtons(boolean disable) {
        upRed.setDisable(disable);
        downYellow.setDisable(disable);
        rightGreen.setDisable(disable);
        leftBlue.setDisable(disable);
    }

    public void disableButton(String target, boolean disable) {
        Button button = null;
        switch (target) {
            case "r":
                button = this.upRed;
                break;
            case "g":
                button = this.rightGreen;
                break;
            case "y":
                button = this.downYellow;
                break;
            case "b":
                button = this.leftBlue;
                break;
            default:
                break;
        }
        if(button != null)
            button.setDisable(disable);
    }

    public void quitarAccion() {
        this.upRed.setOnAction(null);
        this.rightGreen.setOnAction(null);
        this.downYellow.setOnAction(null);
        this.leftBlue.setOnAction(null);
    }

    public void agregarAccion() {
        this.upRed.setOnAction(e -> Controller.getInstance().colorButtonReaccion("r"));
        this.rightGreen.setOnAction(e -> Controller.getInstance().colorButtonReaccion("g"));
        this.downYellow.setOnAction(e -> Controller.getInstance().colorButtonReaccion("y"));
        this.leftBlue.setOnAction(e -> Controller.getInstance().colorButtonReaccion("b"));
    }
}
