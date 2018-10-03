package com.itis.android.vkapp.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.itis.android.vkapp.consts.ApiConstants;

import java.util.Map;

public class VideoGetRequestModel extends BaseRequestModel {

    @SerializedName(ApiConstants.VIDEOS)
    private String videos;

    public VideoGetRequestModel() {
    }

    public VideoGetRequestModel(String ownerId, String videoId) {
        this.videos = ownerId + "_" + videoId;
    }

    public VideoGetRequestModel(int ownerId, int videoId) {
        this.videos = ownerId + "_" + videoId;
    }

    public VideoGetRequestModel(String videos) {
        this.videos = videos;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(ApiConstants.VIDEOS, getVideos());
    }
}
