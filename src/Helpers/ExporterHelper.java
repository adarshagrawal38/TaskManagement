package Helpers;

import Models.TaskModel;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class ExporterHelper {

    ArrayList<String> columnHeader = new ArrayList<>();

    public ExporterHelper() {
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
        columnHeader.add("Comp. Date");
        columnHeader.add("Remarks");
    }

    public ArrayList<ArrayList<String>> exportData(List<TaskModel> taskModels) {

        ArrayList<ArrayList<String>> tableRows = new ArrayList<>();

        /*Added colum header*/
        tableRows.add(columnHeader);

        int count = 1;
        for (TaskModel taskModel: taskModels) {
            ArrayList<ArrayList<String>> data = taskModel.exportableData();
            boolean first = true;
            for (ArrayList<String> strings: data) {
                strings.add(0, String.valueOf(count));
                tableRows.add(strings);
            }
            count++;
        }
        return tableRows;
    }

    public void saveDialog(List<TaskModel> taskModels) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(Main.stage);

        int index = file.getName().indexOf(".");
        index++;
        String extension = ".xlsx";
        String fileName = file.getName() + extension;
        String path = file.getAbsolutePath() + extension;

        ArrayList<ArrayList<String>> exportableData = exportData(taskModels);
        // Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("student Details");

        // This data needs to be written (Object[])

        // Iterate over data and write to sheet
        int rownum = 0;
        /*for (String key : keyset) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String)obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }*/


        for (ArrayList<String> rowData: exportableData) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            //rowData.add(0, String.valueOf(rownum));
            int cellnum = 0;
            for (String string: rowData) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(string);
            }
        }

        try {
            // this Writes the workbook gfgcontribute
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
            System.out.println(file + " written successfully on " + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
