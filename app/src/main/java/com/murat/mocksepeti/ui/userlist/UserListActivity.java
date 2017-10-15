package com.murat.mocksepeti.ui.userlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.murat.mocksepeti.MockSepetiApplication;
import com.murat.mocksepeti.R;
import com.murat.mocksepeti.adapter.UserListAdapter;
import com.murat.mocksepeti.model.User;
import com.murat.mocksepeti.ui.userdetail.UserDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserListActivity extends AppCompatActivity
    implements UserListAdapter.ListItemClickListener, View.OnClickListener, UserListView{
    private static final String TAG = "UserListActivity";
    private UserListPresenterImpl presenter;

    private UserListAdapter listAdapter;
    private List<User> userList = new ArrayList<>();
    private RecyclerView rvUserList;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Log.d(TAG, "onCreate working");

        ((MockSepetiApplication)getApplication()).getAppComponent().inject(this);

        listAdapter = new UserListAdapter(context, userList, this);
        rvUserList = (RecyclerView) findViewById(R.id.rv_user_list);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.setAdapter(listAdapter);

        presenter = new UserListPresenterImpl();
        presenter.setView(this);
        presenter.requestUserList(userList, listAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart working");
        
        presenter.requestUserList(userList, listAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onListItemClick(int index) {
        Log.d(TAG, "onListItemClick working");

        Intent userDetailIntent = new Intent(context, UserDetailActivity.class);
        userDetailIntent.putExtra("index", String.valueOf(index));
        startActivity(userDetailIntent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public UserListAdapter getUserListAdapter() {
        return listAdapter;
    }
}
