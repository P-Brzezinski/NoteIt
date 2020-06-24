import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Notebook} from './model/notebook';
import {ApiService} from '../shared/api.service';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
  notebooks: Notebook[] = [];

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getAllNotebooks();
  }

  public getAllNotebooks() {
    this.apiService.getAllNotebooks().subscribe(
      res => {
        this.notebooks = res;
      },
      err => {
        alert('An error has occurred');
      }
    );
  }

  createNotebook() {
    const newNotebook: Notebook = {
      name: 'New notebook',
      id: null,
      numberOfNotes: 0
    };

    this.apiService.postNoteBook(newNotebook).subscribe(
      res => {
        newNotebook.id = res.id;
        this.notebooks.push(newNotebook);
      },
      error => {
        alert('An error has occurred while saving notebook');
      }
    );
  }

  updateNotebook(updatedNotebook: Notebook) {
    this.apiService.postNoteBook(updatedNotebook).subscribe(
      res => {
      },
      error => {
        alert('An error has occurred while saving notebook');
      }
    );
  }

  deleteNotebook(notebook: Notebook) {
    if (confirm('Are you sure you want to delete notebook?')) {
      this.apiService.deleteNotebook(notebook.id).subscribe(
        res => {
          const indexOfNotebook = this.notebooks.indexOf(notebook);
          this.notebooks.splice(indexOfNotebook, 1);
        },
        err => {
          alert('Could not delete network');
        }
      );
    }
  }
}
