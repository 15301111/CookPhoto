package com.example.morphtin.dishes;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class UserMainActivity extends AppCompatActivity implements View.OnClickListener{
    public ImageView iv_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_main);
        iv_setting = (ImageView)findViewById(R.id.iv_set);
        iv_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_set:
                showSettingDialog();
                break;
        }
    }

    public void showSettingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UserMainActivity.this);
        builder.setIcon(R.drawable.setting);
        builder.setTitle("账户设置");
        final String[] cities = {"我的关注", "我的收藏", "修改个人信息", "隐私","退出","反馈"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(UserMainActivity.this, "选择为：" + cities[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}

