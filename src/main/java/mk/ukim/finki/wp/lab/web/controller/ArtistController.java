package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    public final ArtistService artistService;
    public final SongService songService;

    public ArtistController(TemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("")
    protected String getArtists(@RequestParam String trackId, Model model) throws ServletException, IOException {
        Long trackIdLong = Long.parseLong(trackId);
        model.addAttribute("trackId", trackIdLong);
        model.addAttribute("artists", artistService.listArtists());
        return "artistList.html";
    }

    @PostMapping("")
    protected String doPost(@RequestParam String trackId, @RequestParam String artistId, Model model) throws ServletException, IOException {
        Artist artist = artistService.findById(Long.parseLong(artistId));
//        songService.findByTrackId(trackId).ifPresent(song -> songService.addArtistToSong(artist, song));
        return "redirect:/songDetails?trackId=" + trackId;
    }
}
