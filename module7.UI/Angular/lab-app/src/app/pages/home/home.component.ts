import {Component, OnInit} from '@angular/core';
import {CertificateService} from "../../service/certificate.service";
import {Certificate} from "../../core/entity/certificate";
import {Tag} from "../../core/entity/tag";
import {TagService} from "../../service/tag.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  certificates!: Certificate[];
  tags!: Tag[];

  constructor(private certificateService: CertificateService,
              private tagService: TagService) {
  }

  ngOnInit(): void {
    this.certificateService.getCertificates()
      .subscribe((certificates) => {
        this.certificates = certificates;
      });

    this.tagService.getTags()
      .subscribe((tags) => {
        this.tags = tags;
      })
  }


}
