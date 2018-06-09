package app.wiki.parsing;

import app.config.mayhem.MayhemConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


/**
 * Retrieve Wikipedia pages
 */
public class WikiRetriever {

    /**
     *
     * @param artist
     * @return the Wikipedia's Document of the band.
     * @throws IOException
     *
     * There is a chance that the band name is a "generic" name, such as "kiss".
     * In this case we need to save to the URL the extension "_(band)".
     * for example:
     * en.wikipedia.org/wiki/Lamb_of_God will lead us to --->
     * "Lamb of God is a title for Jesus that appears in the Gospel of John..."
     *
     * Although what we meant for is:
     * en.wikipedia.org/wiki/Lamb_of_God_(band) --->
     * "Lamb of God is an American groove metal ***band***..."
     *
     * So we have to look for the word "band" in the first <p> of the doc.
     *
     */
    public static Document getArtistDoc(String artist){
        Document doc = null;
        try {
            doc = Jsoup.connect(MayhemConfig.WIKI+artist).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error connecting to Wikipedia");
            return doc;
        }

        Element el = doc.select("p").first();
        if(!el.text().contains("band")){
            String formattedBandName = artist + ("_(band)");
            try {
                doc = Jsoup.connect(MayhemConfig.WIKI+formattedBandName).get();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Error connecting to Wikipedia");
                return doc;
            }
        }
        //TODO in case we have more than 1 band in the disambiguation page
        //for exanple : architects ---> american band, british band

        return doc;
    }

    /**
     *
     * @param albumLink
     * @return Album Document
     */
    public static Document getAlbumDoc(String albumLink){
        Document albumDoc = null;
        try {
            albumLink = java.net.URLDecoder.decode(albumLink, "UTF-8");
            //albumLink = albumLink.replaceAll("%27","'");
            albumDoc = Jsoup.connect(albumLink).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error connecting to Wikipedia");
            return albumDoc;
        }

        return albumDoc;
    }



}
