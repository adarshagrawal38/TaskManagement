package Models;

public class ReferenceModel {
    int ref_id;
    String work, des, path;

    public ReferenceModel(int ref_id, String work, String des, String path) {
        this.ref_id = ref_id;
        this.work = work;
        this.des = des;
        this.path = path;
    }

    public int getRef_id() {
        return ref_id;
    }

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
