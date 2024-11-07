import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientListViewComponent } from './client-list-view/client-list-view.component';
import { ClientDetailsViewComponent } from './client-details-view/client-details-view.component';
import { AccountDetailsViewComponent } from './account-details-view/account-details-view.component';
import { TransactionDetailsViewComponent } from './transaction-details-view/transaction-details-view.component';
import { CreateTransactionViewComponent } from './create-transaction-view/create-transaction-view.component';

const routes: Routes = [
  { path: '', redirectTo: '/clientList', pathMatch: 'full' },
  { path: 'clientList', component: ClientListViewComponent },
  { path: 'clientDetails/:pesel', component: ClientDetailsViewComponent },
  { path: 'accountDetails/:accountNumber', component: AccountDetailsViewComponent },
  { path: 'transactionDetails/:transactionReference', component: TransactionDetailsViewComponent },
  { path: 'createTransaction', component: CreateTransactionViewComponent },
  { path: 'createTransaction/:accountNumber', component: CreateTransactionViewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
