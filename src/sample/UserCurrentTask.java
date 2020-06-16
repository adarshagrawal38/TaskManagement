package sample;

import Helpers.*;
import Models.TaskModel;
import Models.WorkBookModel;
import com.jfoenix.controls.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCurrentTask extends Base {




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

    @FXML
    private JFXComboBox<String> pendingCmb;

    public void initialize() {
        checkMaximize();
        FileHelper fileHelper = new FileHelper();
        userName = fileHelper.getUserName();
        //userName_txt.setText(userName);

        List<String> cmbList = new ArrayList<>();
        cmbList.add(Constants.PENDING);
        cmbList.add(Constants.COMPLETED);

        pendingCmb.getItems().addAll(cmbList);
        pendingCmb.getSelectionModel().select(Constants.PENDING);
        widthList.add(Constants.COLUMN_WIDTH-40);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);

        progressBar.setStyle(Constants.PROGRESS_BAR);

        ArrayList<String> columnHeader = new ArrayList<>();
        columnHeader.add("Sno.");
        columnHeader.add("Code");
        columnHeader.add("File Name");
        columnHeader.add("Work");
        columnHeader.add("Stage");
        columnHeader.add("Due Date");
        columnHeader.add("Remarks");

        HBox columnHeader_Hbox = TableHelper.getColumnHeader(columnHeader, widthList);
        tableVbox.getChildren().add(columnHeader_Hbox);

        reloadData();
    }

    @FXML
    void pendCompAction(ActionEvent event) {
        reloadData();
    }


    public void reloadData() {

        tableRow.clear();
        String selectedTaskStatus = pendingCmb.getSelectionModel().getSelectedItem();
        try{
            String query = "select distinct(task_id) from work_book where assigned_to = '" + userName + "'";
            ArrayList<Integer> assignedTask = new ArrayList<>();
            ResultSet rs = databaseHelper.fetchQuery(query);
            while (rs.next()) {
                assignedTask.add(rs.getInt("task_id"));
            }

            for (Integer i: assignedTask) {

                System.out.println("TaskId: " + i);
                query = "SELECT * FROM `work_book` JOIN task USING(task_id) where task_id ="+i + " and stage_status = '"+selectedTaskStatus+"' order by task_id, stage_num LIMIT 1 ";
                System.out.println(query);
                ResultSet resultSet = databaseHelper.fetchQuery(query);
                if (resultSet.next()) {
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
                    workBookModel.setRemarks(resultSet.getString("remark"));
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

            }

            /*Stage should be assinged to current user*/
            ArrayList<TaskModel> filltered = new ArrayList<>();
            for (TaskModel taskModel: tableRow) {
                if (taskModel.getTaskWorkBook().get(0).getAssigenedTo().equalsIgnoreCase(userName)) {
                    filltered.add(taskModel);
                }
            }
            tableRow.clear();
            tableRow.addAll(filltered);

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
            strings.add(taskModel.getTaskWorkBook().get(0).getRemarks());

            HBox box = TableHelper.getTableRow(strings, widthList);
            box.setId(String.valueOf(index));


            IconHelper  iconHelper = new IconHelper();
            JFXButton completedButton = ControlsHelper.getTableButton();
            completedButton.setStyle(Constants.TRANPARENT_COLOR);
            completedButton.setGraphic(iconHelper.getIcon(IconHelper.ICON_COMPLETE));
            completedButton.setId(String.valueOf(index));
            Tooltip tooltip = new Tooltip();
            tooltip. setText( "Task Completed");
            completedButton.setTooltip(tooltip);
            box.getChildren().add(completedButton);

            JFXButton commentBtn = ControlsHelper.getTableButton();
            commentBtn.setId(String.valueOf(index));
            commentBtn.setGraphic(iconHelper.getIcon(IconHelper.ICON_COMMENT));
            box.getChildren().add(commentBtn);

            commentBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Object o =event.getSource();
                    String s = o.toString();
                    Pattern p = Pattern.compile(".*id=(\\d+),.*");
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        int id = Integer.valueOf(m.group(1));
                        commentDaiLog(id);
                    }
                }
            });

            completedButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Object o =event.getSource();
                    String s = o.toString();
                    Pattern p = Pattern.compile(".*id=(\\d+),.*");
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        int id = Integer.valueOf(m.group(1));

                        completedDialog(id);

                        //removeItemFromList(Integer.valueOf(m.group(1)));
                    }
                }
            });

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

    public void commentDaiLog(int rowId) {
        Label label0 = new Label("Please enter remarks");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        JFXTextField txt = new JFXTextField();
        txt.setPromptText("Comments");

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, txt);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Submit");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String str = txt.getText();
                int wb_id = tableRow.get(rowId).getTaskWorkBook().get(0).getWorkBookId();
                String query = "update work_book set remark='"+str+ "' where wb_id = " + wb_id;
                System.out.println(query);
                databaseHelper.insertQuery(query);
                tableRow.get(rowId).getTaskWorkBook().get(0).setRemarks(str);
                populateListView();
                dialog.close();

                displayToastMessage("Remarks Recorded");
            }
        });
        dialog.show();

    }
    public void completedDialog(int rowId) {
        Label label0 = new Label("Did you complete ");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        Label label = new Label(tableRow.get(rowId).getTaskWorkBook().get(0).getStageDes());
        label.setFont(new Font("Segoi UI", Constants.LABEL_SIZE*2));
        label.setStyle("-fx-font-weight: bold");


        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, label);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Completed");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int workBookId = tableRow.get(rowId).getTaskWorkBook().get(0).getWorkBookId();
                String query = "update work_book set completed_date = CURDATE(), stage_status = '"+ Constants.COMPLETED + "'  where wb_id = " + workBookId;
                databaseHelper.insertQuery(query);
                databaseHelper.updateTaskStatus(tableRow.get(rowId).getTaskId());
                tableRow.remove(rowId);
                populateListView();
                dialog.close();

                displayToastMessage("Stage Completed");
            }
        });
        dialog.show();

    }
}
