package sample;

import Helpers.*;
import Models.ColorModel;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskHistoryController extends Base implements Comparator<TaskModel> {





    @FXML
    private JFXTextField fillter_txt;
/*
    @FXML
    private JFXComboBox<String> comPending_cmb;*/


    @FXML
    private JFXComboBox<String> user_cmb;


    int listSelectedIndex = -1;




    @FXML
    private VBox tableVbox;
    @FXML
    private VBox tableRow_Vbox;

    @FXML
    private HBox color_hbox;

    @FXML
    private JFXCheckBox select_cb;

    @FXML
    private JFXButton assignment_btn;


    @FXML
    private JFXComboBox<String> assigment_cmb;



    @FXML
    protected Label userName_txt;

    ArrayList<Integer> widthList = new ArrayList<>();

    ArrayList<String> columnHeader = new ArrayList<>();
    String currentDirectory= "";
    List<TaskModel> tableRowTaskModel = new ArrayList<>();
    List<TaskModel> allTaskModel = new ArrayList<>();
    ArrayList<String> colorCodingStyles = new ArrayList<>();

    int DEADLINE_DAYS = 0;
    int clickCount = 0;

    public void initialize() {
        checkMaximize();
        String userName = "";
        FileHelper fileHelper = new FileHelper();
        userName = USERNAME;
        userName_txt.setText(userName);


        List<String> cmbList = new ArrayList<>();
        cmbList.add(Constants.PENDING);
        cmbList.add(Constants.COMPLETED);

       /* comPending_cmb.getItems().addAll(cmbList);
        comPending_cmb.getSelectionModel().select(Constants.PENDING);*/




        columnHeader.add("Code");
        columnHeader.add("File Name");
        columnHeader.add("Initiator");
        columnHeader.add("Work");
        columnHeader.add("Sub Work");
        columnHeader.add("Year");
        columnHeader.add("Due Date");
        columnHeader.add("Period");
        columnHeader.add("Assigned_to");
        columnHeader.add("Stage");


        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH + 50);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH + 20);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH + 30);
        widthList.add(Constants.COLUMN_WIDTH + 30);

        ArrayList<String> users = databaseHelper.getUsers();
        users.remove(USERNAME);
        user_cmb.getItems().addAll(users);
        if (!IS_ADMIN) {
            assignment_btn.setDisable(true);
        }
        /*HBox columnHeader_Hbox = TableHelper.getColumnHeader(columnHeader, widthList);
        tableVbox.getChildren().add(columnHeader_Hbox);
        columnHeader_Hbox.setPadding(new Insets(5,5,5,480));*/
        assigment_cmb.getItems().add("Assigned");
        assigment_cmb.getItems().add("Not Assigned");
        assigment_cmb.getItems().add("All");
        updateInit();

    }
    protected void updateInit() {
        String query = "";
        if (IS_ADMIN) {
            query = "select * from task where task_status='pending' and initiator='"+USERNAME+"'";
        }else{
            query = "select * from task where task_status='pending'";
        }
        allTaskModel = databaseHelper.getTask(query);
        tableRowTaskModel.clear();
        for (TaskModel taskModel: allTaskModel) {
            if (taskModel.isPending()) {
                tableRowTaskModel.add(taskModel);
                ArrayList<WorkBookModel> workBookModels =databaseHelper.getWorkBook(taskModel.getTaskId());
                taskModel.setTaskWorkBook(workBookModels);

                for (WorkBookModel workBookModel: workBookModels) {
                    if (workBookModel.getStageStatus().equalsIgnoreCase(Constants.PENDING)) {
                        taskModel.setExportWorkBook(workBookModel);
                        break;
                    }
                }

            }
        }

        //tableRowTaskModel.addAll(allTaskModel);

        populateColor();
        populateListView();
    }

    @FXML
    void assignMentFIllterAction(ActionEvent event) {
        String selectedItem = assigment_cmb.getSelectionModel().getSelectedItem();
        tableRowTaskModel.clear();
        if (selectedItem.equals("All")) tableRowTaskModel.addAll(allTaskModel);
        else {
            for (TaskModel taskModel: allTaskModel) {
                ArrayList<WorkBookModel> workBookModels = taskModel.getTaskWorkBook();
                int lastStageIndex = workBookModels.size() - Constants.LAST_STAGE;
                workBookModels.remove(lastStageIndex);

                String initiator = taskModel.getInitiator();

                if (selectedItem.equals("Assigned")) {
                    boolean flag = true;
                    for (WorkBookModel workBookModel: workBookModels) {
                        if (workBookModel.getAssigenedTo().equals(initiator)) {
                            /*means not assined*/
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        tableRowTaskModel.add(taskModel);
                    }
                }else {
                    boolean flag = false;
                    for (WorkBookModel workBookModel: workBookModels) {
                        if (workBookModel.getAssigenedTo().equals(initiator)) {
                            /*means not assined*/
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        tableRowTaskModel.add(taskModel);
                    }
                }

            }
        }
        populateListView();

    }

    void populateColor() {
        color_hbox.getChildren().clear();
        colorCodingStyles.clear();
        List<ColorModel> colorModels = databaseHelper.getColors();
        for (ColorModel colorModel: colorModels) {
            JFXButton jfxButton = new JFXButton(" ");
            Tooltip tooltip = new Tooltip(colorModel.getCol_des());
            jfxButton.setTooltip(tooltip);
            jfxButton.setStyle(colorModel.getStyle());
            color_hbox.getChildren().add(jfxButton);
            colorCodingStyles.add(colorModel.getStyle());

            if (colorModel.getCol_des().contains("days")) {
                String temp[] = colorModel.getCol_des().split(" ");
                DEADLINE_DAYS  = Integer.parseInt(temp[3]);
            }

            jfxButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    colorDialog(colorModel.getColor_id(), colorModel.getCol_des());

                }
            });

        }
    }
    public void colorDialog(int colorId, String description) {
        Label label0 = new Label("Please enter color Hexa decimal value");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        Label label1 = new Label(description);
        label1.setFont(new Font("Segoi UI", Constants.LABEL_SIZE + 10));


        JFXTextField textField = new JFXTextField();
        textField.setPromptText("ex - #F123A4");
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));
        boolean flag = false;
        JFXTextField numberOfDays = new JFXTextField();
        numberOfDays.setPromptText("Enter number of days");


        VBox vBox = new VBox(label0, label1, textField);
        if (description.contains("days")) {
            flag = true;
            vBox.getChildren().add(numberOfDays);
        }
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

                String colorCode = textField.getText();
                if (!colorCode.matches("^#[A-Fa-f0-9]{6}$")) {
                    displayToastMessage("Invalid Color Code");
                    return;
                }
                if (description.contains("days")) {
                    String days = numberOfDays.getText();
                    if (!days.matches("^\\d*$")) {
                        displayToastMessage("Please enter integer!");
                        return;
                    }
                    int deadDay = 0;
                    if (!days.equals("")) deadDay = Integer.parseInt(days);
                    String query = "update colors set color_code = '" + colorCode+"', color_des='Due date within "+ deadDay + " days'  where color_id="+colorId;
                    databaseHelper.insertQuery(query);
                }else {

                    String query = "update colors set color_code = '" + colorCode+"' where color_id="+colorId;
                    databaseHelper.insertQuery(query);
                }


                populateColor();
                populateListView();
                dialog.close();

                displayToastMessage("Color updated");
            }
        });
        dialog.show();

    }


    @FXML
    protected void taskHistory_fillterEvent(KeyEvent event) {
        String s = fillter_txt.getText().toLowerCase();
        String selected = Constants.PENDING;
        System.out.println("fillter: "+ s);
        tableRowTaskModel.clear();
        for (TaskModel taskModel: allTaskModel) {
            WorkBookModel exportWorkBook = taskModel.getExportWorkBook();
            if (taskModel.isContain(s)) {
                tableRowTaskModel.add(taskModel);
            }else if (exportWorkBook!=null) {
                String t = exportWorkBook.getAssigenedTo() + " " + exportWorkBook.getStageDes();
                t=t.toLowerCase();
                if (t.contains(s)) {
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
        //position_txt.getValidators().add(numberValidator1);
        //position_txt.getValidators().add(requiredFieldValidator);
       /* if (position_txt.validate()) {
            *//*Valid number*//*
            int pos = Integer.valueOf(position_txt.getText());
            if (pos < 1) {
                displayToastMessage("Priority must be grater then 0");
            }else{
                if (listSelectedIndex<0){
                    displayToastMessage("Please Select Task First!");
                }else {
                    *//*update position*//*
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
        position_txt.setText("");*/
    }


    private void populateListView() {
        tableRow_Vbox.getChildren().clear();
        int index = 0;
        tableRowTaskModel.sort(new TaskHistoryController());
        Collections.sort(tableRowTaskModel, new Comparator<TaskModel>() {
            @Override
            public int compare(TaskModel o1, TaskModel o2) {
                int i1 = 0, i2 =0;
                if (o1.isSelected())i1=1;
                if (o2.isSelected())i2=1;
                return i2 - i1;
            }
        });

        tableRow_Vbox.setPrefWidth(screenMaxWidth - 200);

        /*Add Headers*/
        HBox columnHeader_Hbox = TableHelper.getColumnHeader(columnHeader, widthList);
        columnHeader_Hbox.setPadding(new Insets(5,5,5,280));
        tableRow_Vbox.getChildren().add(columnHeader_Hbox);

        if (tableRowTaskModel.size()==0)return;


        for (TaskModel taskModel: tableRowTaskModel) {
            System.out.println("TaskCode " + taskModel.getFileName() + " Priority: " + taskModel.getTaskPriorityPosition());
            int sno = index + 1;
            ArrayList<String> strings = taskModel.getStrings(true);
            WorkBookModel exportWorkBook = taskModel.getExportWorkBook();

            if (exportWorkBook!=null) {
                strings.add( exportWorkBook.getAssigenedTo());
                strings.add( exportWorkBook.getStageDes());

            }else {
                strings.add( "");
                strings.add( "");
            }

            HBox box = TableHelper.getTableRow(strings, widthList);

            box.setId(String.valueOf(index));

            /*colorCoding Hbox*/
            Tooltip tooltip = new Tooltip("Task");
            String hboxStyle = colorCodingStyles.get(3);
            if (taskModel.getPriority().equalsIgnoreCase("yes")) {
                hboxStyle = colorCodingStyles.get(0);;
                tooltip = new Tooltip("Task have a priority");
            }
            if (taskModel.getDateDiff(DEADLINE_DAYS) ==0) {
                hboxStyle = colorCodingStyles.get(1);
                tooltip = new Tooltip("Today you have to complete task");
            }
            if (taskModel.getDateDiff(DEADLINE_DAYS) >0 && taskModel.getDateDiff(DEADLINE_DAYS) <=DEADLINE_DAYS) {
                hboxStyle = colorCodingStyles.get(2);
                tooltip = new Tooltip("You have less then 7 days to complete task");
            }


            box.setStyle(hboxStyle);

            IconHelper iconHelper = new IconHelper();
            ImageView imageView = iconHelper.getIcon(IconHelper.ICON_CHANGE);
            ImageView upImageView = iconHelper.getIcon(IconHelper.ICON_UP);
            ImageView downImageView = iconHelper.getIcon(IconHelper.ICON_DOWN);
            ImageView deleteImageView = iconHelper.getIcon(IconHelper.ICON_DELETE);


            JFXButton upTaskPriorityBtn = ControlsHelper.getTableButton();
            upTaskPriorityBtn.setTooltip(new Tooltip("Increase Priority"));
            upTaskPriorityBtn.setGraphic(upImageView);
            upTaskPriorityBtn.setId(String.valueOf(index));
            box.getChildren().add(0, upTaskPriorityBtn);


            JFXButton downTaskPriorityBtn = ControlsHelper.getTableButton();
            downTaskPriorityBtn.setTooltip(new Tooltip("Decrease Priority"));
            downTaskPriorityBtn.setGraphic(downImageView);
            downTaskPriorityBtn.setId(String.valueOf(index));
            box.getChildren().add(1, downTaskPriorityBtn);


            JFXButton deleteButton = ControlsHelper.getTableButton();
            deleteButton.setTooltip(new Tooltip("Delete"));
            deleteButton.setGraphic(deleteImageView);
            deleteButton.setId(String.valueOf(index));


            if (index==0)
                upTaskPriorityBtn.setDisable(true);
            if (index == tableRowTaskModel.size()-1)
                downTaskPriorityBtn.setDisable(true);

            JFXButton changeInitBtn = ControlsHelper.getTableButton();
            changeInitBtn.setTooltip(new Tooltip("Change Initiator"));
            changeInitBtn.setGraphic(imageView);
            changeInitBtn.setId(String.valueOf(index));
            box.getChildren().add(2,changeInitBtn);

            box.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    clickCount++;
                    if (clickCount%2==0) {
                        System.out.println("Double cliked");
                        int taskDatabaseId = taskModel.getTaskId();
                        Track_TASK_ID = taskDatabaseId;
                        System.out.println(" "+ " " + " Task Id: " + taskDatabaseId);
                        loadFrame(LocationHelper.TRACK_STAGE);
                    }
                }
            });

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

            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int id = getIdFromEvent(event);
                    System.out.println("Delete task from table row " + id);
                    deleteDaiLog(id);

                }
            });


            if (!taskModel.isPending()){
                changeInitBtn.setStyle(Constants.TABLE_ROW_COLOR_COMPLETED);
            }

            changeInitBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Object o =event.getSource();
                    String s = o.toString();
                    Pattern p = Pattern.compile(".*id=(\\d+),.*");
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        int id = Integer.valueOf(m.group(1));
                        int taskDatabaseId = tableRowTaskModel.get(id).getTaskId();
                        changeInitDialog(taskDatabaseId, id);
                        /*Track_TASK_ID = taskDatabaseId;
                        System.out.println("Stage button id: "+id + " Task Id: " + taskDatabaseId);
                        loadFrame(LocationHelper.TRACK_STAGE);*/
                        //removeItemFromList(Integer.valueOf(m.group(1)));
                    }
                }
            });

            JFXCheckBox checkBox = new JFXCheckBox();
            checkBox.setStyle("-fx-border-color: #FFFF");
            checkBox.setSelected(taskModel.isSelected());
            checkBox.setPadding(new Insets(10));
            box.getChildren().add(0,checkBox);
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    taskModel.setSelected(checkBox.isSelected());
                    System.out.println(taskModel.getFileName() + " selected " + checkBox.isSelected() );
                }
            });
            JFXComboBox<String> stageCmb = new JFXComboBox();
            stageCmb.setPrefWidth(Constants.DESCRIPTION_WIDTH);

            for (WorkBookModel workBookModel: taskModel.getTaskWorkBook()) {
                stageCmb.getItems().add(workBookModel.getStageDes());
            }
            box.getChildren().add( stageCmb);
            /*Get 2nd last stage and select it*/
            String defaultSelect = taskModel.getTaskWorkBook().get(taskModel.getTaskWorkBook().size() - 2).getStageDes();
            stageCmb.getSelectionModel().select(defaultSelect);
            taskModel.setSelectedStage(defaultSelect);

            stageCmb.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String selectedItem = stageCmb.getSelectionModel().getSelectedItem();
                    taskModel.setSelectedStage(selectedItem);
                    System.out.println(taskModel.getFileName()+ " stage Selected: " + selectedItem);
                }
            });



            tableRow_Vbox.getChildren().add(box);

            box.getChildren().add(deleteButton);
            if (!IS_ADMIN) {
                deleteButton.setDisable(true);
                checkBox.setDisable(true);
            }
            index++;
        }
        if (tableRowTaskModel.size()==0) {
            displayToastMessage("Sorry! No Task To Display...");
        }
    }
    public void changeInitDialog(int taskId, int index) {
        Label label0 = new Label("Please Select new initiator");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));


        ArrayList<String> userList = new ArrayList<>();
        String query = "select * from user where role='admin' and user_name!='"+USERNAME+"'";
        try{
            ResultSet resultSet = databaseHelper.fetchQuery(query);
            while (resultSet.next()) {
                userList.add(resultSet.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JFXComboBox<String> adminList = new JFXComboBox<>();
        adminList.setPrefWidth(300);
        adminList.getItems().addAll(userList);
        adminList.setPromptText("Select new initiator");

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, adminList);
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
                populateListView();
                dialog.close();
                String selectedInit = adminList.getSelectionModel().getSelectedItem();
                if (selectedInit == null) displayToastMessage("Please select user");

                String query = "update task set initiator='"+selectedInit+"' where task_id = " + taskId;
                databaseHelper.insertQuery(query);
                tableRowTaskModel.remove(index);
                populateListView();

                displayToastMessage("Initiator changed");
            }
        });
        dialog.show();

    }
    @FXML
    void selectAllAction(ActionEvent event) {
        for (TaskModel taskModel: tableRowTaskModel) {
            taskModel.setSelected(select_cb.isSelected());
        }
        populateListView();

    }
    /*@FXML
    void listItemSelected(MouseEvent event) {
        listSelectedIndex = listView.getSelectionModel().getSelectedIndex();
    }*/

    public void deleteDaiLog(int rowId) {
        TaskModel currentTask = tableRowTaskModel.get(rowId);

        Label label0 = new Label("Are You Sure You Want To Delete " + currentTask.getFileName());
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
                String query = "delete from task where task_id = " + currentTask.getTaskId();
                System.out.println("Delete query: " + query);
                databaseHelper.insertQuery(query);
                query = "delete from work_book where task_id = " + currentTask.getTaskId();
                databaseHelper.insertQuery(query);
                displayToastMessage("Task Deleted");
                allTaskModel.remove(rowId);
                tableRowTaskModel.remove(rowId);
                populateListView();
                dialog.close();

            }
        });
        dialog.show();

    }
    @Override
    public int compare(TaskModel o1, TaskModel o2) {
        return o1.getTaskPriorityPosition() - o2.getTaskPriorityPosition();
    }

    @FXML
    void assignmentAction(ActionEvent event) {

        String username = user_cmb.getSelectionModel().getSelectedItem();

        if (username!=null) {
            for (TaskModel taskModel: allTaskModel) {
                if (taskModel.isSelected()) {
                    System.out.println(taskModel.getFileName() + " is selected");
                    /*Start assigning task to them*/
                    ArrayList<WorkBookModel> workBookModels = taskModel.getTaskWorkBook();
                    String selectedStage = taskModel.getSelectedStage();

                    for (WorkBookModel workBookModel: workBookModels) {
                        String query = "update work_book set assigned_to='"+username+"' where wb_id="+workBookModel.getWorkBookId()+" and assigned_to='"+taskModel.getInitiator()+"' and stage_status='pending'";
                        databaseHelper.insertQuery(query);
                        if (workBookModel.getAssigenedTo().equalsIgnoreCase(taskModel.getInitiator()) && workBookModel.getStageStatus().equalsIgnoreCase(Constants.PENDING)) {
                            workBookModel.setAssigenedTo(username);
                        }
                        if (workBookModel.getStageDes().equalsIgnoreCase(selectedStage)) {
                            break;
                        }

                    }
                    /*String query = "update work_book set assigned_to='"+username+"' where task_id="+taskModel.getTaskId()+ " and stage_des != 'approve' and stage_status='pending'";
                    databaseHelper.insertQuery(query);*/
                    taskModel.setSelected(false);
                }
            }
            populateListView();
            displayToastMessage("Task Assignment completed");


        }else {
            displayToastMessage("Please select user");
        }

    }
    @FXML
    void openFolderAction(ActionEvent event) {
        FileHelper fileHelper = new FileHelper();
        String location = fileHelper.getFolderPath();
        if (location.equals("")) {
            locationDialog();

        }else {
            Desktop desktop = Desktop.getDesktop();
            File file = new File(location);
            try {
                desktop.open(file);
            } catch (IOException e) {
                displayToastMessage(e.getMessage());
                e.printStackTrace();
            }
        }

    }
    public void locationDialog() {
        Label label0 = new Label("Please enter folder location");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));


        JFXTextField folderLocation = new JFXTextField();
        folderLocation.setPromptText("C:\\Tally");
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, folderLocation);
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

                String loc = folderLocation.getText();
                if (loc.equals("")) {
                    displayToastMessage("Please enter location");
                    return;
                }
                FileHelper fileHelper = new FileHelper();
                fileHelper.setFolderPath(loc);
                displayToastMessage("Folder path is set Now you can open it!");
                dialog.close();
            }
        });
        dialog.show();

    }

}
