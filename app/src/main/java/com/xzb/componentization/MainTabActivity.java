package com.xzb.componentization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xzb.basecore.ServiceFactory;
import com.xzb.basecore.constants.GlobalConstant;
import com.xzb.basecore.constants.RouterPathManager;
import com.xzb.basecore.entitys.Student;

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
                if(ServiceFactory.getInstance().getAccountService().isLogin()){
                    //利用Arouter进行模块间跳转，解耦
                    ARouter.getInstance()
                            .build(RouterPathManager.SHARE_PATH_SHARE_MAIN_ACTIVITY)
                            .withString(GlobalConstant.DATA_KEY, "app工程的value") //key:value
                            .withParcelable(GlobalConstant.STUDENT_KEY,new Student("xzb", 26))
                            .navigation();
                } else {
                    Toast.makeText(MainTabActivity.this, "用户未登录，请先登录", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build(RouterPathManager.LOGIN_PATH_LOGIN_MAIN_ACTIVITY)
                            .navigation();
                }

            }
        });
    }
}
