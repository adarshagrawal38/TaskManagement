package sample;

import Helpers.DatabaseHelper;
import Helpers.ImportTaskHelper;
import Helpers.TableHelper;
import Models.TaskModel;
import Models.WorkBookModel;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class ImportController extends Base {
    @FXML
    private VBox tableVbox;

    @FXML
    private JFXListView<HBox> listView;
    ArrayList<ArrayList<String>> tableRowData = new ArrayList<>();
    HBox colHeader_Hbox;

    public void initialize() {
        checkMaximize();
        ArrayList<String> colHeader = new ArrayList<>();
        //Task No.	Code	File Name	Assigner	Work	Year	Due Date	Period	Priority	Task Status	Assigned To	Current Task	Updated Status

        colHeader.add("Task No");
        colHeader.add("Code");
        colHeader.add("File Name");
        colHeader.add("Assigner");
        colHeader.add("Work");
        colHeader.add("Year");
        colHeader.add("mm-dd-yyyy");
        colHeader.add("Period");
        colHeader.add("Priority");
        colHeader.add("Assigned To");
        colHeader.add("Current Task");
        colHeader.add("StageDue");
         colHeader_Hbox = TableHelper.getColumnHeader(colHeader);
        listView.getItems().add(colHeader_Hbox);


        importTask();
    }


    @FXML
    void exportToDatabaseAction(ActionEvent event) {
        if (tableRowData.size() == 0) {
            displayToastMessage("Please import some data first");

        }else{
            getTaskModels();
            displayToastMessage("Data exported in database");
            tableRowData.clear();
            listView.getItems().clear();
            listView.getItems().add(colHeader_Hbox);

        }

    }

    @FXML
    void importAction(ActionEvent event) {
        importTask();
    }

    public void importTask() {
        ImportTaskHelper importTaskHelper = new ImportTaskHelper();
        //ArrayList<TaskModel> taskModelArrayList = importTaskHelper.openFileChooserAndReadFile();
        try {
            tableRowData = importTaskHelper.readExcelFile();

            for (ArrayList<String> list: tableRowData) {
                HBox tableRow_HBOX = TableHelper.getTableRow(list);
                listView.getItems().add(tableRow_HBOX);
            }

            displayToastMessage("Task Imported");
            //populateListView();
        } catch (InvalidFormatException e) {
            displayToastMessage(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            displayToastMessage(e.getMessage());
            e.printStackTrace();
        }

    }

    void getTaskModels() {

        ArrayList<String> uniqueTaskId = getUniqueTaskIds();
        DatabaseHelper databaseHelper = new DatabaseHelper();
        ArrayList<String> userList = databaseHelper.getUsers();

        String userString = "";
        for (String s : userList) {
            userString+=s + " ";

        }
        userString = userString.toLowerCase();

        for (String  s: uniqueTaskId) {
            ArrayList<ArrayList<String>> taskData = fillterRowData(uniqueTaskId.get(0));
            ArrayList<String> taskModelData = getSubList(1, 8, taskData.get(0));
            TaskModel taskModel = new TaskModel(taskModelData);
            ArrayList<WorkBookModel> workBookModels = new ArrayList<>();

            for (ArrayList<String> stageModelList: taskData) {
                ArrayList<String> stageModelData = getSubList(9, 11, stageModelList);
                /*Check User name*/
                String stageAssignedTo = stageModelList.get(0).toLowerCase();

                taskModel.setInsertInDatabase(userString.contains(stageAssignedTo));
                WorkBookModel workBookModel = new WorkBookModel(stageModelData, taskModel.getTaskId());
                workBookModels.add(workBookModel);
            }
            taskModel.setTaskWorkBook(workBookModels);
            databaseHelper.createTask(taskModel, true);
        }

    }
    ArrayList<String> getSubList(int startIndex, int endIndex, ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<>();
        for (int i=startIndex;i<=endIndex;i++) {
            result.add(list.get(i));
        }
        return result;

    }

    private ArrayList<ArrayList<String>> fillterRowData(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (ArrayList<String> list: tableRowData) {
            if (list.get(0).equals(s))result.add(list);
        }
        return result;
    }

    public ArrayList<String> getUniqueTaskIds() {
        Set<String> set = new HashSet<>();
        for (ArrayList<String> list: tableRowData) {
            set.add(list.get(0));
        }
        ArrayList<String> uniqueTaskIds = new ArrayList<>();
        uniqueTaskIds.addAll(set);
        return uniqueTaskIds;
    }
}
