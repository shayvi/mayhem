package app.repository.mayhem.music;

import app.domain.mayhem.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
//DAO
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s WHERE s.name=(:name)")
    Song findOneByName(@Param("name") String name);

    @Query("SELECT s FROM Song s WHERE s.name=(:name) and s.album.name=(:albumName)")
    Song findOneByNameAndAlbumName(@Param("name") String name, @Param("albumName") String albumName);

    void delete(Song song);

    @Query("SELECT s FROM Song s")
    List<Song> findAll();


    Song save(Song song);
}
