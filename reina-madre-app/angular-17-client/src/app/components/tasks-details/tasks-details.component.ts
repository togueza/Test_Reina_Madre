import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tasks } from '../../models/tasks.model';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-tasks-details',
  templateUrl: './tasks-details.component.html',
  styleUrls: ['./tasks-details.component.css'],
})
export class TasksDetailsComponent implements OnInit {
  prioritiesList: any = ['BAJA', 'MEDIA', 'ALTA']

  @Input() viewMode = false;

  @Input() currentTasks: Tasks = {
    title: '',
    description: '',
    completed: false,
    priorities: ''
  };

  message = '';

  constructor(
    private tasksService: TasksService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getTasks(this.route.snapshot.params['id']);
    }
  }

  getTasks(id: string): void {
    this.tasksService.get(id).subscribe({
      next: (data) => {
        this.currentTasks = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  updateCompleted(status: boolean): void {
    const data = {
      title: this.currentTasks.title,
      description: this.currentTasks.description,
      completed: status,
      priorities: this.currentTasks.priorities
    };

    this.message = '';

    this.tasksService.update(this.currentTasks.id, data).subscribe({
      next: (res) => {
        console.log(res);
        this.currentTasks.completed = status;
        this.message = res.message
          ? res.message
          : 'The status was updated successfully!';
      },
      error: (e) => console.error(e)
    });
  }

  updateTasks(): void {
    this.message = '';

    this.tasksService
      .update(this.currentTasks.id, this.currentTasks)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message
            ? res.message
            : 'This tasks was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteTasks(): void {
    this.tasksService.delete(this.currentTasks.id).subscribe({
      next: (res) => {
        console.log(res);
        this.router.navigate(['/tasks']);
      },
      error: (e) => console.error(e)
    });
  }

}
