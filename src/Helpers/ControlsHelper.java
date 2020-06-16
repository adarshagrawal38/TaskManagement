package Helpers;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ControlsHelper {

    public static Label getLabel(String txt){
        Label l = new Label(txt);

        l.setPrefHeight(Constants.table_Hbox_height);
        l.setAlignment(Pos.CENTER);
        l.setFont(new Font(15));
        l.setStyle("-fx-text-fill: white;");

        return l;

    }
    public static Label getLabel(String txt, double width){
        Label l = new Label(txt);
        l.setStyle("-fx-text-fill: white;");
        l.setPrefWidth(width);
        l.setPrefHeight(Constants.table_Hbox_height);
        l.setAlignment(Pos.CENTER);
        l.setFont(new Font(Constants.LABEL_SIZE));
        return l;

    }

    /*public static HBox getTableRowHBox(Items items) {

        ArrayList<String> result = items.getItemInArrayList();




        for (int i=1;i<result.size();i++) {

            Label l = getLabel(result.get(i));
            if (i==Constants.DESCRIPTION_INDEX)l.setPrefWidth(Constants.DESCRIPTION_WIDTH);
            else l.setPrefWidth(Constants.COLUMN_WIDTH);

        }

        return null;

    }
*/
    public static JFXButton getTableButton(String s) {
        JFXButton editButton = new JFXButton(s);
        editButton.setPrefWidth(200);
        editButton.setPrefHeight(20);
        editButton.setFont(new Font(15));
        editButton.setStyle(Constants.Column_Hbox_BACKGROUND_COLOR);
        return editButton;
    }

    public static JFXButton getTableButton() {
        JFXButton editButton = new JFXButton();
        editButton.setPrefHeight(20);
        editButton.setStyle(Constants.TRANPARENT_COLOR);
        return editButton;
    }

    public static JFXButton getButton(String s) {
        JFXButton editButton = new JFXButton(s);
        editButton.setPrefWidth(120);
        editButton.setPrefHeight(60);
        editButton.setStyle("-fx-background-color:#F6F6F6;");
        return editButton;
    }

    public static JFXButton getButton(String s, boolean darkColor) {
        JFXButton editButton = new JFXButton(s);
        editButton.setPrefWidth(120);
        editButton.setPrefHeight(60);
        editButton.setStyle("-fx-background-color:#95A5A6;");
        return editButton;
    }

}
