package app.web.rest.mayhem.users;

import app.domain.mayhem.Song;
import app.domain.mayhem.MayhemUser;
import app.service.mayhem.users.MayhemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("mayhem")
public class MayhemUserController {

    @Autowired
    private MayhemUserService mayhemUserService;

    @PostMapping("user")
    public ResponseEntity<Void> saveUser(@RequestBody MayhemUser mayhemUser, UriComponentsBuilder builder) {
        mayhemUserService.save(mayhemUser);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/article/{id}").buildAndExpand(mayhemUser.getUsername()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @CrossOrigin(origins = "http://localhost:7200")
    @PostMapping(value = "/user/{username}/add")
    public ResponseEntity<Void> saveSongToUser(@PathVariable String username, @RequestBody Song song) {
        mayhemUserService.saveSong(username,song);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:7200")
    @DeleteMapping(value = "/user/{username}/delete/{albumName}/{songName}")
    public ResponseEntity<Void> deleteUserSong(@PathVariable String username,
                                               @PathVariable String albumName,
                                               @PathVariable String songName) {
        mayhemUserService.deleteSong(username,albumName,songName);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


}
