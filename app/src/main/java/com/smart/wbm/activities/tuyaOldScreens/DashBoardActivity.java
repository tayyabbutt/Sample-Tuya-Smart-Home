package com.smart.wbm.activities.tuyaOldScreens;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.smart.wbm.R;
import com.smart.wbm.fragments.HomeFragment;
import com.smart.wbm.fragments.MyProfileFragment;

import java.util.ArrayList;

public class DashBoardActivity extends BaseActivity {

    HomeFragment homeFragment;
    MyProfileFragment profileFragment;
    //   SmartHomeFragment smartHomeFragment;
    AHBottomNavigation bottomNavigation;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_dashboard);
        FrameLayout contentFrameLayout = findViewById(R.id.frame_hloder);
        getLayoutInflater().inflate(R.layout.activity_dashboard, contentFrameLayout);
        bottomNavigation = findViewById(R.id.navigation);
        homeFragment = new HomeFragment();
     //   smartHomeFragment = new SmartHomeFragment();
        profileFragment = new MyProfileFragment();

        setBottomNavigationUI();

    }

    private void setBottomNavigationUI() {
        AHBottomNavigationItem home = new AHBottomNavigationItem(R.string.home, R.drawable.home, R.color.black);
        AHBottomNavigationItem notification = new AHBottomNavigationItem(R.string.smart, R.drawable.smart_home, R.color.black);
        AHBottomNavigationItem learning = new AHBottomNavigationItem(R.string.me, R.drawable.user, R.color.black);

        bottomNavigationItems.add(home);
        bottomNavigationItems.add(notification);
        bottomNavigationItems.add(learning);

        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.white));

        // bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setAccentColor(getResources().getColor(R.color.red));
        bottomNavigation.setInactiveColor(Color.parseColor("#000000"));
        bottomNavigation.setForceTint(true);

        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setColored(false);
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#FF0000"));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (position == 0) {
                    changeFragment(homeFragment);
                    gettitleview().setText(getString(R.string.home));
                } else if (position == 1) {
               //     changeFragment(smartHomeFragment);
                    gettitleview().setText(getString(R.string.smart));
                } else if (position == 2) {
                    changeFragment(profileFragment);
                    gettitleview().setText(getString(R.string.me));
                }


                return true;
            }
        });

        changeFragment(homeFragment);
        gettitleview().setText(getString(R.string.home));
    }

    private void changeFragment(Fragment targetFragment) {
//        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainframelayout, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    private void refreshFragment() {
        Fragment frg = null;
        frg = getSupportFragmentManager().findFragmentByTag("fragment");
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }

}
