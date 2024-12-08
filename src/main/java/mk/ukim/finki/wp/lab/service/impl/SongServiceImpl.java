package mk.ukim.finki.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    public final SongRepository songRepository;
    public final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }


    @Override
    @Transactional
    public Optional<Song> findByTrackId(String trackId) {
        Optional<Song> song = songRepository.findByTrackId(trackId);
        return song;
    }


    @Override
    public Optional<Song> findById(Long songId) {
        return songRepository.findById(songId);
    }

    @Override
    public void deleteSongById(Long songId) {
        songRepository.deleteById(songId);
    }

    @Override
    public void saveSong(String trackId, String title, String genre, int releaseYear, Long songID, Long albumId) {
        Album album = albumRepository.findById(albumId).orElse(null);
        Song song = new Song(trackId, title, genre, releaseYear, album);
        if (songID != null) song.setId(songID);
        songRepository.save(song);
    }

    @Override
    public List<Song> findByAlbumId(String albumId) {
        return songRepository.findAllByAlbum_Id(Long.valueOf(albumId));
    }


}
