
package demosample.nirav.com.login;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LoginInfoDto {

    @SerializedName("authToken")
    private String mAuthToken;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("imageUrl")
    private Object mImageUrl;
    @SerializedName("lastName")
    private String mLastName;
    @SerializedName("userID")
    private int mUserID;
    @SerializedName("Phone")
    private String mPhoneNumber;
    @SerializedName("UserName")
    private String mUserName;
    @SerializedName("Password")
    private String mPassword;
    @SerializedName("BirthDate")
    private String mBirthDate;
    @SerializedName("ParentEmailId")
    private String mParentEmailId;

    @SerializedName("OTP")
    private String mOTP;
    @SerializedName("userType")
    private int mUserType;
    @SerializedName("userRole")
    private int mUserRole;

    public int getUserType() {
        return mUserType;
    }

    public void setUserType(int mUserType) {
        this.mUserType = mUserType;
        this.mUserRole=mUserType;
    }

    public void setmBirthDate(String mBirthDate) {
        this.mBirthDate = mBirthDate;
    }

    public void setmParentEmailId(String mParentEmailId) {
        this.mParentEmailId = mParentEmailId;
    }

    public String getOTP() {
        return mOTP;
    }

    public void setOTP(String mOTP) {
        this.mOTP = mOTP;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }


    public String getAuthToken() {
        return mAuthToken;
    }

    public void setAuthToken(String authToken) {
        mAuthToken = authToken;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Object getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getUserID() {
        return mUserID;
    }

    public void setUserID(int userID) {
        mUserID = userID;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

}
