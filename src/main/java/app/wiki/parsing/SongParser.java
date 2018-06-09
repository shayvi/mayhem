package app.wiki.parsing;

import app.config.mayhem.MayhemConfig;
import app.domain.mayhem.Album;
import app.domain.mayhem.Song;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Shay Vikel on 19/05/2017.
 */
public class SongParser  {

    public static Song getSong(Element songElement){
        Elements songParts = songElement.select("td");
        String idString = songParts.first().text();//song id
        String songName = songParts.get(1).text();//song name

        if(songName.startsWith("\"") && songName.endsWith("\"")){
            songName = songName.substring(1, songName.length() - 1);
        }

        return Song.getSong(MayhemConfig.extractId(idString), songName);
    }
}

