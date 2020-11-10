package sample;

import Helpers.*;
import Models.ClientModel;
import Models.WorkModel;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class Client extends Base {
    @FXML
    private JFXListView<HBox> client_listView;
    @FXML
    private VBox tableVbox;
    @FXML
    private JFXComboBox<String> work_cmb;

    @FXML
    private  VBox client_tableBox;

    @FXML
    private JFXTextField fillter_txt;
    ArrayList<Integer> widthList = new ArrayList<>();

    public static ClientModel updateClient = null;

    int mouseTap = 0;
    ArrayList<ClientModel> clientModels = new ArrayList<>();
    ClientDataBaseHelper clientDataBaseHelper= new ClientDataBaseHelper();
    public void initialize() {
        checkMaximize();
        widthList.add(30);
        widthList.add(70);
        widthList.add(300);
        widthList.add(100);
        widthList.add(70);
        widthList.add(200);
        widthList.add(200);
        ArrayList<String> colItem = new ArrayList<>();
        colItem.add("Sno");
        colItem.add("Code");
        colItem.add("FileName");
        colItem.add("Group");
        colItem.add("Phone");
        colItem.add("Email");
        colItem.add("Person");
        clientModels = clientDataBaseHelper.getClient();

        //importClient();
        HBox colHeader = TableHelper.getColumnHeader(colItem, widthList);
        client_tableBox.getChildren().add(colHeader);
        populateList();
        WorkDataBaseHelper workDataBaseHelper = new WorkDataBaseHelper();
        for (WorkModel workModel: workDataBaseHelper.getWorks()) {
            work_cmb.getItems().add(workModel.getWorkDes());
        }
        work_cmb.getItems().add("All");
    }
    void importClient() {
        ImportTaskHelper importTaskHelper = new ImportTaskHelper();
        try{

            ArrayList<ArrayList<String>> rowdata = importTaskHelper.readExcelFile();
            System.out.println("data size: "+ rowdata.size() + " rowSize: " + rowdata.get(0).size());
            for (ArrayList<String> data: rowdata) {
                ClientModel clientModel = new ClientModel(data);
                String query = clientModel.query();
                clientDataBaseHelper.insertQuery(query);
                int clientId = clientDataBaseHelper.getRecentDataId("client", "client_id");
                for (WorkModel workModel: clientModel.getClientWork()) {
                    int workId = workModel.getWork_id();
                    query = "INSERT INTO `client_work`(`client_id`, `work_id`) VALUES("+clientId+", "+workId+")";
                    clientDataBaseHelper.insertQuery(query);
                }
            }
            System.out.println("All rercord inserted");
        } catch (InvalidFormatException e) {
            displayToastMessage(e.getMessage());
        } catch (IOException e) {
            displayToastMessage(e.getMessage());
        }
    }

     private void populateList() {

        client_listView.getItems().clear();
        int index=0;
        String selectedWork = work_cmb.getSelectionModel().getSelectedItem();
        String searchTxt = fillter_txt.getText();
        for (ClientModel clientModel: clientModels) {

            ArrayList<String> rowData = clientModel.getRowData();
            rowData.add(0, (index+1)+"");

            if (selectedWork!=null && !selectedWork.equals("All")) {
                boolean flag = false;
                ArrayList<WorkModel> workModels = clientModel.getClientWork();
                for (WorkModel workModel: workModels) {
                    if (workModel.getWorkDes().equals(selectedWork)) {
                        flag = true;
                        break;
                    }
                }
                /*when flag not found*/
                if (!flag) {
                    continue;
                }
            }

            if (clientModel.getString().contains(searchTxt.toLowerCase())) {
                HBox rowHbox = TableHelper.getTableRow(rowData, widthList);
                Label contactLabsl = (Label) rowHbox.getChildren().get(4);
                contactLabsl.setPrefHeight(60);
                Label emailLabel = (Label) rowHbox.getChildren().get(5);
                emailLabel.setPrefHeight(60);
                Label personLabel = (Label) rowHbox.getChildren().get(6);
                personLabel.setPrefHeight(60);


                rowHbox.setPrefHeight(60);
                rowHbox.setId(index+"");

                rowHbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        mouseTap++;
                        if (mouseTap%2 == 0) {
                            String s = event.getSource().toString().replace("]", "");
                            String s2[] = s.split("=");
                            int id = Integer.valueOf(s2[1]);
                            System.out.println("Mouse double clicked " + id);
                            updateClient = clientModels.get(id);
                            loadFrame(LocationHelper.CLIENT_ADD_UPDATE);
                        }
                    }
                });

            /*View Client work*/
                IconHelper iconHelper = new IconHelper();
                ImageView editIcon = iconHelper.getIcon(IconHelper.ICON_VIEW);
                ImageView deleteIcon = iconHelper.getIcon(IconHelper.ICON_DELETE);

                JFXButton viewWorkBtn = new JFXButton(" ");
                viewWorkBtn.setGraphic(editIcon);
                viewWorkBtn.setId(index+"");

                JFXButton deleteBtn = new JFXButton(" ");
                deleteBtn.setGraphic(deleteIcon);
                deleteBtn.setId(index+"");


                viewWorkBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int id = getIdFromEvent(event);
                        if (id >= 0) {
                            System.out.println("Client " + id + " view work");
                            viewWorkDailog(id);
                        }else {
                            System.out.println("client id " + id);
                        }
                    }
                });

                deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int id = getIdFromEvent(event);
                        if (id >= 0) {
                            System.out.println("Client " + id + " Delete");
                            deleteDialog(id);

                        }else {
                            System.out.println("client id " + id);
                        }
                    }
                });

                rowHbox.getChildren().add(viewWorkBtn);
                rowHbox.getChildren().add(deleteBtn);

                client_listView.getItems().add(rowHbox);
                ++index;
            }
        }
    }

    @FXML
    void addClientAction(ActionEvent event) {
        updateClient = null;
        loadFrame(LocationHelper.CLIENT_ADD_UPDATE);

    }
    public void viewWorkDailog(int rowId) {
        ArrayList<WorkModel> workModels = clientModels.get(rowId).getClientWork();
        Label label0 = new Label(clientModels.get(rowId).getFileName());
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE + 10));

        String works = "";
        for (WorkModel workModel: workModels) {
            works+=workModel.getWorkDes() + "\n";
        }
        Label label = new Label(works);
        label.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));


        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, label);
        vBox.setSpacing(20);
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
        dialog.show();

    }
    public void deleteDialog(int rowId) {
        ArrayList<WorkModel> workModels = clientModels.get(rowId).getClientWork();
        Label label0 = new Label(clientModels.get(rowId).getFileName());
        label0.setFont(new Font("Segoi UI", Constants.LABEL_SIZE + 10));

        Label label = new Label("Are you sure you want to delete!");
        label.setFont(new Font("Segoi UI", Constants.LABEL_SIZE));


        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Text(""));

        VBox vBox = new VBox(label0, label);
        vBox.setSpacing(20);
        layout.setBody(vBox);
        JFXButton button = new JFXButton("Yes");
        button.setPrefWidth(100);
        button.getStyleClass().add("btn-dialog");

        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(stack_pane, layout, JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int clientId = clientModels.get(rowId).getClientId();
                String query = "delete from client where client_id="+clientId;
                clientDataBaseHelper.insertQuery(query);
                populateList();
                dialog.close();
            }
        });
        dialog.show();

    }
    @FXML
    void fillterAction(KeyEvent event) {
        populateList();
    }
    @FXML
    void workSelected(ActionEvent event) {
        populateList();

    }


}
