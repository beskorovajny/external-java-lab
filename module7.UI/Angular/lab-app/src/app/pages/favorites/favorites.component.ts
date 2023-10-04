import {Component, OnInit} from '@angular/core';
import {Certificate} from "../../core/entity/certificate";
import {FavoriteService} from "../../service/favorite.service";
import {Tag} from "../../core/entity/tag";

@Component({
  selector: 'app-favorites',
  templateUrl: '../home/home.component.html',
  styleUrls: ['../home/home.component.css']
})
export class FavoritesComponent implements OnInit{
  certificates: Certificate[] = [];
  tags: Tag[] = [];

  constructor(private favoritesService: FavoriteService) {

  }

  ngOnInit(): void {
    this.certificates = this.favoritesService.getCertificates();
  }
}
