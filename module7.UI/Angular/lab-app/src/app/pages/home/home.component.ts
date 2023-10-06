import {Component, HostListener, OnInit} from '@angular/core';
import {CertificateService} from "../../service/certificate.service";
import {Certificate} from "../../core/entity/certificate";
import {Tag} from "../../core/entity/tag";
import {TagService} from "../../service/tag.service";
import { FormControl } from '@angular/forms';
import {SearchService} from "../../service/search.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  certificates: Certificate[] = [];
  tags!: Tag[];
  page = 0;
  loading = false;

  constructor(private certificateService: CertificateService,
              private tagService: TagService,
              private searchService: SearchService) {
  }

  ngOnInit(): void {
    this.loadCertificates();
    this.loadTags();
  }

  loadCertificates() {
    this.loading = true;
    this.certificateService.getCertificates(this.page)
      .subscribe((certificates) => {
        this.certificates = this.certificates.concat(certificates);
        this.loading = false;
        this.page++;
      });
  }

  loadTags() {
    this.tagService.getTags()
      .subscribe((tags) => {
        this.tags = tags;
      })
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    if (this.shouldLoadMore()) {
      this.loadCertificates();
    }
  }

  shouldLoadMore(): boolean {
    const scrollPosition = window.innerHeight + window.scrollY;
    const documentHeight = document.documentElement.scrollHeight;

    return scrollPosition >= documentHeight && !this.loading;
  }

  subscribeToSearch() {
    this.searchService.searchValue$.subscribe((searchValue) => {
      this.page = 0;
      this.certificates = [];
      this.loadCertificates();
    });
  }
}
