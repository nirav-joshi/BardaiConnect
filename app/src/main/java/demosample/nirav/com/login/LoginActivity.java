package demosample.nirav.com.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import demosample.nirav.com.R;
import demosample.nirav.com.bardaiconnect.MainActivity;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.data.DataManager;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.utils.AppUtility;
import demosample.nirav.com.utils.IntentParameter;
import demosample.nirav.com.utils.OnTouchPasswordListener;
import demosample.nirav.com.utils.custom_button.ButtonWithProgressBar;
import demosample.nirav.com.welcome_screen.OnBoardingActivity;

import static demosample.nirav.com.utils.AppUtility.isEmptyString;
import static demosample.nirav.com.utils.AppUtility.isValidEmail;


public class LoginActivity extends AbstractBaseActivity implements ILoginView {
    @Inject
    ILoginPresenter<ILoginView> mPresenter;
    @Inject
    Gson gson;
    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login_email_wrapper)
    TextInputLayout loginEmailWrapper;
    @BindView(R.id.login_email_password_wrapper)
    TextInputLayout loginPasswordWrapper;
    @BindView(R.id.btn_login)
    ButtonWithProgressBar btnLogin;
    @BindView(R.id.edt_mail)
    EditText edtMail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    private boolean isForResult;

    @Override
    protected int getContentView() {
        return R.layout.activty_login;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setSupportActionBar(toolbar);
        showBackArrow();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        edtPassword.setOnTouchListener(new OnTouchPasswordListener(edtPassword));
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        mPresenter.onAttach(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(IntentParameter.SELECTED_VALUE)) {
            isForResult = bundle.getBoolean(IntentParameter.SELECTED_VALUE);
        }
        btnLogin.setPaddingProgress(8);
        btnLogin.setSpinningBarColor(Color.WHITE);
    }


    @OnClick(R.id.txt_register)
    public void clickRegister() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class).putExtra
                (IntentParameter
                        .SELECTED_VALUE, isForResult));
        finish();
    }

    @OnClick(R.id.btn_login)
    public void loginClicked() {
        AppUtility.hideSoftInputKeyboard(this);
        if (isValidData()) {
            btnLogin.startAnimation();
            LoginInfoDto dto = new LoginInfoDto();
            dto.setmUserName(edtMail.getText().toString().trim());
            dto.setmPassword(edtPassword.getText().toString().trim());
            mPresenter.doLogin(dto);
        }
    }

    private boolean isValidData() {
        if (!isEmptyString(edtMail.getText().toString())
                && isValidEmail(edtMail.getText().toString())) {
            loginEmailWrapper.setError(null);
            if (!isEmptyString(edtPassword.getText().toString())) {
                loginPasswordWrapper.setError(null);
                return true;
            } else {
                loginPasswordWrapper.setError(getString(R.string.password_required));
                return false;
            }
        } else {
            loginEmailWrapper.setError(getString(R.string.valid_email_required));
            return false;
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
        btnLogin.doneLoadingAnimation(getResources().getColor(R.color.green),R.drawable.ic_baseline_done_24px);
        btnLogin.stopAnimation(() -> {
            if (loginInfoDto != null) {
                OnBoardingActivity.loginInfoDto = loginInfoDto;
                String json = gson.toJson(loginInfoDto);
                dataManager.setLoginObject(json);
                if (isForResult) {
                    finish();
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    @OnClick(R.id.txt_forgot_password)
    public void forgotPasswordClicked() {
        startActivity(new Intent(this, ForgotPasswordActivity.class).putExtra(IntentParameter.SELECTED_VALUE,
                isForResult));
        finish();
    }

    @Override
    public void setErrorMessage(String message) {

        if (message.equalsIgnoreCase("verifyotp")) {
            Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
            intent.putExtra(IntentParameter.EMAIL, edtMail.getText().toString());
            intent.putExtra(IntentParameter.PASSWORD, edtPassword.getText().toString());
            startActivity(intent);
            finish();
        } else {
            btnLogin.doneLoadingAnimation(getResources().getColor(R.color.red_dark),R.drawable.ic_clear);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        btnLogin.revertAnimation();
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
        /*Deliberately left unimplemented*/
    }

    @Override
    public void lockedUser(String lockedUser) {
        btnLogin.doneLoadingAnimation(getResources().getColor(R.color.red_dark),R.drawable.ic_clear);
        Toast.makeText(this, lockedUser, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
        intent.putExtra(IntentParameter.EMAIL, edtMail.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        btnLogin.dispose();
    }
}
