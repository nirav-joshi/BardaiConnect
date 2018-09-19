package demosample.nirav.com.welcome_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.data.DataManager;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.login.LoginActivity;
import demosample.nirav.com.login.LoginInfoDto;
import demosample.nirav.com.registration.RegistrationDTO;

import static demosample.nirav.com.registration.PersonalDetailActvity.registrationDTO;


public class OnBoardingActivity extends AbstractBaseActivity  {

    public static LoginInfoDto loginInfoDto;
    @Inject
    DataManager dataManager;

    @BindView(R.id.viewpager)
    ViewPager pager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.txt_skip)
    TextView txtSkip;
    List<Integer> imageUrl;


    @Override
    protected int getContentView() {
        return R.layout.activity_on_boarding;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        registrationDTO=new RegistrationDTO();
        showWelcomeScreens();
        boolean mIsFirstTimeLoad = dataManager.checkForWelcomeScreen();
        /*if (mIsFirstTimeLoad) {
            showWelcomeScreens();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }*/
    }



    private void showWelcomeScreens() {
        imageUrl = new ArrayList<>();
        imageUrl.add(R.drawable.ic_basic);
        imageUrl.add(R.drawable.ic_basic);
        imageUrl.add(R.drawable.ic_basic);

        String[] messageArray = {
                getString(R.string.home),
                getString(R.string.home),
                getString(R.string.home),
        };

        String[] nameArray = {
                getString(R.string.home1),
                getString(R.string.home1),
                getString(R.string.home1),
        };
        PagerAdapter adapter = new SlidingImageAdapter(this, imageUrl, messageArray,nameArray);

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager, true);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == (tabLayout.getTabCount() - 1)) {
                    btnNext.setText("Let's start");
                    txtSkip.setVisibility(View.INVISIBLE);
                } else {
                    btnNext.setText("Next");
                    txtSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @OnClick(R.id.btn_next)
    public void nextClicked() {
        dataManager.setWelcomeScreen();
        if (tabLayout.getSelectedTabPosition() < tabLayout.getTabCount() - 1)
            pager.setCurrentItem(tabLayout.getSelectedTabPosition() + 1, true);
        else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @OnClick(R.id.txt_skip)
    public void skipClicked() {
        dataManager.setWelcomeScreen();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }



}