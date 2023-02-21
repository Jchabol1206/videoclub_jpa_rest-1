import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {CategoriaModule} from "./categoria/categoria.module";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {PeliculaModule} from "./pelicula/pelicula.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    CategoriaModule,
    FormsModule,
    HttpClientModule,
    PeliculaModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
