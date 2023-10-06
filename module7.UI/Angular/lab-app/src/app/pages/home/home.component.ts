import {Component, HostListener, OnInit} from '@angular/core';
import {CertificateService} from "../../service/certificate.service";
import {Certificate} from "../../core/entity/certificate";
import {Tag} from "../../core/entity/tag";
import {TagService} from "../../service/tag.service";
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

  searchValue: string = '';

  searchOption: string = 'All';


  constructor(private certificateService: CertificateService,
              private tagService: TagService,
              private searchService: SearchService) {
  }

  ngOnInit(): void {
    this.loadTags();

    this.loadCertificates()

    this.subscribeToSearch();
  }


  loadCertificates() {
    alert(`Search option: ${this.searchOption} \n search value ${this.searchValue}`);
    this.loading = true;
    if (this.searchOption === 'Tags') {
      this.certificateService.getCertificatesByTags(this.page, this.searchValue)
        .subscribe((certificates) => {
          this.processCertificates(certificates);
        });
    } else if (this.searchOption === 'Name') {
      this.certificateService.getCertificatesByNameLike(this.page, this.searchValue)
        .subscribe((certificates) => {
          this.processCertificates(certificates);
        });
    } else if (this.searchOption === 'Description') {
      this.certificateService.getCertificatesByDescription(this.page, this.searchValue)
        .subscribe((certificates) => {
          this.processCertificates(certificates);
        });
    } else {
      this.certificateService.getCertificates(this.page)
        .subscribe((certificates) => {
          this.processCertificates(certificates);
        });
    }
  }

  processCertificates(certificates: Certificate[]) {
    this.certificates = this.certificates.concat(certificates);
    this.loading = false;
    this.page++;
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
    this.searchService.searchTerm$.subscribe((searchData) => {
      this.searchValue = searchData.searchValue;
      this.searchOption = searchData.searchOption;

      this.page = 0;
      this.certificates = [];

      this.loadCertificates();
    });
  }
}
