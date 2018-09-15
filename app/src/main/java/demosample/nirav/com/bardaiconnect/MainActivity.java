package demosample.nirav.com.bardaiconnect;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import demosample.nirav.com.R;
import demosample.nirav.com.base.AbstractBaseActivity;
import demosample.nirav.com.data.DataManager;
import demosample.nirav.com.di.component.ActivityComponent;
import demosample.nirav.com.login.LoginActivity;
import demosample.nirav.com.login.LoginInfoDto;
import demosample.nirav.com.utils.AppUtility;
import demosample.nirav.com.utils.FragmentChangeListener;
import demosample.nirav.com.welcome_screen.OnBoardingActivity;

import static demosample.nirav.com.utils.AppUtility.isEmptyString;

public class MainActivity extends AbstractBaseActivity implements
        FragmentChangeListener, NavigationView
        .OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @Inject
    DataManager dataManager;
    @Inject
    Gson gson;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private TextView mInitial = null;
    private TextView fullname = null;
    private String currentFragmentName;
    public static boolean mNewLogin = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setSupportActionBar(toolbar);
        mNewLogin = false;
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        setObjectFromPref();

        //BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(itemClick(R.id.nav_home));

        View headerView = navigationView.getHeaderView(0);
        mInitial = headerView.findViewById(R.id.item_user_image);
        fullname = headerView.findViewById(R.id.item_user_title);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            navigationView.dispatchSetSelected(true);
            replaceFragment(itemClick(item.getItemId()));
            return true;
        });
        //setupDrawerItems();

        updateBottomNavigationIconSize();
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (AppUtility.openNextFragment > 0) {
            currentFragmentName = null;//for refresh current fragment
            replaceFragment(itemClick(AppUtility.openNextFragment));
            AppUtility.openNextFragment = 0;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateDrawerAndNavigation();
    }

    private void updateDrawerAndNavigation() {
        Fragment selectedFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        currentFragmentName = selectedFragment.getClass().getName();
       /* if (selectedFragment instanceof TempHomeFragment) {
            updateNavigationBarState(R.id.nav_home);
            updateDrawerNavigationSelection(R.id.nav_home);
        }
        else if (selectedFragment instanceof BookmarkFragment) {
          *//*  updateNavigationBarState(R.id.nav_search);
            updateDrawerNavigationSelection(R.id.nav_search);*//*
        } else if (selectedFragment instanceof PlansTabFragment) {
            updateNavigationBarState(R.id.nav_goal);
            updateDrawerNavigationSelection(R.id.nav_goal);
        } else if (selectedFragment instanceof BuddyTabFragment) {
            updateNavigationBarState(R.id.nav_buddy);
            updateDrawerNavigationSelection(R.id.nav_buddy);
        }else if (selectedFragment instanceof AllSearchResultFragment) {
            updateNavigationBarState(R.id.nav_resource);
            updateDrawerNavigationSelection(R.id.nav_resource);
        } else if (selectedFragment instanceof CalenderFragment) {
            updateNavigationBarState(R.id.nav_calendar);
            updateDrawerNavigationSelection(R.id.nav_calendar);
        }*/
    }

    private void setObjectFromPref() {
        String objectJson = dataManager.getLoginObject();
        if (!isEmptyString(objectJson))
         OnBoardingActivity.loginInfoDto = gson.fromJson(objectJson, LoginInfoDto.class);
    }

    /**
     * get fragment object
     *
     * @param id FragmentId
     * @return Fragment
     */
    private Fragment itemClick(int id) {
        updateNavigationBarState(id);
        updateDrawerNavigationSelection(id);

        //set bundle for evaluation, barrier and rewards screen
        switch (id) {
            case R.id.nav_home:
                return HomeFragment.newInstance();
            case R.id.nav_home1:
                startActivity(new Intent(MainActivity.this,
                        LoginActivity.class));
                finish();
                return  null;


            default:
                Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
                return null;
        }
    }

    /**
     * Replace fragment
     *
     * @param fragment Fragment to replace
     */
    private void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            String backStackName = fragment.getClass().getName();
            if (!backStackName.equals(currentFragmentName)) {
                currentFragmentName = backStackName;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                tr.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                boolean fragmentPopped = fm.popBackStackImmediate(backStackName, 0);
                if (!fragmentPopped) {
                    tr.addToBackStack(backStackName);
                    tr.replace(R.id.frame_container, fragment);
                }
                tr.replace(R.id.frame_container, fragment);
                tr.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        replaceFragment(itemClick(id));
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void clearDrawerNavigationSelection() {
        int size = navigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    private void updateDrawerNavigationSelection(int id) {
        if (navigationView.getMenu().findItem(id) != null)
            navigationView.getMenu().findItem(id).setChecked(true);
        else
            clearDrawerNavigationSelection();
    }


    private void updateNavigationBarState(int actionId) {
        Menu menu = bottomNavigationView.getMenu();
        boolean isBottomSelected = false;
        if (actionId > 0) {
            for (int i = 0, size = menu.size(); i < size; i++) {
                MenuItem item = menu.getItem(i);
                if (item.getItemId() == actionId) {
                    isBottomSelected = true;
                    item.setChecked(true);
                    break;
                } else {
                    isBottomSelected = false;
                }
            }
        }
        if (isBottomSelected)
            changeMenuItemCheckedStateColor(ContextCompat.getColor(this, R.color.colorAccent),
                    ContextCompat.getColor(this, R.color.primary_text_color));
        else
            changeMenuItemCheckedStateColor(ContextCompat.getColor(this, R.color.primary_text_color),
                    ContextCompat.getColor(this, R.color.primary_text_color));
    }

    private void updateBottomNavigationIconSize()
    {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
            updateDrawerAndNavigation();
        }
    }

    private void changeMenuItemCheckedStateColor(int checkedColor, int uncheckedColor) {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_checked}, // checked
        };
        int[] colors = new int[]{
                uncheckedColor,
                checkedColor
        };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        bottomNavigationView.setItemTextColor(colorStateList);
        bottomNavigationView.setItemIconTintList(colorStateList);
    }

    @Override
    public void changeFragment(Fragment fragment) {
        updateNavigationBarState(0);
        replaceFragment(fragment);
    }

    public void navHeaderClick(View view) {
        drawer.closeDrawer(GravityCompat.START);
        if (OnBoardingActivity.loginInfoDto == null)
            startActivity(new Intent(MainActivity.this,
                    LoginActivity.class));
/*
        new Handler().postDelayed(() -> {
            if (OnBoardingActivity.loginInfoDto == null)
                startActivity(new Intent(MainActivity.this,
                        LoginActivity.class));
            else
                startActivity(new Intent(MainActivity.this,
                        EditProfileActivity.class));
        }, 200);*/
    }
}
