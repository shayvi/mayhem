package app.repository.mayhem.music;

import app.domain.mayhem.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
//DAO
public interface ArtistRepository extends JpaRepository<Artist, Long> {


    @Query("SELECT a FROM Artist a WHERE a.name=(:name) AND a.origin=(:origin)")
    Artist findOneByNameAndOrigin(@Param("name") String name, @Param("origin") String origin);
//
//    @Query("SELECT s FROM Song s WHERE s.name=(:name) and s.album.name=(:albumName)")
//    List<Song> findOneByNameAndAlbumName(@Param("name") String name, @Param("albumName") String albumName);
//
//    @Query("SELECT s FROM Song s")
//    List<Song> listSongs();

}
