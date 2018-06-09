package app.web.rest.mayhem.music;


import app.domain.mayhem.Album;
import app.service.mayhem.music.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("mayhem")
public class AlbumController {
    @Autowired
    private AlbumService albumService;


    @PostMapping("album")
    public ResponseEntity<Void> addAlbum(@RequestBody Album album, UriComponentsBuilder builder) {
        albumService.save(album);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(album.getName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
