import { Component, OnInit } from '@angular/core';
import { AccountDetails } from '../model/account-details';
import { ApiService } from '../service/api.service';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-account-details',
    templateUrl: './account-details.component.html',
    styleUrl: './account-details.component.scss'
})
export class AccountDetailsComponent implements OnInit {

    constructor(private apiService: ApiService, private activatedRoute: ActivatedRoute) { }

    account: AccountDetails = {
        accountNumber: '', balance: '', ownerName: ''
    }

    ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var accountNumber = params.get('accountNumber');
            if (accountNumber) {
                this.fetchAccountDetails(accountNumber);
            } else {
                console.error('No account found.');
            }
        });
    }

    fetchAccountDetails(accountNumber: string): void {
        this.apiService.getAccountDetails(accountNumber).subscribe(account => {
            this.account = account
        });
    }
}
