package app.wiki.parsing;

import app.domain.mayhem.Album;
import app.domain.mayhem.Artist;
import app.domain.mayhem.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ParserService {

    public static Artist getArtist(String artistName) {
        ArtistParser artistParser = ArtistParser.getArtist(artistName);
        Artist artist = artistParser.getArtist();
        return artist;
    }

    public static List<Album> getArtistAlbums(String artistName){
        ArtistParser artistParser = ArtistParser.getArtist(artistName);
        List<Album> albums = new ArrayList<Album>();
        List<AlbumParser> albumParsers = artistParser.getAlbumParsersList();

        for(Album album : getArtistAlbums(albumParsers)){
            albums.add(album);
        }
        return albums;
    }

    private static List<Album> getArtistAlbums(List<AlbumParser> albumParsers){
        List<Album> albums = new ArrayList<Album>();
        Album album;
        for(AlbumParser albumParser : albumParsers){
            AlbumHandler albumHandler = new AlbumHandler(albumParser);
            album = albumHandler.getAlbum();
            for(Song song: albumHandler.getAlbumSongs()){
                album.addSong(song);
            }
            albums.add(album);
        }
        return albums;
    }

    public static List<Song> getArtistSongs(String artistName) {
        ArtistParser artistParser = ArtistParser.getArtist(artistName);
        List<AlbumParser> albumParsers = artistParser.getAlbumParsersList();

        List<Song> songs = new ArrayList<Song>();
        List<Song> albumSongs = new ArrayList<Song>();

        //Get ExecutorService from Executors utility class, thread pool size is the same as albums number
        ExecutorService executor = Executors.newFixedThreadPool(albumParsers.size());

        //create a list to hold the Future object associated with Callable
        List<Future<List<Song>>> list = new ArrayList<Future<List<Song>>>();

        for(AlbumParser albumParser : albumParsers){

            AlbumHandler albumHandler = new AlbumHandler(albumParser);
            //Create MyCallable instance
            Callable<List<Song>> callable = albumHandler;
            Future<List<Song>> future = executor.submit(callable);
            //save Future to the list, we can get return value using Future
            list.add(future);

            //albumSongs =  SongParser.getAlbumSongs(albumParser);
            //songs.addAll(albumSongs);
        }

        for(Future<List<Song>> fut : list){
            try {
                songs.addAll(fut.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
        return songs;
    }


}
