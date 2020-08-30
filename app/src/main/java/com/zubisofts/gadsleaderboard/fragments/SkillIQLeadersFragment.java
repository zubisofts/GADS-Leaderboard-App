package com.zubisofts.gadsleaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zubisofts.gadsleaderboard.R;
import com.zubisofts.gadsleaderboard.adapters.LeaderBoardListAdapter;
import com.zubisofts.gadsleaderboard.model.Response;
import com.zubisofts.gadsleaderboard.viewmodel.LeaderBoardViewModel;

import java.util.ArrayList;

public class SkillIQLeadersFragment extends Fragment {

    private LeaderBoardViewModel leaderBoardViewModel;

    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_skill_i_q_leaders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        leaderBoardViewModel = new ViewModelProvider.NewInstanceFactory().create(LeaderBoardViewModel.class);
        leaderBoardViewModel.fetchLeaderBoardByIQ();

        RecyclerView leaderBordList = view.findViewById(R.id.leaderBordList);
        LeaderBoardListAdapter leaderBoardListAdapter = new LeaderBoardListAdapter(getContext());
        leaderBordList.setLayoutManager(new LinearLayoutManager(getContext()));
        leaderBordList.setAdapter(leaderBoardListAdapter);

        LinearLayout progressLayout = view.findViewById(R.id.progress);

        leaderBoardViewModel.getLeaderBordIQ().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response.isError()) {
                    Toast.makeText(getContext(), "An error occured", Toast.LENGTH_SHORT).show();
                } else {
                    leaderBoardListAdapter.setLeaderBoardList((ArrayList<?>) response.getLeaderBoards());
                }

                progressLayout.setVisibility(View.GONE);
            }
        });

    }
}