package com.murat.mocksepeti.ui.userdetail;

import com.google.firebase.database.DatabaseReference;

public interface UserDetailPresenter {
    void setView(UserDetailView view);

    void getData(DatabaseReference database);

    void updateInfo(DatabaseReference database, String field, String value);
}
