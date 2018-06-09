import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DashboardComponent} from "./dashboard/dashboard.component";
import { NewSongComponent} from "./new-song/new-song.component";
import { SongsComponent} from "./songs/songs.component";
import { StatisticsComponent} from "./statistics/statistics.component";
import {WikiSongModalService} from "./wiki-songs/wiki-songs-modal.service";
import {WikiSongsModalComponent} from "./wiki-songs/wiki-songs-modal.component";
import {WikiSongsComponent} from "../layouts";

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [
        DashboardComponent,
        SongsComponent,
        NewSongComponent,
        StatisticsComponent,
        WikiSongsModalComponent,
        WikiSongsComponent
        ],
    entryComponents: [WikiSongsModalComponent,WikiSongsComponent],
    providers: [WikiSongModalService],
    schemas: []
})
export class MayhemMayhemModule {}
