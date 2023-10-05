import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {CertificateService} from "../../../service/certificate.service";

@Component({
    selector: 'app-item-form',
    templateUrl: './item-form.component.html',
    styleUrls: ['./item-form.component.css']
})
export class ItemFormComponent {
    newItemForm: FormGroup;

    constructor(private fb: FormBuilder, private certificateService: CertificateService, private router: Router) {
        this.newItemForm = this.fb.group({
            categories: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(128)]],
            validTo: ['', [Validators.required]],
            price: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(64)]],
            itemName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(64)]],
            description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(256)]],
        });
    }

    onSubmit() {
        if (this.newItemForm.valid) {
            this.saveCertificate()
            console.log('Register form submitted:', this.newItemForm.value);
        }
    }

    saveCertificate() {
        const data = {
            name: this.newItemForm.value.itemName,
            categories: this.newItemForm.value.categories,
            validTo: this.newItemForm.value.validTo,
            price: this.newItemForm.value.price,
            description: this.newItemForm.value.description
        };

        alert(`the date : ${data.validTo}`)

        this.certificateService.save(data)
            .subscribe(
                (itemId) => {
                    alert(`Certificate saved with ID: ${itemId}`)
                    this.router.navigate(['/home']);
                },
                error => {
                    console.error('Failed to save certificate', error);
                }
            );
    }
}
