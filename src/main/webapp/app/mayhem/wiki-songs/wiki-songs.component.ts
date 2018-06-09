import { Component, OnInit, Injectable } from '@angular/core';
import { WikiSongsService } from './wiki-songs.service'
import { Band } from '../../entities/band';
import { Song } from '../../entities/song';
import {WikiSongModalService} from "./wiki-songs-modal.service";
import {NgbModalRef} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-wiki-songs',
  templateUrl: './wiki-songs.component.html',
  styleUrls: ['./wiki-songs.component.css']
})


@Injectable()
export class WikiSongsComponent implements OnInit {
  artistName = '';
  modalRef: NgbModalRef;

  constructor(private wikiSongsModalService : WikiSongModalService) { }

  ngOnInit() {
  }

  onKey(event: any) { // without type info
    this.artistName += event.target.value;
  }

  searchArtist(artistName: string): void {
    this.modalRef = this.wikiSongsModalService.open(artistName);
  }


}
