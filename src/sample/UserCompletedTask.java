package sample;

import Helpers.*;
import Models.TaskModel;
import Models.WorkBookModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCompletedTask extends Base {




    @FXML
    protected Label userName_txt;

    @FXML
    private ProgressBar progressBar;


    @FXML
    private VBox tableVbox;

    @FXML
    private JFXListView<HBox> listView;



    ArrayList<TaskModel> tableRow = new ArrayList<>();
    ArrayList<Integer> widthList = new ArrayList<>();

    String userName = "";
    public void initialize() {
        checkMaximize();
        FileHelper fileHelper = new FileHelper();
        userName = fileHelper.getUserName();
        userName_txt.setText(userName);

        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);

        progressBar.setStyle(Constants.PROGRESS_BAR);

        ArrayList<String> columnHeader = new ArrayList<>();
        columnHeader.add("Sno.");
        columnHeader.add("Code");
        columnHeader.add("File Name");
        columnHeader.add("Work");
        columnHeader.add("Stage");
        columnHeader.add("Due Date");

        HBox columnHeader_Hbox = TableHelper.getColumnHeader(columnHeader, widthList);
        tableVbox.getChildren().add(columnHeader_Hbox);

        reloadData();
    }

    public void reloadData() {

        tableRow.clear();
        try{

            String query = "SELECT * FROM `work_book` JOIN task USING(task_id) where assigned_to ='"+userName + "' and stage_status = '"+Constants.COMPLETED + "' order by task_id, stage_num";
            System.out.println(query);
            ResultSet resultSet = databaseHelper.fetchQuery(query);
            while (resultSet.next()) {
                TaskModel taskModel = new TaskModel(
                        resultSet.getInt("task_id"),
                        resultSet.getString("client_code"),
                        resultSet.getString("file_Name"),
                        resultSet.getString("initiator"),

                        resultSet.getString("work_des"),
                        0,
                        resultSet.getString("period"),
                        resultSet.getString("year"),
                        resultSet.getString("task_priority"),
                        resultSet.getDate("task_due_date").toString(),
                        resultSet.getInt("task_priority_position"),
                        resultSet.getString("task_status"),
                        0);

                String date = DateFormatChange.changeForMatTo_DD_MM_YY(taskModel.getDueDate());
                taskModel.setDueDate(date);
                System.out.println("Due date: " + date);


                    /*Now get work book data*/
                WorkBookModel workBookModel = new WorkBookModel();
                workBookModel.setStageNumber(resultSet.getInt("stage_num"));
                workBookModel.setStageDes(resultSet.getString("stage_des"));
                workBookModel.setDueDate(resultSet.getDate("due_date").toString());
                workBookModel.setStageStatus(resultSet.getString("stage_status"));
                workBookModel.setAssigenedTo(resultSet.getString("assigned_to"));
                workBookModel.setWorkBookId(resultSet.getInt("wb_id"));
                Date compdate = resultSet.getDate("completed_date");
                if (compdate!=null) {
                    workBookModel.setCompletedDate(DateFormatChange.changeForMatTo_DD_MM_YY(compdate.toString()));
                }

                date = DateFormatChange.changeForMatTo_DD_MM_YY(workBookModel.getDueDate());
                workBookModel.setDueDate(date);

                ArrayList<WorkBookModel> workBookModels = new ArrayList<>();
                workBookModels.add(workBookModel);

                taskModel.setTaskWorkBook(workBookModels);
                tableRow.add(taskModel);
            }


            tableRow.sort(new TaskHistoryController());
            populateListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void setProgressBar() {
        try{
            String query = "Select count(*) as count from work_book where assigned_to = '" + userName + "'";
            ResultSet rs = databaseHelper.fetchQuery(query);
            int total = 1, comp=1;
            if (rs.next()) total = rs.getInt("count");

            query = query + " and stage_status = '"+Constants.COMPLETED + "' ";
            rs = databaseHelper.fetchQuery(query);
            if (rs.next()) comp = rs.getInt("count");

            double progress = (double) comp / total;
            progressBar.setProgress(progress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void pendCompAction(ActionEvent event) {

    }

    private void populateListView() {
        listView.getItems().clear();
        int index = 0;
        setProgressBar();

        for (TaskModel taskModel: tableRow) {
            System.out.println("TaskCode " + taskModel.getFileName() + " Priority: " + taskModel.getTaskPriorityPosition());
            int sno = index + 1;
            ArrayList<String> strings = new ArrayList<>();
            strings.add(0, String.valueOf(sno));
            strings.add(taskModel.getClientCode());
            strings.add(taskModel.getFileName());
            strings.add(taskModel.getWorkDescription());
            String stageDes = taskModel.getTaskWorkBook().get(0).getStageNumber() + " - " +  taskModel.getTaskWorkBook().get(0).getStageDes();
            strings.add(stageDes);
            strings.add(taskModel.getTaskWorkBook().get(0).getDueDate());

            HBox box = TableHelper.getTableRow(strings, widthList);
            box.setId(String.valueOf(index));

            box.setStyle(Constants.TABLE_ROW_COLOR_COMPLETED);

            listView.getItems().add(box);
            index++;
        }
        if (tableRow.size()==0) {
            displayToastMessage("Sorry! No Task To Display...");
        }
    }

    @FXML
    void refreshAction(ActionEvent event) {

        reloadData();
    }
}


