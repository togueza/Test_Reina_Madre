import { Component } from '@angular/core';
import { Tasks } from '../../models/tasks.model';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-add-tasks',
  templateUrl: './add-tasks.component.html',
  styleUrls: ['./add-tasks.component.css'],
})
export class AddTasksComponent {
  tasks: Tasks = {
    title: '',
    description: '',
    completed: false,
    priorities: ''
  };
  submitted = false;

  prioritiesList: any = ['BAJA', 'MEDIA', 'ALTA']

  constructor(private tasksService: TasksService) {}

  saveTasks(): void {
    const data = {
      title: this.tasks.title,
      description: this.tasks.description,
      priorities: this.tasks.priorities
    };

    this.tasksService.create(data).subscribe({
      next: (res) => {
        console.log(res, this.tasks.priorities);
        this.submitted = true;
      },
      error: (e) => console.error(e)
    });
  }

  newTasks(): void {
    this.submitted = false;
    this.tasks = {
      title: '',
      description: '',
      completed: false,
      priorities: ''
    };
  }
}
