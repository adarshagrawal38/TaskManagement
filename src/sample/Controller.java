package sample;

import Helpers.Constants;
import Helpers.FileHelper;
import Helpers.LocationHelper;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller extends Base{

    @FXML
    private AnchorPane anchorPane_resize;

    @FXML
    private JFXTextField userName_txt;

    @FXML
    private JFXPasswordField pass_txt;

    @FXML
    private Label forgotPass_label;

    public void initialize() {
        checkMaximize();
        FileHelper fileHelper = new FileHelper();
        if (fileHelper.isLogedIn()) {
            USERNAME = fileHelper.getUserName();
            String role = databaseHelper.getUserRole(USERNAME);
            if (role.equals(Constants.ADMIN)) {
                loadFrame(LocationHelper.TASK_HISTORY_SCENE);
            }else if (role.equals(Constants.USER)) {
                /*load user frame*/
            }
        }

    }

    @FXML
    void forgotPasswordAction(MouseEvent event) {

        forgotPasswordDailog();
    }

    @FXML
    void logInAction(ActionEvent event) {
        String userName = userName_txt.getText();
        String password = pass_txt.getText();

        String result = databaseHelper.validateUser(userName, password);

        if (!result.equals("")) {
            FileHelper fileHelper = new FileHelper();
            fileHelper.logInUser(userName);

            if (result.equals(Constants.ADMIN)){
                loadFrame(LocationHelper.TASK_HISTORY_SCENE);
            }else{
                /*login normal user*/
                loadFrame(LocationHelper.USER_CURRENT_TASK);
            }

        }else{
            displayToastMessage("Invalid username or password");
        }


    }

    @FXML
    void registerAction(ActionEvent event) {

        loadFrame(LocationHelper.REGISTER_SCENE);
    }

    public void forgotPasswordDailog() {


        String displayInfo[] = {""};

        Label label[] = new Label[displayInfo.length];

        for (int i = 0; i < displayInfo.length; i++) {
            label[i] = new Label(displayInfo[i]);
            label[i].setFont(new Font("Segoi UI", 15));
        }

        Label label0 = new Label("UserName");
        label0.setFont(new Font("Segoi UI", 15));

        JFXTextField userNameTxt = new JFXTextField();
        userNameTxt.setPromptText("Username");
        userNameTxt.setText(userName_txt.getText());


        Label label1 = new Label("Please enter root password");
        label1.setFont(new Font("Segoi UI", 15));
        JFXPasswordField rootPass = new JFXPasswordField();
        rootPass.setPromptText("Root Password");

        Label label2 = new Label("New Password");
        label2.setFont(new Font("Segoi UI", 15));

        JFXPasswordField newPassword = new JFXPasswordField();
        newPassword.setPromptText("New Password");

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, userNameTxt, label1, rootPass, label2, newPassword);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Confirm");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String userName = userNameTxt.getText();
                if (userName.equals("")) {
                    displayToastMessage("Please First Enter user name");
                }else{

                    String rp = rootPass.getText();
                    String np = newPassword.getText();
                    if (rp.equals(Constants.ADMIN_PASSWORD)) {
                        /*Updare new pass*/
                        String query = "update user set password = '" + np + "' where user_name = '" + userName + "' ";
                        System.out.println(query);
                        databaseHelper.insertQuery(query);
                        displayToastMessage(userName + " password updated");
                    }else {
                        displayToastMessage("Root password incorrect");
                    }

                }

                dialog.close();
            }
        });


        dialog.show();
    }
}
