import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ApiService } from '../service/api.service';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-transaction-details-view',
    templateUrl: './transaction-details-view.component.html',
    styleUrl: './transaction-details-view.component.scss'
})
export class TransactionDetailsViewComponent implements OnInit {
    constructor(private location: Location, 
        private activatedRoute: ActivatedRoute,
        private apiService: ApiService
    ) { }

    back(): void {
        this.location.back()
    }

    ngOnInit(): void {
        
    }

    cancelTransfer(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var transactionReference = params.get('transactionReference');
            if (transactionReference) {
                this.apiService.cancelTransfer(transactionReference);
                window.location.reload();
            } else {
                console.error('No transaction found.');
            }
        });
    }
}
