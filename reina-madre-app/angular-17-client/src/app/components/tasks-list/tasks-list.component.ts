import { Component, OnInit } from '@angular/core';
import { Tasks } from '../../models/tasks.model';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css'],
})
export class TasksListComponent implements OnInit {
  tasks?: Tasks[];
  currentTasks: Tasks = {};
  currentIndex = -1;
  title = '';

  constructor(private tasksService: TasksService) {}

  ngOnInit(): void {
    this.retrieveTasks();
  }

  retrieveTasks(): void {
    this.tasksService.getAll().subscribe({
      next: (data) => {
        this.tasks = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  refreshList(): void {
    this.retrieveTasks();
    this.currentTasks = {};
    this.currentIndex = -1;
  }

  setActiveTutorial(tutorial: Tasks, index: number): void {
    this.currentTasks = tutorial;
    this.currentIndex = index;
  }

  removeAllTasks(): void {
    this.tasksService.deleteAll().subscribe({
      next: (res) => {
        console.log(res);
        this.refreshList();
      },
      error: (e) => console.error(e)
    });
  }

  searchTitle(): void {
    this.currentTasks = {};
    this.currentIndex = -1;

    this.tasksService.findByTitle(this.title).subscribe({
      next: (data) => {
        this.tasks = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
}
