package com.surah.rahman.audio.app.Modelclasses;

public class SongInfoModel {

    public String SongName;
    public String ArtistName;
    String Url;

    public SongInfoModel(String songName, String artistName, String url) {
        SongName = songName;
        ArtistName = artistName;
        Url=url;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
