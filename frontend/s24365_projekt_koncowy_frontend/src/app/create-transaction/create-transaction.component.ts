import { ChangeDetectorRef, Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CreateTransaction } from '../model/create-transaction';
import { OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { AccountDetails } from '../model/account-details';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'app-create-transaction',
    templateUrl: './create-transaction.component.html',
    styleUrl: './create-transaction.component.scss'
})
export class CreateTransactionComponent implements OnInit {
    registerForm = new FormGroup({
        sender: new FormControl(''),
        receiver: new FormControl(''),
        amount: new FormControl('')
    });

    transaction: CreateTransaction = {
        senderAccountNumber: '',
        receiverAccountNumber: '',
        amount: 0
    };

    receiverAccount: AccountDetails = {
        accountNumber: '',
        balance: '',
        ownerName: ''
    }

    senderAccount: AccountDetails = {
        accountNumber: '',
        balance: '',
        ownerName: ''
    }

    constructor(
        private location: Location,
        private apiService: ApiService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit() {
        this.activatedRoute.paramMap.subscribe(params => {
            var accountNumber = params.get('accountNumber');
            if (accountNumber) {
                this.transaction.senderAccountNumber = accountNumber;
                this.apiService.getAccountDetails(this.transaction.senderAccountNumber).subscribe(
                    account => {
                        this.senderAccount = account;
                    },
                    error => {
                        this.senderAccount = {
                            accountNumber: '',
                            balance: '',
                            ownerName: ''
                        }
                    }
                );
            }
        });
    }

    onSubmit(form: any) {
        this.apiService.createTransfer(this.transaction).subscribe(response => {
            this.router.navigate(['/transactionDetails/' + response]);
        }, error => {
            alert('Error during transfer creation');
        });
    }

    back(): void {
        this.location.back()
    }

    findSender(): void {
        this.apiService.getAccountDetails(this.transaction.senderAccountNumber).subscribe(
            account => {
                this.senderAccount = account;
            },
            error => {
                this.senderAccount = {
                    accountNumber: '',
                    balance: '',
                    ownerName: ''
                }
            }
        );
    }

    findReceiver(): void {
        this.apiService.getAccountDetails(this.transaction.receiverAccountNumber).subscribe(
            account => {
                this.receiverAccount = account;
            },
            error => {
                this.receiverAccount = {
                    accountNumber: '',
                    balance: '',
                    ownerName: ''
                }
            }
        );
    }
}
