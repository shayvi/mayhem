package app.wiki.parsing;

import app.domain.mayhem.Artist;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArtistParser {

    private Document artistDoc;
    private Artist artist;

    public ArtistParser(String artistName){
        this.artistDoc = WikiRetriever.getArtistDoc(artistName);
        this.artist = new Artist(artistName,this.getOrigin());
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    public static ArtistParser getArtist(String artistName) {
        return new ArtistParser(artistName);
    }
    /**
     *
     * @return the origin country of the artist
     *
     * In wikipedia, origin is given in the following manner:
     * City, State/County, Country or City, Country
     * Examples:
     * Los Angeles, California, U.S.
     * Helsinki, Finland
     *
     */
    private String getOrigin(){
        Elements origin =  artistDoc.select("tr:has(th:containsOwn(Origin)) > td");

        String[] origins = origin.text().split(",");
        String country  = origins.length == 3 ?  origins[2].trim() : origins[1].trim();

        country = country.contains("U.S") || country.contains("United States") ?  "USA" : country;
        return  country;
    }

    /**
     *
     * @return artist albums Elements
     */
    private Elements getAlbumsElements(){
        Elements albumsElements =  artistDoc.select( "tr:contains(Studio albums) i a");

        if(albumsElements.size() == 0){
            Element albumsList = artistDoc.select("h2:contains(Discography) ~ ul ").first();
            albumsElements = albumsList.select("a");
        }
        if(albumsElements.size() == 0){
            albumsElements =  artistDoc.select( "dl:contains(Studio albums) ~ table i a");
        }

        //beacuse some times the headline "studio albums" is also given in <a> element.
        albumsElements = albumsElements.not("a:contains(Studio albums)");

        return albumsElements;
    }

    /**
     *
     * @return list of artist's albums parsers
     */
    public List<AlbumParser> getAlbumParsersList(){
        Elements albumsElements = this.getAlbumsElements();
        String albumName;
        String albumLink;
        AlbumParser albumParser;

        List<AlbumParser> parsers = new ArrayList<AlbumParser>();

        ExecutorService es = Executors.newCachedThreadPool();

        for(Element albumElement : albumsElements){

            //because there is a case that "Wikipedia does not have an article with this exact name"
            try{
                albumParser = AlbumParser.getAlbumParser(albumElement,this.artist);
                parsers.add(albumParser);

            }
                catch(NullPointerException e){}
        }

        for(AlbumParser parser : parsers){
            es.execute(parser);
        }

//
//        List<Thread> threadList = new ArrayList<Thread>();
//        for(AlbumParser parser : parsers){
//            Thread t = new Thread(parser);
//            t.start();
//            threadList.save(t);
//        }
//
//        for(Thread thread : threadList){
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        es.shutdown();
        try {
            ////wait for all tasks to finish
            es.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return parsers;
    }


    /**
     *
     * @return list of artist albums
     * @throws IOException
     */
//    public List<Album> getAlbums(ArtistParser artistParser) {
//
//        Album albumObject = null;
//        String albumName;
//        String albumLink;
//        List<Album> albums = new ArrayList<Album>();
//
//        Elements albumsElements = artistParser.getAlbumsElements();
//
//        for(Element albumElement : albumsElements){
//            albumName = albumElement.text();
//            albumLink = Config.WIKIPEDIA + albumElement.attr("href");
////            albumLink.replaceAll("'","%27");
//
//            //because there is a case that "Wikipedia does not have an article with this exact name"
//            try{
//                albumObject = this.getAlbum(albumName, albumLink, artistParser.getArtist());
//            }
//            catch(NullPointerException e){};
//
//            albums.save(albumObject);
//        }
//        return albums;
//    }


}
