package app.com.studentsevolution;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class YouTubeModel implements Serializable {
    @SerializedName("nextPageToken")
    String NextPageToken;

    @SerializedName("pageInfo")
    PageInfo PageInfo;

    @SerializedName("items")
    List<PlaylistItems> playlistItems;

    class PageInfo {
        @SerializedName("totalResults")
        int TotalResult;
    }

    int getTotalPlayList() {
        return PageInfo.TotalResult;
    }

    String getNextPageToken() {
        return NextPageToken;
    }

    List<PlaylistItems> getPlaylistItems() {
        return playlistItems;
    }
}