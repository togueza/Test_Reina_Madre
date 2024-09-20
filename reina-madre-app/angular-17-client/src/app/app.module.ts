import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddTasksComponent } from './components/add-tasks/add-tasks.component';
import { TasksDetailsComponent } from './components/tasks-details/tasks-details.component';
import { TasksListComponent } from './components/tasks-list/tasks-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddTasksComponent,
    TasksDetailsComponent,
    TasksListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
