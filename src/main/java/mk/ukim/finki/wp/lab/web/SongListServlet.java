package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/listSongs")
public class SongListServlet extends HttpServlet {

    public final TemplateEngine springTemplateEngine;
    public final SongService songService;

    public SongListServlet(TemplateEngine springTemplateEngine, SongService songService) {
        this.springTemplateEngine = springTemplateEngine;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        String songName = req.getParameter("songNameFilter");
        List<Song> songs = songService.listSongs();
        if (songName != null && !songName.isBlank()) {
            songs = songs.stream().filter(song -> song.getTitle().toLowerCase().contains(songName.toLowerCase())).toList();
        }
        WebContext context = new WebContext(webExchange);
        context.setVariable("songs", songs);
        springTemplateEngine.process("listSongs.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/artist?trackId=" + req.getParameter("trackId")); // TODO HOW TO DO IT WITH DISPATCHER
    }
}
