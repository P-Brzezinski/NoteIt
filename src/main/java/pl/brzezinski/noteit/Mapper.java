package pl.brzezinski.noteit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.brzezinski.noteit.api.viewModel.NoteViewModel;
import pl.brzezinski.noteit.api.viewModel.NotebookViewModel;
import pl.brzezinski.noteit.db.NotebookRepository;
import pl.brzezinski.noteit.model.Note;
import pl.brzezinski.noteit.model.Notebook;

import java.util.UUID;

@Component
public class Mapper {
    private NotebookRepository notebookRepository;

    @Autowired
    public Mapper(NotebookRepository notebookRepository){
        this.notebookRepository = notebookRepository;
    }

    public NoteViewModel convertToNoteViewModel(Note entity){
        var viewModel = new NoteViewModel();
        viewModel.setTitle(entity.getTitle());
        viewModel.setId(entity.getId().toString());
        viewModel.setLastModified(entity.getLastModified());
        viewModel.setText(entity.getText());
        viewModel.setNotebookId(entity.getNotebook().getId().toString());

        return viewModel;
    }

    public Note convertToNoteEntity(NoteViewModel viewModel){
        var notebook = this.notebookRepository.findById(UUID.fromString(viewModel.getNotebookId())).get();
        var entity = new Note(viewModel.getId(), viewModel.getTitle(), viewModel.getText(), notebook);

        return entity;
    }

    public NotebookViewModel convertToNotebookViewModel(Notebook entity){
        var viewModel = new NotebookViewModel();
        viewModel.setId(entity.getId().toString());
        viewModel.setName(entity.getName());
        viewModel.setNbNotes(entity.getNotes().size());

        return viewModel;
    }

    public Notebook convertToNotebookEntity(NotebookViewModel viewModel){
        var entity = new Notebook(viewModel.getId(), viewModel.getName());

        return entity;
    }
}
