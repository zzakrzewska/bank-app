import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-account-details-view',
    templateUrl: './account-details-view.component.html',
    styleUrl: './account-details-view.component.scss'
})
export class AccountDetailsViewComponent {
    constructor(private location: Location,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) { }

    back(): void {
        this.location.back()
    }

    createTransfer(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var accountNumber = params.get('accountNumber');
            this.router.navigate(['/createTransaction/' + accountNumber]);
        });
    }
}
