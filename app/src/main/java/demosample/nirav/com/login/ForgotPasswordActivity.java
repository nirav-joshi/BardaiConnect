package demosample.nirav.com.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.utils.IntentParameter;

import static demosample.nirav.com.utils.AppUtility.isEmptyString;
import static demosample.nirav.com.utils.AppUtility.isValidEmail;


public class ForgotPasswordActivity extends AbstractBaseActivity implements ILoginView {

    @Inject
    ILoginPresenter<ILoginView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.forgot_email_wrapper)
    TextInputLayout forgotEmailWrapper;
    @BindView(R.id.edt_mail)
    AutoCompleteTextView edtMail;
    private boolean isForResult;

    @Override
    protected int getContentView() {
        return R.layout.activity_forgot_password;
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
        if (bundle != null && bundle.containsKey(IntentParameter.SELECTED_VALUE)) {
            isForResult = bundle.getBoolean(IntentParameter.SELECTED_VALUE);
        }
        mPresenter.onAttach(this);
    }

    @OnClick(R.id.btn_reset_password)
    public void btnResetPassword() {
        if (!isEmptyString(edtMail.getText().toString())
                && isValidEmail(edtMail.getText())) {
            showProgressDialog("", getString(R.string.loading));
            forgotEmailWrapper.setError(null);
            mPresenter.doForgetPassword(edtMail.getText().toString().trim());
        } else {
            forgotEmailWrapper.setError(getString(R.string.valid_email_required));
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
        if (!isEmptyString(message)) {
            errorToast(message);
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
        informationToast(getString(R.string.check_email_for_otp));
        startActivity(new Intent(this, OtpActivity.class).putExtra(IntentParameter.EMAIL, edtMail.getText()
                .toString().trim())
                .putExtra(IntentParameter.SELECTED_VALUE, isForResult));
        finish();
    }

    @Override
    public void getPasswordUpdateResponse(String response) {

    }

    @Override
    public void lockedUser(String lockedUser) {

    }
}
