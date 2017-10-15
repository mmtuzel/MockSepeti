package com.murat.mocksepeti;

import com.murat.mocksepeti.model.User;
import com.murat.mocksepeti.rest.ApiService;
import com.murat.mocksepeti.ui.userlist.UserListPresenterImpl;
import com.murat.mocksepeti.ui.userlist.UserListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import retrofit2.Call;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;

@RunWith(MockitoJUnitRunner.class)
public class UserListTest {
    private UserListPresenterImpl presenter;
    private Call<List<User>> userCall;

    @Mock ApiService apiService;
    @Mock User user;
    @Mock UserListView view;

    @Before
    public void setup() {
        initMocks(this);
        presenter = new UserListPresenterImpl();
        presenter.setView(view);
    }

    @Test
    public void shouldGetList() throws Exception {
        when(apiService.getUsers()).thenReturn(userCall);
        presenter.requestUserList(view.getUserList(), view.getUserListAdapter());
        verify(view).getUserList();
        verify(view).getUserListAdapter();
    }
}
