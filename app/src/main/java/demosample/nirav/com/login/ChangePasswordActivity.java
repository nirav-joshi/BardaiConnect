package demosample.nirav.com.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.utils.AppUtility;
import demosample.nirav.com.welcome_screen.OnBoardingActivity;

public class ChangePasswordActivity extends AbstractBaseActivity implements ILoginView  {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_current_password)
    TextInputEditText edt_current_pass;
    @BindView(R.id.edt_new_password)
    TextInputEditText edt_new_pass;
    @BindView(R.id.edt_confirm_password)
    TextInputEditText edt_confirm_pass;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.btn_send)
    Button btn_contn;
    @BindView(R.id.rootView)
    View rootView;
    @Inject
    ILoginPresenter<ILoginView> mPresenter;


    @Override
    protected int getContentView() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ActivityComponent component = getActivityComponent();
        if(component!=null)
            component.inject(this);
        mPresenter.onAttach(this);
        setSupportActionBar(toolbar);
        showBackArrow();
        setTitle("");
        btn_contn.setEnabled(false);
    }

    @OnClick(R.id.btn_send)
    public void changePass()
    {
            showProgressBar();
            UpdatePasswordInfoDto updatePasswordInfo = new UpdatePasswordInfoDto();
            updatePasswordInfo.setEmail(OnBoardingActivity.loginInfoDto.getEmail());
            updatePasswordInfo.setCurrentPassword(edt_current_pass.getText().toString());
            updatePasswordInfo.setNewPassword(edt_new_pass.getText().toString());
            mPresenter.updatePassword(updatePasswordInfo);
    }

    private void showProgressBar()
    {
        progressBar.setVisibility(View.VISIBLE);
        rootView.setClickable(false);
    }

    private void hideProgressBar(){progressBar.setVisibility(View.GONE);rootView.setClickable(true);}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

                default:return super.onOptionsItemSelected(item);
        }
    }

    private void clearFields()
    {
        edt_current_pass.getText().clear();
        edt_new_pass.getText().clear();
        edt_confirm_pass.getText().clear();
        edt_current_pass.clearFocus();
        edt_new_pass.clearFocus();
        edt_confirm_pass.clearFocus();
    }

    private boolean validateInputs()
    {
        if(AppUtility.isEmptyString(edt_current_pass.getText().toString())) {
            edt_current_pass.setError("cannot be empty");
            return false;
        }
        if(AppUtility.isEmptyString(edt_new_pass.getText().toString())) {
            edt_new_pass.setError("cannot be empty");
            return false;
        }
        if(!edt_new_pass.getText().toString().equals(edt_confirm_pass.getText().toString())) {
            edt_confirm_pass.setError("New password and confirm password should be same");
            return false;
        }
        return true;
    }

    @Override
    public void login(LoginInfoDto loginInfoDto) {
        /*Deliberately left unimplemented*/
    }

    @Override
    public void setErrorMessage(String message) {
        /*Deliberately left unimplemented*/
    }

    @Override
    public void registerationCompleted(boolean isregisted) {
        /*Deliberately left unimplemented*/
    }


    @Override
    public void otpVerificationSucess() {

    }

    @Override
    public void forgetLinkSentSucess() {

    }

    @Override
    public void getPasswordUpdateResponse(String response) {
        hideProgressBar();
        if(response!=null){
            if(response.equals("1"))
                    {
                        OnBoardingActivity.loginInfoDto.setmPassword(edt_new_pass.getText().toString());
                        successToast("Password changed");
                    }
                    else
                        errorToast(response);
        }
        else errorToast("something went wrong");
        clearFields();
    }

    @Override
    public void lockedUser(String lockedUser) {

    }

    @OnTextChanged({R.id.edt_current_password,R.id.edt_new_password,R.id.edt_confirm_password})
    public void enableButton()
    {
        btn_contn.setEnabled(validateInputs());
    }

}
