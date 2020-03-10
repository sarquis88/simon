package simon;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Funcion main
     * @param args argumentos de ejecucion
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.setTitle("simon");

        primaryStage.setScene(View.getInstance().getScene());
        primaryStage.show();
    }
}
