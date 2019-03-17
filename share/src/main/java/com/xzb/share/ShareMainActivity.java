package com.xzb.share;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
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
        setContentView(R.layout.activity_share_main);

        data = getIntent().getStringExtra(GlobalConstant.DATA_KEY);
        student = getIntent().getParcelableExtra(GlobalConstant.STUDENT_KEY);
        Toast.makeText(this, "接收到跳转携带过来的数据data：" + data, Toast.LENGTH_SHORT).show();
        if(student != null){
            Toast.makeText(this, "接收到跳转携带过来的数据student：" + student, Toast.LENGTH_SHORT).show();
        }

    }
}
