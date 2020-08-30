package com.zubisofts.gadsleaderboard.repository;

import android.util.Log;

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


    private static final String TAG = "ApiRepository";

    public void fetchLeaderBoardByHours(MutableLiveData<Response> leaderBoardRes) {
        GADSAPI gadsapi = GADSAPIClient.getInstance("https://gadsapi.herokuapp.com/").create(GADSAPI.class);
        Call<List<LeaderBoardHour>> leaderBoardHours = gadsapi.getLeaderBoardHours();
        leaderBoardHours.enqueue(new Callback<List<LeaderBoardHour>>() {
            @Override
            public void onResponse(Call<List<LeaderBoardHour>> call, retrofit2.Response<List<LeaderBoardHour>> response) {
                if (response.isSuccessful()) {
                    leaderBoardRes.postValue(new Response(false, response.body()));
                } else {
                    leaderBoardRes.postValue(new Response(true, null));
                }
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
                if (response.isSuccessful()) {
                    leaderBordIQ.postValue(new Response(false, response.body()));
                } else {
                    leaderBordIQ.postValue(new Response(true, null));
                }
            }

            @Override
            public void onFailure(Call<List<LeaderBoardIQ>> call, Throwable t) {
                leaderBordIQ.postValue(new Response(true, null));
            }
        });
    }

    public void submitProject(String email, String lastName, String firstName, String link, MutableLiveData<Boolean> projectSubmitionResult) {
        GADSAPI gadsapi = GADSAPIClient.getInstance("https://docs.google.com/forms/d/e/")
                .create(GADSAPI.class);
        Call<Void> submission = gadsapi.submitProject(
                email,
                lastName,
                firstName,
                link
        );
        submission.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (response.isSuccessful()) {
                    projectSubmitionResult.postValue(true);
                } else {
                    projectSubmitionResult.postValue(false);
                }

                Log.i(TAG, "onResponse: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                projectSubmitionResult.postValue(false);
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
