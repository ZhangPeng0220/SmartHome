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

import com.zhangpeng.administrator.smarthome.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignUpActivity extends AppCompatActivity {

    EditText _nameText;
    EditText _passwordText;
    EditText _surePassword;
    Button _signupButton;
    TextView _loginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sign_up);
        _nameText =(EditText) findViewById(R.id.input_name);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _surePassword = (EditText) findViewById(R.id.sure_password);
        _signupButton = (Button)findViewById(R.id.btn_signup);
        _loginLink = (TextView)findViewById(R.id.link_login);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        Bmob.initialize(this, "1560140656cb1461a13ae0c6e999ce80");

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void signup() {
        if (!validate()) {
            onSignupFailed();
        }else {
            _signupButton.setEnabled(true);
            final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();

            final String name = _nameText.getText().toString();
            final String password = _passwordText.getText().toString();

            // TODO: Implement your own signup logic here.

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            Account account = new Account();
                            account.setName(name);
                            account.setPassword(password);
                            account.save(new SaveListener<String>() {
                                @Override
                                public void done(String objectId,BmobException e) {
                                    if(e==null){
                                        onSignupSuccess();
                                        progressDialog.dismiss();
                                    }else{
                                        onSignupFailed();
                                    }
                                }
                            });
                        }
                    }, 3000);
        }

    }
    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);//返回数据
        Intent intent = new Intent(SignUpActivity.this,LogInActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

    }

    public boolean validate() {
        boolean valid  = true;
        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();
        if(name.isEmpty()){
            _nameText.setError("请输入用户名");
            valid = false;
        }else {
            _nameText.setError(null);
        }
        if(password.isEmpty()){
            _passwordText.setError("请输入用户名");
            valid = false;
        }else {
            _passwordText.setError(null);
        }

        if (_passwordText.getText().toString().equals(_surePassword.getText().toString()) ){
            _surePassword.setError(null);
        }else {
            valid = false;
            _surePassword.setError("密码不一致");
        }

        return valid;
    }

}
