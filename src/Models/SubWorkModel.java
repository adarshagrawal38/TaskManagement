package Models;

import java.util.ArrayList;

public class SubWorkModel {

    int sub_work_id;
    String sub_des;
    ArrayList<StageModel> stageModels;

    public SubWorkModel(int sub_work_id, String sub_des, ArrayList<StageModel> stageModels) {
        this.sub_work_id = sub_work_id;
        this.sub_des = sub_des;
        this.stageModels = stageModels;
    }

    public SubWorkModel(int sub_work_id, String sub_des) {
        this.sub_work_id = sub_work_id;
        this.sub_des = sub_des;
    }

    public int getSub_work_id() {
        return sub_work_id;
    }

    public void setSub_work_id(int sub_work_id) {
        this.sub_work_id = sub_work_id;
    }

    public String getSub_des() {
        return sub_des;
    }

    public void setSub_des(String sub_des) {
        this.sub_des = sub_des;
    }

    public ArrayList<StageModel> getStageModels() {
        return stageModels;
    }

    public void setStageModels(ArrayList<StageModel> stageModels) {
        this.stageModels = stageModels;
    }
}
