package sample;

import Helpers.*;
import Models.TaskModel;
import Models.WorkBookModel;
import com.jfoenix.controls.*;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskHistoryController extends Base implements Comparator<TaskModel> {




    @FXML
    private JFXTextField position_txt;

    @FXML
    private JFXTextField fillter_txt;

    @FXML
    private JFXComboBox<String> comPending_cmb;


    @FXML
    private ProgressBar progressBar;


    @FXML
    private JFXListView<HBox> listView;

    int listSelectedIndex = -1;




    @FXML
    private VBox tableVbox;


    String currentDirectory= "";
    List<TaskModel> tableRowTaskModel = new ArrayList<>();
    List<TaskModel> allTaskModel = new ArrayList<>();

    public void initialize() {
        checkMaximize();

        currentDirectory = System.getProperty("user.dir");
        currentDirectory+="\\src\\sample\\";
        System.out.println("The current working directory is " + currentDirectory);

        List<String> cmbList = new ArrayList<>();
        cmbList.add(Constants.PENDING);
        cmbList.add(Constants.COMPLETED);

        comPending_cmb.getItems().addAll(cmbList);
        comPending_cmb.getSelectionModel().select(Constants.PENDING);

        ArrayList<String> columnHeader = new ArrayList<>();
        columnHeader.add("Sno.");
        columnHeader.add("Code");
        columnHeader.add("File Name");
        columnHeader.add("Initiator");
        columnHeader.add("Work");
        columnHeader.add("Year");
        columnHeader.add("Due Date");
        columnHeader.add("Period");
        columnHeader.add("Priority");
        columnHeader.add("Status");

        HBox columnHeader_Hbox = TableHelper.getColumnHeader(columnHeader);
        tableVbox.getChildren().add(columnHeader_Hbox);
        columnHeader_Hbox.setPadding(new Insets(5,5,5,240));
        //listView.getItems().add(columnHeader_Hbox);

        allTaskModel = databaseHelper.getTask();

        tableRowTaskModel.addAll(allTaskModel);

        int numberOfTaskCompleted = 0;
        for (TaskModel taskModel: allTaskModel) {
            if (!taskModel.isPending())numberOfTaskCompleted++;
        }
        double percentTageCompleted =(double) numberOfTaskCompleted / allTaskModel.size();
        progressBar.setProgress(percentTageCompleted);
        progressBar.setStyle(Constants.PROGRESS_BAR);


        populateListView();
        UpdateHelper updateHelper = new UpdateHelper();
       // updateHelper.addStage(1, "ITR", "RKA", true, 1, "TestUp4", "2020-06-03");
    }


    @FXML
    void fillterEvent(KeyEvent event) {
        String s = fillter_txt.getText();
        String selected = comPending_cmb.getSelectionModel().getSelectedItem();
        System.out.println("Selected: " + selected);

        tableRowTaskModel.clear();
        for (TaskModel taskModel: allTaskModel) {
            if (taskModel.isContain(s)) {
                if (selected != null) {
                    if (taskModel.getStatus().equalsIgnoreCase(selected)) {
                        tableRowTaskModel.add(taskModel);
                    }
                }else {
                    tableRowTaskModel.add(taskModel);
                }
            }
        }
        populateListView();

        System.out.println("typed: " + s);
    }

    @FXML
    void updatePositionAction(ActionEvent event) {

        /*Updating task priority position*/
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage("Invalid");
        NumberValidator numberValidator1 = new NumberValidator();
        numberValidator1.setMessage("Invalid");
        position_txt.getValidators().add(numberValidator1);
        position_txt.getValidators().add(requiredFieldValidator);
        if (position_txt.validate()) {
            /*Valid number*/
            int pos = Integer.valueOf(position_txt.getText());
            if (pos < 1) {
                displayToastMessage("Priority must be grater then 0");
            }else{
                if (listSelectedIndex<0){
                    displayToastMessage("Please Select Task First!");
                }else {
                    /*update position*/
                    if (tableRowTaskModel.size()> listSelectedIndex){
                        System.out.println("Selected Pos: " + listSelectedIndex);
                        TaskModel model = tableRowTaskModel.get(listSelectedIndex);
                        databaseHelper.updatePriorityPosition(model.getTaskId(), pos);

                        if (model.isPending()) {
                            tableRowTaskModel.get(listSelectedIndex).setTaskPriorityPosition(pos);
                            populateListView();
                        }else{
                            displayToastMessage("Task Already Completed");
                        }

                    }else {
                        displayToastMessage("No valid Item Selected");
                    }
                }
            }
        }
        position_txt.setText("");
    }

    @FXML
    void comPendingAction(ActionEvent event) {
        String s = comPending_cmb.getSelectionModel().getSelectedItem();
        tableRowTaskModel.clear();
        for (TaskModel taskModel: allTaskModel) {
            if (taskModel.getStatus().equalsIgnoreCase(s)){
                tableRowTaskModel.add(taskModel);
            }
        }
        if (s.equalsIgnoreCase(Constants.COMPLETED)) {
            position_txt.setText("");
            position_txt.setDisable(true);
        }else{
            position_txt.setDisable(false);
        }
        populateListView();
    }
    private void populateListView() {
        listView.getItems().clear();
        int index = 0;
        tableRowTaskModel.sort(new TaskHistoryController());

        for (TaskModel taskModel: tableRowTaskModel) {

            System.out.println("TaskCode " + taskModel.getFileName() + " Priority: " + taskModel.getTaskPriorityPosition());
            int sno = index + 1;
            ArrayList<String> strings = taskModel.getStrings();
            strings.add(0, String.valueOf(sno));
            HBox box = TableHelper.getTableRow(strings);
            box.setId(String.valueOf(index));

            IconHelper iconHelper = new IconHelper();
            ImageView imageView = iconHelper.getIcon(Constants.ICON_VIEW);
            ImageView upImageView = iconHelper.getIcon(IconHelper.ICON_UP);
            ImageView downImageView = iconHelper.getIcon(IconHelper.ICON_DOWN);



            JFXButton upTaskPriorityBtn = ControlsHelper.getTableButton();
            upTaskPriorityBtn.setGraphic(upImageView);
            upTaskPriorityBtn.setId(String.valueOf(index));
            box.getChildren().add(0, upTaskPriorityBtn);


            JFXButton downTaskPriorityBtn = ControlsHelper.getTableButton();
            downTaskPriorityBtn.setGraphic(downImageView);
            downTaskPriorityBtn.setId(String.valueOf(index));
            box.getChildren().add(1, downTaskPriorityBtn);


            if (index==0)
                upTaskPriorityBtn.setDisable(true);
            if (index == tableRowTaskModel.size()-1)
                downTaskPriorityBtn.setDisable(true);

            JFXButton viewStageButton = ControlsHelper.getTableButton();
            viewStageButton.setGraphic(imageView);
            viewStageButton.setId(String.valueOf(index));
            box.getChildren().add(2,viewStageButton);

            upTaskPriorityBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Object o =event.getSource();
                    String s = o.toString();
                    Pattern p = Pattern.compile(".*id=(\\d+),.*");
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        int id = Integer.valueOf(m.group(1));
                        TaskModel currentTask = tableRowTaskModel.get(id);
                        TaskModel upTask = tableRowTaskModel.get(id - 1);
                        currentTask.decrementTaskPriority();
                        upTask.incrementTaskPriority();
                        UpdateHelper updateHelper = new UpdateHelper();
                        updateHelper.updateTaskPriority(currentTask);
                        updateHelper.updateTaskPriority(upTask);
                        populateListView();


                    }
                }
            });
            downTaskPriorityBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Object o =event.getSource();
                    String s = o.toString();
                    Pattern p = Pattern.compile(".*id=(\\d+),.*");
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        int id = Integer.valueOf(m.group(1));
                        TaskModel currentTask = tableRowTaskModel.get(id);
                        TaskModel downTask = tableRowTaskModel.get(id + 1);
                        currentTask.incrementTaskPriority();
                        downTask.decrementTaskPriority();

                        UpdateHelper updateHelper = new UpdateHelper();
                        updateHelper.updateTaskPriority(currentTask);
                        updateHelper.updateTaskPriority(downTask);
                        populateListView();


                    }
                }
            });


            if (!taskModel.isPending()){
                viewStageButton.setStyle(Constants.TABLE_ROW_COLOR_COMPLETED);
            }

            viewStageButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Object o =event.getSource();
                    String s = o.toString();
                    Pattern p = Pattern.compile(".*id=(\\d+),.*");
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        int id = Integer.valueOf(m.group(1));

                        int taskDatabaseId = tableRowTaskModel.get(id).getTaskId();
                        Track_TASK_ID = taskDatabaseId;
                        System.out.println("Stage button id: "+id + " Task Id: " + taskDatabaseId);
                        loadFrame(LocationHelper.TRACK_STAGE);
                        //removeItemFromList(Integer.valueOf(m.group(1)));
                    }
                }
            });

            listView.getItems().add(box);
            index++;
        }
        if (tableRowTaskModel.size()==0) {
            displayToastMessage("Sorry! No Task To Display...");
        }
    }
    @FXML
    void listItemSelected(MouseEvent event) {
        listSelectedIndex = listView.getSelectionModel().getSelectedIndex();
    }

    @Override
    public int compare(TaskModel o1, TaskModel o2) {
        return o1.getTaskPriorityPosition() - o2.getTaskPriorityPosition();
    }
    @FXML
    void exportTask(ActionEvent event) {
        exportTask();
    }

    @FXML
    void navigationAction(MouseEvent event) {
        if (event.getSource() == createTask_vbox) {
            System.out.println("Create task clicked");
            loadFrame(LocationHelper.CREATE_NEW_TASK_SCENE);
        }
        if (event.getSource() == taskHistory_vbox) {
            System.out.println("Task History vbox");
            loadFrame(LocationHelper.TASK_HISTORY_SCENE);
        }
        if (event.getSource() == import_vbox) {
            //importTask();
            loadFrame(LocationHelper.IMPORT_SCENE);
        }
        if (event.getSource() == export_vbox) {
            exportTask();
        }
        if (event.getSource() == user_vbox) {
            loadFrame(LocationHelper.USER_SCENE);
        }
        if (event.getSource() == logOut_vbox) {
            FileHelper fileHelper = new FileHelper();
            fileHelper.logout();
            loadFrame(LocationHelper.LOG_IN_SCENE);
        }

    }
    public void importTask() {
        ImportTaskHelper importTaskHelper = new ImportTaskHelper();
        ArrayList<TaskModel> taskModelArrayList = importTaskHelper.openFileChooserAndReadFile();
        Map<String, Integer> workMap = databaseHelper.getWorkId();

        for (TaskModel taskModel: taskModelArrayList) {
            String des = taskModel.getWorkDescription().toLowerCase();
            int id = workMap.get(des);
            System.out.println(des +" " + id);
            taskModel.setWorkId(id);
            databaseHelper.createTask(taskModel);
            tableRowTaskModel.add(taskModel);
        }
        displayToastMessage("Task Imported");
        populateListView();
    }

    public void exportTask() {
        System.out.println("Reading to export table row data");
        /*ImportTaskHelper importTaskHelper = new ImportTaskHelper();
        importTaskHelper.saveDailog();
        */

        for (TaskModel taskModel: tableRowTaskModel) {
            ArrayList<WorkBookModel> workBookModel = databaseHelper.getWorkBook(taskModel.getTaskId());
            taskModel.setTaskWorkBook(workBookModel);
            //System.out.println(workBookModel.toArray());
        }
        ExporterHelper exporterHelper = new ExporterHelper();
        exporterHelper.saveDialog(tableRowTaskModel);

        displayToastMessage("Data exported");

    }
}
