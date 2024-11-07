import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-client-list-view',
    templateUrl: './client-list-view.component.html',
    styleUrl: './client-list-view.component.scss'
})
export class ClientListViewComponent {
    constructor(private router: Router) { }

    newTransfer(): void {
        this.router.navigate(['/createTransaction']);
    }
}
