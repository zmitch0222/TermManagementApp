package com.mobile.zackmitchellc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.zackmitchellc196.Entities.User;
import com.mobile.zackmitchellc196.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> mUsers;
    private final Context context;
    private final LayoutInflater mInflater;

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userFirstName;
        private final TextView userLastName;
        private final TextView userPhoneNumber;
        private final TextView userEmail;

        private UserViewHolder(View itemview){
            super(itemview);
            userFirstName = itemview.findViewById(R.id.userFirstName);
            userLastName = itemview.findViewById(R.id.userLastName);
            userPhoneNumber = itemview.findViewById(R.id.userPhoneNumber);
            userEmail = itemview.findViewById(R.id.userEmail);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final User current = mUsers.get(position);
                    Intent intent = new Intent(context, UserDetails.class);
                    intent.putExtra("id", current.getUserID());
                    intent.putExtra("username", current.getUsername());
                    intent.putExtra("password", current.getPassword());
                    intent.putExtra("first", current.getFirstName());
                    intent.putExtra("last", current.getLastName());
                    intent.putExtra("phone", current.getPhoneNumber());
                    intent.putExtra("email", current.getEmail());
                    intent.putExtra("faculty", current.isFaculty());
                    context.startActivity(intent);
                }
            });
        }
    }
    public UserAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.student_list_item, parent, false);
        return new UserAdapter.UserViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position){
        if (mUsers != null){
            User current = mUsers.get(position);
            String first = current.getFirstName();
            String last = current.getLastName();
            String phone = current.getPhoneNumber();
            String email = current.getEmail();
            holder.userFirstName.setText(first);
            holder.userLastName.setText(last);
            holder.userPhoneNumber.setText(phone);
            holder.userEmail.setText(email);
        } else {
            holder.userFirstName.setText("First null");
            holder.userLastName.setText("Last null");
            holder.userPhoneNumber.setText("Phone null");
            holder.userEmail.setText("Email null");
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setUsers(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

}
