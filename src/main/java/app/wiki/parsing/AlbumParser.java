package app.wiki.parsing;

import app.config.mayhem.MayhemConfig;
import app.domain.mayhem.Artist;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class AlbumParser extends WikiParser implements Runnable {

    private Document albumDoc;
    private String albumLink;
    private String albumName;
    private Artist artist;

    @Override
    public void run()  {
        this.setAlbumDoc();
    }

    public AlbumParser(Element albumElement, Artist artist){
        this.albumName = albumElement.text();
        this.albumLink = MayhemConfig.WIKIPEDIA + albumElement.attr("href");
        this.artist = artist;


//        this.albumDoc = WikiRetriever.getAlbumDoc(albumLink);
    }

    public void setAlbumDoc() {
        this.albumDoc = WikiRetriever.getAlbumDoc(albumLink);
    }

    public static AlbumParser getAlbumParser(Element albumElement, Artist artist){
        return new AlbumParser(albumElement , artist);
    }

    public String getAlbumName() {
        return albumName;
    }

    public Artist getArtist() {
        return artist;
    }

    /**
     * @return the year of the album release
     */
    protected int getAlbumYear(){
        Element release =  albumDoc.select("th:contains(Released) ~ td").first();
        return MayhemConfig.extractYear(release.text());
    }

    /**
     * @return the genres of the album
     */
    protected List<String> getAlbumGenres(){
        List<String> genres = new ArrayList<String>();
        Elements genreElements = null;

        //because there are cases genres are not mentioned
        if(albumDoc.select("tr:contains(Genre)").first() != null){
            genreElements = albumDoc.select("tr:contains(Genre)").first()
                    .select("a").not("a:contains([1]), a:contains([2]), a:contains([3]),"
                            + "a:contains([4]), a:contains([5]), a:contains([6]),"
                            + "a:contains([7]), a:contains([8]), a:contains([9]),"
                            + "a:contains([10]), a:contains(Genre)");
        }

		/*
		Elements genreElements =  albumDoc.select("tr:contains(Genre) a")
								  .not("a:contains([1]), a:contains([2]),a:contains([3]) ,"
								  		+ "a:contains([4]), a:contains(Genre)");
		*/

        if (genreElements != null){
            for(Element genreElement : genreElements){
                String genreText = genreElement.text().toLowerCase();
                genres.add(genreText);
            }
        }
        return  genres;
    }

    /**
     * @return the list of songs of the album
     */
    protected List<Element> getSongsElements() {
        List<Element> songsElements = new ArrayList<Element>();

        Elements tables =  this.albumDoc.select("h2:contains(Track listing) ~ table");
        Elements firstTable = tables.first().select("tr").not("tr:has(th)").not("tr:contains(Total length)");
        Elements secondTable = null;
        if(tables.size() > 1){
            secondTable = tables.get(1).select("table:contains(bonus)").select("tr")
                    .not("tr:has(th)").not("tr:contains(Total length)");
        }


        for(Element song : firstTable){
            songsElements.add(song);
        }

        if(secondTable != null){
            for(Element song : secondTable){
                songsElements.add(song);
            }
        }
        return songsElements;
    }

}
