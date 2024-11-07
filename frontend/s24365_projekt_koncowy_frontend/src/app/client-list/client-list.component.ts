
import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from '../model/client';
import { ApiService } from '../service/api.service';

import { MatTableModule } from '@angular/material/table';

@Component({
    selector: 'app-client-list',
    templateUrl: './client-list.component.html',
    styleUrls: ['./client-list.component.scss'],
    standalone: true,
    imports: [
        MatTableModule
    ]
})
export class ClientListComponent implements OnInit {
    clients: Client[] = [];
    displayedColumns: string[] = ['surname', 'name', 'pesel'];

    constructor(
        private apiService: ApiService,
        private router: Router,
        private activatedRoute: ActivatedRoute) { }


    ngOnInit(): void {
        this.fetchClients();
    }

    fetchClients(): void {
        this.apiService.getClients().subscribe(
            clients => {
                this.clients = clients;
            },
            error => {
                console.error('Error fetching clients:', error);
            }
        );
    }

    onRowClicked(pesel: string): void {
        this.router.navigate(['/clientDetails/' + pesel]);
    }
}