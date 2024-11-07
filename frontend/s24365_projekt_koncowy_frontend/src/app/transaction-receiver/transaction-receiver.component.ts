import { Component, OnInit } from '@angular/core';
import { TransactionReceiver } from '../model/transaction-receiver';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-transaction-receiver',
    templateUrl: './transaction-receiver.component.html',
    styleUrl: './transaction-receiver.component.scss'
})
export class TransactionReceiverComponent implements OnInit {
    displayedColumns: string[] = ['name', 'accountNumber'];
    receiver: TransactionReceiver = { name: '', accountNumber: '', pesel: '' }

    constructor(private apiService: ApiService,
        private activatedRoute: ActivatedRoute,
        private router: Router) { }

    ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var transactionReference = params.get('transactionReference');
            if (transactionReference) {
                this.fetchReceiver(transactionReference);
            } else {
                console.error('No receiver found.');
            }
        });
    }

    fetchReceiver(transactionReference: string): void {
        this.apiService.getTransactionReceiver(transactionReference).subscribe(receiver => {
            this.receiver = receiver;
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
