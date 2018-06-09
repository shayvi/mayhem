package app.repository.mayhem.users;

import app.domain.mayhem.MayhemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
//DAO
public interface MayhemUserRepository extends JpaRepository<MayhemUser, Long> {

    @Query("SELECT u FROM MayhemUser u WHERE u.username=(:username)")
    MayhemUser findOneByUsername(@Param("username") String username);

//    @Query("SELECT u FROM User JOIN  u WHERE u.username=(:username)")
//    Song findSongByUsername(@Param("username") String username,
//                            @Param("songName") String songName,
//                            @Param("artistName") String artistName);
    //    @Query("SELECT s FROM Song s WHERE s.name=(:name)")
//    List<Song> findOneByName(@Param("name") String name);
//
    @Query("SELECT u FROM MayhemUser u")
    List<MayhemUser> listUsers();

}
