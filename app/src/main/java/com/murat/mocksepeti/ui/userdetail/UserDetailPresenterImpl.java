package com.murat.mocksepeti.ui.userdetail;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.murat.mocksepeti.model.UserDetail;

public class UserDetailPresenterImpl implements UserDetailPresenter {
    private UserDetailView view;

    @Override
    public void setView(UserDetailView view) {
        this.view = view;
    }

    @Override
    public void getData(DatabaseReference database) {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDetail userDetail = dataSnapshot.getValue(UserDetail.class);
                view.setDetail(userDetail);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void updateInfo(DatabaseReference database, String field, String value) {
        if (value.isEmpty())
            return;
        database.child(field).setValue(value);
    }
}
