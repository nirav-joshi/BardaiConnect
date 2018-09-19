package demosample.nirav.com.registration;

import com.google.gson.annotations.SerializedName;

public class RegistrationDTO {
    private int gender;
    private String dob;
    private int height;
    @SerializedName("countryId")
    private int countryId;
    @SerializedName("stateId")
    private int stateId;
    @SerializedName("cityId")
    private int cityId;
    @SerializedName("countryName")
    private String countryName;
    @SerializedName("stateName")
    private String stateName;
    @SerializedName("cityName")
    private String cityName;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
