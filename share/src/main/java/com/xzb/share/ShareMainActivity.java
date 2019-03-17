package com.xzb.share;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzb.basecore.constants.RouterPathManager;

@Route(path = RouterPathManager.SHARE_PATH_SHARE_MAIN_ACTIVITY)
public class ShareMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_main);
    }
}
