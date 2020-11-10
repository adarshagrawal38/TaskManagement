package sample;

import Helpers.*;
import Models.WorkBookModel;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUsers extends Client {
    @FXML
    private VBox tableVbox;

    @FXML
    private JFXListView<HBox> listView;

    ArrayList<Integer> widthList = new ArrayList<>();

    ArrayList<HBox> tableRow = new ArrayList<>();
    String userNmae = "";
    boolean isAdmin = false;
    ArrayList<String> userList = new ArrayList<>();

    @FXML
    private Label userName_txt;

    public void initialize() {
        checkMaximize();

        FileHelper fileHelper = new FileHelper();
        userNmae = USERNAME;
        isAdmin = databaseHelper.isAdmin(userNmae);
        userName_txt.setText(userNmae);

        ArrayList<String> columnHeader = new ArrayList<>();
        columnHeader.add("Sno.");
        columnHeader.add("User Name");
        columnHeader.add("Total");
        columnHeader.add("Completed");
        columnHeader.add("Pending");
        columnHeader.add("Progress");

        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.DESCRIPTION_WIDTH);
        widthList.add(Constants.COLUMN_WIDTH);

        HBox columnHeader_Hbox = TableHelper.getColumnHeader(columnHeader, widthList);
        columnHeader_Hbox.setPadding(new Insets(5, 5,5,140));
        tableVbox.getChildren().add(columnHeader_Hbox);
        userList = databaseHelper.getUsers();
        populateList();
        super.initialize();
    }
    public void populateList() {

        listView.getItems().clear();
        int index = 0;
        for (String string: userList) {
            ArrayList<String> row = new ArrayList<>();

            row.add(String.valueOf(index + 1));
            row.add(string);
            double progress = 0.0;
            try{
                String query = "Select count(*) as count from work_book where assigned_to = '" + string + "'";
                ResultSet rs = databaseHelper.fetchQuery(query);
                int total = 1, comp=1;

                if (rs.next()) total = rs.getInt("count");

                query = query + " and stage_status = '"+Constants.COMPLETED + "' ";
                rs = databaseHelper.fetchQuery(query);
                if (rs.next()) comp = rs.getInt("count");

                if (total == 0) {
                    progress = 1.0;
                }else{
                    progress = (double) comp / total;
                }


                row.add(String.valueOf(total));
                row.add(String.valueOf(comp));
                row.add(String.valueOf(total - comp));

                HBox hBox = TableHelper.getTableRow(row, widthList);

                JFXProgressBar progressBar = new JFXProgressBar(progress);
                progressBar.setStyle(Constants.JFXPROGRESS_BAR);
                hBox.getChildren().add(progressBar);
                progressBar.setPrefHeight(20);
                progressBar.setPrefWidth(Constants.COLUMN_WIDTH);
                listView.getItems().add(hBox);
                progressBar.setProgress(progress);

                IconHelper iconHelper = new IconHelper();
                ImageView deleteImage = iconHelper.getIcon(Constants.ICON_DELETE);
                JFXButton deleteButton = ControlsHelper.getTableButton();
                deleteButton.setId(String.valueOf(index));
                deleteButton.setGraphic(deleteImage);

                JFXButton changePasswordBtn = ControlsHelper.getTableButton();
                changePasswordBtn.setGraphic(iconHelper.getIcon(IconHelper.ICON_PASSWORD));
                changePasswordBtn.setId(String.valueOf(index));

                changePasswordBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Object o =event.getSource();
                        String s = o.toString();
                        Pattern p = Pattern.compile(".*id=(\\d+),.*");
                        Matcher m = p.matcher(s);
                        if (m.matches()) {
                            int id = Integer.valueOf(m.group(1));
                            String userName = userList.get(id);
                            forgotPasswordDialog(userName);


                            /*ArrayList<TaskModel> userTaskDetails = databaseHelper.getUserTaskDetails(userName);
                            ExporterHelper exporterHelper = new ExporterHelper();
                            exporterHelper.saveDialog(userTaskDetails, true);*/
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

                hBox.getChildren().add(0, deleteButton);
                hBox.getChildren().add(1, changePasswordBtn);

                if (!isAdmin)deleteButton.setDisable(true);
                index++;


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void forgotPasswordDialog(String userNmae) {



        Label label0 = new Label(userNmae);
        label0.setFont(new Font("Segoi UI", 15));


        Label label2 = new Label("New Password");
        label2.setFont(new Font("Segoi UI", 15));

        JFXPasswordField newPassword = new JFXPasswordField();
        newPassword.setPromptText("New Password");

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, label2, newPassword);
        vBox.setSpacing(20);
        layout.setBody(vBox);

        JFXButton button = new JFXButton("Confirm");
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String userName =userNmae;
                if (userName.equals("")) {
                    displayToastMessage("Please First Enter user name");
                }else{
                    String np = newPassword.getText();
                    String query = "update user set password = '" + np + "' where user_name = '" + userName + "' ";
                    System.out.println(query);
                    databaseHelper.insertQuery(query);
                    displayToastMessage(userName + " password updated");
                }

                dialog.close();
            }
        });


        dialog.show();
    }
    public void deleteDaiLog(int rowId) {
        String username = userList.get(rowId);
        Label label0 = new Label("Do you want to delete " + username.toUpperCase());
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


                int userId = databaseHelper.getUserId(username);
                UpdateHelper updateHelper = new UpdateHelper();
                List<WorkBookModel> workBookModels = updateHelper.getWorkBookModel(username);
                for (WorkBookModel workBookModel: workBookModels) {
                    int taskId = workBookModel.getTaskId();
                    String initiator = "";
                    /*Getting Deleted user assign task*/
                    String query = "Select * from task where task_id = " + taskId;
                    System.out.println(query);
                    ResultSet resultSet = databaseHelper.fetchQuery(query);
                    try {
                        if (resultSet.next()) {
                            initiator = resultSet.getString("initiator");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    /*Assigning all task to initiator*/
                    updateHelper.updateAssign_TO(initiator, workBookModel.getWorkBookId());


                }
                String query = "delete from user where user_name = '" + username + "'";
                databaseHelper.insertQuery(query);
                userList.remove(username);
                populateList();
                dialog.close();

            }
        });
        dialog.show();

    }
}
