package Models;

import java.util.ArrayList;

public class ClientModel {

    int clientId;
    String clientCode, fileName, group, person1, person2, email1, email2, phone1, phone2;
    ArrayList<WorkModel> clientWork = new ArrayList<>();
    boolean isSelected= false;

    public ClientModel(int clientId, String clientCode, String fileName, String group, String person1, String person2, String email1, String email2, String phone1, String phone2, ArrayList<WorkModel> clientWork) {
        this.clientId = clientId;
        this.clientCode = clientCode;
        this.fileName = fileName;
        this.group = group;
        this.person1 = person1;
        this.person2 = person2;
        this.email1 = email1;
        this.email2 = email2;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.clientWork = clientWork;
    }

    public ClientModel(int clientId, String clientCode, String fileName, String group, String person1, String person2, String email1, String email2, String phone1, String phone2) {
        this.clientId = clientId;
        this.clientCode = clientCode;
        this.fileName = fileName;
        this.group = group;
        this.person1 = person1;
        this.person2 = person2;
        this.email1 = email1;
        this.email2 = email2;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public ClientModel(ArrayList<String> input) {
        clientCode = input.get(0);
        fileName = input.get(1);
        group = input.get(2);
        person1 = input.get(3);
        phone1 = input.get(4);
        email1 = input.get(5);
        person2 = input.get(6);
        phone2 = input.get(7);
        email2 = input.get(8);
        ArrayList<WorkModel> workModels = new ArrayList<>();
        if (input.get(9).equalsIgnoreCase("y"))
            workModels.add(new WorkModel(23));
        if (input.get(10).equalsIgnoreCase("y"))
            workModels.add(new WorkModel(18));

        if (input.get(11).equalsIgnoreCase("y"))
            workModels.add(new WorkModel(17));
        if (input.get(12).equalsIgnoreCase("y"))
            workModels.add(new WorkModel(28));
        if (input.get(13).equalsIgnoreCase("y"))
            workModels.add(new WorkModel(22));
        if (input.get(14).equalsIgnoreCase("y"))
            workModels.add(new WorkModel(26));
        if (input.get(15).equalsIgnoreCase("y"))
            workModels.add(new WorkModel(16));

        clientWork = workModels;
    }
    public String query() {
        String query = "INSERT INTO `client`(`code`, `file_name`, `group_name`, `conctact_person_1`, `conctact_person_2`, `contact_no_1`, `contact_no_2`, `contact_email_1`, `contact_email_2`) VALUES ('"+
                clientCode+"', '"+fileName+"', '"+group+"', '"+person1+"', '"+person2+"', '"+phone1+"', '"+phone2+"', '"+email1+"', '"+email2+"')";
        return query;
    }


    public int getClientId() {
        return clientId;
    }
    public ArrayList<String> getRowData() {
        ArrayList<String> result = new ArrayList<>();
        result.add(clientCode);
        result.add(fileName);
        result.add(group);
        result.add(phone1+"\n \n"+phone2);
        result.add(email1+"\n \n"+email2);
        result.add(person1+"\n \n" +person2);
        return result;
    }

    public String getString() {
        String s = clientCode+" " + fileName + " " +group + " " +person1 + " " + phone1;
        return  s.toLowerCase();
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPerson1() {
        return person1;
    }

    public void setPerson1(String person1) {
        this.person1 = person1;
    }

    public String getPerson2() {
        return person2;
    }

    public void setPerson2(String person2) {
        this.person2 = person2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public ArrayList<WorkModel> getClientWork() {
        return clientWork;
    }

    public void setClientWork(ArrayList<WorkModel> clientWork) {
        this.clientWork = clientWork;
    }
}
