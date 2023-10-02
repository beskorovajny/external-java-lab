import {Component, OnInit} from '@angular/core';
import {CertificateService} from "../../service/certificate.service";
import {ICertificate} from "../../core/i-certificate";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  certificates!: ICertificate[];

  constructor(private certificateService: CertificateService) {
  }

  ngOnInit(): void {
    this.certificateService.getCertificates()
      .subscribe((certificates) => {
        this.certificates = certificates;
      });
  }

}
