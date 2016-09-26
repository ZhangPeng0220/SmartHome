package com.zhangpeng.administrator.LogIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangpeng.administrator.smarthome.MainActivity;
import com.zhangpeng.administrator.smarthome.R;

import org.json.JSONArray;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class LogInActivity extends AppCompatActivity {
    Button login;
    EditText _passwordText;
    EditText _emailText;
    TextView signUp;
    String name;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        login = (Button)findViewById(R.id.btn_login);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        signUp = (TextView) findViewById(R.id.link_signup);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("login","点击");
                login();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               Intent intent = new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


    }



    public void login() {


        if (!validate()) {
            onLoginFailed();
            return;
        }

        login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LogInActivity.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("正在登录...");
        progressDialog.show();

        name = _emailText.getText().toString();
        password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        BmobQuery query =new BmobQuery("Account");
                        query.addWhereEqualTo("name", name);
                        query.setLimit(2);
                        query.order("createdAt");
                        //v3.5.0版本提供`findObjectsByTable`方法查询自定义表名的数据
                        query.findObjectsByTable(new QueryListener<JSONArray>() {
                            @Override
                            public void done(JSONArray ary, BmobException e) {
                                if(e==null){
                                    Log.i("bmob","查询成功："+ary.toString());
                                    onLoginSuccess();
                                    progressDialog.dismiss();
                                }else{
                                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                                    onLoginFailed();
                                }
                            }
                        });

                    }
                }, 3000);
        Log.d("login","正在进入");
    }
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _emailText.setError("请输入用户名");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() ) {
            _passwordText.setError("请输入密码");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
    }
    public void onLoginSuccess() {

        Intent intent = new Intent(LogInActivity.this,MainActivity.class);
        startActivity(intent);
        finish();


    }

}
