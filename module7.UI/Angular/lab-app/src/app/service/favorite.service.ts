import {Injectable} from '@angular/core';
import {Certificate} from "../core/entity/certificate";
import {CustomSet} from "./util/custom-set";

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
    const certificatesSet: CustomSet<Certificate> =
      new CustomSet<Certificate>(certificate => certificate.name);

    this.certificates.forEach((item) => certificatesSet.add(item));


    return certificatesSet.values();

  }

  clearFavorites() {
    this.certificates = [];
    window.localStorage.removeItem(FavoriteService.FAVORITES);
  }
}
