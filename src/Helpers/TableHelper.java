package Helpers;

import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class TableHelper {

    public static HBox getColumnHeader(ArrayList<String> data) {

        ArrayList<String> columnList = data;
        HBox hBox = getHBOX(data);
        hBox.setStyle(Constants.Column_Hbox_BACKGROUND_COLOR);

        return hBox;

    }
    public static HBox getColumnHeader(ArrayList<String> data, ArrayList<Integer> widthList) {

        ArrayList<String> columnList = data;
        HBox hBox = getHBOX(data, widthList);
        hBox.setStyle(Constants.Column_Hbox_BACKGROUND_COLOR);

        return hBox;

    }

    public static HBox getTableRow(ArrayList<String> inputs){
        HBox hBox = getHBOX(inputs);

        for (String s: inputs) {
            if (s.equalsIgnoreCase(Constants.COMPLETED)){
                hBox.setStyle(Constants.TABLE_ROW_COLOR_COMPLETED);
                break;
            }
        }

        return hBox;
    }
    public static HBox getTableRow(ArrayList<String> inputs, ArrayList<Integer> widthList){
        HBox hBox = getHBOX(inputs, widthList);

        for (String s: inputs) {
            if (s.equalsIgnoreCase(Constants.COMPLETED)){
                hBox.setStyle(Constants.TABLE_ROW_COLOR_COMPLETED);
                break;
            }
        }

        return hBox;
    }


    public static HBox getTableRowEditAble(ArrayList<String> inputs){
        HBox hBox = getHBOXEditText(inputs);

        return hBox;

    }

    public static HBox getHBOX(ArrayList<String> input) {

        Label l1 = ControlsHelper.getLabel(input.get(0), Constants.COLUMN_WIDTH);

        HBox hBox = new HBox(l1);
        hBox.setSpacing(Constants.COLOUMN_HBOX_SPACING);

        for (int i=1;i<input.size();i++) {
            Label l2 = ControlsHelper.getLabel(input.get(i));
            if (i==Constants.DESCRIPTION_INDEX || i== Constants.CODE_INDEX) {

                l2.setPrefWidth(Constants.DESCRIPTION_WIDTH);
            }else{
                l2.setPrefWidth(Constants.COLUMN_WIDTH);
            }

            hBox.getChildren().add(l2);
        }

        hBox.setPrefHeight(Constants.table_Hbox_height);
        hBox.setPadding(new Insets(5));
        hBox.setStyle(Constants.TABLE_ROW_COLOR);

        return hBox;
    }
    public static HBox getHBOXEditText(ArrayList<String> input) {

        Label l1 = ControlsHelper.getLabel(input.get(0), Constants.COLUMN_WIDTH);
        l1.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(l1);
        hBox.setSpacing(Constants.COLOUMN_HBOX_SPACING);

        for (int i=1;i<input.size();i++) {
            Label l2 = ControlsHelper.getLabel(input.get(i));
            if (i==Constants.DESCRIPTION_INDEX) {

                l2.setPrefWidth(Constants.DESCRIPTION_WIDTH);

                hBox.getChildren().add(l2);
            }else if (i>=3 && i<=11) {
                JFXTextField textField = new JFXTextField(input.get(i));
                textField.setPrefWidth(Constants.COLUMN_WIDTH);
                textField.setPrefHeight(10);
                textField.setFont(new Font(15));
                hBox.getChildren().add(textField);

            }
            else{
                l2.setPrefWidth(Constants.COLUMN_WIDTH);
                hBox.getChildren().add(l2);
            }


        }

        hBox.setPadding(new Insets(10));
        hBox.setStyle(Constants.BUTTON_BACKGROUND_COLOR);

        return hBox;
    }



    public static HBox getHBOX(ArrayList<String> input, ArrayList<Integer> width) {

        Label l1 = ControlsHelper.getLabel(input.get(0), width.get(0));

        HBox hBox = new HBox(l1);
        hBox.setSpacing(Constants.COLOUMN_HBOX_SPACING);

        for (int i=1;i<input.size();i++) {
            Label l2 = ControlsHelper.getLabel(input.get(i), width.get(i));
            hBox.getChildren().add(l2);
        }

        hBox.setPrefHeight(Constants.table_Hbox_height);
        hBox.setPadding(new Insets(5));
        hBox.setStyle(Constants.TABLE_ROW_COLOR);

        return hBox;
    }

}
