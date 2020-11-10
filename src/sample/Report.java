package sample;

import Helpers.*;
import Models.*;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Report extends Base  implements EventHandler<KeyEvent> {


    @FXML
    protected Label userName_txt;
    @FXML
    private JFXListView<HBox> listView;

    @FXML
    private JFXComboBox<String> user_cmb;

    @FXML
    private JFXComboBox<String> file_cmb;

    @FXML
    private JFXComboBox<String> work_cmb;

    @FXML
    private JFXComboBox<String> status_cmb;

    @FXML
    private JFXButton getReport_btn;

    @FXML
    private JFXButton getReport_btn11;

    @FXML
    private JFXDatePicker start_datePicker;

    @FXML
    private JFXDatePicker end_datePicker;
    ArrayList<String> columnHeader = new ArrayList<>();

    ArrayList<Integer> widthList = new ArrayList<>();
    @FXML
    private JFXCheckBox stageWise_cb;

    @FXML
            private  JFXCheckBox myAssignment_cb;
    @FXML
            JFXComboBox<String> sub_work_cmb;
    List<TaskModel> taskModelArrayList = new ArrayList<>();
    String ALL = "All";

    ArrayList<TaskModel> tableRowTaskModel = new ArrayList<>();

    ArrayList<String> clientList = new ArrayList<>();
    ArrayList<String> workList = new ArrayList<>();
    int clickCount = 0;
    public void initialize() {
        checkMaximize();
        userName_txt.setText(USERNAME);
        user_cmb.getItems().addAll(databaseHelper.getUsers());
        user_cmb.getItems().add(ALL);

        /*populate client*/
        ClientDataBaseHelper clientDataBaseHelper = new ClientDataBaseHelper();
        for (ClientModel clientModel: clientDataBaseHelper.getClient()){
            clientList.add(clientModel.getFileName());
        }
        file_cmb.getItems().addAll(clientList);
        file_cmb.getItems().add(ALL);

        doAutoCompleteBox();

        /*Populate work*/
        WorkDataBaseHelper workDataBaseHelper = new WorkDataBaseHelper();
        for (WorkModel workModel: workDataBaseHelper.getWorks()) {
            workList.add(workModel.getWorkDes());
        }
        work_cmb.getItems().addAll(workList);
        work_cmb.getItems().add(ALL);


        /*populate status*/
        status_cmb.getItems().add(Constants.PENDING);
        status_cmb.getItems().add(Constants.COMPLETED);
        //status_cmb.getItems().add(ALL);

        /*COlumn header*/
        columnHeader.add("Task No.");
        columnHeader.add("Code");
        columnHeader.add("File Name");
        columnHeader.add("Assigner");
        columnHeader.add("Work");
        columnHeader.add("Year");
        columnHeader.add("Due Date");
        columnHeader.add("Period");
        columnHeader.add("Priority");
        columnHeader.add("Task Status");
        columnHeader.add("Assigned To");
        columnHeader.add("Current Task");
        columnHeader.add("Updated Status");
        columnHeader.add("AssignedDate");
        columnHeader.add("Comp. Date");
        columnHeader.add("Remarks");

        widthList.add(50);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH *2);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH * 2);
        widthList.add(Constants.COLUMN_WIDTH+50);
        widthList.add(Constants.COLUMN_WIDTH + 50);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);


    }
    @FXML
    void clearAction(ActionEvent event) {
        user_cmb.getSelectionModel().clearSelection();
        file_cmb.getSelectionModel().clearSelection();
        work_cmb.getSelectionModel().clearSelection();
        status_cmb.getSelectionModel().clearSelection();
        start_datePicker.setValue(null);
        end_datePicker.setValue(null);
        stageWise_cb.setSelected(false);
        sub_work_cmb.getItems().clear();
        sub_work_cmb.setDisable(true);


    }

    @FXML
    void exportAction(ActionEvent event) {
        ExporterHelper exporterHelper = new ExporterHelper();
        String selectedStatus = status_cmb.getSelectionModel().getSelectedItem();
        String selectedUser = user_cmb.getSelectionModel().getSelectedItem();
        if (taskModelArrayList.size() == 0) {
            displayToastMessage("No data for export!");
            return;
        }
        if (stageWise_cb.isSelected()) {
            exporterHelper.saveDialog(taskModelArrayList, false);
        }else if (selectedUser != null) {
            exporterHelper.saveDialog(taskModelArrayList, true);
        }
        else if (selectedStatus!= null && selectedStatus.equals(Constants.PENDING)) {
            /*Exporting first pending stastus of each task*/
            /*for (TaskModel taskModel: taskModelArrayList) {
                ArrayList<WorkBookModel> list = taskModel.getTaskWorkBook();
                for (WorkBookModel workBookModel: list) {
                    if (workBookModel.getStageStatus().equals(selectedStatus)) {
                        taskModel.setExportWorkBook(workBookModel);
                        break;
                    }
                }
            }*/
            exporterHelper.summaryExport(taskModelArrayList, false);
        }else {
            /*exporting each slast completed status of each task*/
            /*for (TaskModel taskModel: taskModelArrayList) {
                ArrayList<WorkBookModel> list = taskModel.getTaskWorkBook();
                Collections.sort(list, new Comparator<WorkBookModel>() {
                    @Override
                    public int compare(WorkBookModel o1, WorkBookModel o2) {
                        return o2.getStageNumber() - o1.getStageNumber();
                    }
                });
                for (WorkBookModel workBookModel: list) {
                    if (workBookModel.getStageStatus().equals(Constants.COMPLETED)) {
                        taskModel.setExportWorkBook(workBookModel);
                        break;
                    }
                }
            }*/
            exporterHelper.summaryExport(taskModelArrayList, false);
        }
        displayToastMessage("Data exported");
    }

    @FXML
    void backAction(ActionEvent event) {
        System.out.println("Back action");
        populateList();
    }

    @FXML
    void getReportAction(ActionEvent event) {

        String query = buildQuery();
        System.out.println(query);
        String selectedStatus = status_cmb.getSelectionModel().getSelectedItem();
        taskModelArrayList = databaseHelper.getTask(query);

        ArrayList<TaskModel> taskModels = new ArrayList<>();
        System.out.println("debug 1 query: " + query);
        for(TaskModel taskModel: taskModelArrayList) {
            System.out.println("Date present");
            ArrayList<WorkBookModel> workBookModelArrayList = databaseHelper.getWorkBook(taskModel.getTaskId());
            taskModel.setTaskWorkBook(workBookModelArrayList);

            if (selectedStatus!= null && selectedStatus.equals(Constants.PENDING)) {
                /*Exporting first pending stastus of each task*/
                ArrayList<WorkBookModel> list = taskModel.getTaskWorkBook();
                for (WorkBookModel workBookModel: list) {
                    if (workBookModel.getStageStatus().equals(selectedStatus)) {
                        taskModel.setExportWorkBook(workBookModel);
                        break;
                    }
                }

            }else {
                /*exporting each slast completed status of each task*/
                ArrayList<WorkBookModel> list = taskModel.getTaskWorkBook();
                Collections.sort(list, new Comparator<WorkBookModel>() {
                    @Override
                    public int compare(WorkBookModel o1, WorkBookModel o2) {
                        return o2.getStageNumber() - o1.getStageNumber();
                    }
                });
                for (WorkBookModel workBookModel : list) {
                    if (workBookModel.getStageStatus().equals(Constants.COMPLETED)) {
                        taskModel.setExportWorkBook(workBookModel);
                        break;
                    }
                }

            }

            /*FIllter based on users*/
            String selectedUser= user_cmb.getSelectionModel().getSelectedItem();
            if (taskModel.getExportWorkBook() != null) {
                if (selectedUser!=null && !selectedUser.equals("All")) {
                    WorkBookModel workBookModel = taskModel.getExportWorkBook();
                    if (workBookModel.getAssigenedTo().equals(selectedUser)) {
                    /*and there is some data in export*/
                        taskModels.add(taskModel);
                    }

                }else {
                    taskModels.add(taskModel);
                    System.out.println("Adding to task model: " + taskModel.getFileName());
                }
            }
        }
        System.out.println("Task Model size: " + taskModels.size());
        taskModelArrayList.clear();
        taskModelArrayList.addAll(taskModels);
        if (taskModelArrayList.size() == 0) {
            displayToastMessage("There is no item to display");
        }
        populateList();
    }

    void populateList() {
        listView.getItems().clear();
        HBox columnHeader_Hbox = TableHelper.getColumnHeader(columnHeader, widthList);
        listView.getItems().add(columnHeader_Hbox);
        int index = 0;
        if (taskModelArrayList.size() == 0) {
            displayToastMessage("Sorry no data to display");
            return;
        }

        for (TaskModel taskModel: taskModelArrayList) {
            /*Start adding rows*/
            index++;
            ArrayList<String> data = taskModel.getSummaryExport();

            data.add(0, index+"");
            HBox rowHbox = TableHelper.getTableRow(data, widthList);
            listView.getItems().add(rowHbox);
            rowHbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    clickCount++;
                    if (clickCount % 2==0) {
                        System.out.println(taskModel.getFileName() + " file clicked");
                        stageDetails(taskModel);
                    }
                }
            });
        }
    }
    public void stageDetails(TaskModel taskModel) {
        Label label0 = new Label("Stage Details");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        listView.getItems().clear();

        Collections.sort(taskModel.getTaskWorkBook(), new Comparator<WorkBookModel>() {
            @Override
            public int compare(WorkBookModel o1, WorkBookModel o2) {
                return o1.getStageNumber() - o2.getStageNumber();
            }
        });

        for (WorkBookModel workBookModel: taskModel.getTaskWorkBook()) {
            HBox rowHbox = TableHelper.getTableRow(workBookModel.getStrings());
            listView.getItems().add(rowHbox);
        }

        /*JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, listView);
        vBox.setSpacing(20);
        vBox.setPrefWidth(500);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Ok");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                dialog.close();

            }
        });
        dialog.show();*/

    }
    String buildQuery() {
        String query = "Select * from task Where ";

        /*Filter by file name*/
        ArrayList<String> filterList = new ArrayList<>();
        String fileName = file_cmb.getSelectionModel().getSelectedItem();
        String work = work_cmb.getSelectionModel().getSelectedItem();
        String status = status_cmb.getSelectionModel().getSelectedItem();
        LocalDate startDate = start_datePicker.getValue();
        LocalDate endDate = end_datePicker.getValue();
        String selected_sub_work = sub_work_cmb.getSelectionModel().getSelectedItem();
        if (fileName!=null && !fileName.equals("") && !fileName.equals(ALL)) {
            filterList.add("file_name = '"+fileName+"'");
        }
        if (work!=null && !work.equals(ALL)) {
            filterList.add("work_des='"+work+"'");
        }
        /*if (status!=null && !status.equals(ALL)) {
            filterList.add("task_status='"+status+"'");
        }*/
        if (startDate!=null) {
            filterList.add("created_date>= '"+startDate+"'");
        }
        if (endDate !=null) {
            filterList.add("created_date<='"+endDate+"'");
        }
        if (myAssignment_cb.isSelected()) {
            filterList.add("initiator='"+USERNAME+"'");
        }
        if (selected_sub_work!=null) {
            filterList.add("sub_work_des='"+selected_sub_work+"'");
        }

        if (filterList.size() == 0) {
            query+=" 1";
        }else  {
            query+=" " + filterList.get(0);
            for (int i=1;i<filterList.size();i++) {
                query+=" and " + filterList.get(i);
            }
        }

        return query;
    }
    @FXML
    void workSelectionAction(ActionEvent event) {
        sub_work_cmb.getItems().clear();
        sub_work_cmb.setDisable(true);
        String selectedWork = work_cmb.getSelectionModel().getSelectedItem();
        if (selectedWork!=null) {
            WorkDataBaseHelper workDataBaseHelper = new WorkDataBaseHelper();
            ArrayList<WorkModel> workModels = workDataBaseHelper.getWorks(selectedWork);
            if (workModels!=null && workModels.size()>0) {
                ArrayList<SubWorkModel> subWorkModels = workModels.get(0).getSubWorkModels();
                if (subWorkModels!=null && subWorkModels.size()>0) {
                    sub_work_cmb.setDisable(false);
                    for (SubWorkModel subWorkModel: subWorkModels) {
                        sub_work_cmb.getItems().addAll(subWorkModel.getSub_des());
                    }
                }
            }

        }

    }
    protected void doAutoCompleteBox() {
        file_cmb.setEditable(true);
        file_cmb.getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){//mean onfocus
                file_cmb.show();
            }
        });

        file_cmb.getEditor().setOnMouseClicked(event ->{
            if(event.getButton().equals(MouseButton.PRIMARY)){
                if(event.getClickCount() == 2){
                    return;
                }
            }
            file_cmb.show();
        });

        file_cmb.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            moveCaret(file_cmb.getEditor().getText().length());
        });

        file_cmb.setOnKeyPressed(t -> file_cmb.hide());
        file_cmb.setOnKeyReleased(this);
    }
    private void moveCaret(int textLength) {
        file_cmb.getEditor().positionCaret(textLength);
    }
    public void handle(KeyEvent event) {

        if (event.getCode()== KeyCode.ENTER) {
            System.out.println("Enter key found");
            String s = file_cmb.getEditor().getText();
            //setDetailsAccordingToCombo(Constants.VENDOR_COMBO_BOX, s);
        }

        if ( event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN
                || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB
                ) {
            return;
        }

        if(event.getCode() == KeyCode.BACK_SPACE){
            String str = file_cmb.getEditor().getText();
            if (str != null && str.length() > 0) {
                str = str.substring(0, str.length() - 1);
            }
            if(str != null){
                file_cmb.getEditor().setText(str);
                moveCaret(str.length());
            }
            file_cmb.getSelectionModel().clearSelection();
        }

        if(event.getCode() == KeyCode.ENTER && file_cmb.getSelectionModel().getSelectedIndex()>-1)
            return;

        setItems();
    }
    private void setItems() {
        ObservableList list = FXCollections.observableArrayList();

        for (String datum : clientList) {
            String s = file_cmb.getEditor().getText().toLowerCase();
            if (datum.toLowerCase().contains(s.toLowerCase())) {
                list.add(datum.toString());
            }
        }

        if(list.isEmpty()) file_cmb.hide();

        file_cmb.setItems(list);
        file_cmb.show();
    }
}
