package sample;

import Helpers.Constants;
import Helpers.FileHelper;
import Models.WorkBookModel;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewTaskController extends Base {

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


    List<Integer> workId = new ArrayList<>();
    @FXML
    void createTaskAction(ActionEvent event) throws SQLException {
        String code = code_txt.getText();
        String fileName = fileName_txt.getText();
        int selectedIndex = work_cmb.getSelectionModel().getSelectedIndex();
        String year = year_txt.getText();
        String period = period_txt.getText();
        String date = datePicker.getValue().toString();
        String priority = "No";

        if (priority_ckb.isSelected())priority = "Yes";

        if (code.equals("") || fileName.equals("") || year.equals("")) {
            displayToastMessage("Please fill necessary details");
            return;
        }

        String userName = new FileHelper().getUserName();
        String work = work_cmb.getSelectionModel().getSelectedItem();
        /*Create task*/
        String query = "INSERT INTO `task`(`client_code`, `file_name`, `Initiator`, `work_des`, `period`, `task_priority`, task_due_date, year) Values ('"+
                code + "', '" + fileName + "', '" + userName + "', '"+work + "', '"+period+"', '"+priority + "', '"+date+"', '" + year + "')";
        databaseHelper.insertQuery(query);

        int taskId = databaseHelper.getRecentDataId("task", "task_id");
        query = "update task set task_priority_position = "+taskId + " where task_id = " + taskId;
        System.out.println(query);

        databaseHelper.insertQuery(query);

        int id = workId.get(work_cmb.getSelectionModel().getSelectedIndex());
        /*take out work info*/
        List<WorkBookModel> workBookModels = new ArrayList<>();

        query = "Select * from stage where work_id = " + id + " order by stage_num";
        ResultSet resultSet = databaseHelper.fetchQuery(query);

        while (resultSet.next()) {
            WorkBookModel workBookModel = new WorkBookModel();
            workBookModel.setStageNumber(resultSet.getInt("stage_num"));
            workBookModel.setStageDes(resultSet.getString("stage_des"));
            workBookModel.setTaskId(taskId);
            workBookModel.setAssigenedTo(userName);
            workBookModel.setDueDate(date);

            workBookModels.add(workBookModel);
        }

        /*Insert work book data*/
        for (WorkBookModel workBookModel: workBookModels) {
            query = "INSERT INTO `work_book`( `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`) VALUES ("+
                    workBookModel.getTaskId() + ", " + workBookModel.getStageNumber() + " ,'" +workBookModel.getStageDes() + "', '" + workBookModel.getAssigenedTo() + "', '" +
                    workBookModel.getDueDate() + "')";
            System.out.println(query);

            databaseHelper.insertQuery(query);
        }

        displayToastMessage("Task Created");
        clearTxt();

        /*update priority position set it to last*/

    }
    public void initialize() {
        checkMaximize();

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
        work_cmb.getSelectionModel().select(0);
        datePicker.setValue(LocalDate.now());

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int nextYear = year + 1;

        String userYear = year + "-" + nextYear%100;
        year_txt.setText(userYear);


    }
    void clearTxt(){
        code_txt.setText("");
        fileName_txt.setText("");
        period_txt.setText("");
        datePicker.setValue(LocalDate.now());
        code_txt.requestFocus();
    }
}
