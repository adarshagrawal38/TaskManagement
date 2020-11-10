package sample;

import Helpers.*;
import Models.TaskModel;
import Models.WorkBookModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base extends Main  {



    @FXML
    protected StackPane stack_pane;

    @FXML
    private AnchorPane anchorPane_resize;

    @FXML
    protected VBox taskHistory_vbox;

    @FXML
    protected VBox createTask_vbox;


    @FXML
    protected VBox import_vbox;

    @FXML
    protected VBox export_vbox;

    @FXML
    protected VBox user_vbox;

    @FXML
    protected VBox logOut_vbox;


    @FXML
    private VBox userCurrentWork_vbox;

    @FXML
    private VBox userCompletedWork_vbox;
    @FXML
    private JFXButton taskHistory_btn;

    @FXML
    private JFXButton myStage_btn;

    @FXML
    private JFXButton createTask_btn;

    @FXML
    private JFXButton import_btn;

    @FXML
    private JFXButton export_btn;

    @FXML
    private JFXButton user_btn;

    @FXML
    private JFXButton work_btn;

    @FXML
    private JFXButton client_btn;

    @FXML
    private JFXButton report_btn;

    @FXML
    private JFXButton logOut_btn;

    @FXML
    private JFXButton assign_btn;

    @FXML
    private JFXButton references_btn;


    public static int Track_TASK_ID = -1;
    public static boolean BACK = false;

    public DatabaseHelper databaseHelper = new DatabaseHelper();

    public void loadFrame(String frameLocation) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(frameLocation));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void checkMaximize() {

        //anchorPaneResize.setPrefWidth(screenMaxWidth);
        //anchorPaneResize.setPrefHeight(screenMaxHeight);


        if (stage != null) {

            if (stage.isMaximized()) {
                System.out.println("Maximized");
                anchorPane_resize.setPrefWidth(screenMaxWidth);
                anchorPane_resize.setPrefHeight(screenMaxHeight);
            }else {
                anchorPane_resize.setPrefWidth(WIDTH);
                anchorPane_resize.setPrefHeight(HEIGHT);
            }
        }

    }

    public int getIdFromEvent(ActionEvent event) {
        Object o = event.getSource();
        String s = o.toString();
        Pattern p = Pattern.compile(".*id=(\\d+),.*");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            int id = Integer.valueOf(m.group(1));
            return id;
        }
        return -1;
    }


    @FXML
    void navigationTask(ActionEvent event) {
        if (event.getSource() == createTask_btn) {
            System.out.println("Create task clicked");
            loadFrame(LocationHelper.CREATE_NEW_TASK_SCENE);
        }
        if (event.getSource() == taskHistory_btn) {
            System.out.println("Task History vbox");
            loadFrame(LocationHelper.TASK_HISTORY_SCENE);
        }
        if (event.getSource() == import_btn) {
            loadFrame(LocationHelper.IMPORT_SCENE);
            displayToastMessage("Go to history page then import");
        }
        if (event.getSource() == export_btn) {
            displayToastMessage("Go to history page then export");
        }
        if (event.getSource() == user_btn) {
            loadFrame(LocationHelper.USER_SCENE);
        }
        if (event.getSource() == logOut_btn) {
            loadFrame(LocationHelper.LOG_IN_SCENE);
            FileHelper fileHelper = new FileHelper();
            USERNAME = "";
            //fileHelper.logout();
        }
        if (event.getSource() == myStage_btn) {
            loadFrame(LocationHelper.USER_CURRENT_TASK);
        }
        if (event.getSource() == work_btn) {
            loadFrame(LocationHelper.WORK);
        }
        if (event.getSource() == client_btn) {
            loadFrame(LocationHelper.CLIENT);
        }
        if (event.getSource() == report_btn) {
            loadFrame(LocationHelper.REPORT);
        }
        if (event.getSource() == references_btn) {
            loadFrame(LocationHelper.REFERENCES);
        }

    }
    public void disablePreviousDate(JFXDatePicker datePicker) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
    }

    public void displayToastMessage(String message) {

        JFXSnackbar toastMessage = new JFXSnackbar(stack_pane);
        //toastMessage.setStyle("-fx-text-fill: #FFF;-fx-border-radius: 3em;-fx-background-color:#1620A1;");
        toastMessage.show(message, Constants.TOST_DURATION);
    }


}
