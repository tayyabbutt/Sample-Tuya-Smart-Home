package com.smart.wbm.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.callback.ITuyaGetHomeListCallback;
import com.smart.wbm.R;
import com.smart.wbm.adapters.TuyaHomeAdapter;
import com.smart.wbm.models.HomeModel;
import com.smart.wbm.recyclerItemDecorator.LinearDividerItemDecoration;
import com.smart.wbm.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class ShowHomeActivity extends AppCompatActivity {


    RecyclerView homeRecyclerView;
    LinearLayout noHomeFound;
    TextView addHome;
    ImageView back;
    KProgressHUD progress;
    List<HomeModel> homeList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_home_list_activity);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        init();
    }

    private void init() {
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        homeRecyclerView = findViewById(R.id.homeRecyclerView);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeRecyclerView.setHasFixedSize(true);
        LinearDividerItemDecoration seprator = new LinearDividerItemDecoration(this, getResources().getColor(R.color.darkAppColor), 0.8f);
        homeRecyclerView.addItemDecoration(seprator);
        noHomeFound = findViewById(R.id.noHomeFound);

        addHome = findViewById(R.id.addHome);
        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(ShowHomeActivity.this, AddHomeActivity.class, false, null);
            }
        });
        //showHome();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHomeList();
    }

    /* private void setAdapter(List<HomeModel> homeList) {
         if (homeList.size() > 0) {
             noHomeFound.setVisibility(View.GONE);
             homeRecyclerView.setVisibility(View.VISIBLE);
             HomeAdapter adapter = new HomeAdapter(ShowHomeActivity.this, homeList);
             homeRecyclerView.setAdapter(adapter);
             adapter.notifyDataSetChanged();
         } else {
             noHomeFound.setVisibility(View.VISIBLE);
             homeRecyclerView.setVisibility(View.GONE);
         }
     }
 */
    private void getHomeList() {
        showProgress();
        TuyaHomeSdk.getHomeManagerInstance().queryHomeList(new ITuyaGetHomeListCallback() {
            @Override
            public void onSuccess(List<HomeBean> list) {
                hideProgress();
                if (!list.isEmpty()) {
                    HomeBean homeBean = list.get(0);

                    TuyaHomeAdapter adapter = new TuyaHomeAdapter(ShowHomeActivity.this, list);
                    homeRecyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(ShowHomeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String s, String s1) {
                hideProgress();
                Toast.makeText(ShowHomeActivity.this, s + "\n" + s1, Toast.LENGTH_LONG).show();
            }
        });
    }

   /* private void showHome() {
        if (Utility.isOnline(this)) {
            showProgress();
            Call<List<HomeModel>> call = RetrofitClient.getInstance().getApiService().getHome(ApiKeys.BASE_URL + ApiKeys.getAllHome);
            call.enqueue(new Callback<List<HomeModel>>() {
                @Override
                public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                    hideProgress();
                    if (response.isSuccessful()) {
                        if (homeList.size() > 0) {
                            homeList.clear();
                        }
                        homeList = response.body();
                        if (homeList != null) {
                            setAdapter(homeList);
                        } else {
                            Toast.makeText(ShowHomeActivity.this, "No home found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<HomeModel>> call, Throwable t) {
                    hideProgress();
                    Toast.makeText(ShowHomeActivity.this, "server error", Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(this, "No internet available", Toast.LENGTH_SHORT).show();
        }
    }*/


    public void showProgress() {
        progress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait...")
                .setAnimationSpeed(2)
                .show();
    }

    public void hideProgress() {
        progress.dismiss();
    }


}
