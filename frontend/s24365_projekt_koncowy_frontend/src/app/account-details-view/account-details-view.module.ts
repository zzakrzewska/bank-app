import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountOwnersComponent } from '../account-owners/account-owners.component';
import { TransactionListComponent } from '../transaction-list/transaction-list.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AccountOwnersComponent,
    TransactionListComponent
  ]
})
export class AccountDetailsViewModule { }
