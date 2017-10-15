package com.murat.mocksepeti.ui.userlist;

import com.murat.mocksepeti.adapter.UserListAdapter;
import com.murat.mocksepeti.model.User;

import java.util.List;

public interface UserListPresenter {
    void setView(UserListView view);

    void requestUserList(List<User> userList, UserListAdapter userListAdapter);
}
