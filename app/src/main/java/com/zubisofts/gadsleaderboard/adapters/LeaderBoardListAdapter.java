package com.zubisofts.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zubisofts.gadsleaderboard.R;
import com.zubisofts.gadsleaderboard.model.LeaderBoardHour;
import com.zubisofts.gadsleaderboard.model.LeaderBoardIQ;

import java.util.ArrayList;

public class LeaderBoardListAdapter extends RecyclerView.Adapter<LeaderBoardListAdapter.LeaderBoardListItemViewHolder> {

    private ArrayList<?> leaderBoardList;
    private Context context;

    public LeaderBoardListAdapter(Context context) {
        leaderBoardList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public LeaderBoardListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.leading_leaders_item_layout, parent, false);
        return new LeaderBoardListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardListItemViewHolder holder, int position) {
        if (leaderBoardList != null) {
            if (leaderBoardList.get(position) instanceof LeaderBoardHour) {
                LeaderBoardHour leaderBoardHour = (LeaderBoardHour) leaderBoardList.get(position);
                holder.txtName.setText(leaderBoardHour.getName());
                String desc = leaderBoardHour.getHours() + " learning hours, " + leaderBoardHour.getCountry();
                holder.txtDesc.setText(desc);
                Glide.with(holder.imgBadge)
                        .load(leaderBoardHour.getBadgeUrl())
                        .into(holder.imgBadge);
            } else {
                LeaderBoardIQ leaderBoardIQ = (LeaderBoardIQ) leaderBoardList.get(position);
                holder.txtName.setText(leaderBoardIQ.getName());
                String desc = leaderBoardIQ.getScore() + " Skill IQ Score, " + leaderBoardIQ.getCountry();
                holder.txtDesc.setText(desc);
                Glide.with(holder.imgBadge)
                        .load(leaderBoardIQ.getBadgeUrl())
                        .into(holder.imgBadge);
            }
        }
    }

    @Override
    public int getItemCount() {
        return leaderBoardList.size();
    }

    public void setLeaderBoardList(ArrayList<?> leaderBoardList) {
        this.leaderBoardList = leaderBoardList;
        notifyDataSetChanged();
    }

    public static class LeaderBoardListItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgBadge;
        private TextView txtName;
        private TextView txtDesc;

        public LeaderBoardListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBadge = itemView.findViewById(R.id.imgBadge);
            txtName = itemView.findViewById(R.id.txtName);
            txtDesc = itemView.findViewById(R.id.txtDesc);
        }
    }
}
