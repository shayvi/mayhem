import { Injectable } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { WikiSongsModalComponent } from './wiki-songs-modal.component';
import {WikiSongsService} from "./wiki-songs.service";
import {Song} from "../../entities/song";

@Injectable()
export class WikiSongModalService {
    private isOpen = false;
    private songs : Song [];
    private artistName : String;
    constructor(private wikiSongsService: WikiSongsService,
        private modalService: NgbModal,
    ) {}



    open(artistName: String): NgbModalRef {
        this.artistName = artistName;
        this.songs = [];
        this.wikiSongsService.getArtist(artistName)
            .subscribe(songs => this.songs = songs);

        this.wikiSongsService.setArtistName(artistName);
        this.wikiSongsService.setArtistSongs(this.songs);


        if (this.isOpen) {
            return;
        }
        this.isOpen = true;
        const modalRef = this.modalService.open(WikiSongsModalComponent);
        modalRef.result.then((result) => {
            this.isOpen = false;
        }, (reason) => {
            this.isOpen = false;
        });
        return modalRef;
    }
}
