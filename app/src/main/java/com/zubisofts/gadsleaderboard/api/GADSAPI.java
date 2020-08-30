package com.zubisofts.gadsleaderboard.api;

import com.zubisofts.gadsleaderboard.model.LeaderBoardHour;
import com.zubisofts.gadsleaderboard.model.LeaderBoardIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GADSAPI {

        @GET("api/hours")
        Call<List<LeaderBoardHour>> getLeaderBoardHours();

        @GET("api/skilliq")
        Call<List<LeaderBoardIQ>> getLeaderBoardIQ();

        @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
        @FormUrlEncoded
        Call<Void> submitProject(
                @Field("entry.1824927963") String emailAddress,
                @Field("entry.1877115667") String lastName,
                @Field("entry.2006916086") String firstName,
                @Field("entry.284483984") String link
        );

}
