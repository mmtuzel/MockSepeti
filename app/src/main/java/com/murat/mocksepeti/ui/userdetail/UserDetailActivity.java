package com.murat.mocksepeti.ui.userdetail;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.murat.mocksepeti.MockSepetiApplication;
import com.murat.mocksepeti.R;
import com.murat.mocksepeti.model.UserDetail;

import javax.inject.Inject;

public class UserDetailActivity extends AppCompatActivity
    implements View.OnClickListener, UserDetailView {
    private static final String TAG = "UserDetailActivity";
    private UserDetailPresenterImpl presenter;

    private ImageView ivUserPicture;
    private TextView ivUserFirstName;
    private TextView ivUserLastName;
    private TextView ivUserEmail;
    private TextView ivUserPhone;
    private TextView ivUserBirthDate;
    private TextView ivUserLocation;
    private LinearLayout llUserEmail;
    private LinearLayout llUserPhone;
    private LinearLayout llUserLocation;

    @Inject
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Log.d(TAG, "onCreate working");

        ((MockSepetiApplication)getApplication()).getAppComponent().inject(this);

        llUserEmail = (LinearLayout) findViewById(R.id.ll_user_detail_email);
        llUserPhone = (LinearLayout) findViewById(R.id.ll_user_detail_phone);
        llUserLocation = (LinearLayout) findViewById(R.id.ll_user_detail_location);
        ivUserPicture = (ImageView) findViewById(R.id.iv_user_detail_picture);
        ivUserFirstName = (TextView) findViewById(R.id.tv_user_detail_first_name);
        ivUserLastName = (TextView) findViewById(R.id.tv_user_detail_last_name);
        ivUserEmail = (TextView) findViewById(R.id.tv_user_detail_email);
        ivUserPhone = (TextView) findViewById(R.id.tv_user_detail_phone);
        ivUserBirthDate = (TextView) findViewById(R.id.tv_user_detail_date);
        ivUserLocation = (TextView) findViewById(R.id.tv_user_detail_location);

        presenter = new UserDetailPresenterImpl();
        presenter.setView(this);
        presenter.getData(database.child(getIntent().getExtras().getString("index")));

        llUserEmail.setOnClickListener(this);
        llUserPhone.setOnClickListener(this);
        llUserLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_user_detail_email:
                createUpdateDialog("email", "Change Email Address");
                break;
            case R.id.ll_user_detail_phone:
                createUpdateDialog("phone", "Change Phone Number");
                break;
            case R.id.ll_user_detail_location:
                createUpdateDialog("location", "Change Location");
                break;
        }
    }

    @Override
    public void setDetail(UserDetail userDetail) {
        Log.d(TAG, "setDetail working");

        setIvUserPicture(userDetail.getPhoto());
        ivUserFirstName.setText(userDetail.getFirstName());
        ivUserLastName.setText(userDetail.getLastName());
        ivUserEmail.setText(userDetail.getEmail());
        ivUserPhone.setText(userDetail.getPhone());
        ivUserBirthDate.setText(userDetail.getBirthDate());
        ivUserLocation.setText(userDetail.getLocation());
    }

    @Override
    public void setIvUserPicture(int index) {
        Log.d(TAG, "setIvUserPicture working");

        int resId[] = {R.drawable.user1, R.drawable.user2, R.drawable.user3,
                R.drawable.user4, R.drawable.user5, R.drawable.user6,
                R.drawable.user7, R.drawable.user8};
        if (index > 8) {
            index = index / 8;
        }
        ivUserPicture.setImageResource(resId[index - 1]);
    }

    @Override
    public void createUpdateDialog(final String type, String title) {
        Log.d(TAG, "createUpdateDialog working");

        AlertDialog.Builder builder = new AlertDialog.Builder(UserDetailActivity.this);
        builder.setTitle(title);
        final EditText input = new EditText(UserDetailActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        if (type.equals("email"))
            input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        if (type.equals("phone"))
            input.setInputType(InputType.TYPE_CLASS_PHONE);
        builder.setView(input);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.updateInfo(database.child(getIntent().getExtras().getString("index")),
                        type,
                        input.getText().toString());

            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create();
        builder.show();
    }
}
