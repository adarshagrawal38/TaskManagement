package Models;

import Helpers.Constants;
import Helpers.DateFormatChange;

import java.util.ArrayList;

public class WorkBookModel {

    int stageId;
    int stageNumber;
    String stageDes, assigenedTo, dueDate, stageStatus;
    int taskId;
    int workBookId;
    String completedDate = "";
    String remarks =  "";
    String assignedDate;
    boolean isSelected= false;


    public WorkBookModel() {
    }
    public WorkBookModel(ArrayList<String> list, int taskId) {
        assigenedTo = list.get(0);
        String stageNumAndDis[] = list.get(1).split("-");
        stageNumber = Integer.valueOf(stageNumAndDis[0].trim());
        stageDes = stageNumAndDis[1].trim();
        dueDate = DateFormatChange.changeForMatTo_YYYY_MM_DD(list.get(2));
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public ArrayList<String> getStrings() {
        ArrayList<String> result = new ArrayList<>();
        result.add(stageNumber+"");
        result.add(stageDes);
        result.add(assigenedTo);
        result.add(dueDate);
        result.add(stageStatus);
        result.add(remarks);
        return result;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        if (remarks==null)remarks="";
        this.remarks = remarks;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void decrementStage() {--stageNumber;}
    public void incrementStage(){++stageNumber;};
    public String getCompletedDate() {
        return completedDate;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public int getWorkBookId() {
        return workBookId;
    }

    public void setWorkBookId(int workBookId) {
        this.workBookId = workBookId;
    }

    public boolean isPending() {
        return stageStatus.equalsIgnoreCase(Constants.PENDING);
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public String getStageDes() {
        return stageDes;
    }

    public void setStageDes(String stageDes) {
        this.stageDes = stageDes;
    }

    public String getAssigenedTo() {
        return assigenedTo;
    }

    public void setAssigenedTo(String assigenedTo) {
        this.assigenedTo = assigenedTo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStageStatus() {
        return stageStatus;
    }

    public void setStageStatus(String stageStatus) {
        this.stageStatus = stageStatus;
    }
}

