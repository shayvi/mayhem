package app.service.mayhem.music;

import app.domain.mayhem.Song;
import app.repository.mayhem.music.AlbumRepository;
import app.repository.mayhem.music.ArtistRepository;
import app.repository.mayhem.music.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;

    @Transactional
    @Override
    public Song save(Song song)    {
        Song dbSong = songRepository.findOneByName(song.getName());
        if(dbSong!=null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@not empty, dont save");
            //song is already in db , dont insert
            return dbSong;
        }

        return songRepository.save(song);

    }

//    @Override
//    public Song delete(int id) {
//        Song song = findById(id);
//        if(song != null){
//            songRepository.delete(song);
//        }
//        return song;
//    }

//    @Override
//    public List<Song> findAll() {
//        return songRepository.findAll();
//    }
//
//    @Override
//    public Song findById(int id) {
//        return songRepository.findOne(id);
//    }
//
//    @Override
//    public Song update(Song song) {
//        return null;
//    }
}
