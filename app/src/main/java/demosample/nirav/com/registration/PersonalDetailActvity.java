package demosample.nirav.com.registration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.chooser.ChooseAddressDialog;
import demosample.nirav.com.chooser.SelectDTO;
import demosample.nirav.com.utils.AppUtility;
import demosample.nirav.com.utils.IntentParameter;
import demosample.nirav.com.utils.ToolbarUtil;

import static demosample.nirav.com.utils.AppUtility.isNotEmptyString;
import static demosample.nirav.com.utils.DateUtil.DATE_FORMAT_SEPARATED;

public class PersonalDetailActvity extends AbstractBaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.rb_male)
    RadioButton rbMale;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;
    @BindView(R.id.edt_birth_date)
    TextInputEditText edtDob;

    @BindView(R.id.edt_country)
    EditText edtCountry;
    @BindView(R.id.edt_state)
    EditText edtState;
    @BindView(R.id.edt_city)
    EditText edtCity;

    private Calendar calDateGiven;
    public static RegistrationDTO registrationDTO;

    @Override
    protected int getContentView() {
        return R.layout.activity_personaldetail;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setSupportActionBar(toolbar);
        showBackArrow();
        btnNext.setEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View toolbarView = ToolbarUtil.addRemoveViewFromToolbar(this, R.layout
                .toolbar_title);
        TextView tvTitle = toolbarView.findViewById(R.id.txt_title);
        tvTitle.setText(getString(R.string.personaldetail));
    }

    @OnClick(R.id.btn_next)
    void onNextClick() {
        onConfirmationDialog();
    }

    @OnClick(R.id.edt_country)
    void onCountryClick() {
        Intent intent = new Intent(getApplicationContext(), ChooseAddressDialog.class);
        intent.putExtra(IntentParameter.CASE_ADDRESS, AppUtility.COUNTRY_TAG);
        intent.putExtra(IntentParameter.SEARCH_ID, 0);
        startActivityForResult(intent, AppUtility.COUNTRY_TAG);
    }

    @OnClick(R.id.edt_state)
    void onStateClick() {
        if (registrationDTO.getCountryId() > 0) {
            Intent intent = new Intent(getApplicationContext(), ChooseAddressDialog.class);
            intent.putExtra(IntentParameter.CASE_ADDRESS, AppUtility.STATE_TAG);
            // pass countryid over heere
            intent.putExtra(IntentParameter.SEARCH_ID, registrationDTO.getCountryId());
            startActivityForResult(intent, AppUtility.STATE_TAG);
        }
    }

    @OnClick(R.id.edt_city)
    void onCityClick() {
        if (registrationDTO.getStateId() > 0) {
            Intent intent = new Intent(getApplicationContext(), ChooseAddressDialog.class);
            intent.putExtra(IntentParameter.CASE_ADDRESS, AppUtility.CITY_TAG);
            // pass countryid over heere
            intent.putExtra(IntentParameter.SEARCH_ID, registrationDTO.getStateId());
            startActivityForResult(intent, AppUtility.CITY_TAG);
        }
    }

    @OnClick(R.id.edt_birth_date)
    void ondobClick() {
        chooseDate();
    }

    void validateForm() {
        if (registrationDTO.getGender() > 0) {
        //    if (registrationDTO.getHeight() > 0) {
                if (isNotEmptyString(registrationDTO.getDob())) {
                    if (registrationDTO.getCountryId() > 0) {
                        if (registrationDTO.getStateId() > 0) {
                            if (registrationDTO.getCityId() > 0) {
                                enableNextButton(true);
                            } else {
                                enableNextButton(false);
                            }

                        } else {
                            enableNextButton(false);
                        }
                    } else {
                        enableNextButton(false);
                    }
                } else {
                    enableNextButton(false);
                }
            } else {
                enableNextButton(false);
            }
        /*} else {
            enableNextButton(false);
        }*/
    }

    private void enableNextButton(boolean b) {
        btnNext.setEnabled(b);
    }

    private void chooseDate() {
        if (calDateGiven == null) {
            calDateGiven = Calendar.getInstance();
        }
        int year = calDateGiven.get(Calendar.YEAR);
        int month = calDateGiven.get(Calendar.MONTH);
        int day = calDateGiven.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, monthOfYear, dayOfMonth) -> {
            calDateGiven = Calendar.getInstance();
            calDateGiven.set(Calendar.YEAR, year1);
            calDateGiven.set(Calendar.MONTH, monthOfYear);
            calDateGiven.set(Calendar.DATE, dayOfMonth);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_SEPARATED, Locale.getDefault());
            String selectedDate = simpleDateFormat.format(calDateGiven.getTime());
            edtDob.setText(selectedDate);
            registrationDTO.setDob(selectedDate);
            validateForm();
        }, year, month, day);
        /*Set today as minimum date*/
        Calendar calDateGive1 = Calendar.getInstance();
        if (rbMale.isChecked())
            calDateGive1.add(Calendar.YEAR, -21);
        else
            calDateGive1.add(Calendar.YEAR, -18);
        datePickerDialog.getDatePicker().setMaxDate(calDateGive1.getTimeInMillis());
        datePickerDialog.show();
    }

    @OnCheckedChanged({R.id.rb_female, R.id.rb_male})
    public void onRadioButtonCheckChanged(CompoundButton button, boolean checked) {
        if (checked) {
            validateForm();
            switch (button.getId()) {
                case R.id.rb_female:
                    registrationDTO.setGender(1);
                    break;
                case R.id.rb_male:
                    registrationDTO.setGender(2);
                    break;
            }
        }
    }

    public void onConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalDetailActvity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.confirmation, null);
        builder.setView(dialogView);

        builder.setPositiveButton(getString(R.string.dialog_ok), (dialog, which) -> {
            dialog.dismiss();
            startActivity(new Intent(this, CarrierDetailActivity.class));
        });
        builder.setNegativeButton(getString(R.string.dialog_edit), (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppUtility.COUNTRY_TAG && resultCode == RESULT_OK) {
            if (data != null) {
                SelectDTO selectDTO = (SelectDTO) data.getSerializableExtra(IntentParameter.ADDRESS_DTO);
                registrationDTO.setCountryId(selectDTO.getValue());
                registrationDTO.setCountryName(selectDTO.getLabel());
                edtCountry.setText(registrationDTO.getCountryName());
                edtState.setHint(getString(R.string.state));
                edtCity.setHint(getString(R.string.city));
            }

        } else if (requestCode == AppUtility.STATE_TAG && resultCode == RESULT_OK) {
            SelectDTO selectDTO = (SelectDTO) data.getSerializableExtra(IntentParameter.ADDRESS_DTO);
            registrationDTO.setStateId(selectDTO.getValue());
            registrationDTO.setStateName(selectDTO.getLabel());
            edtState.setText(registrationDTO.getStateName());
            edtCity.setHint(getString(R.string.city));

        } else if (requestCode == AppUtility.CITY_TAG && resultCode == RESULT_OK) {
            SelectDTO selectDTO = (SelectDTO) data.getSerializableExtra(IntentParameter.ADDRESS_DTO);
            registrationDTO.setCityId(selectDTO.getValue());
            registrationDTO.setCityName(selectDTO.getLabel());
            edtCity.setText(registrationDTO.getCityName());
            validateForm();
        }

    }
}
