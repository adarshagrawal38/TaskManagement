package Models;

import java.util.ArrayList;

public class WorkModel {

    int work_id;
    String workDes;
    int isSubWorkPresent;
    ArrayList<SubWorkModel> subWorkModels = new ArrayList<>();
    ArrayList<StageModel> stageModels;

    public WorkModel(int work_id, String workDes, int isSubWorkPresent) {
        this.work_id = work_id;
        this.workDes = workDes;
        this.isSubWorkPresent = isSubWorkPresent;
    }

    public WorkModel(int work_id) {
        this.work_id = work_id;
    }

    public ArrayList<String> getWorkStringList() {
        /*TO display data to user*/
        ArrayList<String> result = new ArrayList<>();
        result.add(workDes);
        result.add(subWorkModels.size()+"");
        return result;
    }

    public int getWork_id() {
        return work_id;
    }

    public ArrayList<SubWorkModel> getSubWorkModels() {
        return subWorkModels;
    }

    public ArrayList<StageModel> getStageModels() {
        return stageModels;
    }

    public void setStageModels(ArrayList<StageModel> stageModels) {
        this.stageModels = stageModels;
    }

    public void setSubWorkModels(ArrayList<SubWorkModel> subWorkModels) {
        this.subWorkModels = subWorkModels;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public String getWorkDes() {
        return workDes;
    }

    public void setWorkDes(String workDes) {
        this.workDes = workDes;
    }

    public int getIsSubWorkPresent() {
        return isSubWorkPresent;
    }

    public void setIsSubWorkPresent(int isSubWorkPresent) {
        this.isSubWorkPresent = isSubWorkPresent;
    }
}
