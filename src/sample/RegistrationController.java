package sample;

import Helpers.Constants;
import Helpers.FileHelper;
import Helpers.LocationHelper;
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


    public void initialize() {
        checkMaximize();


    }

    @FXML
    void registerAction(ActionEvent event) {

        String userName = userName_txt.getText();
        String name = name_txt.getText();
        String  phone = phone_txt.getText();
        String email = email_txt.getText();
        String pass = pass_txt.getText();
        String confirm = confirm_pass_txt.getText();

        if (userName.equals("")) {
            displayToastMessage("Please Enter Username");
        }else if (name.equals("")){
            displayToastMessage("Please Enter Name");
        }else if (!phone.equals("") && !phone.matches("\\d{10}")) {
            displayToastMessage("Please Enter correct phone number");
        }else if (!email.equals("") && !email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$") ){
            displayToastMessage("Please Enter Valid Email");
        }else if (pass.equals("")){
            displayToastMessage("Please enter password");
        }else if (!pass.equals(confirm)){
            displayToastMessage("Password mismatch");
        }else {
            /*Register user*/
            String role = Constants.USER;
            if (pass.equals("admin1234")) role=Constants.ADMIN;

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
            fileHelper.logInUser(userName);
            System.out.println("Login Done");
            displayToastMessage("You are registered");


            if (role.equalsIgnoreCase(Constants.ADMIN)) {
                loadFrame(LocationHelper.TASK_HISTORY_SCENE);
            }else {
                /*load normal user fram*/
                loadFrame(LocationHelper.USER_CURRENT_TASK);
            }

        }


    }
    @FXML
    void backAction(MouseEvent event) {

        loadFrame(LocationHelper.LOG_IN_SCENE);
    }
}
