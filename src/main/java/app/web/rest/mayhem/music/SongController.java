package app.web.rest.mayhem.music;//package mayhem.services;

import app.domain.mayhem.Song;
import app.service.mayhem.music.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@CrossOrigin(origins = "http://localhost:7200")
@RequestMapping(value = "/mayhem")
public class SongController {

    @Autowired
    private SongService songService;

    @CrossOrigin(origins = "http://localhost:7200")
    @PostMapping(value = "/song")
    public ResponseEntity<Void> add(@RequestBody Song song) {
        songService.save(song);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


//    @GetMapping(path = {"/{id}"})
//    public Song findOne(@PathVariable("id") int id){
//        return songService.findById(id);
//    }
//
//    @PutMapping
//    public Song update(@RequestBody Song song){
//        return songService.update(song);
//    }
//
//    @DeleteMapping(path ={"/{id}"})
//    public Song delete(@PathVariable("id") int id) {
//        return songService.delete(id);
//    }
//
//    @GetMapping
//    public List findAll(){
//        return songService.findAll();
//    }



}
