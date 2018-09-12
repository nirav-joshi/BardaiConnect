package demosample.nirav.com.bardaiconnect;

import android.content.Intent;
import android.os.Bundle;

import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.di.component.ActivityComponent;

public class MainActivity extends AbstractBaseActivity {



    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
