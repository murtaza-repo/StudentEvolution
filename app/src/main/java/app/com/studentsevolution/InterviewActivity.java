package app.com.studentsevolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import app.com.studentsevolution.api.ApiClient;
import app.com.studentsevolution.api.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterviewActivity extends AppCompatActivity implements SpokenAdapter.OnSubCateListener {


    String TAG = CommunicationActivity.class.getSimpleName();
    //List<PlaylistItems> items;

    String YouTubeAPIChannelId = "UCTRxnMudmOGNw0g4gZf5D8g";
    String YouTubeAPIKey = "AIzaSyDZgwTXO52cDoeGBqZAn45X-tSsaxPhQ1M";
    String YouTubeAPIUrl = "https://www.googleapis.com/youtube/v3/playlists?" +
            "part=snippet&" +
            "part=statistics&" +
            "channelId=" + YouTubeAPIChannelId + "&" +
            "&maxResults=50&" +
            "order=date&" +
            "key=" + YouTubeAPIKey;

    //private List<PlaylistItems> items;
    RecyclerView playRecycle;
    SpokenAdapter adapter;
    ImageView play_image;
    TextView play_title;
    YouTubeModel model;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        progressBar = findViewById(R.id.playProgress);
        try {
            play_image = findViewById(R.id.play_image);
            play_title = findViewById(R.id.play_title);

            playRecycle = findViewById(R.id.playRecylce);
            playRecycle.setItemAnimator(new DefaultItemAnimator());
            playRecycle.setHasFixedSize(true);
            playRecycle.setLayoutManager(new LinearLayoutManager(this));

            adapter = new SpokenAdapter(InterviewActivity.this);
            playRecycle.setAdapter(adapter);
            showProgressBar();
            loadPlaylits(YouTubeAPIUrl);
        }catch (Exception ex){
            Log.e(TAG,ex.getMessage());
        }

    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    private void loadPlaylits(String url) {
        ApiInterface apiService = ApiClient.createService(ApiInterface.class);
        if (apiService == null) return;

        Call<YouTubeModel> call = apiService.getPlaylist(url);
        call.enqueue(new Callback<YouTubeModel>() {
            @Override
            public void onResponse(Call<YouTubeModel> call, Response<YouTubeModel> response) {
                YouTubeModel serverResponse = response.body();
                hideProgressBar();
                loadDataAction(serverResponse);
            }

            @Override
            public void onFailure(Call<YouTubeModel> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void loadDataAction(YouTubeModel item) {

        if (item != null && item.getPlaylistItems() != null) {
            model = item;

            adapter.addAll(model.getPlaylistItems());
            adapter.notifyDataSetChanged();

        }
    }


    @Override
    public void OnSubCateClick(int position) {

        //System.out.println(adapter.getPlaylistId(position));
        if(position == 0){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            intent.putExtra("videoName","Logical Puzzles");
            //intent.putExtra("PLAYLIST_ID", playListId);
            startActivity(intent);
        }
        else if(position == 1){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            intent.putExtra("videoName","Logical Puzzles");
            //intent.putExtra("PLAYLIST_ID", playListId);
            startActivity(intent);
        }else if(position == 2){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            intent.putExtra("videoName","Logical Puzzles");
            //intent.putExtra("PLAYLIST_ID", playListId);
            startActivity(intent);
        }else if(position == 3){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            intent.putExtra("videoName","Logical Puzzles");
            //intent.putExtra("PLAYLIST_ID", playListId);
            startActivity(intent);
        }else if(position == 4){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            intent.putExtra("videoName","Logical Puzzles");
            //intent.putExtra("PLAYLIST_ID", playListId);
            startActivity(intent);
        }else if(position == 5){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            //intent.putExtra("PLAYLIST_ID", playListId);
            intent.putExtra("videoName","Logical Puzzles");
            startActivity(intent);
        }else if(position == 6){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            //intent.putExtra("PLAYLIST_ID", playListId);
            intent.putExtra("videoName","Logical Puzzles");
            startActivity(intent);
        }else if(position == 7){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            //intent.putExtra("PLAYLIST_ID", playListId);
            intent.putExtra("videoName","Logical Puzzles");
            startActivity(intent);
        }else if(position == 8){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            //intent.putExtra("PLAYLIST_ID", playListId);
            intent.putExtra("videoName","Logical Puzzles");
            startActivity(intent);
        }else if(position == 9){
            Intent intent = new Intent(InterviewActivity.this,VideoActivity.class);
            intent.putExtra("playlistId", adapter.getPlaylistId(position));
            //intent.putExtra("PLAYLIST_ID", playListId);
            intent.putExtra("videoName","Logical Puzzles");
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"Wrong Click!",Toast.LENGTH_SHORT).show();
        }
    }
}
