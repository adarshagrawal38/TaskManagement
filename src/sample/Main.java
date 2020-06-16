package sample;

import Helpers.Constants;
import Helpers.DatabaseHelper;
import Helpers.FileHelper;
import Helpers.LocationHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    public Screen screen = Screen.getPrimary();

    Rectangle2D bounds = screen.getVisualBounds();

    public double screenXmin = bounds.getMinX();

    public double screenYmin = bounds.getMinY();

    public double screenMaxWidth = bounds.getWidth();

    public double screenMaxHeight = bounds.getHeight();

    public double screenMinWidth = 1300;

    public double screenMinHeight = 700;


    public static String USERNAME;




    public final int WIDTH = 1300;
    public final int HEIGHT = 700;
    @Override
    public void start(Stage primaryStage) throws Exception{

        String scene = LocationHelper.LOG_IN_SCENE;
        FileHelper fileHelper = new FileHelper();
        if (fileHelper.isLogedIn()) {
            USERNAME = fileHelper.getUserName();
            DatabaseHelper databaseHelper = new DatabaseHelper();
            String role = databaseHelper.getUserRole(USERNAME);
            if (role.equals(Constants.ADMIN)) {
                scene = LocationHelper.TASK_HISTORY_SCENE;
            }else if (role.equals(Constants.USER)) {
                /*load user frame*/
                scene = LocationHelper.USER_CURRENT_TASK;
            }
        }


        Parent root = FXMLLoader.load(getClass().getResource(scene));
        primaryStage.setTitle("Task Management");
        //primaryStage.setMaximized(true);
        stage = primaryStage;
        stage.maximizedProperty().addListener((ov, minimumSize, maximumSize) -> {

            if (maximumSize) {
                stage.setX(screenXmin);
                stage.setX(screenYmin);
                stage.setWidth(screenMaxWidth);
                stage.setHeight(screenMaxHeight);

            }
            if (minimumSize) {
                stage.setX(60);
                stage.setY(60);
                stage.setWidth(WIDTH);
                stage.setHeight(HEIGHT);
            }
        });


        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        //stage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
