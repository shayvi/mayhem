package app.service.mayhem.music;

import app.domain.mayhem.Album;

import java.util.List;

public interface AlbumService {
    Album save(Album album);
    List<Album> listAlbums();
}
