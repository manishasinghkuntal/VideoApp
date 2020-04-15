package com.manisha.videoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

@SerializedName("description")
@Expose
private String description;
@SerializedName("sources")
@Expose
private String sources;
@SerializedName("subtitle")
@Expose
private String subtitle;
@SerializedName("thumb")
@Expose
private String thumb;
@SerializedName("title")
@Expose
private String title;

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getSources() {
return sources;
}

public void setSources(String sources) {
this.sources = sources;
}

public String getSubtitle() {
return subtitle;
}

public void setSubtitle(String subtitle) {
this.subtitle = subtitle;
}

public String getThumb() {
return thumb;
}

public void setThumb(String thumb) {
this.thumb = thumb;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

}