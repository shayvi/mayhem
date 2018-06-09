package app.wiki.parsing;

import app.domain.mayhem.Album;
import app.domain.mayhem.Song;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class AlbumHandler implements Callable<List<Song>> {

    private Album album;
    private AlbumParser albumParser;

    public AlbumHandler(AlbumParser albumParser) {
        this.albumParser = albumParser;

        //TODO save genres
        this.album = new Album(albumParser.getAlbumName(),albumParser.getAlbumYear());
    }


    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Song> getAlbumSongs(){
        List<Song> songs = new ArrayList<Song>();
        for(Element songElement : albumParser.getSongsElements() ){
            songs.add(SongParser.getSong(songElement));
        }
        return songs;
    }

    @Override
    public List<Song> call() throws Exception {
        return this.getAlbumSongs();
    }
}
