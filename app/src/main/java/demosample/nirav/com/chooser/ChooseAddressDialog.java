package demosample.nirav.com.chooser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.utils.AppUtility;
import demosample.nirav.com.utils.DividerItemDecoration;
import demosample.nirav.com.utils.IntentParameter;

public class ChooseAddressDialog extends AbstractBaseActivity implements IAddressView {
    @Inject
    IAddressPresenter<IAddressView> mPresenter;
    @BindView(R.id.selectFor)
    TextView mSelectFor;
    @BindView(R.id.edtSearchText)
    EditText mSearchText;
    @BindView(R.id.rvSeach)
    RecyclerView mRecyclerView;
    @BindView(R.id.ivNotfound)
    AppCompatImageView ivNotFound;
    private SearchAddressAdapter adapter;
    private int mType;
    private SelectDTO localDTO = new SelectDTO();

    @Override
    protected int getContentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_address_chooser;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        mPresenter.onAttach(this);
        mSelectFor.setText(R.string.select_city);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,
                false));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.divider)));
        adapter = new SearchAddressAdapter(getApplicationContext());
        mRecyclerView.setAdapter(adapter);

        adapter.asViewObservable().subscribe(view -> {
            SelectDTO dto = adapter.getAttachedList().get(mRecyclerView.getChildAdapterPosition(view));
            returnValues(dto);
        });

        RxTextView.textChanges(mSearchText)
                .debounce(AppUtility.AFTER_TEXTED_CHANGE_QUANTUM_MILI, TimeUnit.MILLISECONDS)
                .subscribe(charSequence -> {
                    if (mSearchText.hasFocus()) {
                        loadValuesFromServer();
                    }
                });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            localDTO.setLabel("");
            localDTO.setValue(bundle.getInt(IntentParameter.SEARCH_ID, 0));
            mType = bundle.getInt(IntentParameter.CASE_ADDRESS);

        }
        prepareViews();
    }

    @OnClick(R.id.ivClose)
    void closeDialog()
    {
        setResult(RESULT_CANCELED);
        finish();
    }
    private void returnValues(SelectDTO dto) {
        localDTO.setValue(dto.getValue());
        localDTO.setLabel(dto.getLabel());
        Intent intent = new Intent();
        intent.putExtra(IntentParameter.ADDRESS_DTO, localDTO);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void loadValuesFromServer() {
        localDTO.setLabel(mSearchText.getText().toString().trim());
        mPresenter.getAddress(localDTO, mType);
    }

    private void prepareViews() {
        switch (mType) {
            case AppUtility.COUNTRY_TAG:
                mSelectFor.setText(getString(R.string.selectCountry));
                break;
            case AppUtility.STATE_TAG:
                mSelectFor.setText(getString(R.string.selectState));
                break;
            case AppUtility.CITY_TAG:
                mSelectFor.setText(getString(R.string.selectCity));
                break;

        }
        loadValuesFromServer();
    }


    @Override
    public void getValues(List<SelectDTO> list) {
        if(list!=null && !list.isEmpty())
        {
            ivNotFound.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter.addAll(list);
        }
        else
        {
            ivNotFound.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }
}
