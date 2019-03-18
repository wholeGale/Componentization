package com.xzb.share;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xzb.basecore.ServiceFactory;
import com.xzb.basecore.constants.GlobalConstant;
import com.xzb.basecore.constants.RouterPathManager;
import com.xzb.basecore.entitys.Student;

@Route(path = RouterPathManager.SHARE_PATH_SHARE_MAIN_ACTIVITY)
public class ShareMainActivity extends AppCompatActivity {

    public String data; //特别注意：这里不能用private，要用 public!!!
    public Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharelib_activity_share_main);

        data = getIntent().getStringExtra(GlobalConstant.DATA_KEY);
        student = getIntent().getParcelableExtra(GlobalConstant.STUDENT_KEY);
        /*Toast.makeText(this, "接收到跳转携带过来的数据data：" + data, Toast.LENGTH_SHORT).show();
        if(student != null){
            Toast.makeText(this, "接收到跳转携带过来的数据student：" + student, Toast.LENGTH_SHORT).show();
        }*/

        share();

    }

    private void share() {
        if(ServiceFactory.getInstance().getAccountService().isLogin()){
            Toast.makeText(this, ServiceFactory.getInstance().getAccountService().getAccountId()
                            + "用户分享成功，要分享的数据student:" + student, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "分享失败：用户未登录，请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(RouterPathManager.LOGIN_PATH_LOGIN_MAIN_ACTIVITY)
                    .navigation();
        }
    }
}
