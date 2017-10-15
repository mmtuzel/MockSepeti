package com.murat.mocksepeti.ui.login;

import android.widget.Button;

public interface LoginPresenter {
    void setView(LoginView view);

    void login();

    void validateButton(Button button);
}
