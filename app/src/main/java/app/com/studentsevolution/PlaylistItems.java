package app.com.studentsevolution;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlaylistItems implements Serializable {
    @SerializedName("id")
    String Id;

    @SerializedName("snippet")
    Snippet Snippet;

    public class Snippet {
        @SerializedName("title")
        String Title;

        @SerializedName("publishedAt")
        String Date;

        @SerializedName("thumbnails")
        Thumbnails Thumbnails;
    }

    public class Thumbnails {
        @SerializedName("medium")
        Medium Medium;

        class Medium {
            @SerializedName("url")
            String Url;
        }
    }

    public String getPlaylistId() {
        return Id;
    }

    public void setPlaylistId(String id) {
        Id = id;
    }

    public String getTitle() {
        if (Snippet.Title == null) {
            return "";
        }

        return Snippet.Title;
    }


    public String getDate() {
        return Snippet.Date;
    }

    public String getDefaultImage() {
        return Snippet.Thumbnails.Medium.Url;
    }
}