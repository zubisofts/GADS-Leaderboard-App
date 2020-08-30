package com.zubisofts.gadsleaderboard.repository;

import androidx.lifecycle.MutableLiveData;

import com.zubisofts.gadsleaderboard.api.GADSAPI;
import com.zubisofts.gadsleaderboard.api.GADSAPIClient;
import com.zubisofts.gadsleaderboard.model.LeaderBoardHour;
import com.zubisofts.gadsleaderboard.model.LeaderBoardIQ;
import com.zubisofts.gadsleaderboard.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiRepository {

    public void fetchLeaderBoardByHours(MutableLiveData<Response> leaderBoardRes) {
        GADSAPI gadsapi = GADSAPIClient.getInstance("https://gadsapi.herokuapp.com/").create(GADSAPI.class);
        Call<List<LeaderBoardHour>> leaderBoardHours = gadsapi.getLeaderBoardHours();
        leaderBoardHours.enqueue(new Callback<List<LeaderBoardHour>>() {
            @Override
            public void onResponse(Call<List<LeaderBoardHour>> call, retrofit2.Response<List<LeaderBoardHour>> response) {
                leaderBoardRes.postValue(new Response(false, response.body()));
            }

            @Override
            public void onFailure(Call<List<LeaderBoardHour>> call, Throwable t) {
                leaderBoardRes.postValue(new Response(true, null));
            }
        });

    }

    public void fetchLeaderBoardByIQ(MutableLiveData<Response> leaderBordIQ) {
        GADSAPI gadsapi = GADSAPIClient.getInstance("https://gadsapi.herokuapp.com/").create(GADSAPI.class);
        Call<List<LeaderBoardIQ>> leaderBoardIQ = gadsapi.getLeaderBoardIQ();
        leaderBoardIQ.enqueue(new Callback<List<LeaderBoardIQ>>() {
            @Override
            public void onResponse(Call<List<LeaderBoardIQ>> call, retrofit2.Response<List<LeaderBoardIQ>> response) {
                leaderBordIQ.postValue(new Response(false, response.body()));
            }

            @Override
            public void onFailure(Call<List<LeaderBoardIQ>> call, Throwable t) {
                leaderBordIQ.postValue(new Response(true, null));
            }
        });
    }

    public void submitProject(String email, String lastName, String firstName, String link, MutableLiveData<Boolean> projectSubmitionResult) {
        GADSAPI gadsapi = GADSAPIClient.getInstance("https://docs.google.com/forms/d/e/").create(GADSAPI.class);
        Call<Void> submition = gadsapi.submitProject(
                email,
                lastName,
                firstName,
                link
        );
        submition.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                projectSubmitionResult.postValue(true);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                projectSubmitionResult.postValue(false);
            }
        });
    }
}
