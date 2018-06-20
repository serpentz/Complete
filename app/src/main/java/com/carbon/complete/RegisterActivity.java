package com.carbon.complete;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carbon.complete.Firebase.Register.RegisterContract;
import com.carbon.complete.Firebase.Register.RegisterPresenter;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterContract.View {


    private Button button,button_register;
    private EditText editText_email,editText_password,EditText_password_again;

    public static void startActivity(Context context) {

        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {

        button = findViewById(R.id.btnFacebookLogin);
        button_register = findViewById(R.id.button_register_user);
        button.setOnClickListener(this);
        button_register.setOnClickListener(this);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        EditText_password_again = findViewById(R.id.editText_password_again);


    }
    private void RegisterUser() {

        RegisterPresenter presenter = new RegisterPresenter(this);
        presenter.register(RegisterActivity.this,editText_email.getText().toString(),editText_password.getText().toString());

    }


    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btnFacebookLogin:break;
            case R.id.button_register_user:RegisterUser();
        }

    }
    @Override
    public void onRegistrationSuccess(String message) {

        GoToLogin();

    }

    private void GoToLogin() {

        FirebaseAuth.getInstance().signOut();
        LoginActivity.startActivity(this);
    }

    @Override
    public void onRegistrationFailure(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }


}
