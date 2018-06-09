import { Component, OnInit } from '@angular/core';
import { Song } from '../../entities/song';

import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'my-songs',
  templateUrl: 'songs.component.html',
  styleUrls: [ 'songs.component.css' ]
})

export class SongsComponent implements OnInit {
  songs: Song[];
  selectedSong: Song;
  mode = 'Observable';


  constructor(
    private router: Router) {}

  // getSongs(): void {
  //   this.songService.getSongs().subscribe(songs => this.songs = songs);
  // }

  ngOnInit(): void {
  }

  onSelect(song: Song): void {
    this.selectedSong = song;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedSong.name]);
  }
}

