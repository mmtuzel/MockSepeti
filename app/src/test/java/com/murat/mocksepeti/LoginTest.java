package com.murat.mocksepeti;

import com.murat.mocksepeti.model.Credential;
import com.murat.mocksepeti.rest.ApiService;
import com.murat.mocksepeti.ui.login.LoginPresenterImpl;
import com.murat.mocksepeti.ui.login.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Call;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
    private LoginPresenterImpl presenter;
    private Call<Credential> credentialCall;

    @Mock ApiService apiService;
    @Mock Credential credential;
    @Mock
    LoginView view;

    @Before
    public void setup() {
        initMocks(this);
        presenter = new LoginPresenterImpl();
        presenter.setView(view);
    }

    @Test
    public void shouldLogin() throws Exception {
        when(apiService.getCredentials()).thenReturn(credentialCall);
        presenter.login();
        verify(view).showProgressDialog();
    }
}