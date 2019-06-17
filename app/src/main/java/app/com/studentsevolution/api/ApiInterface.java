package app.com.studentsevolution.api;


import app.com.studentsevolution.YouTubeModel;
import app.com.studentsevolution.YouTubeVideoModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET
    Call<YouTubeModel> getPlaylist(@Url String url);

    @GET
    Call<YouTubeVideoModel> getVideos(@Url String url);

}