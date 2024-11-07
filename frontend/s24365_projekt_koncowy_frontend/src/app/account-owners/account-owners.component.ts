import { Component, OnInit } from '@angular/core';
import { OwnerDetails } from '../model/owner-details';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';

import {MatListModule} from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';

@Component({
    selector: 'app-account-owners',
    templateUrl: './account-owners.component.html',
    styleUrl: './account-owners.component.scss',
    standalone: true,
    imports: [
        MatTableModule
    ]
})
export class AccountOwnersComponent implements OnInit {
    constructor(
        private apiService: ApiService,
        private router: Router,
        private activatedRoute: ActivatedRoute) { }

    owners: OwnerDetails[] = [];
    displayedColumns: string[] = ['nameSurname', 'ownerType'];

    ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var routeAccount = params.get('accountNumber');
            if (routeAccount) {
                this.fetchAccountOwners(routeAccount);
            } else {
                console.error('No client found.');
            }
        });
    }

    fetchAccountOwners(accountNumber: string): void {
        this.apiService.getAccountOwners(accountNumber).subscribe(owners =>
            this.owners = owners
        );
    }

    onRowClicked(pesel: string): void {
        this.router.navigate(['/clientDetails/' + pesel]);
    }
}
