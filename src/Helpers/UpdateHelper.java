package Helpers;

import Models.TaskModel;
import Models.WorkBookModel;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateHelper extends DatabaseHelper {

    public List<WorkBookModel> getWorkBookModel(int taskId) {
        List<WorkBookModel> result = new ArrayList<>();
        String query = "Select * from work_book where task_id = " + taskId + " order by stage_num";
        System.out.println(query);
        ResultSet resultSet = fetchQuery(query);

        try {
            while (resultSet.next()){
                WorkBookModel workBookModel = new WorkBookModel();
                workBookModel.setStageNumber(resultSet.getInt("stage_num"));
                workBookModel.setStageDes(resultSet.getString("stage_des"));
                workBookModel.setDueDate(resultSet.getDate("due_date").toString());
                workBookModel.setStageStatus(resultSet.getString("stage_status"));
                workBookModel.setAssigenedTo(resultSet.getString("assigned_to"));
                workBookModel.setWorkBookId(resultSet.getInt("wb_id"));

                String date = DateFormatChange.changeForMatTo_DD_MM_YY(workBookModel.getDueDate());
                workBookModel.setDueDate(date);
                result.add(workBookModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<WorkBookModel> getWorkBookModel(String assigned_to) {
        List<WorkBookModel> result = new ArrayList<>();
        String query = "Select * from work_book where assigned_to = '" + assigned_to + "' order by stage_num";
        System.out.println(query);
        ResultSet resultSet = fetchQuery(query);

        try {
            while (resultSet.next()){
                WorkBookModel workBookModel = new WorkBookModel();
                workBookModel.setStageNumber(resultSet.getInt("stage_num"));
                workBookModel.setStageDes(resultSet.getString("stage_des"));
                workBookModel.setDueDate(resultSet.getDate("due_date").toString());
                workBookModel.setStageStatus(resultSet.getString("stage_status"));
                workBookModel.setAssigenedTo(resultSet.getString("assigned_to"));
                workBookModel.setWorkBookId(resultSet.getInt("wb_id"));
                workBookModel.setTaskId(resultSet.getInt("task_id"));

                String date = DateFormatChange.changeForMatTo_DD_MM_YY(workBookModel.getDueDate());
                workBookModel.setDueDate(date);
                result.add(workBookModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addStage(int taskId, String work_des, String initiator , boolean toAll, int stageNum, String description, String dueDate)  {

        try{
            ArrayList<Integer> taskIds = new ArrayList<>();
            ArrayList<String> initiators = new ArrayList<>();
            if (!toAll){
                taskIds.add(taskId);
                initiators.add(initiator);
            }
            else {
                //add all taskid where according to work id
                String query = "Select * from task where work_des = '"+work_des +"'";
                ResultSet resultSet = fetchQuery(query);
                while (resultSet.next()) {
                    taskIds.add(resultSet.getInt("task_id"));
                    initiators.add(resultSet.getString("initiator"));
                }
                query = "select * from work where work_des = '" + work_des + "'";
                int id = 0;
                ResultSet rs = fetchQuery(query);
                rs.next();
                id = rs.getInt("work_id");

                query = "update stage set stage_num = stage_num + 1 where work_id = "+id + " and stage_num >="+stageNum;
                //System.out.println(query);
                insertQuery(query);

                query = "INSERT INTO `stage`(`work_id`, `stage_num`, `stage_des`) VALUES("+id + ","+stageNum+", '"+description+"')";
                //System.out.println(query);
                insertQuery(query);

            }
            for (int i=0;i<taskIds.size();i++) {
                int new_TeskId = taskIds.get(i);
                String new_initiator = initiators.get(i);
                List<WorkBookModel> workBookModels = getWorkBookModel(new_TeskId);
                int insertIndex = stageNum;
                String query = "update work_book set stage_num=stage_num + 1 where task_id = " + taskId + " and stage_num >="+stageNum;
                System.out.println(query);
                insertQuery(query);

                 query = "INSERT INTO `work_book`( `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`) VALUES ("+
                        new_TeskId + ", " + stageNum + " ,'" +description + "', '" + new_initiator + "', '" +
                        dueDate + "')";
                System.out.println(query);
                insertQuery(query);
                /*for (int j=stageNum-1;j<workBookModels.size();j++) {
                    query = "update work_book set stage_num=stage_num + 1 where wb_id = " + workBookModels.get(j).getWorkBookId();
                    System.out.println(query);
                    insertQuery(query);
                }*/



            /*Insert new Stage*/


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void updateStageNumber(WorkBookModel workBookModel) {
        String query = "update work_book set stage_num="+workBookModel.getStageNumber() + " where wb_id = " + workBookModel.getWorkBookId();
        insertQuery(query);
    }
    public void updateTaskPriority(TaskModel taskModel) {
        String query = "update task set task_priority_position = "+taskModel.getTaskPriorityPosition() + " where task_id = " + taskModel.getTaskId();
        insertQuery(query);
    }
    public void updateAssign_TO(String init, int wb_id) {
        String query = "update work_book set assigned_to='"+init + "' where wb_id = " + wb_id;
        insertQuery(query);
    }

}
