package app.service.mayhem.users;

import app.domain.mayhem.MayhemUser;
import app.domain.mayhem.Song;
import app.repository.mayhem.music.SongRepository;
import app.repository.mayhem.users.MayhemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MayhemUserSercviceImpl implements MayhemUserService {

    @Autowired
    private MayhemUserRepository mayhemUserRepository;

    @Autowired
    private SongRepository songRepository;

    @Transactional
    @Override
    public MayhemUser save(MayhemUser mayhemUser) {
        if(!mayhemUserRepository.findOneByUsername(mayhemUser.getUsername()).equals(null)){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@not empty, dont save");
            //song is already in db , dont insert
            return null;
        }
        return mayhemUserRepository.save(mayhemUser);


    }

    @Override
    public void saveSong(String username, Song song) {
        MayhemUser mayhemUser = mayhemUserRepository.findOneByUsername(username);
        if(mayhemUser == null){//TODO
            mayhemUser = new MayhemUser(username);
            mayhemUserRepository.save(mayhemUser);
        }
        Song songInDb = songRepository.findOneByNameAndAlbumName(song.getName(),song.getAlbum().getName());
        if (songInDb != null){
            mayhemUser.addSongToUserSongs(songInDb);
        }
        else{
            mayhemUser.addSongToUserSongs(song);
        }
        mayhemUserRepository.save(mayhemUser);
    }

    @Override
    public void deleteSong(String username, String albumName, String songName){
        MayhemUser mayhemUser = mayhemUserRepository.findOneByUsername(username);
        if(mayhemUser == null){//TODO
            mayhemUser = new MayhemUser(username);
            mayhemUserRepository.save(mayhemUser);
        }
        Song songInDb = songRepository.findOneByNameAndAlbumName(songName, albumName);
        if (songInDb != null){
            mayhemUser.removeSongFromUserSongs(songInDb);
        }
        else{
            //error, song shouldnt have been in user's db
        }
        mayhemUserRepository.save(mayhemUser);
    }

    @Transactional()
    @Override
    public List<MayhemUser> listUsers() {
        return mayhemUserRepository.listUsers();
    }

}
