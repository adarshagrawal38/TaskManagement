package Helpers;

import java.io.*;

public class FileHelper {

    String path = "C://Task Management//userName.txt";
    String folderPath = "C://Task Management//folder.txt";
    public void createFolder() {
        new File("C://Task Management").mkdirs();
    }

    /*public void logInUser(String userName) {

        try{

            File file = new File(path);
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw=new FileWriter(path);
            fw.write(userName);
            fw.close();
           *//* BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(userName);*//*

            System.out.println("user: " + userName + " login");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getUserName() {
        String userName = "";
        try{
            File file = new File(path);
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            if (file.exists()) {
                // variable declaration
                int ch;
                // check if File exists or not
                FileReader fr=null;
                try
                {
                    fr = new FileReader(path);
                }
                catch (FileNotFoundException fe)
                {
                    System.out.println("File not found");
                }

                // read from FileReader till the end of file
                while ((ch=fr.read())!=-1)
                    userName+=(char)ch;

                System.out.println("UserName: " + userName);

                // close the file
                fr.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userName;
    }
    public boolean isLogedIn(){
        boolean result = false;
        try{
            File file = new File(path);
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            if (file.exists()) {
                // variable declaration
                int ch;
                String userName = getUserName();
                if (!userName.equals(""))result = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void logout() {
        FileWriter fw= null;
        try {
            fw = new FileWriter(path);
            fw.write("");
            fw.close();
            System.out.println("logout User");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public String getFolderPath(){
        String result = "";
        try{
            File file = new File(folderPath);
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            if (file.exists()) {
                // variable declaration
                int ch;
                // check if File exists or not
                FileReader fr=null;
                try
                {
                    fr = new FileReader(folderPath);
                }
                catch (FileNotFoundException fe)
                {
                    System.out.println("File not found");
                }

                // read from FileReader till the end of file
                while ((ch=fr.read())!=-1)
                    result+=(char)ch;

                System.out.println("Folder Path: " + result);

                // close the file
                fr.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setFolderPath(String location) {
        try{

            File file = new File(folderPath);
            file.getParentFile().mkdirs(); // Will create parent directories if not exists
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw=new FileWriter(folderPath);
            fw.write(location);
            fw.close();
           /* BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(userName);*/
            System.out.println("Loacation: " + location + " written");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
