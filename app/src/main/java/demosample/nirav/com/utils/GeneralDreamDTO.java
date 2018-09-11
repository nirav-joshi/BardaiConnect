package demosample.nirav.com.utils;

import java.io.Serializable;

public class GeneralDreamDTO implements Serializable{
    private Integer mGoalID;
    private Integer mDreamId;
    private Integer mMilestoneId;

    public GeneralDreamDTO() {
    }

    public GeneralDreamDTO(Integer mDreamId, Integer mGoalID, Integer mMilestoneId) {
        if (mDreamId!= null && mDreamId > 0) {
            this.mDreamId = mDreamId;
        }
        if (mGoalID !=null && mGoalID > 0) {
            this.mGoalID = mGoalID;
        }
        if (mMilestoneId != null && mMilestoneId > 0) {
            this.mMilestoneId = mMilestoneId;
        }
    }

    public GeneralDreamDTO(Integer mDreamId){
        if (mDreamId!= null && mDreamId > 0) {
            this.mDreamId = mDreamId;
        }
    }

    public GeneralDreamDTO(Integer mDreamId, Integer mGoalID) {
        if (mDreamId!= null && mDreamId > 0) {
            this.mDreamId = mDreamId;
        }
        if (mGoalID !=null && mGoalID > 0) {
            this.mGoalID = mGoalID;
        }
    }
    

    public Integer getGoalID() {
        return mGoalID;
    }

    public void setGoalID(Integer mGoalID) {
        this.mGoalID = mGoalID;
    }

    public Integer getDreamId() {
        return mDreamId;
    }

    public void setDreamId(Integer mDreamId) {
        this.mDreamId = mDreamId;
    }

    public Integer getMilestoneId() {
        return mMilestoneId;
    }

    public void setMilestoneId(Integer mMilestoneId) {
        this.mMilestoneId = mMilestoneId;
    }
}
