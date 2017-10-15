package com.murat.mocksepeti.ui.userdetail;

import com.murat.mocksepeti.model.UserDetail;

public interface UserDetailView {
    void setDetail(UserDetail userDetail);

    void setIvUserPicture(int index);

    void createUpdateDialog(final String type, String title);

    //void editInfo(String field, String value);
}
