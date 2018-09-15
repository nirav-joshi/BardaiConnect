package demosample.nirav.com.login;


import demosample.nirav.com.base.MvpPresenter;

public interface ILoginPresenter<V extends ILoginView>
        extends MvpPresenter<V> {
    void doLogin(LoginInfoDto loginInfoDto);

    void doRegister(LoginInfoDto loginInfoDto);

    void doOTPVerification(LoginInfoDto loginInfoDto);


    void doForgetPassword(String email);

    void doResetPassWord(LoginInfoDto loginInfoDto);

    void updatePassword(UpdatePasswordInfoDto updatePasswordInfoDto);
}
