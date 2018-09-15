package demosample.nirav.com.login;


import demosample.nirav.com.base.BaseView;

public interface ILoginView extends BaseView {
    void login(LoginInfoDto loginInfoDto);

    void setErrorMessage(String message);

    void registerationCompleted(boolean isregisted);



    void otpVerificationSucess();

    void forgetLinkSentSucess();

    void getPasswordUpdateResponse(String response);

    void lockedUser(String lockedUser);
}
