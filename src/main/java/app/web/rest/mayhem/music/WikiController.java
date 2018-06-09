package app.web.rest.mayhem.music;

/**
 * Created by Shay Vikel on 19/05/2017.
 */

import app.config.mayhem.MayhemConfig;
import app.domain.mayhem.Album;
import app.domain.mayhem.Artist;
import app.domain.mayhem.Song;
import app.service.mayhem.music.AlbumService;
import app.service.mayhem.music.ArtistService;
import app.service.mayhem.music.SongService;
import app.wiki.parsing.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:7200")
@RequestMapping(value = "/wiki")
public class WikiController {


    @Autowired
    ParserService parserService;

    @Autowired
    ArtistService artistService;

    @Autowired
    AlbumService albumService;

    @Autowired
    SongService songService;


    @CrossOrigin(origins = "http://localhost:7200")
    @RequestMapping(value = "/{bandName}", method = RequestMethod.GET)
    public List<Song> getAllArtistSongs(@PathVariable String bandName) {
        bandName  = bandName.replaceAll(" ", "_").toLowerCase();
        long startTime = System.nanoTime();
        List<Song> songs = parserService.getArtistSongs(bandName);
        System.out.println(MayhemConfig.calcElapsedTime(startTime));
        return songs;
    }

    @CrossOrigin(origins = "http://localhost:7200")
    @PostMapping(value = "/{bandName}")
    public Artist saveArtist(@PathVariable String bandName) {
        bandName  = bandName.replaceAll(" ", "_").toLowerCase();
        Artist artist = parserService.getArtist(bandName);
        artistService.save(artist);

        for(Album album: parserService.getArtistAlbums(bandName)){
            artist.addAlbum(album);
            albumService.save(album);
            for(Song song: album.getSongs()){
                songService.save(song);
            }
        }
        return artist;
    }


//    @PostMapping(value = "/{users}")
//    public void saveArtist(@PathVariable String username) {
//
//    }




//    @RequestMapping(value = "/{bandName}/{albumName}", method = RequestMethod.GET)
//    public Album getAlbum(@PathVariable String bandName,
//                          @PathVariable String albumName) {
//        Artist band = ParserService.getArtist(bandName);  //TODO error handling : noSuchAlbum noSuchSong etc.
//        Album album = band.getAlbumByName(albumName);
//        return album;
//    }
//
//
//    @RequestMapping(value = "/{bandName}/{albumName}/{songName}", method = RequestMethod.GET)
//    public Song getSongByName(@PathVariable String bandName,
//                              @PathVariable String albumName,
//                              @PathVariable String songName) {
//        return new Song(3, "Somewhere I Belong", "Meteora");
//    }

}
