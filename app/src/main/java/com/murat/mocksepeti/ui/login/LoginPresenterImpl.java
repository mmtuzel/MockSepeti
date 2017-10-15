package com.murat.mocksepeti.ui.login;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.murat.mocksepeti.model.Credential;
import com.murat.mocksepeti.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl implements LoginPresenter {
    private static final String TAG = "LoginPresenterImpl";
    private LoginView view;

    @Override
    public void setView(LoginView view) {
        this.view = view;
    }

    @Override
    public void login() {
        Log.d(TAG, "login working");
        view.showProgressDialog();

        if (view.validateCredentials(view.getEtUsername(), view.getEtPassword())) {

            Call<Credential> call =  new RestClient()
                    .getApiService()
                    .getCredentials();
            call.enqueue(new Callback<Credential>() {
                @Override
                public void onResponse(Call<Credential> call, Response<Credential> response) {
                    //Toast.makeText(LoginActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    if ((response.code() == 200)) {
                        view.closeProgressDialog();
                        view.startUserListActivity();
                    }
                }

                @Override
                public void onFailure(Call<Credential> call, Throwable t) {
                    view.closeProgressDialog();
                }
            });
        } else {
            view.closeProgressDialog();
            view.showToast();
        }
    }

    @Override
    public void validateButton(Button button) {
        if (!view.getEtUsername().getText().toString().isEmpty()
                &&
                !view.getEtPassword().getText().toString().isEmpty())
            button.setEnabled(true);
        else
            button.setEnabled(false);
    }
}
