package com.manisha.videoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosModel {

@SerializedName("status")
@Expose
private String status;
@SerializedName("videos")
@Expose
private List<Video> videos = null;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public List<Video> getVideos() {
return videos;
}

public void setVideos(List<Video> videos) {
this.videos = videos;
}

}