package com.murat.mocksepeti.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.murat.mocksepeti.R;
import com.murat.mocksepeti.model.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{
    private Context context;
    private List<User> userList;
    private ListItemClickListener clickListener;

    public UserListAdapter(Context context, List<User> userList, ListItemClickListener clickListener) {
        this.context = context;
        this.userList = userList;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rowitem_user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.setUser(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int index);
    }

    public void clear() {
        int size = userList.size();
        userList.clear();
        this.notifyItemRangeRemoved(0, size);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private User user;
        private TextView tvFirstName;
        private TextView tvLastName;
        private TextView tvEmail;
        private ImageView ivPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFirstName = (TextView) itemView.findViewById(R.id.tv_user_first_name);
            tvLastName = (TextView) itemView.findViewById(R.id.tv_user_last_name);
            tvEmail = (TextView) itemView.findViewById(R.id.tv_user_email);
            ivPicture = (ImageView) itemView.findViewById(R.id.iv_user_picture);
            itemView.setOnClickListener(this);
        }

        public void setUser(User user) {
            //this.user = user;
            setUserFirstName(user.getFirstName());
            setUserLastName(user.getLastName());
            setUserEmail(user.getEmail());
            setUserPicture(user.getPhoto());
        }

        private void setUserPicture(Integer photo) {
            int resId[] = {R.drawable.user1, R.drawable.user2, R.drawable.user3,
                    R.drawable.user4, R.drawable.user5, R.drawable.user6,
                    R.drawable.user7, R.drawable.user8};
            if (photo > 8) {
                photo = photo / 8;
            }
            ivPicture.setImageResource(resId[photo - 1]);
        }

        private void setUserEmail(String email) {
            tvEmail.setText(email);
        }

        private void setUserLastName(String lastName) {
            tvLastName.setText(lastName);
        }

        private void setUserFirstName(String firstName) {
            tvFirstName.setText(firstName);
        }


        @Override
        public void onClick(View v) {
            clickListener.onListItemClick(getAdapterPosition());
        }
    }
}
