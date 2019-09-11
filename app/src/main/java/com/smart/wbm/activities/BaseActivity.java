package com.smart.wbm.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;
import com.smart.wbm.R;

import java.io.File;

public class BaseActivity extends  AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {


    ImageView btnRightMenu;
    ProgressDialog dialog;
    public ProgressDialog myDialog;
    androidx.appcompat.widget.Toolbar toolbar;
    TextView screentitle;
    public LinearLayout childselect;
    public ImageView userdp, userPlusBtn;
    public ImageView navigtionUserDp;
    public TextView userName;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        screentitle = findViewById(R.id.screentitle);
        childselect = findViewById(R.id.childselect);
        btnRightMenu = findViewById(R.id.btnRightMenu);
        userdp = findViewById(R.id.userdp);
        userPlusBtn = findViewById(R.id.plusBtn);




        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        btnRightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawers();
                    //  btnRightMenu.setImageDrawable(ContextCompat.getDrawable(BaseActivity.this,R.drawable.ic_menu));

                } else {
                    //   btnRightMenu.setImageDrawable(ContextCompat.getDrawable(BaseActivity.this,R.drawable.left_arrow));
                    drawer.openDrawer(Gravity.LEFT);
                }

            }
        });


    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.itemHome) {
            Intent intent = new Intent(BaseActivity.this, DashBoardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setSelected(true);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }







    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }


    public LinearLayout getChildselect() {
        return childselect;
    }

    public TextView gettitleview() {
        return screentitle;
    }

    public DrawerLayout getDrawerLayout() {
        return drawer;
    }


    public void startprogress(Context context, String string) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(string);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void stopprogress() {
        dialog.cancel();
    }

    public void staticStartprogress(Context context, String string) {
        myDialog = new ProgressDialog(context);
        myDialog.setMessage(string);
        myDialog.setCancelable(false);
        myDialog.show();
    }

    public void staticStopprogress() {
        myDialog.cancel();
    }

}
