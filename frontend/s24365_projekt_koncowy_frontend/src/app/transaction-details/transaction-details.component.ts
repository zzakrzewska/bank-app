import { Component } from '@angular/core';
import { ApiService } from '../service/api.service';
import { ActivatedRoute } from '@angular/router';
import { AccountTransaction } from '../model/account-transaction';
import { TransactionDetails } from '../model/transaction-details';

@Component({
    selector: 'app-transaction-details',
    templateUrl: './transaction-details.component.html',
    styleUrl: './transaction-details.component.scss'
})
export class TransactionDetailsComponent {
    constructor(private apiService: ApiService, private activatedRoute: ActivatedRoute) { }

    transaction: TransactionDetails = {
        amount: '', creationDate: '', state: ''
    };

    
    ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var routeReference = params.get('transactionReference');
            if (routeReference) {
                this.fetchTransaction(routeReference);
            } else {
                console.error('No transaction found.');
            }
        });
    }

    fetchTransaction(transactionReference: string): void {
        this.apiService.getTransaction(transactionReference).subscribe(transaction =>
            this.transaction = transaction
        );
    }
}
