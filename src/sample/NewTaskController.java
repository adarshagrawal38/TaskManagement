package sample;

import Helpers.ClientDataBaseHelper;
import Helpers.Constants;
import Helpers.FileHelper;
import Models.ClientModel;
import Models.WorkBookModel;
import Models.WorkModel;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class NewTaskController extends TaskHistoryController {

    @FXML
    private JFXTextField code_txt;

    @FXML
    private JFXTextField fileName_txt;

    @FXML
    private JFXComboBox<String> work_cmb;

    @FXML
    private JFXTextField year_txt;

    @FXML
    private JFXTextField period_txt;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXCheckBox priority_ckb;

    @FXML
    protected Label userName_txt;
    @FXML
    private JFXComboBox<String> sub_work_cmb;

    @FXML
    private JFXListView<JFXCheckBox> clientList;

    @FXML
    private JFXTabPane task_tabPane;

    @FXML
    private Tab assignTask_tab;
    @FXML
    private Tab creatTask_tab;

    @FXML
    private JFXComboBox<String> init_cmb;

    List<Integer> workId = new ArrayList<>();
    ArrayList<ClientModel> allClientModel = new ArrayList<>();
    ArrayList<ClientModel> clientSpecificWorkList = new ArrayList<>();
    ArrayList<ClientModel> fillteredList = new ArrayList<>();
    ClientDataBaseHelper clientDataBaseHelper = new ClientDataBaseHelper();
    ArrayList<String> subWorkIds = new ArrayList<>();

    public void initialize() {
        checkMaximize();
        sub_work_cmb.setDisable(true);
        disablePreviousDate(datePicker);

        String userName = "";
        FileHelper fileHelper = new FileHelper();
        userName = USERNAME;
        userName_txt.setText(userName);

        allClientModel = clientDataBaseHelper.getClient();

        for (String s: databaseHelper.getUsers()) {
            if (databaseHelper.getUserRole(s).equalsIgnoreCase("admin")) {
                init_cmb.getItems().add(s);
            }
        }

        try{
            String query = "Select * from work";
            ResultSet rs = databaseHelper.fetchQuery(query);
            while (rs.next()) {
                workId.add(rs.getInt("work_id"));
                work_cmb.getItems().add(rs.getString("work_des"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        datePicker.setValue(LocalDate.now());

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int nextYear = year + 1;

        String userYear = year + "-" + nextYear%100;
        year_txt.setText(userYear);

        task_tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                if (newValue == assignTask_tab) {
                    updateInit();
                }

            }
        });
        //init_cmb.getSelectionModel().select(USERNAME);

        super.initialize();
        if (BACK) {
            BACK = false;
            System.out.println("Back Detected");
            task_tabPane.getSelectionModel().select(1);
        }
    }




    @FXML
    void createTaskAction(ActionEvent event) throws SQLException {
        /*Get all selected task*/

        String year = year_txt.getText();
        if (year.equals("")) {
            displayToastMessage("Please fill year is required");
            return;
        }
        if (sub_work_cmb.getItems().size() > 0) {
            /*Check if use selected sub work or not*/
            int index = sub_work_cmb.getSelectionModel().getSelectedIndex();
            if (index==-1) {
                displayToastMessage("Please select sub work");
                return;
            }
        }
        String initiator = init_cmb.getSelectionModel().getSelectedItem();
        if (initiator == null) {
            displayToastMessage("Please select initiator");
            return;
        }
        ArrayList<ClientModel> selectedClient = new ArrayList<>();

        ArrayList<ClientModel> waringMessageForClient = new ArrayList<>();
        for (ClientModel clientModel: clientSpecificWorkList) {
            if (clientModel.isSelected()) {
                selectedClient.add(clientModel);

                String code = clientModel.getClientCode();
                String fileName = clientModel.getFileName();
                String period = period_txt.getText();
                String date = datePicker.getValue().toString();
                String priority = "No";
                String sub_work_des = "";
                if (priority_ckb.isSelected())priority = "Yes";
                String work = work_cmb.getSelectionModel().getSelectedItem();
                String subWorkId = "";


                String check_task_created_query = "select * from task where file_name='"+fileName+"' and year='"+year+"' and work_des ='"+work+"' ";
                if (sub_work_cmb.getItems().size() > 0){
                    sub_work_des = sub_work_cmb.getSelectionModel().getSelectedItem();
                    subWorkId = subWorkIds.get(sub_work_cmb.getSelectionModel().getSelectedIndex());
                    check_task_created_query+=" and sub_work_des='"+sub_work_des+"'";
                }
                boolean flag = false;
                System.out.println(check_task_created_query);
                ResultSet checkResultSet = databaseHelper.fetchQuery(check_task_created_query);
                if (checkResultSet.next()) {
                    flag = true;
                }

                if (flag) {
                    /*Task already created
                    * Issue a warning*/
                    waringMessageForClient.add(clientModel);
                    System.out.println("Issue warning for " + clientModel.getFileName());
                    continue;
                }

                /*Create task*/
                String query = "INSERT INTO `task`(`client_code`, `file_name`, `Initiator`, `work_des`, `period`, `task_priority`, task_due_date, year, sub_work_des, created_date) Values ('"+
                        code + "', '" + fileName + "', '" + initiator + "', '"+work + "', '"+period+"', '"+priority + "', '"+date+"', '" + year + "','" + sub_work_des + "', '"+LocalDate.now()+"')";
                databaseHelper.insertQuery(query);

                int taskId = databaseHelper.getRecentDataId("task", "task_id");
                query = "update task set task_priority_position = "+taskId + " where task_id = " + taskId;
                System.out.println(query);

                databaseHelper.insertQuery(query);

                int id = workId.get(work_cmb.getSelectionModel().getSelectedIndex());
                /*take out work info*/
                List<WorkBookModel> workBookModels = new ArrayList<>();

                query = "Select * from stage where work_id = " + id;

                if (!subWorkId.equals("")) {
                    query+=" and sub_work_id = " + subWorkId;
                }
                query+=" order by stage_num";
                ResultSet resultSet = databaseHelper.fetchQuery(query);

                while (resultSet.next()) {
                    WorkBookModel workBookModel = new WorkBookModel();
                    workBookModel.setStageNumber(resultSet.getInt("stage_num"));
                    workBookModel.setStageDes(resultSet.getString("stage_des"));
                    workBookModel.setTaskId(taskId);
                    workBookModel.setAssigenedTo(initiator);
                    workBookModel.setDueDate(date);

                    workBookModels.add(workBookModel);
                }

                /*Insert work book data*/
                for (WorkBookModel workBookModel: workBookModels) {
                    query = "INSERT INTO `work_book`( `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`, sub_work_des, assigned_date) VALUES ("+
                            workBookModel.getTaskId() + ", " + workBookModel.getStageNumber() + " ,'" +workBookModel.getStageDes() + "', '" + workBookModel.getAssigenedTo() + "', '" +
                            workBookModel.getDueDate() + "', '"+sub_work_des+"', '"+LocalDate.now()+"')";
                    System.out.println(query);
                    databaseHelper.insertQuery(query);
                }
            }
        }
        if (waringMessageForClient.size() > 0) {
            /*Issuer warnign message*/
            warningDialog(waringMessageForClient);
            return;
        }
        if (selectedClient.size() == 0) {
            displayToastMessage("Please select one or more client ");
            return;
        }
        displayToastMessage("Task Created");
        clearTxt();


        /*update priority position set it to last*/

    }
    public void warningDialog(ArrayList<ClientModel> waringList) {
        Label label0 = new Label("Task Already created for same year for the client");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));



        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));
        boolean flag = false;



        VBox vBox = new VBox(label0);
        ArrayList<JFXCheckBox> checkBoxes = new ArrayList<>();
        for (ClientModel clientModel: waringList) {
            JFXCheckBox jfxCheckBox = new JFXCheckBox(clientModel.getFileName());
            checkBoxes.add(jfxCheckBox);
            vBox.getChildren().add(jfxCheckBox);
        }
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Create Task");
        button.setPrefWidth(200);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int i=0;
                for (JFXCheckBox jfxCheckBox: checkBoxes) {
                    if (jfxCheckBox.isSelected()) {
                        ClientModel clientModel = waringList.get(i);
                        System.out.println("Client : " + clientModel.getFileName() + " is selected");
                        String code = clientModel.getClientCode();
                        String fileName = clientModel.getFileName();
                        String period = period_txt.getText();
                        String date = datePicker.getValue().toString();
                        String priority = "No";
                        String sub_work_des = "";
                        if (priority_ckb.isSelected())priority = "Yes";
                        String work = work_cmb.getSelectionModel().getSelectedItem();
                        String subWorkId = "";
                        String initiator = init_cmb.getSelectionModel().getSelectedItem();
                        String year = year_txt.getText();
                        /*Creat task for client*/
                        String query = "INSERT INTO `task`(`client_code`, `file_name`, `Initiator`, `work_des`, `period`, `task_priority`, task_due_date, year, sub_work_des, created_date) Values ('"+
                                code + "', '" + fileName + "', '" + initiator + "', '"+work + "', '"+period+"', '"+priority + "', '"+date+"', '" + year + "','" + sub_work_des + "', '"+LocalDate.now()+"')";
                        databaseHelper.insertQuery(query);

                        int taskId = databaseHelper.getRecentDataId("task", "task_id");
                        query = "update task set task_priority_position = "+taskId + " where task_id = " + taskId;
                        System.out.println(query);

                        databaseHelper.insertQuery(query);
                        int id = 0;
                        try{
                            String q1 = "Select * from work where work_des = '" + work+"'";
                            ResultSet rs = databaseHelper.fetchQuery(q1);
                            while (rs.next()) {
                                id = rs.getInt("work_id");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        /*take out work info*/
                        List<WorkBookModel> workBookModels = new ArrayList<>();

                        query = "Select * from stage where work_id = " + id;

                        if (!subWorkId.equals("")) {
                            query+=" and sub_work_id = " + subWorkId;
                        }
                        query+=" order by stage_num";
                        ResultSet resultSet = databaseHelper.fetchQuery(query);

                        try {
                            while (resultSet.next()) {
                                WorkBookModel workBookModel = new WorkBookModel();
                                workBookModel.setStageNumber(resultSet.getInt("stage_num"));
                                workBookModel.setStageDes(resultSet.getString("stage_des"));
                                workBookModel.setTaskId(taskId);
                                workBookModel.setAssigenedTo(initiator);
                                workBookModel.setDueDate(date);

                                workBookModels.add(workBookModel);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                /*Insert work book data*/
                        for (WorkBookModel workBookModel: workBookModels) {
                            query = "INSERT INTO `work_book`( `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`, sub_work_des, assigned_date) VALUES ("+
                                    workBookModel.getTaskId() + ", " + workBookModel.getStageNumber() + " ,'" +workBookModel.getStageDes() + "', '" + workBookModel.getAssigenedTo() + "', '" +
                                    workBookModel.getDueDate() + "', '"+sub_work_des+"', '"+LocalDate.now()+"')";
                            System.out.println(query);
                            databaseHelper.insertQuery(query);
                        }
                    }
                    i++;
                }

                dialog.close();
                clearTxt();

                displayToastMessage("Selected Task Created");
            }
        });
        dialog.show();

    }

    void clearTxt(){
        fileName_txt.setText("");
        period_txt.setText("");
        datePicker.setValue(LocalDate.now());
        clientList.getItems().clear();
        work_cmb.getSelectionModel().clearSelection();
        sub_work_cmb.setDisable(true);
    }
    @FXML
    void fillterEvent(KeyEvent event) {
        /*Fillter file name*/
        System.out.println(fileName_txt.getText().toLowerCase());
        fillteredList.clear();
        String fileName = fileName_txt.getText().toLowerCase();
        for (ClientModel clientModel: clientSpecificWorkList) {
            if (clientModel.getFileName().toLowerCase().contains(fileName)) {
                fillteredList.add(clientModel);
            }
        }
        taskHistory_fillterEvent(event);
        populateList(fillteredList);
    }

    @FXML
    void taskAssigmentAction(ActionEvent event) {

    }
    @FXML
    void workSelectionAction(ActionEvent event) {
        /*Load sub work*/
        String work_des = work_cmb.getSelectionModel().getSelectedItem();

        String query = "SELECT * FROM `sub_work` NATURAL JOIN work where work_des='"+work_des+"'";
        ResultSet rs = databaseHelper.fetchQuery(query);
        sub_work_cmb.setDisable(true);
        sub_work_cmb.getItems().clear();
        try {
            while (rs.next()) {
                subWorkIds.add(rs.getInt("sub_work_id")+"");
                sub_work_cmb.getItems().add(rs.getString("sub_work_des"));
                sub_work_cmb.setDisable(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clientSpecificWorkList.clear();
        System.out.println("work box cmb");
        for (ClientModel clientModel: allClientModel) {
            for (WorkModel workModel: clientModel.getClientWork()) {
                if (workModel.getWorkDes().equals(work_des)){
                    clientModel.setSelected(false);
                    clientSpecificWorkList.add(clientModel);
                    break;
                }
            }
        }
        populateList(clientSpecificWorkList);

    }

    public void populateList(ArrayList<ClientModel> populateList) {
        Collections.sort(populateList, new Comparator<ClientModel>() {
            @Override
            public int compare(ClientModel o1, ClientModel o2) {
                int i1 = 0;
                int i2 = 0;
                if (o1.isSelected())i1=1;
                if (o2.isSelected())i2=1;
                return i2 - i1;
            }
        });
        clientList.getItems().clear();
        for (ClientModel clientModel: populateList) {
            JFXCheckBox cb = new JFXCheckBox(clientModel.getFileName());
            cb.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Check box selected " + cb.isSelected() + " fileName: " +clientModel.getFileName());
                    clientModel.setSelected(cb.isSelected());
                }
            });
            cb.setSelected(clientModel.isSelected());
            clientList.getItems().add(cb);
        }
    }

}
