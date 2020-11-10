package sample;

import Helpers.*;
import Models.WorkModel;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class References extends Base {
    @FXML
    JFXListView<HBox> listView;
    @FXML
    private VBox tableVbox;

    @FXML
    protected Label userName_txt;

    ArrayList<Integer> colWidth = new ArrayList<>();
    public void initialize() {
        checkMaximize();
        ArrayList colHeader = new ArrayList();
        userName_txt.setText(USERNAME);
        colHeader.add("Sno");
        colHeader.add("Work");
        colHeader.add("Description");
        colWidth.add(Constants.COLUMN_WIDTH);
        colWidth.add(Constants.COLUMN_WIDTH);
        colWidth.add(Constants.DESCRIPTION_WIDTH * 2);
        HBox hBox = TableHelper.getColumnHeader(colHeader, colWidth);
        tableVbox.getChildren().add(hBox);
        populate();
    }
    @FXML
    void addReferenceAction(ActionEvent event) {
        addRef();

    }
    public void populate() {

        String query = "Select * from reference where user_name = '"+USERNAME+"'";
        ResultSet rs = databaseHelper.fetchQuery(query);
        listView.getItems().clear();
        try {
            int sno = 0;
            while (rs.next()) {
                sno++;
                int ref_id = rs.getInt("ref_id");
                String des = rs.getString("ref_des");
                String work = rs.getString("ref_work");
                String path = rs.getString("ref_path");

                ArrayList<String> rowData = new ArrayList<>();
                rowData.add(sno+"");
                rowData.add(work);
                rowData.add(des);

                IconHelper iconHelper = new IconHelper();
                HBox rowHbox = TableHelper.getTableRow(rowData, colWidth);
                JFXButton openFolder = new JFXButton(path);
                openFolder.setGraphic(iconHelper.getIcon(IconHelper.ICON_FOLDER));
                openFolder.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Desktop desktop = Desktop.getDesktop();
                        File file = new File(path);
                        try {
                            desktop.open(file);
                        } catch (IOException e) {
                            displayToastMessage(e.getMessage());
                            e.printStackTrace();
                        }

                    }
                });

                JFXButton deleteBtn = new JFXButton();
                deleteBtn.setGraphic(iconHelper.getIcon(IconHelper.ICON_DELETE));

                deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String query= "delete from reference where ref_id="+ref_id;
                        databaseHelper.insertQuery(query);
                        displayToastMessage("Reference Deleted");
                        populate();
                    }
                });
                rowHbox.getChildren().add(openFolder);
                rowHbox.getChildren().add(deleteBtn);
                listView.getItems().add(rowHbox);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addRef() {
        Label label0 = new Label("Reference description");
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));
        JFXTextField txt = new JFXTextField();
        txt.setPromptText("Description");

        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage("Please enter work description");
        txt.getValidators().add(new RequiredFieldValidator());

        JFXComboBox<String> workCmb = new JFXComboBox();
        workCmb.setPromptText("Select Work");
        WorkDataBaseHelper workDataBaseHelper = new WorkDataBaseHelper();
        ArrayList<WorkModel> workList = workDataBaseHelper.getWorks();
        for (WorkModel workModel: workList) {
            workCmb.getItems().add(workModel.getWorkDes());
        }

        JFXComboBox<String> userCmb = new JFXComboBox<>();
        userCmb.setPromptText("Select User");
        ArrayList<String> userList = workDataBaseHelper.getUsers();
        userCmb.getItems().addAll(userList);
        userCmb.getItems().add("All");

        JFXTextField pathTxt = new JFXTextField();
        pathTxt.setPromptText("Path of the folder");

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, txt, workCmb, userCmb, pathTxt);
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
                    String des = txt.getText().trim();
                    String selectedWork = workCmb.getSelectionModel().getSelectedItem();
                    String selectedUser = userCmb.getSelectionModel().getSelectedItem();
                    String path = pathTxt.getText();

                    path = path.replace("\\", "\\\\");
                    System.out.println("Path: " + path);
                    if (des.equals("") || path.equals("") ||selectedUser==null || selectedWork == null) {
                        displayToastMessage("Please fill all details!");
                        return;
                    }
                    if (selectedUser.equals("All")) {
                        for (String s: userList) {
                            String query = "INSERT INTO `reference`(`ref_work`, `ref_des`, `ref_path`, user_name) VALUES ('"+selectedWork+"', '"+des+"', '"+path+"','"+s+"')";
                            workDataBaseHelper.insertQuery(query);
                        }
                    }else {
                        String query = "INSERT INTO `reference`(`ref_work`, `ref_des`, `ref_path`, user_name) VALUES ('"+selectedWork+"', '"+des+"', '"+path+"','"+selectedUser+"')";
                        workDataBaseHelper.insertQuery(query);
                    }
                    displayToastMessage("Reference sent to user");
                    populate();
                    dialog.close();
                }else {
                    displayToastMessage("Enter some text");
                }

            }
        });
        dialog.show();

    }

}
