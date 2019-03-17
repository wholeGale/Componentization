package com.xzb.componentization;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xzb.basecore.constants.RouterPathManager;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-17 17:41
 * @Description: 应用启动引导页
 */
@Route(path = RouterPathManager.APP_PATH_WELCOME_ACTIVITY)
public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";

    private final Timer timer = new Timer();
    //倒计时
    private TimerTask reciprocalTask;
    private MyHandler myHandler;
    private int count = 5;

    private TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Log.d(TAG, "onCreate: ");
        tvCount = findViewById(R.id.tv_count);

        myHandler = new MyHandler(this);
        reciprocalTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "reciprocalTask->run: ");
                Message message = myHandler.obtainMessage();
                message.what = 1;
                message.arg1 = count--;
                myHandler.sendMessage(message);
            }
        };
        timer.schedule(reciprocalTask, 0L,1000L);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
        reciprocalTask.cancel();
    }

    static class MyHandler extends Handler{

        WeakReference<WelcomeActivity> mActivityWeakReference;

        public MyHandler(WelcomeActivity activity){
            mActivityWeakReference = new WeakReference<WelcomeActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: ");
            WelcomeActivity activity = mActivityWeakReference.get();
            switch (msg.what){
                case 1:
                    if(msg.arg1 == 0){
                        //跳转到主界面，这是组件内跳转，既可以用startActivity，也可以用ARouter跳转
                        //这里主界面一般不会移动到其它module，但是其它界面如果有可能要移动，则一开始可以考虑用ARouter跳转
                        /*if(activity != null){
                            activity.startActivity(new Intent(activity, MainTabActivity.class));
                        }*/
                        ARouter.getInstance().build(RouterPathManager.APP_PATH_MAIN_TAB_ACTIVITY).navigation();
                        activity.finish();
                    }else{
                        if(activity != null){
                            activity.tvCount.setText(String.valueOf(msg.arg1));
                        }
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
