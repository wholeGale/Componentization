package com.xzb.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzb.basecore.constants.RouterPathManager;
import com.xzb.login.entitys.UserInfo;
import com.xzb.login.utils.AccountUtil;

@Route(path = RouterPathManager.LOGIN_PATH_LOGIN_MAIN_ACTIVITY)
public class LoginMainActivity extends AppCompatActivity {

    private Button btnClickLogin;

    private EditText etLoginAccountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        etLoginAccountId = findViewById(R.id.et_login_account_id);
        btnClickLogin = findViewById(R.id.btn_click_login);
        btnClickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //test
                AccountUtil.userInfo = new UserInfo(etLoginAccountId.getText().toString());
                Toast.makeText(LoginMainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
