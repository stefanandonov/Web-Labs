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
            // Pink Floyd Albums
            Album darkSide = new Album("The Dark Side of the Moon", "Progressive Rock", "1973");
            Album wishYouWereHere = new Album("Wish You Were Here", "Progressive Rock", "1975");
            Album theWall = new Album("The Wall", "Progressive Rock", "1979");

            albumRepository.save(darkSide);
            albumRepository.save(wishYouWereHere);
            albumRepository.save(theWall);

            // Ed Sheeran Albums
            Album divide = new Album("Divide", "Pop", "2017");
            Album x = new Album("Multiply", "Pop", "2014");
            Album equals = new Album("Equals", "Pop", "2021");

            albumRepository.save(divide);
            albumRepository.save(x);
            albumRepository.save(equals);

            // Toto Cutugno Albums
            Album lItaliano = new Album("L'Italiano", "Pop", "1983");
            Album mediterraneo = new Album("Mediterraneo", "Pop", "1987");
            Album voglioDiVivere = new Album("Voglio di Vivere Così", "Pop", "1990");

            albumRepository.save(lItaliano);
            albumRepository.save(mediterraneo);
            albumRepository.save(voglioDiVivere);
        }

        if (songRepository.count() == 0) {
            // Pink Floyd Songs
            Album darkSide = albumRepository.findAll().stream().filter(a -> a.getName().equals("The Dark Side of the Moon")).findFirst().get();
            songRepository.save(new Song("1", "Time", "Progressive Rock", 1973, darkSide));
            songRepository.save(new Song("2", "Money", "Progressive Rock", 1973, darkSide));

            Album wishYouWereHere = albumRepository.findAll().stream().filter(a -> a.getName().equals("Wish You Were Here")).findFirst().get();
            songRepository.save(new Song("3", "Shine On You Crazy Diamond", "Progressive Rock", 1975, wishYouWereHere));
            songRepository.save(new Song("4", "Wish You Were Here", "Progressive Rock", 1975, wishYouWereHere));

            Album theWall = albumRepository.findAll().stream().filter(a -> a.getName().equals("The Wall")).findFirst().get();
            songRepository.save(new Song("5", "Another Brick in the Wall", "Progressive Rock", 1979, theWall));
            songRepository.save(new Song("6", "Comfortably Numb", "Progressive Rock", 1979, theWall));

            // Ed Sheeran Songs
            Album divide = albumRepository.findAll().stream().filter(a -> a.getName().equals("Divide")).findFirst().get();
            songRepository.save(new Song("7", "Shape of You", "Pop", 2017, divide));
            songRepository.save(new Song("8", "Perfect", "Pop", 2017, divide));

            Album x = albumRepository.findAll().stream().filter(a -> a.getName().equals("Multiply")).findFirst().get();
            songRepository.save(new Song("9", "Thinking Out Loud", "Pop", 2014, x));
            songRepository.save(new Song("10", "Photograph", "Pop", 2014, x));

            Album equals = albumRepository.findAll().stream().filter(a -> a.getName().equals("Equals")).findFirst().get();
            songRepository.save(new Song("11", "Bad Habits", "Pop", 2021, equals));
            songRepository.save(new Song("12", "Shivers", "Pop", 2021, equals));

            // Toto Cutugno Songs
            Album lItaliano = albumRepository.findAll().stream().filter(a -> a.getName().equals("L'Italiano")).findFirst().get();
            songRepository.save(new Song("13", "L'Italiano", "Pop", 1983, lItaliano));
            songRepository.save(new Song("14", "Serenata", "Pop", 1983, lItaliano));

            Album mediterraneo = albumRepository.findAll().stream().filter(a -> a.getName().equals("Mediterraneo")).findFirst().get();
            songRepository.save(new Song("15", "Mediterraneo", "Pop", 1987, mediterraneo));
            songRepository.save(new Song("16", "Solo Noi", "Pop", 1987, mediterraneo));

            Album voglioDiVivere = albumRepository.findAll().stream().filter(a -> a.getName().equals("Voglio di Vivere Così")).findFirst().get();
            songRepository.save(new Song("17", "Voglio di Vivere Così", "Pop", 1990, voglioDiVivere));
            songRepository.save(new Song("18", "Le Mamme", "Pop", 1990, voglioDiVivere));
        }
    }
}