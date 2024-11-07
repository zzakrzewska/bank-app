import { Component, OnInit } from '@angular/core'
import { ClientDetails } from '../model/client-details';
import { ApiService } from '../service/api.service';
import { ActivatedRoute } from '@angular/router';

import { MatListModule } from '@angular/material/list';


@Component({
    selector: 'app-client-details',
    templateUrl: './client-details.component.html',
    styleUrls: ['./client-details.component.scss'],
    standalone: true,
    imports: [
        MatListModule
    ]
})
export class ClientDetailsComponent implements OnInit {
    constructor(private apiService: ApiService, private activatedRoute: ActivatedRoute) { }

    client: ClientDetails = {
        name: '', surname: '', pesel: '', birthDate: '',
        age: '',
        address: '',
        mail: ''
    };

    ngOnInit(): void {
        this.activatedRoute.paramMap.subscribe(params => {
            var routePesel = params.get('pesel');
            if (routePesel) {
                this.fetchClient(routePesel);
            } else {
                console.error('No client found.');
            }
        });
    }

    fetchClient(pesel: string): void {
        this.apiService.getClient(pesel).subscribe(client =>
            this.client = client
        );
    }
}