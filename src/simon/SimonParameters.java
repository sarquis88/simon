package simon;

import javafx.scene.paint.Color;

public interface SimonParameters {

    int sceneHeight = 380;
    int sceneWidth = 400;
    int paneHeight = 300;
    int paneWidth = 400;
	int vPadding = 5;
    int hPadding = 10;
    int buttonsWidth = 85;
    int buttonsHeight = 20;
    int buttonsSimonWidth = 80;
    int buttonsSimonHeight = 80;
    int arcWidth = 15;
    int arcHeight = 15;

    Color rectangleColor = Color.rgb(0, 0, 0, 0.6);

    double separatorHeight = 5.0;

    String buttonsStyle = "-fx-font-size: 12.5";
    String buttonsSimonStyle = "-fx-font-size: 15; ; -fx-border-color: black; -fx-border-radius: 5em; -fx-background-radius: 5em;";
    String statusStyle = "-fx-font-size: 15; -fx-text-fill: white";
    String layoutStyle = "-fx-background-color: dimgray";

    String bddPath = "./src/simon/simon.db";
}
