package app.repository.mayhem.music;

import app.domain.mayhem.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
//DAO
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a WHERE a.name=(:name) and a.artist.name=(:artistName)")
    Album findOneByNameAndArtistName(@Param("name") String name, @Param("artistName") String artistName);

    @Query("SELECT a FROM Album a")
    List<Album> listAlbums();

}
