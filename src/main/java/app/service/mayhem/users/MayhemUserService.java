package app.service.mayhem.users;

import app.domain.mayhem.MayhemUser;
import app.domain.mayhem.Song;

import java.util.List;

public interface MayhemUserService {
    MayhemUser save(MayhemUser mayhemUser);
    void saveSong(String username, Song song);
    void deleteSong(String username, String albumName, String SongName);
    List<MayhemUser> listUsers();
}
