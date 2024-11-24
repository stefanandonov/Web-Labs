package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();


    @PostConstruct
    public void init() {

        for (int i = 0; i < 6; i++) {
            albums.add(new Album(String.format("Album%d", i), String.format("Genre%d", i), String.format("200%d", i)));
        }

        artists = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            artists.add(new Artist(Long.parseLong(String.valueOf(i)), String.format("ArtistName%d", i), String.format("ArtistLastName%d", i), String.format("Bio%d", i)));
        }
        artists.add(new Artist(Long.parseLong("101"), "Darko", "Donev", "Darko Donev Bio"));
        songs = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Song newSong = new Song(String.valueOf(i), String.format("Title%d", i), String.format("Genre%d", i), 2000 + i, new ArrayList<>());
            newSong.setAlbum(albums.get(i - 1));
            songs.add(newSong);
        }
    }

}
