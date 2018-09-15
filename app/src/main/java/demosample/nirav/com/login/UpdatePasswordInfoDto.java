
package demosample.nirav.com.login;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UpdatePasswordInfoDto {

    @SerializedName("currentPassword")
    private String mCurrentPassword;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("newPassword")
    private String mNewPassword;

    public String getCurrentPassword() {
        return mCurrentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        mCurrentPassword = currentPassword;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getNewPassword() {
        return mNewPassword;
    }

    public void setNewPassword(String newPassword) {
        mNewPassword = newPassword;
    }

}
