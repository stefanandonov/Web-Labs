package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Song {
    @Id
    private Long id;

    String trackId;
    String title;
    String genre;
    int releaseYear;
    List<Artist> performers;

    @ManyToOne
    Album album;

    public Song(String trackId, String title, String genre, int releaseYear, List<Artist> performers) {
        this.id = (long) (Math.random() * 1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = performers;
    }

}
