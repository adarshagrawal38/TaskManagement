package sample;

import Helpers.*;
import Models.TaskModel;
import Models.WorkBookModel;
import com.jfoenix.controls.*;
import com.sun.org.omg.CORBA.ContextIdSeqHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrackStage extends Base {

    @FXML
    private VBox tableVbox;

    @FXML
    private Label fileCode_lb;

    @FXML
    private Label filename_lb;

    @FXML
    private Label work_lb;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private JFXListView<HBox> listView;

    ArrayList<Integer> widthList = new ArrayList<>();
    List<WorkBookModel> tableRows = new ArrayList<>();

    String currentDirectory = "";

    int taskId = 1;
    String userNmae = "";
    ArrayList<String> userList;
    boolean isAdmin = false;
    String initiator = "";
    public void initialize() {
        checkMaximize();
        taskId = Track_TASK_ID;
        currentDirectory = System.getProperty("user.dir");
        currentDirectory+="\\src\\sample\\";
        System.out.println("The current working directory is " + currentDirectory);

        ArrayList<String> columnHeader = new ArrayList<>();
        columnHeader.add("Stage Num");
        columnHeader.add("Description");
        columnHeader.add("Assigned T0");
        columnHeader.add("Due Date");
        columnHeader.add("Status");
        columnHeader.add("Remarks");

        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);

        FileHelper fileHelper = new FileHelper();
        userNmae = fileHelper.getUserName();
        isAdmin = databaseHelper.isAdmin(userNmae);

        HBox columnHeader_Hbox = TableHelper.getColumnHeader(columnHeader, widthList);
        tableVbox.getChildren().add(0, columnHeader_Hbox);


        userList = databaseHelper.getUsers();

        String query = "Select * from task where task_id = " + taskId;
        System.out.println(query);
        ResultSet resultSet = databaseHelper.fetchQuery(query);
        try {
            if (resultSet.next()) {
                fileCode_lb.setText(resultSet.getString("client_code"));
                filename_lb.setText(resultSet.getString("file_name"));
                work_lb.setText(resultSet.getString("work_des"));
                initiator = resultSet.getString("initiator");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        populateListView();
    }
    public void loadStageData() {

        tableRows.clear();
        tableRows.addAll(databaseHelper.getWorkBook(taskId));
    }
    @FXML
    void backAction(MouseEvent event) {

    }

    @FXML
    void listItemSelected(MouseEvent event) {

    }

    private void populateListView() {
        loadStageData();
        listView.getItems().clear();
        int index = 0;

        Collections.sort(tableRows, new Comparator<WorkBookModel>() {
            @Override
            public int compare(WorkBookModel o1, WorkBookModel o2) {
                return o1.getStageNumber() - o2.getStageNumber();
            }
        });

        int numberOfTaskCompleted = 0;
        for (WorkBookModel workBookModel: tableRows) {
            if (!workBookModel.isPending()) numberOfTaskCompleted++;
        }
        double percentTageCompleted =(double) numberOfTaskCompleted / tableRows.size();
        progressBar.setProgress(percentTageCompleted);
        progressBar.setStyle(Constants.PROGRESS_BAR);
        int start =0;
        int end = tableRows.size()-1;

        try{
            for (WorkBookModel workBookModel: tableRows) {

                ArrayList<String> strings = workBookModel.getStrings();

                HBox box = TableHelper.getTableRow(strings, widthList);
                box.setId(String.valueOf(index));

                IconHelper iconHelper = new IconHelper();

                ImageView upImageView = iconHelper.getIcon(IconHelper.ICON_UP);
                ImageView downImageView = iconHelper.getIcon(IconHelper.ICON_DOWN);



                JFXButton upStagenumber = ControlsHelper.getTableButton();
                upStagenumber.setGraphic(upImageView);
                upStagenumber.setId(String.valueOf(index));
                box.getChildren().add( upStagenumber);
                if (index==start)
                    upStagenumber.setDisable(true);

                JFXButton downStageNum = ControlsHelper.getTableButton();
                downStageNum.setGraphic(downImageView);
                downStageNum.setId(String.valueOf(index));
                box.getChildren().add( downStageNum);
                if (index==end)
                downStageNum.setDisable(true);

                ImageView editImage = iconHelper.getIcon(Constants.ICON_EDIT);
                JFXButton editButton = ControlsHelper.getTableButton();
                editButton.setId(String.valueOf(index));
                editButton.setGraphic(editImage);
                box.getChildren().add(editButton);

                JFXButton commentBtn = ControlsHelper.getTableButton();
                commentBtn.setId(String.valueOf(index));
                commentBtn.setGraphic(iconHelper.getIcon(IconHelper.ICON_COMMENT));
                box.getChildren().add(commentBtn);


                ImageView deleteImage = iconHelper.getIcon(Constants.ICON_DELETE);

                JFXButton deleteButton = ControlsHelper.getTableButton();
                deleteButton.setId(String.valueOf(index));
                deleteButton.setGraphic(deleteImage);
                if (isAdmin){
                    box.getChildren().add(deleteButton);
                }
                upStagenumber.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Object o =event.getSource();
                        String s = o.toString();
                        Pattern p = Pattern.compile(".*id=(\\d+),.*");
                        Matcher m = p.matcher(s);
                        if (m.matches()) {
                            int id = Integer.valueOf(m.group(1));
                            WorkBookModel currentStage = tableRows.get(id);
                            WorkBookModel upStage = tableRows.get(id - 1);
                            currentStage.decrementStage();
                            upStage.incrementStage();

                            UpdateHelper updateHelper = new UpdateHelper();
                            updateHelper.updateStageNumber(currentStage);
                            updateHelper.updateStageNumber(upStage);
                            populateListView();


                        }
                    }
                });
                downStageNum.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Object o =event.getSource();
                        String s = o.toString();
                        Pattern p = Pattern.compile(".*id=(\\d+),.*");
                        Matcher m = p.matcher(s);
                        if (m.matches()) {
                            int id = Integer.valueOf(m.group(1));
                            WorkBookModel currentStage = tableRows.get(id);
                            WorkBookModel belowStage = tableRows.get(id + 1);
                            currentStage.incrementStage();
                            belowStage.decrementStage();

                            UpdateHelper updateHelper = new UpdateHelper();
                            updateHelper.updateStageNumber(currentStage);
                            updateHelper.updateStageNumber(belowStage);
                            populateListView();


                        }
                    }
                });

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
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Object o =event.getSource();
                        String s = o.toString();
                        Pattern p = Pattern.compile(".*id=(\\d+),.*");
                        Matcher m = p.matcher(s);
                        if (m.matches()) {
                            int id = Integer.valueOf(m.group(1));
                            deleteDaiLog(id);
                        }
                    }
                });
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Object o =event.getSource();
                        String s = o.toString();
                        Pattern p = Pattern.compile(".*id=(\\d+),.*");
                        Matcher m = p.matcher(s);
                        if (m.matches()) {
                            int id = Integer.valueOf(m.group(1));
                            editDialog(id);
                        }
                    }
                });



            /*
            editButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Object o =event.getSource();
                    String s = o.toString();
                    Pattern p = Pattern.compile(".*id=(\\d+),.*");
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        int id = Integer.valueOf(m.group(1));

                        int taskDatabaseId = tableRowTaskModel.get(id).getTaskId();
                        System.out.println("Stage button id: "+id + " Task Id: " + taskDatabaseId);
                        //removeItemFromList(Integer.valueOf(m.group(1)));
                    }
                }
            });*/

                listView.getItems().add(box);
                index++;
            }
            if (tableRows.size()==0) {
                displayToastMessage("Sorry! No Task To Display...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addStageAction(ActionEvent event) {



        Label label0 = new Label("Description");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        JFXTextField desc_txt = new JFXTextField();
        desc_txt.setPromptText("Description");

        Label label2 = new Label("Stage Number");
        label2.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        JFXTextField stageNum_txt = new JFXTextField();
        stageNum_txt.setPromptText("Stage number");


        Label label1 = new Label("Please Due Date");
        label1.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        JFXDatePicker dueDate = new JFXDatePicker();
        dueDate.setValue(LocalDate.now());

        JFXCheckBox addToAll_cb = new JFXCheckBox("Add to all");


        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, desc_txt, label2, stageNum_txt, label1, dueDate, addToAll_cb);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Add");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String description = desc_txt.getText();
                String date = dueDate.getValue().toString();
                boolean isAddToAll = addToAll_cb.isSelected();
                //String initiator =
                 if (description.equals("") || stageNum_txt.getText().equals("")) {
                    displayToastMessage("Please enter description and stage number");
                }else{
                    /*Add stage to the database*/
                    int stageNum = Integer.valueOf(stageNum_txt.getText());
                    if (stageNum < 1) stageNum = 1;
                    if (stageNum > tableRows.get(tableRows.size() -1).getStageNumber() + 1 ) stageNum= tableRows.get(tableRows.size() -1).getStageNumber() + 1;

                    UpdateHelper updateHelper = new UpdateHelper();
                    updateHelper.addStage(taskId, work_lb.getText(), initiator, isAddToAll, stageNum, description, date);
                    String query = "INSERT INTO `work_book`( `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`) VALUES " +
                            "(" + taskId + ", " + stageNum +", '" + description + "', '"+userNmae+"', '"+date+"')";
                    //System.out.println(query);
                    //databaseHelper.insertQuery(query);
                    WorkBookModel workBookModel = new WorkBookModel();
                    workBookModel.setStageNumber(stageNum);
                    workBookModel.setStageDes(description);
                    workBookModel.setDueDate(DateFormatChange.changeForMatTo_DD_MM_YY(date));
                    workBookModel.setStageStatus("Pending");
                    workBookModel.setAssigenedTo("UserName");

                    int id = databaseHelper.getRecentDataId("work_book", "wb_id");
                    workBookModel.setWorkBookId(id);
                    tableRows.add(workBookModel);
                    populateListView();
                    dialog.close();
                }
            }
        });
        dialog.show();
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
                int wb_id = tableRows.get(rowId).getWorkBookId();
                String query = "update work_book set remark='"+str+ "' where wb_id = " + wb_id;
                System.out.println(query);
                databaseHelper.insertQuery(query);
                tableRows.get(rowId).setRemarks(str);
                populateListView();
                dialog.close();

                displayToastMessage("Remark Recorded");
            }
        });
        dialog.show();

    }
    public void deleteDaiLog(int rowId) {
        Label label0 = new Label("Are You Sure You Want To Delete Stage");
        label0.setFont(new Font("Segoi UI", 15));

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Delete");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int wb_id = tableRows.get(rowId).getWorkBookId();
                int stageNum = tableRows.get(rowId).getStageNumber();
                String query = "delete from work_book where wb_id = " + wb_id;
                databaseHelper.insertQuery(query);

                query = "update work_book set stage_num=stage_num - 1 where stage_num > " + stageNum +" and task_id="+taskId;
                databaseHelper.insertQuery(query);

                tableRows.remove(rowId);
                populateListView();
                dialog.close();
                displayToastMessage("Stage Removed");
            }
        });
        dialog.show();

    }

    public void  editDialog(int rowId) {


        String accutualAssignedUser = tableRows.get(rowId).getAssigenedTo();

        Label label0 = new Label("Assigned to");
        label0.setFont(new Font("Segoi UI", 15));

        JFXComboBox<String> userCmb = new JFXComboBox<>();
        userCmb.getItems().addAll(userList);
        userCmb.getSelectionModel().select(accutualAssignedUser);

        Label label1 = new Label("Due Date");
        label1.setFont(new Font("Segoi UI", 15));

        JFXDatePicker dueDate = new JFXDatePicker();
        dueDate.setPromptText("New Due Date");

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));


        Label label = new Label("Status");
        label.setFont(new Font("Segoi UI", 15));

        JFXComboBox<String> statusCmb = new JFXComboBox<>();
        statusCmb.getItems().add(Constants.PENDING);
        statusCmb.getItems().add(Constants.COMPLETED);
        statusCmb.getSelectionModel().select(0);


        VBox vBox = new VBox(label0, userCmb, label1, dueDate, label, statusCmb);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Update");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String assignedUser = userCmb.getSelectionModel().getSelectedItem();
                String query = "";
                int wb_id = tableRows.get(rowId).getWorkBookId();
                WorkBookModel workBookModel = tableRows.get(rowId);
                String status = statusCmb.getSelectionModel().getSelectedItem();

                if (dueDate.getValue() == null) {
                    query = "update work_book set assigned_to = '" + assignedUser + "', stage_status = '" + status + "' where wb_id = " + wb_id;


                }else{
                    String date = DateFormatChange.changeForMatTo_DD_MM_YY(dueDate.getValue().toString());
                    query = "update work_book set assigned_to = '" + assignedUser + "', stage_status = '" + status + "', due_date = '"+date  +"' where wb_id = " + wb_id;
                    workBookModel.setDueDate(date);
                }


                databaseHelper.insertQuery(query);
                if (status.equalsIgnoreCase(Constants.COMPLETED)) {
                    query = "update work_book set completed_date = CURDATE()  where wb_id = " + wb_id;
                    databaseHelper.insertQuery(query);
                }
                databaseHelper.updateTaskStatus(taskId);

                workBookModel.setAssigenedTo(assignedUser);
                workBookModel.setStageStatus(status);
                populateListView();
                dialog.close();
            }
        });
        dialog.show();

    }


}
