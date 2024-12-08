package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping("")
    public String getSongsPage(@RequestParam(required = false) String error, @RequestParam(required = false) String albumId, Model model) {
        List<Album> albums = albumService.findAll();
        List<Song> songs = albumId != null && !albumId.equals("-1") && !albumId.isEmpty() ? songService.findByAlbumId(albumId) : songService.listSongs();
        model.addAttribute("songs", songs);
        model.addAttribute("albums", albums);
        if (albumId != null) model.addAttribute("currentAlbumId", Long.valueOf(albumId));
        return "listSongs.html";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam String title, @RequestParam String trackId, @RequestParam String genre, @RequestParam int releaseYear, @RequestParam Long albumId, Model model) {
        songService.saveSong(trackId, title, genre, releaseYear, null, albumId);
        return "redirect:/songs";
    }

    @GetMapping("/add")
    public String saveSong(Model model) {
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);

        return "addSong.html";
    }

    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId, Model model) {
        Song currentSong = songService.findById(songId).orElse(null);
        model.addAttribute("song", currentSong);
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
        return "editSong.html";
    }

    @PostMapping("/edit")
    public String editSong(@RequestParam String title, @RequestParam String trackId, @RequestParam String genre, @RequestParam int releaseYear, @RequestParam Long albumId, @RequestParam Long songID, Model model) {
        ;
        songService.saveSong(trackId, title, genre, releaseYear, songID, albumId);
        return "redirect:/songs";
    }

    @PostMapping("delete/{songId}")
    public String deleteSong(@PathVariable Long songId, Model model) {
        songService.deleteSongById(songId);
        return "redirect:/songs";
    }

}
