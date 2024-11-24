//package mk.ukim.finki.wp.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.Artist;
//import mk.ukim.finki.wp.lab.model.Song;
//import mk.ukim.finki.wp.lab.service.ArtistService;
//import mk.ukim.finki.wp.lab.service.SongService;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(urlPatterns = "/artist")
//public class ArtistServlet extends HttpServlet {
//    public final TemplateEngine springTemplateEngine;
//    public final ArtistService artistService;
//    public final SongService songService;
//
//    public ArtistServlet(TemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.artistService = artistService;
//        this.songService = songService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("trackId", req.getParameter("trackId"));
//        context.setVariable("artists", artistService.listArtists());
//        springTemplateEngine.process("artistList.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String trackId = req.getParameter("trackId");
//        String artistId = req.getParameter("artistId");
//        Artist artist = artistService.findById(Long.parseLong(artistId));
//        songService.findByTrackId(trackId).ifPresent(song -> songService.addArtistToSong(artist, song));
//        resp.sendRedirect("/songDetails?trackId=" + trackId);
//    }
//}
