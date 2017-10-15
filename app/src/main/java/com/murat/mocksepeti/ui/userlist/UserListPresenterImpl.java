package com.murat.mocksepeti.ui.userlist;

import android.util.Log;

import com.murat.mocksepeti.adapter.UserListAdapter;
import com.murat.mocksepeti.model.User;
import com.murat.mocksepeti.rest.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListPresenterImpl implements UserListPresenter {
    private static final String TAG = "UserListPresenterImpl";
    private UserListView view;

    @Override
    public void setView(UserListView view) {
        this.view = view;
    }

    @Override
    public void requestUserList(final List<User> userList, final UserListAdapter userListAdapter) {
        Log.d(TAG, "requestUserList working");

        Call<List<User>> call = new RestClient().getApiService().getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code() == 200) {
                    userListAdapter.clear();
                    for (User user : response.body()) {
                        userList.add(user);
                        userListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
