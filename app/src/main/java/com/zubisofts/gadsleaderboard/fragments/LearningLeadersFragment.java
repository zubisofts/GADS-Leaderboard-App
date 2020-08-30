package com.zubisofts.gadsleaderboard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

public class LearningLeadersFragment extends Fragment {

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_learning_leaders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LeaderBoardViewModel leaderBoardViewModel = new ViewModelProvider.NewInstanceFactory().create(LeaderBoardViewModel.class);
        leaderBoardViewModel.fetchLeaderBoardByHours();

        RecyclerView leaderBordList = view.findViewById(R.id.leaderBordList);
        LeaderBoardListAdapter leaderBoardListAdapter = new LeaderBoardListAdapter(getContext());
        leaderBordList.setLayoutManager(new LinearLayoutManager(getContext()));
        leaderBordList.setAdapter(leaderBoardListAdapter);

        LinearLayout progressLayout = view.findViewById(R.id.progress);
        LinearLayout errorLayout = view.findViewById(R.id.errorLayout);
        errorLayout.setVisibility(View.GONE);

        errorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressLayout.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                leaderBoardViewModel.fetchLeaderBoardByHours();
            }
        });

        leaderBoardViewModel.getLeaderBordHour().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response.isError()) {
//                    Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                    errorLayout.setVisibility(View.VISIBLE);
                } else {
                    leaderBoardListAdapter.setLeaderBoardList((ArrayList<?>) response.getLeaderBoards());
                    errorLayout.setVisibility(View.GONE);
                }

                progressLayout.setVisibility(View.GONE);
            }
        });

    }


}