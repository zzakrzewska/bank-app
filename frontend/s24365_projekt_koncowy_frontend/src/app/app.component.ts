import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent {
    title = 's24365_projekt_koncowy_frontend';

    constructor(private router: Router) { }

    goToClientList(): void {
        this.router.navigate(['/clientList']);
    }
}
