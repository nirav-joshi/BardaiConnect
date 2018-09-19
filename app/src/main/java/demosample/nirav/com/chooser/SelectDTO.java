
package demosample.nirav.com.chooser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SelectDTO implements Serializable{

    @SerializedName("label")
    private String mLabel;
    @SerializedName("value")
    private int mValue;
    @SerializedName("isSelected")
    private int mIsSelected;

    public SelectDTO(){}

    public SelectDTO(String mLabel , int mValue){
        this.mLabel = mLabel;
        this.mValue = mValue;
    }

    public SelectDTO(String mLabel, int mValue, int mIsSelected){
        this(mLabel,mValue);
        this.mIsSelected = mIsSelected;
    }

    public int isSelected() {
        return mIsSelected;
    }

    public void setSelected(int isSelected) {
        this.mIsSelected = isSelected;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }


    @Override
    public String toString() {
        return getLabel();
    }
}
