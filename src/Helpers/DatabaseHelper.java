package Helpers;

import Models.TaskModel;
import Models.WorkBookModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelper {


    Statement stamt;

    public final String DB_NAME = "task_management";
    public final String IP = "localhost";
    public final String username = "root";
    public final String password = "";



    public DatabaseHelper() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+IP +"/"+ DB_NAME, username, password);
            stamt = connection.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet fetchQuery(String query) {
        try {
            ResultSet resultSet = stamt.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getRecentDataId(String tableName, String colName) {
        int id= 0;
        try {
            String query = "Select * from " + tableName + " order by " + colName + " desc";
            ResultSet resultSet = stamt.executeQuery(query);
            if (resultSet.next()) {
                id = resultSet.getInt(colName);
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }
    public void insertQuery(String query) {
        try{
            System.out.println(query);
            stamt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Insertion Compeletd");
    }

    public String validateUser(String userName, String password) {
        String role = "";
        try{
            String query = "select * from user where user_name = '"+userName + "' and password = '" + password + "'";
            System.out.println(query);
            ResultSet resultSet = stamt.executeQuery(query);
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
    public String getUserRole(String username) {
        String role = "";
        try{
            String query = "select * from user where user_name = '"+username +"'";
            System.out.println(query);
            ResultSet resultSet = stamt.executeQuery(query);
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public ArrayList<String>  getUsers() {
        ArrayList<String> result = new ArrayList<>();
        try{
            String query = "Select * from user";
            ResultSet rs = fetchQuery(query);
            while (rs.next()) {
                result.add(rs.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int getUserId(String username) {
        int result = 0;
        try{
            String query = "Select * from user where user_name = '" + username + "'";
            ResultSet rs = fetchQuery(query);
            while (rs.next()) {
                result = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /*When all stage completed update task status*/
    public void updateTaskStatus(int taskId) {
        try{
            String query = "Select * from work_book where task_id = " + taskId + " and stage_status = '"+Constants.PENDING + "'";
            ResultSet resultSet = fetchQuery(query);
            if (resultSet.next()){
                /*update task status completed*/
                resultSet.close();
            }else{
                query = "update task set task_status = '" + Constants.COMPLETED + "' where task_id = " + taskId;
                System.out.println(query);
                insertQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Integer> getWorkId() {
        Map<String, Integer> workMap = new HashMap<>();
        try{
            String query = "Select * from work";
            ResultSet rs = fetchQuery(query);
            while (rs.next()) {
                workMap.put(rs.getString("work_des").toLowerCase(), rs.getInt("work_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workMap;
    }
    public void createTask(TaskModel taskModel) {

        try{
            String query = "INSERT INTO `task`(`client_code`, `file_name`, `Initiator`, `work_des`, `period`, `task_priority`, task_due_date, year) Values ('"+
                    taskModel.getClientCode() + "', '" + taskModel.getFileName() + "', '" + taskModel.getInitiator() + "', '"+taskModel.getWorkDescription() + "', '"+taskModel.getPeriod()+"', '"+
                    taskModel.getPriority() + "', '"+taskModel.getDueDate()+"', '" + taskModel.getYear() + "')";
            insertQuery(query);

            int taskId = getRecentDataId("task", "task_id");
            query = "update task set task_priority_position = "+taskId + " where task_id = " + taskId;
            System.out.println(query);

            insertQuery(query);

            int id = taskModel.getWorkId();
        /*take out work info*/
            List<WorkBookModel> workBookModels = new ArrayList<>();

            query = "Select * from stage where work_id = " + id + " order by stage_num";
            ResultSet resultSet = fetchQuery(query);

            while (resultSet.next()) {
                WorkBookModel workBookModel = new WorkBookModel();
                workBookModel.setStageNumber(resultSet.getInt("stage_num"));
                workBookModel.setStageDes(resultSet.getString("stage_des"));
                workBookModel.setTaskId(taskId);
                workBookModel.setAssigenedTo(taskModel.getInitiator());
                workBookModel.setDueDate(taskModel.getDueDate());

                workBookModels.add(workBookModel);
            }

        /*Insert work book data*/
            for (WorkBookModel workBookModel: workBookModels) {
                query = "INSERT INTO `work_book`( `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`) VALUES ("+
                        workBookModel.getTaskId() + ", " + workBookModel.getStageNumber() + " ,'" +workBookModel.getStageDes() + "', '" + workBookModel.getAssigenedTo() + "', '" +
                        workBookModel.getDueDate() + "')";
                System.out.println(query);

                insertQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void createTask(TaskModel taskModel, boolean imported) {

        try{
            String query = "INSERT INTO `task`(`client_code`, `file_name`, `Initiator`, `work_des`, `period`, `task_priority`, task_due_date, year) Values ('"+
                    taskModel.getClientCode() + "', '" + taskModel.getFileName() + "', '" + taskModel.getInitiator() + "', '"+taskModel.getWorkDescription() + "', '"+taskModel.getPeriod()+"', '"+
                    taskModel.getPriority() + "', '"+taskModel.getDueDate()+"', '" + taskModel.getYear() + "')";
            insertQuery(query);

            int taskId = getRecentDataId("task", "task_id");
            query = "update task set task_priority_position = "+taskId + " where task_id = " + taskId;
            System.out.println(query);

            insertQuery(query);

            int id = taskModel.getWorkId();
        /*take out work info*/
            List<WorkBookModel> workBookModels = taskModel.getTaskWorkBook();


        /*Insert work book data*/
            for (WorkBookModel workBookModel: workBookModels) {
                query = "INSERT INTO `work_book`( `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`) VALUES ("+
                        workBookModel.getTaskId() + ", " + workBookModel.getStageNumber() + " ,'" +workBookModel.getStageDes() + "', '" + workBookModel.getAssigenedTo() + "', '" +
                        workBookModel.getDueDate() + "')";
                System.out.println(query);

                insertQuery(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public List<TaskModel> getTask()  {
        List<TaskModel> result = new ArrayList<>();

        String query = "select * from task";
        ResultSet resultSet = fetchQuery(query);

        try {
            while (resultSet.next()) {
                TaskModel taskModel = new TaskModel(
                        resultSet.getInt("task_id"),
                        resultSet.getString("client_code"),
                        resultSet.getString("file_Name"),
                        resultSet.getString("initiator"),

                        resultSet.getString("work_des"),
                        0,
                        resultSet.getString("period"),
                        resultSet.getString("year"),
                        resultSet.getString("task_priority"),
                        resultSet.getDate("task_due_date").toString(),
                        resultSet.getInt("task_priority_position"),
                        resultSet.getString("task_status"),
                        0);

                String date = DateFormatChange.changeForMatTo_DD_MM_YY(taskModel.getDueDate());
                taskModel.setDueDate(date);
                System.out.println("Due date: " + date);
                result.add(taskModel);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




        return result;
    }

    public ArrayList<WorkBookModel> getWorkBook(int taskID) {


        String query = "Select * from work_book where task_id = " + taskID + " order by stage_num";
        System.out.println(query);
        ResultSet resultSet = fetchQuery(query);
        ArrayList<WorkBookModel> result = new ArrayList<>();

        try {
            while (resultSet.next()){
                WorkBookModel workBookModel = new WorkBookModel();
                workBookModel.setStageNumber(resultSet.getInt("stage_num"));
                workBookModel.setStageDes(resultSet.getString("stage_des"));
                workBookModel.setDueDate(resultSet.getDate("due_date").toString());
                workBookModel.setStageStatus(resultSet.getString("stage_status"));
                workBookModel.setAssigenedTo(resultSet.getString("assigned_to"));
                workBookModel.setWorkBookId(resultSet.getInt("wb_id"));
                Date compdate = resultSet.getDate("completed_date");
                workBookModel.setRemarks(resultSet.getString("remark"));
                if (compdate!=null) {
                    workBookModel.setCompletedDate(DateFormatChange.changeForMatTo_DD_MM_YY(compdate.toString()));
                }

                String date = DateFormatChange.changeForMatTo_DD_MM_YY(workBookModel.getDueDate());
                workBookModel.setDueDate(date);
                result.add(workBookModel);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void updatePriorityPosition(int taskId,int priority ) {
        System.out.println("Updating task " + taskId + " Priority to " + priority);
        String query = "update task set task_priority_position = " + priority + " where task_id = " + taskId;
        insertQuery(query);

    }

    public boolean isAdmin(String username) {
        String result = getUserRole(username);
        return result.equalsIgnoreCase("admin");
    }

}
