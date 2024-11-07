import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatListModule } from '@angular/material/list';
import { LayoutModule } from '@angular/cdk/layout';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatBadgeModule } from '@angular/material/badge';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatRadioModule } from '@angular/material/radio';
import { MatChipsModule } from '@angular/material/chips';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ClientListViewModule } from './client-list-view/client-list-view.module';
import { ClientDetailsViewComponent } from './client-details-view/client-details-view.component';
import { ClientDetailsComponent } from "./client-details/client-details.component";
import { AccountListComponent } from "./account-list/account-list.component";
import { AccountDetailsViewComponent } from './account-details-view/account-details-view.component';
import { AccountOwnersComponent } from "./account-owners/account-owners.component";
import { TransactionListComponent } from "./transaction-list/transaction-list.component";
import { TransactionDetailsComponent } from './transaction-details/transaction-details.component';
import { TransactionDetailsViewComponent } from './transaction-details-view/transaction-details-view.component';
import { TransactionSenderComponent } from './transaction-sender/transaction-sender.component';
import { TransactionReceiverComponent } from './transaction-receiver/transaction-receiver.component';
import { CreateTransactionComponent } from './create-transaction/create-transaction.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateTransactionViewComponent } from './create-transaction-view/create-transaction-view.component';
import { AccountDetailsComponent } from './account-details/account-details.component';

@NgModule({
    declarations: [
        AppComponent,
        ClientDetailsViewComponent,
        AccountDetailsViewComponent,
        TransactionDetailsComponent,
        TransactionDetailsViewComponent,
        TransactionSenderComponent,
        TransactionReceiverComponent,
        CreateTransactionComponent,
        CreateTransactionViewComponent,
        AccountDetailsComponent
    ],
    providers: [
        provideAnimationsAsync()
    ],
    bootstrap: [AppComponent],
    imports: [
        CommonModule,
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MatToolbarModule,
        MatTableModule,
        MatListModule,
        MatDialogModule,
        MatTooltipModule,
        MatButtonModule,
        MatFormFieldModule,
        MatSelectModule,
        MatInputModule,
        MatMenuModule,
        MatIconModule,
        MatSnackBarModule,
        MatDatepickerModule,
        MatAutocompleteModule,
        MatToolbarModule,
        MatSidenavModule,
        MatCheckboxModule,
        MatCardModule,
        LayoutModule,
        MatGridListModule,
        MatExpansionModule,
        MatTabsModule,
        MatButtonToggleModule,
        MatPaginatorModule,
        MatSortModule,
        MatSlideToggleModule,
        MatRadioModule,
        MatChipsModule,
        ScrollingModule,
        MatBadgeModule,
        BrowserAnimationsModule,
        ClientListViewModule,
        ClientDetailsComponent,
        AccountListComponent,
        AccountOwnersComponent,
        TransactionListComponent,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class AppModule { }
