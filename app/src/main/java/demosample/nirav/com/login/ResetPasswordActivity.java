package demosample.nirav.com.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import demosample.nirav.com.R;
import demosample.nirav.com.bardaiconnect.MainActivity;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.utils.AppConstants;

import static demosample.nirav.com.utils.AppUtility.isEmptyString;


public class ResetPasswordActivity extends AbstractBaseActivity implements ILoginView {

    @Inject
    ILoginPresenter<ILoginView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_new_password)
    EditText edtNewPassword;
    @BindView(R.id.edt_confirm_password)
    EditText edtConfirmPassword;
    @BindView(R.id.new_password_wrapper)
    TextInputLayout newPasswordWrapper;
    @BindView(R.id.confirm_password_wrapper)
    TextInputLayout confirmPasswordWrapper;
    LoginInfoDto loginInfoDto;
    private boolean isForResult;

    @Override
    protected int getContentView() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setSupportActionBar(toolbar);
        showBackArrow();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(AppConstants.EMAIL)) {
            loginInfoDto = new LoginInfoDto();
            loginInfoDto.setEmail(bundle.getString(AppConstants.EMAIL));
            loginInfoDto.setOTP((bundle.getString(AppConstants.OTP)));
        }
        if (bundle != null && bundle.containsKey(AppConstants.SELECTED_VALUE)) {
            isForResult = bundle.getBoolean(AppConstants.SELECTED_VALUE);
        }
        mPresenter.onAttach(this);
    }

    @OnTextChanged(value = R.id.edt_new_password,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterNewPassword(Editable editable) {
        if (edtNewPassword.getText().length() >= 6) {
            newPasswordWrapper.setError(null);
        } else {
            newPasswordWrapper.setError(getString(R.string.password_length_validation_message));
            edtNewPassword.requestFocus();
        }
    }

    @OnTextChanged(value = R.id.edt_confirm_password,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPassword(Editable editable) {
        if (edtConfirmPassword.getText().length() >= 6) {
            confirmPasswordWrapper.setError(null);
        } else {
            confirmPasswordWrapper.setError(getString(R.string.password_length_validation_message));
            edtConfirmPassword.requestFocus();

        }
    }

    @OnClick(R.id.btn_send)
    public void continueClicked() {
        if (!isEmptyString(edtNewPassword.getText().toString())) {
            newPasswordWrapper.setError(null);
            if (!isEmptyString(edtConfirmPassword.getText().toString())
                    && edtConfirmPassword.getText().toString().equals(edtNewPassword.getText().toString())) {
                confirmPasswordWrapper.setError(null);
                loginInfoDto.setmPassword(edtConfirmPassword.getText().toString());
                mPresenter.doResetPassWord(loginInfoDto);
            } else {
                confirmPasswordWrapper.setError("Confirm password should match new password!");
            }
        } else {
            newPasswordWrapper.setError(getString(R.string.password_required));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void login(LoginInfoDto loginInfoDto) {

    }

    @Override
    public void setErrorMessage(String message) {
        hideDialog();
        if (isEmptyString(message)) {
            Toast.makeText(this, "Your password has been changed successfully.", Toast.LENGTH_SHORT).show();
            if (isForResult) {
                finish();
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void registerationCompleted(boolean isregisted) {

    }


    @Override
    public void otpVerificationSucess() {

    }

    @Override
    public void forgetLinkSentSucess() {

    }

    @Override
    public void getPasswordUpdateResponse(String response) {
        /* Deliberately left unimplemented */
    }

    @Override
    public void lockedUser(String lockedUser) {

    }
}

