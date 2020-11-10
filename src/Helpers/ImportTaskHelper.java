package Helpers;

import Models.TaskModel;
import javafx.stage.FileChooser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.Base;
import sample.Main;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class ImportTaskHelper extends Base {


    public ArrayList<TaskModel> openFileChooserAndReadFile() {
        FileChooser fileChooser = new FileChooser();
        ArrayList<TaskModel> dataList = new ArrayList<>();
        File file = fileChooser.showOpenDialog(Main.stage);

        int index = file.getName().indexOf(".");
        index++;
        String extension = file.getName().substring(index);

        System.out.println(extension);
        ArrayList<TaskModel> taskModels = new ArrayList<>();

        try {
            if (extension.equals("xlsx") || extension.equals("xls") || extension.equals("xlsm") || extension.startsWith("xl")) {
                Workbook workbook = WorkbookFactory.create(file);
                //Getting sheet 0
                Sheet sheet = workbook.getSheetAt(0);

                //Data formatter to format cell
                DataFormatter dataFormatter = new DataFormatter();

                System.out.println("\nIterating over row and column using iterator\n");

                Iterator<Row> rowIterator = sheet.rowIterator();

                int i = 0;
                while (rowIterator.hasNext()) {

                    Row row = rowIterator.next();

                    //Now lets us iterate over column of current row
                    Iterator<Cell> cellIterator = row.cellIterator();
                    String data = "";

                    ArrayList<String> strings = new ArrayList<>();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        String cellValue = dataFormatter.formatCellValue(cell);
                        strings.add(cellValue);
                    }
                    if (i>0) {
                        /*this is to avoid first row*/
                        TaskModel taskModel = new TaskModel(strings);
                        taskModels.add(taskModel);
                        System.out.println(taskModel.getString());
                    }
                    i++;
                }
                //System.out.println(dataList);
            }else {
                System.out.println("Not valid file");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return taskModels;
    }

    public ArrayList<ArrayList<String>> readExcelFile() throws IOException, InvalidFormatException {
        FileChooser fileChooser = new FileChooser();
        ArrayList<TaskModel> dataList = new ArrayList<>();
        File file = fileChooser.showOpenDialog(Main.stage);
        //file.setReadOnly();

        int index = file.getName().indexOf(".");
        index++;
        String extension = file.getName().substring(index);

        System.out.println(extension);
        ArrayList<ArrayList<String>> tableRows = new ArrayList<>();
        if (extension.equals("xlsx") || extension.equals("xls") || extension.equals("xlsm") || extension.startsWith("xl")) {
            Workbook workbook = WorkbookFactory.create(file);
            //Getting sheet 0
            Sheet sheet = workbook.getSheetAt(0);

            //Data formatter to format cell
            DataFormatter dataFormatter = new DataFormatter();

            System.out.println("\nIterating over row and column using iterator\n");

            Iterator<Row> rowIterator = sheet.rowIterator();

            int i = 0;
            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                //Now lets us iterate over column of current row
                Iterator<Cell> cellIterator = row.cellIterator();
                String data = "";

                ArrayList<String> strings = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(cell);
                    data+=cellValue + " ";
                    strings.add(cellValue);
                }
                if (i>0 && !data.trim().equals("")) {
                        /*this is to avoid first row*/
                    //TaskModel taskModel = new TaskModel(strings);
                    tableRows.add(strings);
                    //System.out.println(taskModel.getString());
                }
                i++;
            }
            workbook.close();
            //System.out.println(dataList);
        }else {
            System.out.println("Not valid file");
        }
       /* try {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
*/
        return tableRows;
    }






}
