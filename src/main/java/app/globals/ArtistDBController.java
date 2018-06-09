package app.globals;

import app.domain.mayhem.Artist;
import app.service.mayhem.music.ArtistService;
import app.wiki.parsing.AlbumParser;
import app.wiki.parsing.ArtistParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArtistDBController {

    @Autowired
    private ArtistService artistService;

    public void addArtistToDB(String artistName){
        artistName  = artistName.replaceAll(" ", "_").toLowerCase();
        ArtistParser artistParser = new ArtistParser(artistName);
        Artist artist = artistParser.getArtist();

        List<AlbumParser> albumParsers = artistParser.getAlbumParsersList();

    }

    //save artist to db
    //save album
    //save all songs to db
    //save album
    //save all songs to db
    //...
}
