package Helpers;

import Models.ClientModel;
import Models.WorkBookModel;
import Models.WorkModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDataBaseHelper extends WorkDataBaseHelper {

    public ArrayList<ClientModel> getClient() {
        ArrayList<ClientModel> clientModels = new ArrayList<>();
        try{
            String query = "Select *  from client order by file_name";
            ResultSet rs = fetchQuery(query);
            while (rs.next()) {
                int clientId = rs.getInt("client_id");
                String code = rs.getString("code");
                String file_name = rs.getString("file_name");
                String group_name = rs.getString("group_name");
                String person1 = rs.getString("conctact_person_1");
                String person2 = rs.getString("conctact_person_2");
                String phone1 = rs.getString("contact_no_1");
                String phone2 = rs.getString("contact_no_2");
                String email1 = rs.getString("contact_email_1");
                String email2 = rs.getString("contact_email_2");

                ClientModel clientModel = new ClientModel(clientId, code, file_name, group_name, person1, person2, email1, email2, phone1, phone2);
                clientModels.add(clientModel);
            }

            for (ClientModel clientModel: clientModels) {
                query = "select * from work NATURAL JOIN client_work WHERE client_id="+clientModel.getClientId();
                rs = fetchQuery(query);
                ArrayList<WorkModel> workModels = new ArrayList<>();
                while (rs.next()) {
                    int work_id = rs.getInt("work_id");
                    String work_des = rs.getString("work_des");
                    int isSubWork = rs.getInt("isSubWork");
                    WorkModel workModel = new WorkModel(work_id, work_des, isSubWork);
                    workModels.add(workModel);
                }
                clientModel.setClientWork(workModels);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientModels;
    }

}
