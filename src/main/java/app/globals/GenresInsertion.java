package app.globals;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * retrieves list of music genres from Wikipedia & inserts them to a special table in db
 * @author Shay Vikel
 *
 */
public class GenresInsertion {

    private static String WIKI_GENRES = "https://en.wikipedia.org/wiki/List_of_popular_music_genres";

    private static List<String> GenerateGenreList(){

        List<String> genres = new ArrayList<String>();
        Document doc = null;
        try {
            doc = Jsoup.connect(WIKI_GENRES).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("Error connecting to Wikipedia");
            return genres;
        }

        Elements genresElements =  doc.select("li a");
        for(Element genreElement: genresElements){
            genres.add(genreElement.text());
        }

        return genres;
    }

    public static void main(String[] args){
        List<String> genres = GenerateGenreList();
        for(String genre : genres){
            System.out.println(genre);
        }
    }

}
