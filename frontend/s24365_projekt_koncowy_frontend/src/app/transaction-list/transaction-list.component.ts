import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../service/api.service';
import { AccountTransaction } from '../model/account-transaction';

import { MatTableModule } from '@angular/material/table';

@Component({
    selector: 'app-transaction-list',
    templateUrl: './transaction-list.component.html',
    styleUrl: './transaction-list.component.scss',
    standalone: true,
    imports: [
        MatTableModule
    ]
})
export class TransactionListComponent implements OnInit {

    displayedColumns: string[] = ['receiverAccountNumber', 'receiverName', 'amount'];
    transactions: AccountTransaction[] = [];

    constructor(
        private apiService: ApiService,
        private router: Router,
        private activatedRoute: ActivatedRoute) { }

    ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var accountNumber = params.get('accountNumber');
            if (accountNumber) {
                this.fetchTransactions(accountNumber);
            } else {
                console.error('No account found.');
            }
        });
    }

    fetchTransactions(accountNumber: string): void {
        this.apiService.getAccountTransactions(accountNumber).subscribe(transactions =>{
            this.transactions = transactions;
            }
        );
    }

    onRowClicked(transactionReference: string) {
        this.router.navigate(['/transactionDetails/' + transactionReference]);
    }
}
