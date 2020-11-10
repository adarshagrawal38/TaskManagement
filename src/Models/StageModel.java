package Models;

public class StageModel {
    int stageId;
    String description;
    int stageNum;

    public StageModel(int stageId, String description, int stageNum) {
        this.stageId = stageId;
        this.description = description;
        this.stageNum = stageNum;
    }

    public int getStageNum() {
        return stageNum;
    }

    public void setStageNum(int stageNum) {
        this.stageNum = stageNum;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

