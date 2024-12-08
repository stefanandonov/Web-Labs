package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();

    Optional<Song> findByTrackId(String trackId);

    Optional<Song> findById(Long songId);

    void deleteSongById(Long songId);


    void saveSong(String trackId, String title, String genre, int releaseYear, Long songID, Long albumId);

    List<Song> findByAlbumId(String albumId);
}
