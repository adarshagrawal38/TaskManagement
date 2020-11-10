package Models;

import Helpers.Constants;
import Helpers.DateFormatChange;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TaskModel {

    int taskId;
    String clientCode, fileName, initiator, workDescription;
    int workId;
    String period, year, priority, dueDate;
    int taskPriorityPosition;
    String status;
    int workBookId;
    String completedDate = "";
    ArrayList<WorkBookModel> taskWorkBook;
    boolean insertInDatabase = true;
    String subWork = "-";
    boolean isSelected = false;
    String selectedStage = "";

    WorkBookModel exportWorkBook;
    public TaskModel(int taskIs, String clientCode, String fileName, String initiator, String workDescription, int workId, String period, String year, String priority, String dueDate, int taskPriorityPosition, String status, int workBookId) {
        this.taskId = taskIs;
        this.clientCode = clientCode;
        this.fileName = fileName;
        this.initiator = initiator;
        this.workDescription = workDescription;
        this.workId = workId;
        this.period = period;
        this.year = year;
        this.priority = priority;
        this.dueDate = dueDate;
        this.taskPriorityPosition = taskPriorityPosition;
        this.status = status;
        this.workBookId = workBookId;
    }
    public TaskModel(ArrayList<String> list) {
        clientCode = list.get(0);
        fileName = list.get(1);
        initiator = list.get(2);
        workDescription = list.get(3);
        year = list.get(4);
        dueDate = DateFormatChange.changeForMatTo_YYYY_MM_DD(list.get(5));
        period = list.get(6);
        priority = list.get(7);
    }

    public long getDateDiff(int n) {
        long daysBetween = Constants.NOT_FOUND;
        try{
            String DD = DateFormatChange.changeForMatTo_MM_DD_YYYY(dueDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date = DD;
            LocalDate dueDateLocale = LocalDate.parse(date, formatter);
            //System.out.println(dueDateLocale.toString());
            LocalDate todayDate = LocalDate.now();
            //System.out.println(todayDate.toString());

            String dueStr = dueDateLocale.toString();
            String todayStr = todayDate.toString();
            if (dueStr.equals(todayStr))return 0;

            for (int i=1;i<n;i++) {
                todayStr = getDate(i);
                if (todayStr.equals(dueStr))daysBetween = i;
            }
            //DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            //LocalDate todayDateFormated = LocalDate.parse(todayDate.toString(), formatter2);


            //System.out.println ("Days: " + daysBetween);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daysBetween;
    }
    public  String getDate(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, i);
        Date date = calendar.getTime();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String dayOfTheWeek = simpleDateFormat.format(date);
        //System.out.println(dayOfTheWeek);
        //String dayOfTheWeek = date.toString();
        //String dayOfTheWeek = Date.format("dd-MM-yy", date );
        //System.out.println(dayOfTheWeek);
        return dayOfTheWeek;
    }

    public String getSelectedStage() {
        return selectedStage;
    }

    public void setSelectedStage(String selectedStage) {
        this.selectedStage = selectedStage;
    }

    public WorkBookModel getExportWorkBook() {
        return exportWorkBook;
    }

    public void setExportWorkBook(WorkBookModel exportWorkBook) {
        this.exportWorkBook = exportWorkBook;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isInsertInDatabase() {
        return insertInDatabase;
    }

    public void setInsertInDatabase(boolean insertInDatabase) {
        this.insertInDatabase = insertInDatabase;
    }

    public void incrementTaskPriority() {++taskPriorityPosition;};
    public void decrementTaskPriority() {--taskPriorityPosition;};
    public TaskModel(){}

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public ArrayList<WorkBookModel> getTaskWorkBook() {
        return taskWorkBook;
    }

    public void setTaskWorkBook(ArrayList<WorkBookModel> taskWorkBook) {
        this.taskWorkBook = taskWorkBook;
    }

    public String getString() {
        return clientCode + " " + fileName + " " + initiator + " " + workDescription + " " + " " + period + " " + year + " " + priority + " " + dueDate;
    }

    public boolean isContain(String s) {
        String t = clientCode + " " + fileName + " " + " " + initiator + " " + workDescription + " " + period + " " + period + " " + dueDate;
        return t.toLowerCase().contains(s.toLowerCase());
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWorkBookId() {
        return workBookId;
    }

    public void setWorkBookId(int workBookId) {
        this.workBookId = workBookId;
    }

    public void setTaskPriorityPosition(int taskPriorityPosition) {
        this.taskPriorityPosition = taskPriorityPosition;
    }

    public int getTaskId() {
        return taskId;
    }
    public boolean isPending() {
        return status.equalsIgnoreCase(Constants.PENDING);
    }

    public String getClientCode() {
        return clientCode;
    }

    public String getFileName() {
        return fileName;
    }

    public String getInitiator() {
        return initiator;
    }

    public int getWorkId() {
        return workId;
    }

    public String getPeriod() {
        return period;
    }

    public String getYear() {
        return year;
    }

    public String getPriority() {
        return priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getTaskPriorityPosition() {
        return taskPriorityPosition;
    }

    public String getStatus() {
        return status;
    }

    public String getSubWork() {
        return subWork;
    }

    public void setSubWork(String subWork) {
        this.subWork = subWork;
    }

    public ArrayList<String> getStrings() {
        ArrayList<String> result = new ArrayList<>();
        result.add(clientCode);
        result.add(fileName);
        result.add(initiator);
        result.add(workDescription);

        result.add(year);
        result.add(DateFormatChange.changeForMatTo_MM_DD_YYYY(dueDate));
        result.add(period);
        result.add(priority);
        result.add(status);
        return result;
    }
    public ArrayList<String> getStrings(boolean flag) {
        ArrayList<String> result = new ArrayList<>();
        result.add(clientCode);
        result.add(fileName);
        result.add(initiator);
        result.add(workDescription);
        result.add(subWork);
        result.add(year);
        result.add(DateFormatChange.changeForMatTo_MM_DD_YYYY(dueDate));
        result.add(period);

        return result;
    }
    public ArrayList<String> getStringsBlank() {
        ArrayList<String> result = new ArrayList<>();
        result.add("");
        result.add("");
        result.add("");
        result.add("");
        result.add("");
        result.add("");
        result.add("");
        result.add("");
        result.add("");
        return result;
    }


    public ArrayList<ArrayList<String>> getWorkModelStrings() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (WorkBookModel workBookModel: taskWorkBook) {
            ArrayList<String> row= new ArrayList<>();
            row.add(workBookModel.getAssigenedTo());
            row.add(workBookModel.getStageNumber() + " - " + workBookModel.getStageDes());
            row.add(workBookModel.getStageStatus());
            row.add(DateFormatChange.changeForMatTo_MM_DD_YYYY(workBookModel.getAssignedDate()));
            row.add(DateFormatChange.changeForMatTo_MM_DD_YYYY(workBookModel.getCompletedDate()));
            row.add(workBookModel.getRemarks());

            result.add(row);
        }

        return result;
    }
    public ArrayList<String> getExportWorkModelString() {
        ArrayList<String> row= new ArrayList<>();
        row.add(exportWorkBook.getAssigenedTo());
        row.add(exportWorkBook.getStageNumber() + " - " + exportWorkBook.getStageDes());
        row.add(exportWorkBook.getStageStatus());
        row.add(DateFormatChange.changeForMatTo_MM_DD_YYYY(exportWorkBook.getAssignedDate()));
        row.add(DateFormatChange.changeForMatTo_MM_DD_YYYY(exportWorkBook.getCompletedDate()));
        row.add(exportWorkBook.getRemarks());

        return row;
    }


    public ArrayList<ArrayList<String>> exportableData() {
        ArrayList<ArrayList<String>> workBookString = getWorkModelStrings();
        boolean header = true;
        ArrayList<ArrayList<String>> exportRow = new ArrayList<>();
        for (ArrayList<String> data: workBookString) {
            ArrayList<String> row = new ArrayList<>();
            row.addAll(getStrings());
            row.addAll(data);
            exportRow.add(row);
        }
        return exportRow;
    }
    public ArrayList<String> getSummaryExport() {
        ArrayList<ArrayList<String>> workBookString = getWorkModelStrings();
        boolean header = true;
        ArrayList<String> row = new ArrayList<>();
        row.addAll(getStrings());
        row.addAll(getExportWorkModelString());

        return row;
    }
}
