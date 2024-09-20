import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TasksListComponent } from './components/tasks-list/tasks-list.component';
import { TasksDetailsComponent } from './components/tasks-details/tasks-details.component';
import { AddTasksComponent } from './components/add-tasks/add-tasks.component';

const routes: Routes = [
  { path: '', redirectTo: 'tasks', pathMatch: 'full' },
  { path: 'tasks', component: TasksListComponent },
  { path: 'tasks/:id', component: TasksDetailsComponent },
  { path: 'add', component: AddTasksComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
