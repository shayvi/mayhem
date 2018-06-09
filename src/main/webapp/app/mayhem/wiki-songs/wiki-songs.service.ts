import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


import { Observable } from 'rxjs/Observable';

import { Song } from '../../entities/song';
import { Band } from '../../entities/band';
import { User } from '../../entities/user';

@Injectable()
export class WikiSongsService {
    private artistSongs : Song [];
    private artistName : String;

  user: User;
  constructor(private http: HttpClient) {

  }

  configUrl = 'assets/api/config.json';
  serverUrl = 'http://localhost:8080/wiki/';
  mayhemUrl = 'http://localhost:8080/mayhem/';
  songsUrl = 'assets/api/songs.json';

  setArtistName (artistName : String){
      this.artistName = artistName;
  }

  getArtistName () : String{
      return this.artistName;
  }

    setArtistSongs (songs : Song[]){
        this.artistSongs = songs;
    }

  getArtistSongs () : Song[]{
      return this.artistSongs;
  }

  getArtist(name: String) : Observable<Song []>{
    return this.http.get<Song []>(this.serverUrl + name);
  }

  saveArtist(artistName: String) : Observable<Band> {
    return this.http.post<Band>(this.serverUrl + artistName,null);
  }

  getConfig(name: string) {
    console.log("#################################");
    console.log(this.http.get(this.configUrl));
    return this.http.get(this.configUrl);

  }

  addSong(song: Song): Observable<Song> {
    const user = new User('shayvi');
    console.log(song);
    return this.http.post<Song>(this.mayhemUrl +'user/'+ user.username + '/add' ,song);

      // .pipe(
      //   catchError(this.handleError('addHero', hero))
      // );
  }

  deleteSong (songName: String, albumName: String): Observable<{}> {
    const user = new User('shayvi');
    return this.http.delete(this.mayhemUrl +'user/'+ user.username + '/delete/'+ albumName +'/' +songName );
  }

  findUserSong (songName: String, albumName: String): Observable<Boolean> {
    const user = new User('shayvi');
    return new Observable<true>();
  }

}
