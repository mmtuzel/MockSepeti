package com.murat.mocksepeti.ui.login;

import android.widget.EditText;

public interface LoginView {
    void showToast();

    boolean validateCredentials(EditText username, EditText password);

    void startUserListActivity();

    void showProgressDialog();

    void closeProgressDialog();

    EditText getEtUsername();

    EditText getEtPassword();
}
