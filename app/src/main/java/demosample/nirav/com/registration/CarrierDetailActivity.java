package demosample.nirav.com.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.utils.ToolbarUtil;

public class CarrierDetailActivity extends AbstractBaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected int getContentView() {
        return R.layout.activity_careerdetail;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setSupportActionBar(toolbar);
        showBackArrow();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View toolbarView = ToolbarUtil.addRemoveViewFromToolbar(this, R.layout
                .toolbar_title);
        TextView tvTitle = toolbarView.findViewById(R.id.txt_title);
        tvTitle.setText(getString(R.string.careerdetail));
    }

    @OnClick(R.id.btn_next)
    void onNextClick() {
        startActivity(new Intent(this, LogindetailformActivity.class));
    }


}
