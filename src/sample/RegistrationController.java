package sample;

import Helpers.Constants;
import Helpers.FileHelper;
import Helpers.LocationHelper;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationController extends Base {


    @FXML
    private JFXTextField userName_txt;

    @FXML
    private JFXTextField name_txt;

    @FXML
    private JFXTextField phone_txt;

    @FXML
    private JFXTextField email_txt;

    @FXML
    private JFXPasswordField pass_txt;

    @FXML
    private JFXPasswordField confirm_pass_txt;

    @FXML
    private JFXComboBox<String> role_cmb;


    public void initialize() {
        checkMaximize();
        role_cmb.getItems().add("Admin");
        role_cmb.getItems().add("User");
    }

    @FXML
    void registerAction(ActionEvent event) {

        String userName = userName_txt.getText();
        String name = name_txt.getText();
        String  phone = phone_txt.getText();
        String email = email_txt.getText();
        String pass = pass_txt.getText();
        String confirm = confirm_pass_txt.getText();
        String role = role_cmb.getSelectionModel().getSelectedItem();

        if (userName.equals("")) {
            displayToastMessage("Please Enter Username");
        }else if (name.equals("")){
            displayToastMessage("Please Enter Name");
        }else if (role == null) {
            displayToastMessage("Please select role");
        }
        else if (!phone.equals("") && !phone.matches("\\d{10}")) {
            displayToastMessage("Please Enter correct phone number");
        }else if (!email.equals("") && !email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$") ){
            displayToastMessage("Please Enter Valid Email");
        }else if (pass.equals("")){
            displayToastMessage("Please enter password");
        }else if (!pass.equals(confirm)){
            displayToastMessage("Password mismatch");
        }else {
            /*Register user*/
            role = role.toLowerCase();
            /*Check username existance*/
            String checkQuery = "select * from user where user_name = '" + userName + "'";
            ResultSet rs = databaseHelper.fetchQuery(checkQuery);

            try {
                if (rs.next()) {
                    displayToastMessage("UserName Already taken! Please Try Another");
                    return;
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            String query = "INSERT INTO `user`( `user_name`, `name`, `phone`, `email`, `password`, `role`) VALUES ('" +
                    userName + "', '"+name + "', '"+phone + "', '"+email + "', '"+pass +"', '"+role+"')";
            databaseHelper.insertQuery(query);

            System.out.println("DataBase Entry done");
            FileHelper fileHelper =new FileHelper();
            //fileHelper.logInUser("Adaarsh");
            //fileHelper.logInUser(userName);
            System.out.println("Login Done");
            displayToastMessage("You are registered");

            USERNAME= userName;

            if (role.equalsIgnoreCase(Constants.ADMIN)) {
                IS_ADMIN = true;
                loadFrame(LocationHelper.TASK_HISTORY_SCENE);
            }else {
                /*load normal user fram*/
                IS_ADMIN = false;
                loadFrame(LocationHelper.USER_CURRENT_TASK);
            }

        }


    }
    @FXML
    void backAction(MouseEvent event) {

        loadFrame(LocationHelper.LOG_IN_SCENE);
    }
}
