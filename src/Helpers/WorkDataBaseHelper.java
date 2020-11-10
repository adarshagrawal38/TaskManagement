package Helpers;

import Models.StageModel;
import Models.SubWorkModel;
import Models.WorkModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkDataBaseHelper extends DatabaseHelper {

    public void addWork(String work) {
        String query = "insert into work(work_des) values('"+work+"')";
        insertQuery(query);
    }
    public ArrayList<WorkModel> getWorks() {
        ArrayList<WorkModel> workModels = new ArrayList<>();
        try{
            String query = "Select * from work";
            ResultSet resultSet = fetchQuery(query);

            while (resultSet.next()) {
                int workId = resultSet.getInt("work_id");
                String workDes = resultSet.getString("work_des");
                int isSubWork = resultSet.getInt("isSubWork");
                WorkModel workModel = new WorkModel(workId, workDes, isSubWork);
                workModels.add(workModel);
            }
            //fetch subwork

            for (WorkModel workModel: workModels) {
                if (workModel.getIsSubWorkPresent() >= 1) {
                    query = "select * from sub_work where work_id = " + workModel.getWork_id();
                    resultSet = fetchQuery(query);
                    ArrayList<SubWorkModel> subWorkModels = new ArrayList<>();
                    while (resultSet.next()) {
                        int sub_work_id = resultSet.getInt("sub_work_id");
                        String sub_work_des = resultSet.getString("sub_work_des");

                        SubWorkModel subWorkModel = new SubWorkModel(sub_work_id, sub_work_des);
                        subWorkModels.add(subWorkModel);
                    }
                    workModel.setSubWorkModels(subWorkModels);
                }
            }

            //fetch Stages for subwork
            for (WorkModel workModel: workModels) {

                if (workModel.getIsSubWorkPresent() == 0) {
                    //sub work not present
                    query= "select * from stage where work_id = "+workModel.getWork_id();
                    resultSet = fetchQuery(query);
                    ArrayList<StageModel> stageModels = new ArrayList<>();
                    while (resultSet.next()) {
                        int stageId = resultSet.getInt("stage_id");
                        int stage_num = resultSet.getInt("stage_num");
                        String stage_des = resultSet.getString("stage_des");
                        StageModel stageModel = new StageModel(stageId, stage_des, stage_num);
                        stageModels.add(stageModel);

                    }
                    workModel.setStageModels(stageModels);
                }else{
                    //sub work present
                    for (SubWorkModel subWorkModel: workModel.getSubWorkModels()) {
                        query= "select * from stage where sub_work_id = "+subWorkModel.getSub_work_id();
                        resultSet = fetchQuery(query);
                        ArrayList<StageModel> stageModels = new ArrayList<>();
                        while (resultSet.next()) {
                            int stageId = resultSet.getInt("stage_id");
                            int stage_num = resultSet.getInt("stage_num");
                            String stage_des = resultSet.getString("stage_des");
                            StageModel stageModel = new StageModel(stageId, stage_des, stage_num);
                            stageModels.add(stageModel);

                        }
                        subWorkModel.setStageModels(stageModels);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workModels;
    }
    public ArrayList<WorkModel> getWorks(String work) {
        ArrayList<WorkModel> workModels = new ArrayList<>();
        try{
            String query = "Select * from work where work_des='"+work+"'";
            ResultSet resultSet = fetchQuery(query);

            while (resultSet.next()) {
                int workId = resultSet.getInt("work_id");
                String workDes = resultSet.getString("work_des");
                int isSubWork = resultSet.getInt("isSubWork");
                WorkModel workModel = new WorkModel(workId, workDes, isSubWork);
                workModels.add(workModel);
            }
            //fetch subwork

            for (WorkModel workModel: workModels) {
                if (workModel.getIsSubWorkPresent() >= 1) {
                    query = "select * from sub_work where work_id = " + workModel.getWork_id();
                    resultSet = fetchQuery(query);
                    ArrayList<SubWorkModel> subWorkModels = new ArrayList<>();
                    while (resultSet.next()) {
                        int sub_work_id = resultSet.getInt("sub_work_id");
                        String sub_work_des = resultSet.getString("sub_work_des");

                        SubWorkModel subWorkModel = new SubWorkModel(sub_work_id, sub_work_des);
                        subWorkModels.add(subWorkModel);
                    }
                    workModel.setSubWorkModels(subWorkModels);
                }
            }

            //fetch Stages for subwork
            for (WorkModel workModel: workModels) {

                if (workModel.getIsSubWorkPresent() == 0) {
                    //sub work not present
                    query= "select * from stage where work_id = "+workModel.getWork_id();
                    resultSet = fetchQuery(query);
                    ArrayList<StageModel> stageModels = new ArrayList<>();
                    while (resultSet.next()) {
                        int stageId = resultSet.getInt("stage_id");
                        int stage_num = resultSet.getInt("stage_num");
                        String stage_des = resultSet.getString("stage_des");
                        StageModel stageModel = new StageModel(stageId, stage_des, stage_num);
                        stageModels.add(stageModel);

                    }
                    workModel.setStageModels(stageModels);
                }else{
                    //sub work present
                    for (SubWorkModel subWorkModel: workModel.getSubWorkModels()) {
                        query= "select * from stage where sub_work_id = "+subWorkModel.getSub_work_id();
                        resultSet = fetchQuery(query);
                        ArrayList<StageModel> stageModels = new ArrayList<>();
                        while (resultSet.next()) {
                            int stageId = resultSet.getInt("stage_id");
                            int stage_num = resultSet.getInt("stage_num");
                            String stage_des = resultSet.getString("stage_des");
                            StageModel stageModel = new StageModel(stageId, stage_des, stage_num);
                            stageModels.add(stageModel);

                        }
                        subWorkModel.setStageModels(stageModels);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workModels;
    }
}
