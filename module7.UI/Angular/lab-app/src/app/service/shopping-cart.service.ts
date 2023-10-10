import { Injectable } from '@angular/core';
import {Certificate} from "../core/entity/certificate";
import {CustomSet} from "./util/custom-set";

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private readonly cart: string = 'cart';
  private certificates: Certificate[] = [];

  addCertificate(certificate: Certificate) {
    const certificatesStr = window.localStorage.getItem(this.cart);
    if (certificatesStr) {
      this.certificates = JSON.parse(certificatesStr);
    }

    this.certificates.push(certificate);
    const certificatesJSON = JSON.stringify(this.certificates)
    window.localStorage.setItem(this.cart, certificatesJSON);
  }

  getCertificates() {
    const certificatesJSON = window.localStorage.getItem(this.cart);
    if (certificatesJSON) {
      this.certificates = JSON.parse(certificatesJSON);
    }

    const certificatesSet: CustomSet<Certificate> =
      new CustomSet<Certificate>(certificate => certificate.name);

    this.certificates.forEach((item) => certificatesSet.add(item));


    return certificatesSet.values();
  }

  clearCart() {
    this.certificates = [];
    window.localStorage.removeItem(this.cart);
  }
}
