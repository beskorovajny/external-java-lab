import { Injectable } from '@angular/core';
import {Certificate} from "../core/entity/certificate";

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {
  private static readonly FAVORITES: string = 'favorites';
  private certificates: Certificate[] = [];

  addCertificate(certificate: Certificate) {
    const certificatesStr = window.localStorage.getItem(FavoriteService.FAVORITES);
    if (certificatesStr) {
      this.certificates = JSON.parse(certificatesStr);
    }

    this.certificates.push(certificate);
    const certificatesJSON = JSON.stringify(this.certificates)
    window.localStorage.setItem(FavoriteService.FAVORITES, certificatesJSON);
  }

  getCertificates() {
    const certificatesJSON = window.localStorage.getItem(FavoriteService.FAVORITES);
    if (certificatesJSON) {
      this.certificates = JSON.parse(certificatesJSON);
    }
    return this.certificates;
  }

  clearFavorites() {
    this.certificates = [];
    window.localStorage.removeItem(FavoriteService.FAVORITES);
  }
}
