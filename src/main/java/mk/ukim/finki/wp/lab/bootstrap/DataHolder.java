package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Component;

@Component
public class DataHolder {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public DataHolder(SongRepository songRepository, AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @PostConstruct
    public void init() {

        if (albumRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Album album = new Album("albumName" + i, "genre" + i, String.valueOf(2000 + i));
                albumRepository.save(album);
            }
        }
        if (songRepository.count() == 0) {
            Album album = albumRepository.findAll().stream().findFirst().get();
            for (int i = 1; i <= 10; i++) {

                Song song = new Song(
                        String.valueOf(i),
                        "Title " + i,
                        "Genre " + (i % 3 + 1),
                        2000 + i,
                        album
                );
                songRepository.save(song);
            }
        }

    }

}
