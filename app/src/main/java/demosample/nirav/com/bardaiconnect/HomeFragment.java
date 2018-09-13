package demosample.nirav.com.bardaiconnect;

import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseFragment;

public class HomeFragment extends AbstractBaseFragment{
    @Override
    public int getContentView() {
        return R.layout.newlandhome;
    }
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
}
