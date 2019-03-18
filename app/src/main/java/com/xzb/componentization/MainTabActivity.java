package com.xzb.componentization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
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

    private static final String TAG = "MainTabActivity";

    private Button mBtnJumpToShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        mBtnJumpToShare = findViewById(R.id.btn_jump_to_share);
        mBtnJumpToShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                //无拦截器Interceptor时需要每个地方手动判断登录状态
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
                }*/


                NavigationCallback navigationCallback = new NavCallback() {

                    @Override
                    public void onFound(Postcard postcard) {
                        String group = postcard.getGroup();
                        Log.d(TAG, "onFound: 找到了，分组是:" + group);
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d(TAG, "onLost: 找不到了");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d(TAG, "onArrival: 跳转完了");
                    }

                    /**
                     * 如果本次跳转被拦截了，可以在这里处理
                     * @param postcard
                     */
                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d(TAG, "onInterrupt: 被拦截了");
                        //这里子线程不能Toast
                        //Toast.makeText(MainTabActivity.this, "用户未登录，请先登录", Toast.LENGTH_SHORT).show();
                        ARouter.getInstance().build(RouterPathManager.LOGIN_PATH_LOGIN_MAIN_ACTIVITY)
                                .navigation();
                    }
                };
                //有拦截器Interceptor后，直接跳转到目标页面，在IInterceptor实现类具体拦截器中统一处理跳转判断
                ARouter.getInstance()
                        .build(RouterPathManager.SHARE_PATH_SHARE_MAIN_ACTIVITY)
                        .withString(GlobalConstant.DATA_KEY, "app工程的value") //key:value
                        .withParcelable(GlobalConstant.STUDENT_KEY,new Student("xzb", 26))
                        .navigation(MainTabActivity.this, navigationCallback);
            }
        });
    }
}
