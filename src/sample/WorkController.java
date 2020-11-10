package sample;

import Helpers.Constants;
import Helpers.IconHelper;
import Helpers.TableHelper;
import Helpers.WorkDataBaseHelper;
import Models.StageModel;
import Models.SubWorkModel;
import Models.WorkModel;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkController extends Base {


    @FXML
    protected Label userName_txt;
    @FXML
    private JFXListView<HBox> listView;

    @FXML
    private VBox tableVbox;
    ArrayList<WorkModel> workModels;

    WorkDataBaseHelper workDataBaseHelper = new WorkDataBaseHelper();
    public void initialize() {
        checkMaximize();

        //load work
        ArrayList<String> columnHeader = new ArrayList<>();
        columnHeader.add("Sno.");
        columnHeader.add("Work");
        columnHeader.add("No.Of Sub");

        HBox hBox = TableHelper.getColumnHeader(columnHeader);
        tableVbox.getChildren().add(hBox);

        userName_txt.setText(USERNAME);


       populateList();
    }

    void populateList() {
        listView.getItems().clear();
        workModels = workDataBaseHelper.getWorks();
        ArrayList<ArrayList> workRow = new ArrayList<>();

        for (int i=0;i<workModels.size();i++) {
            ArrayList<String> row = workModels.get(i).getWorkStringList();
            row.add(0, String.valueOf(i+1));
            HBox rowHbox = TableHelper.getTableRow(row);
            /*Add icons here*/

            IconHelper iconHelper = new IconHelper();
            ImageView editIcon = iconHelper.getIcon(IconHelper.ICON_EDIT);
            ImageView deleteIcon = iconHelper.getIcon(IconHelper.ICON_DELETE);
            ImageView addIcon = iconHelper.getIcon(IconHelper.ICON_ADD);

            JFXButton editBtn = new JFXButton(" ");
            editBtn.setGraphic(editIcon);
            editBtn.setId(i+"");

            JFXButton deleteBtn = new JFXButton(" ");
            deleteBtn.setGraphic(deleteIcon);
            deleteBtn.setId(i+"");

            JFXButton addButton = new JFXButton();
            addButton.setGraphic(addIcon);
            addButton.setId(i+"");

            editBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int id = getIdFromEvent(event);
                    if (id >= 0) {
                        System.out.println("Work " + id + " edit");
                        editWorkDialog(id);
                    }else {
                        System.out.println("edit id " + id);
                    }
                }
            });

            deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int id = getIdFromEvent(event);
                    if (id >= 0) {
                        System.out.println("Work " + id + " Delete");
                        deleteDialog(id);

                    }else {
                        System.out.println("delete id " + id);
                    }
                }
            });
            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int id = getIdFromEvent(event);
                    if (id >= 0) {
                        System.out.println("Work " + id + " Add");
                        addSubWorkDialog(id);

                    }else {
                        System.out.println("Add id " + id);
                    }
                }
            });

            rowHbox.getChildren().add(addButton);
            rowHbox.getChildren().add(editBtn);
            rowHbox.getChildren().add(deleteBtn);
            listView.getItems().add(rowHbox);
        }
    }
    @FXML
    void addRemarkAction(ActionEvent event) {
            remarksDialog();
    }

    @FXML
    void addWorkAction(ActionEvent event) {
        addWorkDialog();
    }
    public void addWorkDialog() {
        Label label0 = new Label("Please enter work description");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        JFXTextField txt = new JFXTextField();
        txt.setPromptText("Description");
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage("Please enter work description");
        txt.getValidators().add(new RequiredFieldValidator());

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, txt);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("ADD");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (txt.validate()) {
                    String str = txt.getText();
                    workDataBaseHelper.addWork(str);
                    displayToastMessage("work Recorded");
                    dialog.close();
                    populateList();
                }else {
                    displayToastMessage("Enter some text");
                }

            }
        });
        dialog.show();

    }
    public void editWorkDialog(int rowId) {
        WorkModel workModel = workModels.get(rowId);
        Label label0 = new Label(workModel.getWorkDes());
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        /*Sub work list*/
        JFXComboBox<String> subWorkList = new JFXComboBox<>();
        for (SubWorkModel subWorkModel: workModel.getSubWorkModels()) {
            subWorkList.getItems().add(subWorkModel.getSub_des());
        }
        subWorkList.setEditable(true);
        subWorkList.setPromptText("Sub Work");
        /*Stages Details*/
        JFXTextArea stagesDetails = new JFXTextArea();
        stagesDetails.setPromptText("Stage Details");

        subWorkList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = subWorkList.getSelectionModel().getSelectedIndex();
                String str = "";
                if (index>=0) {
                    for (StageModel stageModel: workModel.getSubWorkModels().get(index).getStageModels()) {
                        str+= stageModel.getDescription() + "\n";
                    }
                    str = str.trim();
                    stagesDetails.setText(str);
                }

            }
        });
        if (workModel.getSubWorkModels().size() == 0) {
            subWorkList.setVisible(false);
            String str="";
            for (StageModel stageModel: workModel.getStageModels()) {
                str+= stageModel.getDescription() + "\n";
            }
            str = str.trim();
            stagesDetails.setText(str);
        }

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, subWorkList, stagesDetails);
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

                String stageDet = stagesDetails.getText();
                String stage[] = stageDet.split("\n");
                int workId = workModel.getWork_id();

                int subWorkId = 0;

                if (stageDet.equals("")){
                    displayToastMessage("Enter Stage Details");
                    return;
                }
                if (workModel.getSubWorkModels().size() == 0) {
                    //delete prev stage
                    String query = "delete from stage where work_id = " + workId;
                    workDataBaseHelper.insertQuery(query);
                }

                if (workModel.getSubWorkModels().size() != 0) {
                    //deal with sub work
                    int index = subWorkList.getSelectionModel().getSelectedIndex();
                    String subWorkDesc = subWorkList.getSelectionModel().getSelectedItem();

                    if (index<0) {
                        //New Sub work
                        //update work sub work to 1
                        String query = "update  work set isSubWork=1 where work_id="+workModel.getWork_id();
                        workDataBaseHelper.insertQuery(query);

                        //entry sun work
                        query = "insert into sub_work (work_id, sub_work_des) values("+workId+", '"+subWorkDesc+"')";
                        workDataBaseHelper.insertQuery(query);

                        subWorkId = workDataBaseHelper.getRecentDataId("sub_work", "sub_work_id");

                    }else {
                        subWorkId = workModel.getSubWorkModels().get(index).getSub_work_id();

                        //delete prev stage
                        String query = "delete from stage where work_id = " + workId + " and sub_work_id = " + subWorkId;
                        workDataBaseHelper.insertQuery(query);
                    }
                }

                //Enter stage
                int stageNum = 0;
                for (String s : stage) {
                    ++stageNum;
                    String query = "insert into stage (work_id, sub_work_id, stage_num, stage_des) values("+workId+", " + subWorkId + ", "+stageNum + ", '"+s+"')";
                    workDataBaseHelper.insertQuery(query);
                }
                populateList();
                displayToastMessage("Work updated");
                dialog.close();
            }
        });
        dialog.show();

    }
    public void remarksDialog() {
        Label label0 = new Label("Remarks");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        /*Stages Details*/
        JFXTextArea remarksdetails = new JFXTextArea();
        remarksdetails.setPromptText("Enter remarks");

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, remarksdetails);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Update");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");
        layout.setActions(button);

        /*populate prev remarks*/
        String query = "select * from remarks";
        ResultSet resultSet = workDataBaseHelper.fetchQuery(query);
        try{
            String s = "";
            while (resultSet.next()) {
                s+=resultSet.getString("remark") + "\n";
            }
            remarksdetails.setText(s.trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String s = remarksdetails.getText().trim();
                String query = "delete from remarks";
                workDataBaseHelper.insertQuery(query);

                if (!s.equals("")) {
                    String remark[]  = s.split("\n");
                    for (String r: remark) {
                        query = "insert into remarks(remark) values('"+r+"')";
                        workDataBaseHelper.insertQuery(query);
                    }
                }
                displayToastMessage("Remarks updated");
                dialog.close();
            }
        });
        dialog.show();

    }
    public void addSubWorkDialog(int rowId) {
        WorkModel workModel = workModels.get(rowId);
        Label label0 = new Label(workModel.getWorkDes());
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        JFXTextField subWorkDesTxt = new JFXTextField();
        subWorkDesTxt.setPromptText("Work Description");
        /*Stages Details*/
        JFXTextArea stagesDetails = new JFXTextArea();
        stagesDetails.setPromptText("Stage Details");

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, subWorkDesTxt, stagesDetails);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("ADD");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String subWorkDesc = subWorkDesTxt.getText();
                String stageDet = stagesDetails.getText();

                if (stageDet.equals("")){
                    displayToastMessage("Enter Stage Details");
                    return;
                }
                String stage[] = stageDet.split("\n");
                int workId = workModel.getWork_id();
                int subWorkId = 0;
                if (!subWorkDesc.equals("")) {
                    String query = "update  work set isSubWork=1 where work_id="+workModel.getWork_id();
                    workDataBaseHelper.insertQuery(query);

                    //entry sun work
                    query = "insert into sub_work (work_id, sub_work_des) values("+workId+", '"+subWorkDesc+"')";
                    workDataBaseHelper.insertQuery(query);

                    subWorkId = workDataBaseHelper.getRecentDataId("sub_work", "sub_work_id");
                }else{
                    //Work does not sub work only stage
                    subWorkId = 0;
                }

                //Enter stage
                int stageNum = 0;
                for (String s : stage) {
                    ++stageNum;
                     String query = "insert into stage (work_id, sub_work_id, stage_num, stage_des) values("+workId+", " + subWorkId + ", "+stageNum + ", '"+s+"')";
                    workDataBaseHelper.insertQuery(query);
                }
                populateList();
                displayToastMessage("Work Added");

                subWorkDesTxt.setText("");
                stagesDetails.setText("");
                //dialog.close();
            }
        });
        dialog.show();

    }

    boolean subWorkPresent = false;
    public void deleteDialog(int rowId) {
        WorkModel workModel = workModels.get(rowId);

        Label label0 = new Label("Are you sure you want to delete");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));

        /*Sub work list*/
        JFXComboBox<String> subWorkList = new JFXComboBox<>();
        for (SubWorkModel subWorkModel: workModel.getSubWorkModels()) {
            subWorkList.getItems().add(subWorkModel.getSub_des());
        }

        /*Check box delete all*/
        JFXCheckBox deleteAllCheckBox = new JFXCheckBox("Delete All");


        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        if (workModel.getSubWorkModels().size() >0) {
            vBox.getChildren().add(subWorkList);
            subWorkList.getSelectionModel().selectFirst();
            vBox.getChildren().add(deleteAllCheckBox);
            subWorkPresent = true;
        }

        JFXButton button = new JFXButton("Delete");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (workModel.getSubWorkModels().size()==0 || deleteAllCheckBox.isSelected()) {
                    /*Delete work*/
                    String query = "delete from work where work_id = "+workModel.getWork_id();
                    workDataBaseHelper.insertQuery(query);
                }else {
                    /*Sub work present delete specific subwork*/
                    int index = subWorkList.getSelectionModel().getSelectedIndex();
                    if (index == -1) {
                        displayToastMessage("Please try again");
                        dialog.close();
                        return;
                    }
                    SubWorkModel subWorkModel = workModel.getSubWorkModels().get(index);
                    int id = subWorkModel.getSub_work_id();

                    String query = "delete from sub_work where sub_work_id = " + id;
                    workDataBaseHelper.insertQuery(query);

                    /*deleting satges*/
                    query = "delete from stage where sub_work_id="+id;
                    workDataBaseHelper.insertQuery(query);
                    if (subWorkList.getItems().size() == 1) {
                        //When last sub gets deleted remove entire work
                        query = "delete from work where work_id = "+workModel.getWork_id();
                        workDataBaseHelper.insertQuery(query);
                    }
                }
                populateList();
                displayToastMessage("Work updated");
                dialog.close();
            }
        });
        dialog.show();

    }


}
