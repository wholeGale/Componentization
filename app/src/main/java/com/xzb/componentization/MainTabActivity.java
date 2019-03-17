package com.xzb.componentization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xzb.basecore.constants.RouterPathManager;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-17 17:41
 * @Description: 主界面
 */
//@Route(path = RouterPathManager.APP_PATH_MAIN_TAB_ACTIVITY)
@Route(path = RouterPathManager.APP_PATH_MAIN_TAB_ACTIVITY)
public class MainTabActivity extends AppCompatActivity {

    private Button mBtnJumpToShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        mBtnJumpToShare = findViewById(R.id.btn_jump_to_share);
        mBtnJumpToShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //利用Arouter进行模块间跳转，解耦
                ARouter.getInstance().build(RouterPathManager.SHARE_PATH_SHARE_MAIN_ACTIVITY).navigation();
            }
        });
    }
}
