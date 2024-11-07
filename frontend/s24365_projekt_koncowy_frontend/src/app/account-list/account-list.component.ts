
import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../service/api.service';
import { Account } from '../model/account';

import {MatListModule} from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';

@Component({
    selector: 'app-account-list',
    templateUrl: './account-list.component.html',
    styleUrls: ['./account-list.component.scss'],
    standalone: true,
    imports: [
        MatTableModule
    ]
})
export class AccountListComponent implements OnInit {
    accounts: Account[] = [];
    displayedColumns: string[] = ['accountNumber'];
    
    constructor(
        private apiService: ApiService,
        private router: Router,
        private activatedRoute: ActivatedRoute) { }


    ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var routePesel = params.get('pesel');
            if (routePesel) {
                this.fetchAccounts(routePesel);
            } else {
                console.error('No client found.');
            }
        });
    }

    fetchAccounts(pesel: string): void {
        this.apiService.getClientAccounts(pesel).subscribe(accounts =>
            this.accounts = accounts
        );
    }

    onRowClicked(accountNumber: string) {
        this.router.navigate(['/accountDetails/' + accountNumber.replace(/ /g,'')]);
    }
}