package com.carbon.complete;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carbon.complete.Firebase.Login.LoginInterface;
import com.carbon.complete.Firebase.Login.LoginPresenter;
import com.google.firebase.auth.FirebaseAuth;

import custom.RobotoEditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginInterface.View {


    TextView link_to_register;
    Button btn_login;
    RobotoEditText editText_email, editText_password;
    LoginPresenter loginPresenter;

    public static void startActivity(Context context) {

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            MainActivity.startActivity(this);
        }
        init();


    }

    private void init() {

        link_to_register = findViewById(R.id.textview_link_to_register);
        link_to_register.setOnClickListener(this);

        btn_login = findViewById(R.id.button_login_login_page);
        btn_login.setOnClickListener(this);

        loginPresenter = new LoginPresenter(this);

        editText_email = findViewById(R.id.editText_login_email) ;
        editText_password = findViewById(R.id.editText_login_password);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.textview_link_to_register:
                RegisterActivity.startActivity(this);
                break;
            case R.id.button_login_login_page:
                loginPresenter.LoginUser(LoginActivity.this,editText_email.getText().toString(),editText_password.getText().toString());
                break;


        }

    }

    @Override
    public void onLoginSucces(String message) {
        MainActivity.startActivity(this);

    }

    @Override
    public void onLoginFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
