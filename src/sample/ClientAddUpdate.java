package sample;

import Helpers.ClientDataBaseHelper;
import Helpers.IconHelper;
import Helpers.TableHelper;
import Helpers.WorkDataBaseHelper;
import Models.WorkModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.print.attribute.standard.Severity;
import javax.xml.validation.Validator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ClientAddUpdate extends Client {
    @FXML
    private JFXTextField code_txt;

    @FXML
    private JFXTextField fileName_txt;

    @FXML
    private JFXTextField group_txt;

    @FXML
    private JFXTextField person1_txt;

    @FXML
    private JFXTextField phone1_txt;

    @FXML
    private JFXTextField email1_txt;

    @FXML
    private JFXTextField email2_txt;

    @FXML
    private JFXTextField person2_txt;

    @FXML
    private JFXTextField phone2_txt;

    @FXML
    private JFXListView<JFXCheckBox> work_listView;

    ClientDataBaseHelper clientDataBaseHelper = new ClientDataBaseHelper();
    ArrayList<WorkModel> workModels = new ArrayList<>();

    public void initialize() {
        checkMaximize();

        code_txt.getValidators().add(requiredValidator());
        fileName_txt.getValidators().add(requiredValidator());
        group_txt.getValidators().add(requiredValidator());
        person1_txt.getValidators().add(requiredValidator());
        phone1_txt.getValidators().add(requiredValidator());

        /*Load Work*/
        workModels = clientDataBaseHelper.getWorks();

        for (WorkModel workModel: workModels) {
            JFXCheckBox workCheckBox = new JFXCheckBox(workModel.getWorkDes());
            work_listView.getItems().add(workCheckBox);
        }
        if (updateClient!=null) {
            code_txt.setText(updateClient.getClientCode());
            fileName_txt.setText(updateClient.getFileName());
            group_txt.setText(updateClient.getGroup());
            email1_txt.setText(updateClient.getEmail1());
            email2_txt.setText(updateClient.getEmail2());
            phone1_txt.setText(updateClient.getPhone1());
            phone2_txt.setText(updateClient.getPhone2());
            person1_txt.setText(updateClient.getPerson1());
            person2_txt.setText(updateClient.getPerson2());
            for (WorkModel workModel: updateClient.getClientWork()) {
                for (JFXCheckBox workCB: work_listView.getItems()) {
                    if (workCB.getText().equalsIgnoreCase(workModel.getWorkDes())) {
                        workCB.setSelected(true);
                    }
                }
            }
        }

    }
    @FXML
    void addOrUpdateAction(ActionEvent event) {
        code_txt.validate();fileName_txt.validate();group_txt.validate();person1_txt.validate();phone1_txt.validate();

        /*check phone number length*/
        String code = code_txt.getText();
        String fileName = fileName_txt.getText();
        String group = group_txt.getText();
        String person1 = person1_txt.getText();
        String person2 = person2_txt.getText();
        String phone1 = phone1_txt.getText();
        String phone2 = phone2_txt.getText();
        String email1 = email1_txt.getText();
        String email2 = email2_txt.getText();
        if (!phone1.matches("\\d{10}"))  {
            displayToastMessage("Phone Number Should contain 10 digits");
        }
        else if (!phone2.equals("") && !phone2.matches("\\d{10}")) {
            displayToastMessage("Phone Number Should contain 10 digits");
        }
        else if (!email1.equals("") && !email1.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
            displayToastMessage("Invalid Email");
        }
        else if (!email2.equals("") && !email2.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
            displayToastMessage("Invalid Email");
        }else {

            if (updateClient != null) {
                /*Delete prev client*/
                int clientId = updateClient.getClientId();
                String query = "delete from client where client_id="+clientId;
                clientDataBaseHelper.insertQuery(query);
            }
            /*make new client*/
            /*Check if client code exist*/
            String checkQuery = "select * from client where code = '"+code+"'";
            ResultSet rs = databaseHelper.fetchQuery(checkQuery);
            try{
                while (rs.next()){
                    displayToastMessage("Client Code Exists!");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String query = "INSERT INTO `client`(`code`, `file_name`, `group_name`, `conctact_person_1`, `conctact_person_2`, `contact_no_1`, `contact_no_2`, `contact_email_1`, `contact_email_2`) VALUES ('"+
                    code+"', '"+fileName+"', '"+group+"', '"+person1+"', '"+person2+"', '"+phone1+"', '"+phone2+"', '"+email1+"', '"+email2+"')";
            clientDataBaseHelper.insertQuery(query);
            int clientId = clientDataBaseHelper.getRecentDataId("client", "client_id");
            for (int i=0;i<workModels.size();i++) {
                if (work_listView.getItems().get(i).isSelected()) {
                    int workId = workModels.get(i).getWork_id();
                    query = "INSERT INTO `client_work`(`client_id`, `work_id`) VALUES("+clientId+", "+workId+")";
                    clientDataBaseHelper.insertQuery(query);
                }
            }
            code_txt.setText("");
            fileName_txt.setText("");
            group_txt.setText("");
            code_txt.setText("");
            person1_txt.setText("");
            person2_txt.setText("");
            email1_txt.setText("");
            email2_txt.setText("");
            phone1_txt.setText("");
            phone2_txt.setText("");
            work_listView.getItems().clear();
            for (WorkModel workModel: workModels) {
                JFXCheckBox workCheckBox = new JFXCheckBox(workModel.getWorkDes());
                work_listView.getItems().add(workCheckBox);
            }
            if (updateClient==null) {
                displayToastMessage("Client Added");
            }else {
                displayToastMessage("Client Updated");
            }

        }

    }
    public static RequiredFieldValidator requiredValidator(){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required!");
        ImageView icon = new IconHelper().getIcon(IconHelper.ICON_ERROR);
        validator.setIcon(icon);
        return validator;
    }


}
