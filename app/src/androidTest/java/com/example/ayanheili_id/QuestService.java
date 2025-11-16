package com.example.ayanheili_id;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface QuestService {

    // GET /api/quests
    @GET("quests")
    Call<List<Quest>> getAllQuests();

    // GET /api/quests?title=Something
    @GET("quests")
    Call<List<Quest>> searchByTitle(@Query("title") String title);

    // POST /api/quests
    @POST("quests")
    Call<Quest> createQuest(@Body Quest quest);
}
