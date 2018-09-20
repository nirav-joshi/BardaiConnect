package demosample.nirav.com.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;

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
import demosample.nirav.com.utils.CustomFTextView;
import demosample.nirav.com.utils.IntentParameter;
import demosample.nirav.com.utils.pinview.PinView;
import demosample.nirav.com.welcome_screen.OnBoardingActivity;

public class OtpActivity extends AbstractBaseActivity implements ILoginView {
    @Inject
    ILoginPresenter<ILoginView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lbl_email)
    CustomFTextView lblEmail;

    @BindView(R.id.pinView)
    PinView pinView;
    @Inject
    Gson gson;
    @Inject
    DataManager dataManager;
    @BindView(R.id.btn_send)
    Button btnVerify;
    private String mPassword;
    private boolean isForResult;

    @Override
    protected int getContentView() {
        return R.layout.activity_otp;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setSupportActionBar(toolbar);
        showBackArrow();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(IntentParameter.EMAIL)) {
                lblEmail.setText(bundle.getString(IntentParameter.EMAIL));
            }
            if (bundle.containsKey(IntentParameter.PASSWORD)) {
                mPassword = bundle.getString(IntentParameter.PASSWORD);
            }
        }
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        mPresenter.onAttach(this);
        if (bundle != null && bundle.containsKey(IntentParameter.SELECTED_VALUE)) {
            isForResult = bundle.getBoolean(IntentParameter.SELECTED_VALUE);
        }
        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (pinView.getText().length() == 6)
                    btnVerify.setEnabled(true);
                else
                    btnVerify.setEnabled(false);

            }
        });
    }

    @OnClick(R.id.btn_send)
    public void btnVerifyClicked() {
        if (pinView.getText().length() == 6) {
            /*LoginInfoDto dto = new LoginInfoDto();
            dto.setEmail(lblEmail.getText().toString());
            dto.setOTP(pinView.getText().toString());
            showProgressDialog("", "Loading...");
            mPresenter.doOTPVerification(dto);*/

            LoginInfoDto dto = new LoginInfoDto();
            dto.setmUserName("oliver@arrow.com");
            dto.setmPassword("oliver");
            showProgressDialog(getString(R.string.pleasewait), getString(R.string.loading));
            mPresenter.doLogin(dto);
        }
    }


    @OnClick(R.id.lbl_resend)
    public void resendOTP() {
        showProgressDialog("", "Loading...");
        mPresenter.doForgetPassword(lblEmail.getText().toString());

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
        hideDialog();
        if (loginInfoDto != null) {
            OnBoardingActivity.loginInfoDto = loginInfoDto;
            String json = gson.toJson(loginInfoDto);
            dataManager.setLoginObject(json);
            if (isForResult) {
                finish();
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
    }

    @Override
    public void setErrorMessage(String message) {
        hideDialog();
        if (!AppUtility.isEmptyString(message)) {
            errorToast(message);
        }
    }


    @Override
    public void registerationCompleted(boolean isregisted) {

    }



    @Override
    public void otpVerificationSucess() {
        /*if (AppUtility.isNotEmptyString(mPassword)) {
            LoginInfoDto dto = new LoginInfoDto();
            dto.setmUserName(lblEmail.getText().toString());
            dto.setmPassword(mPassword);
            showProgressDialog(getString(R.string.pleasewait), getString(R.string.loading));
            mPresenter.doLogin(dto);
        } else {
            Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
            intent.putExtra(IntentParameter.EMAIL, lblEmail.getText().toString());
            intent.putExtra(IntentParameter.OTP, pinView.getText().toString());
            startActivity(intent);
            finish();
        }*/


    }

    @Override
    public void forgetLinkSentSucess() {
        hideDialog();
        informationToast("OTP sent sucessfully to your email.");
    }

    @Override
    public void getPasswordUpdateResponse(String response) {
        /* Deliberately left unimplemented */
    }

    @Override
    public void lockedUser(String lockedUser) {

    }

}
