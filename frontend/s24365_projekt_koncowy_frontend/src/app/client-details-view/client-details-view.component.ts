import { Component } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-client-details-view',
  templateUrl: './client-details-view.component.html',
  styleUrl: './client-details-view.component.scss'
})
export class ClientDetailsViewComponent {
  constructor(private location: Location) { }

  back(): void {
    this.location.back()
  }

}
