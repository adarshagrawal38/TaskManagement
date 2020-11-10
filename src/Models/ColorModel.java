package Models;

import Helpers.DatabaseHelper;

public class ColorModel {
    int color_id;
    String col_des, col_code;

    String style = "";

    public ColorModel(int color_id, String col_des, String col_code) {
        this.color_id = color_id;
        this.col_des = col_des;
        this.col_code = col_code;
    }

    public String getStyle() {
        style = "-fx-background-color: "+col_code+";-fx-border-color: "+col_code+";  -fx-background-radius: 30em;";
        return style;
    }
    public void updateColorCode(String code) {
        String query = "update colors set color_code='"+code+"'";
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.insertQuery(query);

    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getCol_des() {
        return col_des;
    }

    public void setCol_des(String col_des) {
        this.col_des = col_des;
    }

    public String getCol_code() {
        return col_code;
    }

    public void setCol_code(String col_code) {
        this.col_code = col_code;
    }

}
