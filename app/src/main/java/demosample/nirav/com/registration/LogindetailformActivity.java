package demosample.nirav.com.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.login.OtpActivity;
import demosample.nirav.com.utils.IntentParameter;
import demosample.nirav.com.utils.ToolbarUtil;

import static demosample.nirav.com.utils.AppUtility.isEmptyString;
import static demosample.nirav.com.utils.AppUtility.isNotEmptyString;
import static demosample.nirav.com.utils.AppUtility.isValidEmail;
import static demosample.nirav.com.utils.AppUtility.isValidName;
import static demosample.nirav.com.utils.AppUtility.isValidPhone;
import static demosample.nirav.com.utils.ViewUtils.setTextInputError;

public class LogindetailformActivity extends AbstractBaseActivity{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.til_firstname)
    TextInputLayout tiFirstname;
    @BindView(R.id.til_lastname)
    TextInputLayout tiLastname;
    @BindView(R.id.til_email)
    TextInputLayout tiEmail;
    @BindView(R.id.til_phone)
    TextInputLayout tiPhone;
    @BindView(R.id.til_password)
    TextInputLayout tiPassword;

    @BindView(R.id.edt_firstname)
    EditText edtFirstName;
    @BindView(R.id.edt_lastname)
    EditText edtLastName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_phone)
    EditText edtphone;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    private RegistrationDTO  registrationDTO= PersonalDetailActvity.registrationDTO;
    @Override
    protected int getContentView() {
        return R.layout.activity_logindetail;

    }
    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setSupportActionBar(toolbar);
        showBackArrow();
        enableButton(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View toolbarView = ToolbarUtil.addRemoveViewFromToolbar(this, R.layout
                .toolbar_title);
        TextView tvTitle = toolbarView.findViewById(R.id.txt_title);
        tvTitle.setText(getString(R.string.logindetail));
    }


    @OnTextChanged(value = R.id.edt_phone,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterphoneInput(Editable editable) {
        if (!isValidPhone(editable.toString())) {
            setTextInputError(tiPhone, getString(R.string.invalidphone));
            edtphone.requestFocus();
        } else {
            setTextInputError(tiPhone, null);
        }
        validateEmptyForms();
    }

    @OnTextChanged(value = R.id.edt_firstname,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterFnameInput(Editable editable) {
        if (isEmptyString(editable.toString())) {
            setTextInputError(tiFirstname, getString(R.string.first_name_required));
            edtFirstName.requestFocus();
        } else if (!isValidName(editable.toString())) {
            setTextInputError(tiFirstname, getString(R.string.name_validation_message));
            edtFirstName.requestFocus();
        } else {
            setTextInputError(tiFirstname, null);
        }
        validateEmptyForms();
    }

    @OnTextChanged(value = R.id.edt_lastname,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterLnameInput(Editable editable) {
        if (isEmptyString(editable.toString())) {
            setTextInputError(tiLastname, getString(R.string.last_name_required));
            edtLastName.requestFocus();
        } else if (!isValidName(editable.toString())) {
            setTextInputError(tiLastname, getString(R.string.name_validation_message));
            edtLastName.requestFocus();
        } else {
            setTextInputError(tiLastname, null);
        }
        validateEmptyForms();
    }

    @OnTextChanged(value = R.id.edt_email, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void validEmailInput(Editable editable) {
        if (isEmptyString(editable.toString())) {
            setTextInputError(tiEmail, getString(R.string.email_required));
            edtEmail.requestFocus();
        } else if (!isValidEmail(editable.toString())) {
            setTextInputError(tiEmail, getString(R.string.valid_email_required));
            edtEmail.requestFocus();
        } else {
            setTextInputError(tiEmail, null);
        }
        validateEmptyForms();
    }

    void validateEmptyForms(){
        enableButton(false);
        if(isNotEmptyString(edtFirstName.getText().toString()))
            if(isNotEmptyString(edtLastName.getText().toString()))
               if(isNotEmptyString(edtEmail.getText().toString()))
                   if(isNotEmptyString(edtphone.getText().toString()))
                       if(isNotEmptyString(edtPassword.getText().toString()))
                           enableButton(true);
    }
    void enableButton(boolean isEnable){
        btnNext.setEnabled(isEnable);
    }

    @OnClick(R.id.btn_next)
    void onNextClick() {
       Intent intent=new Intent(getApplicationContext(), OtpActivity.class);
       intent.putExtra(IntentParameter.EMAIL,edtEmail.getText().toString());
       intent.putExtra(IntentParameter.PASSWORD,edtphone.getText().toString());
       startActivity(intent);
       finish();
    }
}
