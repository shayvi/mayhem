import { Component, OnInit } from '@angular/core';

import { Song } from '../../entities/song';

@Component({
  moduleId: module.id,
  selector: 'my-statistics',
  templateUrl: 'statistics.component.html',
  styleUrls: [ 'statistics.component.css' ]
})

export class StatisticsComponent implements OnInit {

  songs: Song[] = [];

  constructor() { }

  ngOnInit(): void {
    // this.songService.getSongs()
      // .subscribe(songs => this.songs = songs.slice(0, 5));
  }
}
