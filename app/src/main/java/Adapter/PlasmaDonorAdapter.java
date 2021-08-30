package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliferous.sudohacksandroid.MessageActivity;
import com.aliferous.sudohacksandroid.R;

import java.util.List;

import Model.User;

import static android.content.ContentValues.TAG;

public class PlasmaDonorAdapter extends RecyclerView.Adapter<PlasmaDonorAdapter.ViewHolder>{

    private final Context mContext;
    private final List<User> mUsers;


    public PlasmaDonorAdapter(Context mContext, List<User> mUsers, boolean b) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.plasma_donor_item,parent,false);
        return new PlasmaDonorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User user = mUsers.get(position);
        Log.i(TAG, "onBindViewHolder: "+position);
        holder.pd_age.setText("Age: "+user.getAge());
        holder.pd_locality.setText("Locality: "+user.getLocality());
        holder.pd_pincode.setText("Pincode: "+user.getPin());
        holder.pd_gender.setText("Gender: "+user.getGender());
        holder.pd_bloodgroup.setText("Blood Group: "+user.getBlood());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });


    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pd_age,pd_locality,pd_pincode,pd_gender,pd_bloodgroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pd_age = itemView.findViewById(R.id.pd_item_age);
            pd_locality = itemView.findViewById(R.id.pd_item_locality);
            pd_pincode = itemView.findViewById(R.id.pd_item_pincode);
            pd_gender = itemView.findViewById(R.id.pd_item_gender);
            pd_bloodgroup = itemView.findViewById(R.id.pd_item_bloodgroup);

        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
