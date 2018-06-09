package app.web.rest.mayhem.music;

/**
 * Created by Shay Vikel on 19/05/2017.
 */

import app.domain.mayhem.Artist;
import app.domain.mayhem.Song;
import app.service.mayhem.music.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/mayhem")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PostMapping(value = "/{artistName}")
    public Artist addArtist(@PathVariable Artist artist) {
        return artistService.save(artist);
    }


//    @RequestMapping(value = "/{bandName}", method = RequestMethod.GET)
//    public Artist getAllArtistSongs(@PathVariable String bandName) {
////        return new Artist(bandName,"USA",null);
//    }

//
//    @RequestMapping(value = "/{bandName}/{albumName}", method = RequestMethod.GET)
//    public Album getAlbum(@PathVariable String bandName,
//                          @PathVariable String albumName) {
////        return new Album(albumName,  2000 , null , null, bandName);
//    }


    @RequestMapping(value = "/{bandName}/{albumName}/{songName}", method = RequestMethod.GET)
    public Song getSongByName(@PathVariable String bandName,
                              @PathVariable String albumName,
                              @PathVariable String songName) {
        return new Song();
    }

}
