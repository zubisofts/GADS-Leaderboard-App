package com.zubisofts.gadsleaderboard.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zubisofts.gadsleaderboard.model.Response;
import com.zubisofts.gadsleaderboard.repository.ApiRepository;

public class LeaderBoardViewModel extends ViewModel {

    private ApiRepository repository;
    private MutableLiveData<Response> leaderBordHour;
    private MutableLiveData<Response> leaderBordIQ;
    private MutableLiveData<Boolean> projectSubmitionResult;

    public LeaderBoardViewModel() {
        leaderBordHour = new MutableLiveData<>();
        leaderBordIQ = new MutableLiveData<>();
        projectSubmitionResult = new MutableLiveData<>();
        repository = new ApiRepository();
    }

    public void fetchLeaderBoardByHours() {
        repository.fetchLeaderBoardByHours(leaderBordHour);
    }

    public LiveData<Response> getLeaderBordHour() {
        return leaderBordHour;
    }

    public void fetchLeaderBoardByIQ() {
        repository.fetchLeaderBoardByIQ(leaderBordIQ);
    }

    public LiveData<Response> getLeaderBordIQ() {
        return leaderBordIQ;
    }

    public void submitProject(String email, String lastName, String firstName, String link) {
        repository.submitProject(email, lastName, firstName, link, projectSubmitionResult);
    }

    public MutableLiveData<Boolean> getProjectSubmitionResult() {
        return projectSubmitionResult;
    }
}
