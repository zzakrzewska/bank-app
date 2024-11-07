import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Client } from '../model/client';
import { ClientDetails } from '../model/client-details';
import { Account } from '../model/account';
import { AccountTransaction } from '../model/account-transaction';
import { OwnerDetails } from '../model/owner-details';
import { TransactionDetails } from '../model/transaction-details';
import { TransactionSender } from '../model/transaction-sender';
import { TransactionReceiver } from '../model/transaction-receiver';
import { AccountDetails } from '../model/account-details';
import { CreateTransaction } from '../model/create-transaction';

@Injectable({
    providedIn: 'root'
})
export class ApiService {

    private baseUrl = 'http://localhost:8080'

    constructor(private http: HttpClient) { }

    getClients(): Observable<Client[]> {
        return this.http.get<Client[]>(`${this.baseUrl}/bank/BREXPLPWNBK/clients`);
    }

    getClient(pesel: string): Observable<ClientDetails> {
        return this.http.get<ClientDetails>(`${this.baseUrl}/clients/${pesel}`);
    }

    getClientAccounts(pesel: string): Observable<Account[]> {
        return this.http.get<Account[]>(`${this.baseUrl}/clients/${pesel}/accounts`);
    }

    getAccountDetails(accountNumber: string): Observable<AccountDetails> {
        return this.http.get<AccountDetails>(`${this.baseUrl}/accounts/${accountNumber}`);
    }

    getAccountTransactions(accountNumber: string): Observable<AccountTransaction[]> {
        return this.http.get<AccountTransaction[]>(`${this.baseUrl}/accounts/${accountNumber}/transactions`);
    }

    getAccountOwners(accountNumber: string): Observable<OwnerDetails[]> {
        return this.http.get<OwnerDetails[]>(`${this.baseUrl}/accounts/${accountNumber}/owners`);
    }

    getTransaction(transactionReference: string): Observable<TransactionDetails> {
        return this.http.get<TransactionDetails>(`${this.baseUrl}/transactions/${transactionReference}`);
    }

    getTransactionSender(transactionReference: string): Observable<TransactionSender> {
        return this.http.get<TransactionSender>(`${this.baseUrl}/transactions/${transactionReference}/sender`);
    }

    getTransactionReceiver(transactionReference: string): Observable<TransactionReceiver> {
        return this.http.get<TransactionReceiver>(`${this.baseUrl}/transactions/${transactionReference}/receiver`);
    }

    cancelTransfer(transactionReference: string): Observable<void> {
        this.http.delete<Boolean>(`${this.baseUrl}/transactions/${transactionReference}`).subscribe(
            data => window.alert("Transaction cancelled successfully"),
            error => window.alert("Could not cancel transfer")
        );
        return of(undefined);
    }

    createTransfer(createTransaction: CreateTransaction): Observable<number> {
        return this.http.post<number>(`${this.baseUrl}/transactions`, createTransaction);
    }
}