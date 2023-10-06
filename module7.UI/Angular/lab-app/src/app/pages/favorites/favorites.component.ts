import {Component, OnInit} from '@angular/core';
import {Certificate} from "../../core/entity/certificate";
import {FavoriteService} from "../../service/favorite.service";

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['../home/home.component.css', '/favorites.component.css']
})
export class FavoritesComponent implements OnInit {
  certificates: Certificate[] = [];


  constructor(private favoritesService: FavoriteService) {
  }

  ngOnInit(): void {
    this.certificates = this.favoritesService.getCertificates();
  }
}
