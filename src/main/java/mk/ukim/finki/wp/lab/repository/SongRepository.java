package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {


    List<Song> findAll();

//     Artist addArtistToSong(Artist artist, Song song);

    List<Song> findAllByAlbum_Id(Long albumId);

    Optional<Song> findOneById(Long Id);


    @Query("SELECT s from Song s where s.trackId = :trackId")
    Optional<Song> findByTrackId(String trackId);
}
