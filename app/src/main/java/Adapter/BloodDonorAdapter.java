package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliferous.sudohacksandroid.R;

import java.util.List;

import Model.User;

import static android.content.ContentValues.TAG;

public class BloodDonorAdapter extends RecyclerView.Adapter<BloodDonorAdapter.ViewHolder>{

    private final Context mContext;
    private final List<User> mUsers;


    public BloodDonorAdapter(Context mContext, List<User> mUsers, boolean b) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.blood_donor_item,parent,false);
        return new BloodDonorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User user = mUsers.get(position);
        Log.i(TAG, "onBindViewHolder: "+position);
        holder.bd_age.setText(user.getAge());
        holder.bd_locality.setText(user.getLocality());
        holder.bd_pincode.setText(user.getPin());
        holder.bd_gender.setText(user.getGender());
        holder.bd_bloodgroup.setText(user.getBlood());




    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView bd_age,bd_locality,bd_pincode,bd_gender,bd_bloodgroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bd_age = itemView.findViewById(R.id.bd_item_age);
            bd_locality = itemView.findViewById(R.id.bd_item_locality);
            bd_pincode = itemView.findViewById(R.id.bd_item_pincode);
            bd_gender = itemView.findViewById(R.id.bd_item_gender);
            bd_bloodgroup = itemView.findViewById(R.id.bd_item_bloodgroup);

        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
