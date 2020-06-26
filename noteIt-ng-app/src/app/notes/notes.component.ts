import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Notebook} from './model/notebook';
import {ApiService} from '../shared/api.service';
import {Note} from './model/note';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
  notebooks: Notebook[] = [];
  notes: Note[] = [];
  selectedNotebook: Notebook;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getAllNotebooks();
    this.getAllNotes();
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

  getAllNotes() {
    this.apiService.getAllNotes().subscribe(
      res => {
        this.notes = res;
      },
      error => {
        alert('Error occurred while downloading message');
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

  deleteNote(note: Note) {
    if (confirm('Are you sure you want to delete this note?')) {
      this.apiService.deleteNote(note.id).subscribe(
        res => {
          const indexOfTheNote = this.notes.indexOf(note);
          this.notes.splice(indexOfTheNote, 1);
        },
        error => {
          alert('An error has occurred while deleting the note');
        }
      );
    }
  }

  createNote(specificNotebookId: string) {
    const newNote: Note = {
      id: null,
      title: 'New note',
      text: 'Write some text here',
      lastModifiedOn: null,
      notebookId: specificNotebookId
    };

    this.apiService.postNote(newNote).subscribe(
      res => {
        newNote.id = res.id;
        this.notes.push(newNote);
      },
      error => {
        alert('An error occurred while saving note');
      }
    );
  }

  selectNotebook(notebook: Notebook) {
    this.selectedNotebook = notebook;
    this.apiService.getNotesByNotebook(notebook.id).subscribe(
      res => {
        this.notes = res;
      },
      error => {
        alert('An error has occurred wile downloading the notes');
      }
    );
  }

  updateNote(updatedNote: Note) {
    this.apiService.postNote(updatedNote).subscribe(
      res => {
      },
      error => {
        alert('An error occurred while saving note');
      }
    );
  }

  selectAllNotes() {
    this.selectedNotebook = null;
    this.getAllNotes();
  }
}
