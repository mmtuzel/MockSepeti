package com.murat.mocksepeti.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.murat.mocksepeti.R;
import com.murat.mocksepeti.ui.userlist.UserListActivity;

public class LoginActivity extends AppCompatActivity
    implements TextWatcher, View.OnClickListener, LoginView {
    private static final String TAG = "LoginActivity";

    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;
    private ProgressDialog progressDialog;

    private LoginPresenterImpl loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate working");

        btnLogin = (Button) findViewById(R.id.btn_login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        progressDialog = new ProgressDialog(LoginActivity.this);

        btnLogin.setEnabled(false);
        etUsername.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        btnLogin.setOnClickListener(this);

        loginPresenter = new LoginPresenterImpl();
        loginPresenter.setView(this);
    }

    /*private void validateButton(EditText etUsername, EditText etPassword, Button button) {
        if (!etUsername.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty())
            button.setEnabled(true);
        else
            button.setEnabled(false);

    }*/

    @Override
    public void showToast() {
        Toast.makeText(this, "Username or password is incorrect ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean validateCredentials(EditText username, EditText password) {
        if (username.getText().toString().equals("username") &&
                password.getText().toString().equals("password"))
            return true;
        return false;
    }

    @Override
    public void startUserListActivity() {
        Intent userListIntent = new Intent(LoginActivity.this, UserListActivity.class);
        startActivity(userListIntent);
    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Authenticating");
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public EditText getEtUsername() {
        return etUsername;
    }

    @Override
    public EditText getEtPassword() {
        return etPassword;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginPresenter.login();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        loginPresenter.validateButton(btnLogin);
    }
}
