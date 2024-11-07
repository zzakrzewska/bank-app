import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientDetailsComponent } from '../client-details/client-details.component';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AccountListComponent } from '../account-list/account-list.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ClientDetailsComponent,
    MatListModule,
    MatButtonModule,
    BrowserAnimationsModule,
    Location,
    AccountListComponent
  ]
})
export class ClientDetailsViewModule { }
