package app.service.mayhem.music;

import app.domain.mayhem.Artist;
import app.repository.mayhem.music.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional
    @Override
    public Artist save(Artist artist) {
        Artist dbArtist = artistRepository.findOneByNameAndOrigin(artist.getName(), artist.getOrigin());
        if( dbArtist != null){
            //song is already in db , dont insert
            return dbArtist;
        }
        return artistRepository.save(artist);

    }

}
