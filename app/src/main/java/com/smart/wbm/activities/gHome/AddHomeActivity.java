package com.smart.wbm.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.smart.wbm.R;
import com.smart.wbm.adapters.RoomAdapter;
import com.smart.wbm.interfaces.OnRoomClick;
import com.smart.wbm.models.RoomModel;
import com.smart.wbm.utils.MyDialog;

import java.util.ArrayList;
import java.util.List;

public class AddHomeActivity extends AppCompatActivity {
    KProgressHUD progress;
    EditText familyName;
    TextView saveTxt, addRoomTxt;
    RecyclerView roomRecyclerView;
    ImageView back;
    LinearLayout noRoomFound;
    String roomNameStr;
    List<RoomModel> newRoomList = new ArrayList<>();
    List<String> roomList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_family_info_activity);
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
        roomRecyclerView = findViewById(R.id.roomRecyclerView);
        roomRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        roomRecyclerView.setHasFixedSize(true);

        saveTxt = findViewById(R.id.saveTxt);
        familyName = findViewById(R.id.homeNameEt);
        addRoomTxt = findViewById(R.id.addRoom);
        noRoomFound = findViewById(R.id.noRoomFound);


        addRoomTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.showDialog(AddHomeActivity.this, "Add new room", new OnRoomClick() {
                    @Override
                    public void onRoomClickListner(String name) {
                        createHomeWithRoom(name);

                    }
                });
            }
        });


        saveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (familyName.getText().toString() != null) {
                    createTuyaHome(familyName.getText().toString(), roomList);
                } else {
                    Toast.makeText(AddHomeActivity.this, "Please enter home name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

 /*   private void setDataAndAdapter(String roomName1) {
        newRoomList.add(new RoomModel(roomName1));
        roomNameStr = roomName1;
        if (newRoomList != null && newRoomList.size() > 0) {
            roomRecyclerView.setVisibility(View.VISIBLE);
            noRoomFound.setVisibility(View.GONE);
            RoomAdapter adapter = new RoomAdapter(AddHomeActivity.this, newRoomList);
            roomRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            roomRecyclerView.setVisibility(View.GONE);
            noRoomFound.setVisibility(View.VISIBLE);
        }


    }*/


    private void createHomeWithRoom(String roomName1) {
        newRoomList.add(new RoomModel(roomName1));
        roomList.add(roomName1);
        if (newRoomList != null && newRoomList.size() > 0) {
            roomRecyclerView.setVisibility(View.VISIBLE);
            noRoomFound.setVisibility(View.GONE);
            RoomAdapter adapter = new RoomAdapter(AddHomeActivity.this, newRoomList);
            roomRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            roomRecyclerView.setVisibility(View.GONE);
            noRoomFound.setVisibility(View.VISIBLE);
        }
    }

    private void createTuyaHome(String homeName, List<String> rooms) {
        //  showProgress();
        if (rooms != null && rooms.size() > 0) {
            showProgress();
            TuyaHomeSdk.getHomeManagerInstance().createHome(homeName, 3.7485, 3.3257, "Lahore", rooms, new ITuyaHomeResultCallback() {
                @Override
                public void onSuccess(HomeBean bean) {
                    hideProgress();
                    finish();
                    Toast.makeText(AddHomeActivity.this, "Sucess " + bean, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    hideProgress();
                    Toast.makeText(AddHomeActivity.this, "failure " + errorMsg, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            showProgress();
            TuyaHomeSdk.getHomeManagerInstance().createHome(homeName, 3.7485, 3.3257, "Lahore", rooms, new ITuyaHomeResultCallback() {
                @Override
                public void onSuccess(HomeBean bean) {
                    hideProgress();
                    finish();
                    Toast.makeText(AddHomeActivity.this, "Sucess " + bean, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    hideProgress();
                    Toast.makeText(AddHomeActivity.this, "failure " + errorMsg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


  /*  private void postHome(String homeName) {

        if (Utility.isOnline(this)) {
            if (homeName != null) {
                showProgress();
                Call<HomePost> homePostCall = RetrofitClient.getInstance().getApiService().postHome(ApiKeys.BASE_URL + ApiKeys.postHome, homeName);
                homePostCall.enqueue(new Callback<HomePost>() {
                    @Override
                    public void onResponse(Call<HomePost> call, Response<HomePost> response) {
                        hideProgress();
                        Toast.makeText(AddHomeActivity.this, "Home created successfully", Toast.LENGTH_SHORT).show();
                        postRooms(response.body().getId());
                    }

                    @Override
                    public void onFailure(Call<HomePost> call, Throwable t) {
                        hideProgress();
                        Toast.makeText(AddHomeActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Please enter home name", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "no internet available", Toast.LENGTH_SHORT).show();
        }
    }*/

 /*   private void postRooms(String homeId) {
        if (Utility.isOnline(this)) {
            showProgress();
            JSONObject roomName = null;
            JSONArray roomArray = new JSONArray();
            for (int i = 0; i < newRoomList.size(); i++) {
                try {
                    roomName = new JSONObject();
                    roomName.put("name", newRoomList.get(i).getRoomName());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                roomArray.put(roomName);
            }


            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), roomArray.toString());
            Call<List<RoomPost>> call = RetrofitClient.getInstance().getApiService().postRoom(ApiKeys.BASE_URL + ApiKeys.postRoom + homeId, body);
            call.enqueue(new Callback<List<RoomPost>>() {
                @Override
                public void onResponse(Call<List<RoomPost>> call, Response<List<RoomPost>> response) {
                    if (response.isSuccessful()) {
                        hideProgress();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<List<RoomPost>> call, Throwable t) {
                    hideProgress();
                }
            });

        } else {
            Toast.makeText(this, "no internet available", Toast.LENGTH_SHORT).show();
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
