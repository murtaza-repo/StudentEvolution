package app.com.studentsevolution;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import app.com.studentsevolution.api.ApiClient;
import app.com.studentsevolution.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    String TAG = CommunicationActivity.class.getSimpleName();
    //String playlistId = "PLsXdBvuJ5ox5eX6gADDo5ZHykXBaXYpVO";
    String playlistId;
    String YouTubeAPIKey;
    //String YouTubeAPIKey = "AIzaSyDZgwTXO52cDoeGBqZAn45X-tSsaxPhQ1M";
    String YouTubeAPIUrl;
    /*String YouTubeAPIUrl = "https://www.googleapis.com/youtube/v3/playlistItems?" +
            "part=snippet&" +
            "part=statistics&" +
            "maxResults=25&" +
            "playlistId=" + playlistId + "&" +
            "resourceId&" +
            "key=" + YouTubeAPIKey;*/

    Context context;
    VideoModel videoModel;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static final int RECOVERY_REQUEST = 1;
    private int selected = 0;

    TextView txtTotalVideo;
    TextView txt_video_name;
    ProgressBar progressBar;
    RecyclerView mRecyclerView;
    VideoRecyclerAdapter adapter;
    YouTubeVideoModel model;
    YouTubePlayer youTubePlayer;
    YouTubePlayerSupportFragment youtubePlayerFragment;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        playlistId = getIntent().getStringExtra("playlistId");
        YouTubeAPIKey = "AIzaSyDZgwTXO52cDoeGBqZAn45X-tSsaxPhQ1M";
        YouTubeAPIUrl = "https://www.googleapis.com/youtube/v3/playlistItems?" +
                "part=snippet&" +
                "part=statistics&" +
                "maxResults=25&" +
                "playlistId=" + playlistId + "&" +
                "resourceId&" +
                "key=" + YouTubeAPIKey;

        try {
            mRecyclerView = findViewById(R.id.recycler_view);
            progressBar = findViewById(R.id.progress_bar);
            txtTotalVideo = findViewById(R.id.txt_total_video);
            txt_video_name = findViewById(R.id.txt_video_name);

            txt_video_name.setText(getIntent().getStringExtra("videoName"));

            adapter = new VideoRecyclerAdapter(mContext);
            youtubePlayerFragment = (YouTubePlayerSupportFragment) this.getSupportFragmentManager()
                    .findFragmentById(R.id.frame_fragment);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());


            mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
                @Override
                protected void loadMoreItems() {
                    isLoading = true;
                    if (!isLastPage) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadVideos(YouTubeAPIUrl + "&pageToken=" + model.getNextPageToken());
                            }
                        }, 200);
                    }
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClicklListener(new VideoRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    selected = position;
                    if (adapter.getItems().size() > 0 && adapter.getItems().get(selected) != null && adapter.getItems().get(selected).getVideoId() != null) {

                        youTubePlayer.cueVideo(adapter.getItems().get(selected).getVideoId());
                        youTubePlayer.play();
                    }
                }
            });

            loadVideos(YouTubeAPIUrl);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }



    private void loadVideos(String url) {
        ApiInterface apiService = ApiClient.createService(ApiInterface.class);
        if (apiService == null) return;

        showProgressBar();
        Call<YouTubeVideoModel> call = apiService.getVideos(url);
        call.enqueue(new Callback<YouTubeVideoModel>() {
            @Override
            public void onResponse(Call<YouTubeVideoModel> call, Response<YouTubeVideoModel> response) {
                YouTubeVideoModel serverResponse = response.body();
                hideProgressBar();
                loadDataAction(serverResponse);
            }

            @Override
            public void onFailure(Call<YouTubeVideoModel> call, Throwable t) {
                Log.e(TAG, t.toString());
                hideProgressBar();
            }
        });
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void loadDataAction(YouTubeVideoModel item) {
        isLoading = false;

        if (item != null && item.getVideoModels() != null) {
            model = item;
            txtTotalVideo.setText("Total Videos: " + item.getTotalVideos());

            adapter.addAll(model.getVideoModels());
            adapter.notifyDataSetChanged();

            youtubePlayerFragment.initialize(YouTubeAPIKey, onInitializedListener);

            if (model.getNextPageToken() == null) {
                isLastPage = true;
            }
        }
    }

    YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
            if (!b) {
                youTubePlayer = player;
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                try {

                    if (adapter != null && adapter.getItems().size() > 0) {
                        if (adapter.getItems().get(selected) != null && adapter.getItems().get(selected).getVideoId() != null) {
                            youTubePlayer.cueVideo(adapter.getItems().get(selected).getVideoId());
                            youTubePlayer.loadVideo(adapter.getItems().get(selected).getVideoId());
                            youTubePlayer.play();
                        }
                    }

                    youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                        @Override
                        public void onLoading() {

                        }

                        @Override
                        public void onLoaded(String s) {

                        }

                        @Override
                        public void onAdStarted() {

                        }

                        @Override
                        public void onVideoStarted() {
                            Log.e(TAG, "Video Started");
                        }

                        @Override
                        public void onVideoEnded() {
                            Log.e(TAG, "Video End");
                            if (selected >= adapter.getItems().size()) {
                                selected = 0;
                            } else {
                                selected++;
                            }

                            youTubePlayer.cueVideo(adapter.getItems().get(selected).getVideoId());
                            youTubePlayer.play();
                        }

                        @Override
                        public void onError(YouTubePlayer.ErrorReason errorReason) {

                        }
                    });

                } catch (Exception e) {
                    Log.e(TAG, "Exception occurred");
                }
            }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            if (youTubeInitializationResult.isUserRecoverableError()) {
                youTubeInitializationResult.getErrorDialog((Activity) context, RECOVERY_REQUEST).show();
            } else {
                Toast.makeText(context, "Error Initializing Youtube Player", Toast.LENGTH_LONG).show();
            }
        }
    };

}

