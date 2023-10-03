import {Component, OnInit} from '@angular/core';
import {Certificate} from "../../core/entity/certificate";
import {CertificateService} from "../../service/certificate.service";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-item-details',
    templateUrl: './item-details.component.html',
    styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent implements OnInit {
    certificate!: Certificate;
    certificateId!: number;

    constructor(private certificateService: CertificateService, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');

        if (id) {
            this.certificateId = Number(id);
        }

        if (this.certificateId) {
            this.certificateService.getSingleCertificate(this.certificateId)
                .subscribe((certificate) => {
                    this.certificate = certificate;
                });

        }
    }
}
