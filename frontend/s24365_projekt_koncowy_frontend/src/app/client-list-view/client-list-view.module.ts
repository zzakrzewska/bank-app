import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientListViewComponent } from './client-list-view.component';
import { ClientListComponent } from '../client-list/client-list.component';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    ClientListViewComponent
  ],
  imports: [
    CommonModule,
    ClientListComponent,
    MatTableModule,
    MatButtonModule,
    BrowserAnimationsModule
  ]
})
export class ClientListViewModule { }
