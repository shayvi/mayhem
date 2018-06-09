package app.service.mayhem.music;

import app.domain.mayhem.Album;
import app.repository.mayhem.music.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Transactional
    @Override
    public Album save(Album album) {
        Album dbAlbum = albumRepository.findOneByNameAndArtistName(album.getName(), album.getArtist().getName());
        if( dbAlbum != null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@not empty, dont save");
            //album is already in db , dont insert
            return dbAlbum;
        }
        return albumRepository.save(album);
    }

    @Transactional()
    @Override
    public List<Album> listAlbums() {
        return albumRepository.listAlbums();
    }

}
