import { Component } from '@angular/core';
import { TransactionSender } from '../model/transaction-sender';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-transaction-sender',
    templateUrl: './transaction-sender.component.html',
    styleUrl: './transaction-sender.component.scss'
})
export class TransactionSenderComponent {
    displayedColumns: string[] = ['name', 'accountNumber'];
    sender: TransactionSender = { name: '', accountNumber: '', pesel: '' }

    constructor(private apiService: ApiService,
        private activatedRoute: ActivatedRoute,
        private router: Router) { }

    ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var transactionReference = params.get('transactionReference');
            if (transactionReference) {
                this.fetchSender(transactionReference);
            } else {
                console.error('No receiver found.');
            }
        });
    }

    fetchSender(transactionReference: string): void {
        this.apiService.getTransactionSender(transactionReference).subscribe(sender => {
            this.sender = sender;
        }
        );
    }

    goToClient(pesel: string) {
        this.router.navigate(['/clientDetails/' + pesel]);
    }

    goToAccount(accountNumber: string) {
        this.router.navigate(['/accountDetails/' +  accountNumber.replace(/ /g,'')]);
    }
}