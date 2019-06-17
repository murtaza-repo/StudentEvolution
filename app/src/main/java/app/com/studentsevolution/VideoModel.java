package app.com.studentsevolution;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoModel implements Serializable {

    //  @SerializedName("id")
    //String id;

    @SerializedName("snippet")
    Snippet Snippet;

    public class Snippet {
        @SerializedName("title")
        String Title;

        @SerializedName("description")
        String Description;

        @SerializedName("publishedAt")
        String Date;

        @SerializedName("thumbnails")
        Thumbnails Thumbnails;

        @SerializedName("channelTitle")
        String ChannelTitle;

        @SerializedName("position")
        String Position;

        @SerializedName("resourceId")
        ResourceId ResourceId;
    }

    public class Thumbnails {
        @SerializedName("default")
        Default Default;

        class Default {
            @SerializedName("url")
            String Url;
        }
    }

    public class ResourceId{
        @SerializedName("videoId")
        String videoId;
    }

    //public String getPlaylistId() {
    //  return id;
    //}

    public String getTitle() {
        if (Snippet.Title == null) {
            return "";
        }

        return Snippet.Title;
    }

    public String getDescription() {
        return Snippet.Description;
    }

    public String getDate() {
        return Snippet.Date;
    }

    public String getDefaultImage() {
        return Snippet.Thumbnails.Default.Url;
    }

    public String getPostion(){return  Snippet.Position;}

    public String getVideoId(){ return Snippet.ResourceId.videoId;}

}
