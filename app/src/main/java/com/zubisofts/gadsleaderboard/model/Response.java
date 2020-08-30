package com.zubisofts.gadsleaderboard.model;

public class Response {

    boolean error;
    Object leaderBoards;

    public Response(boolean error, Object leaderBoards) {
        this.error = error;
        this.leaderBoards = leaderBoards;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Object getLeaderBoards() {
        return leaderBoards;
    }

    public void setLeaderBoards(Object leaderBoards) {
        this.leaderBoards = leaderBoards;
    }
}
