package pl.brzezinski.noteit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import pl.brzezinski.noteit.model.Note;
import pl.brzezinski.noteit.model.Notebook;

/**
 * This component will only execute (and get instantiated) if the
 * property noteit.db.recreate is set to true in the
 * application.properties file
 */

@Component
@ConditionalOnProperty(name = "noteit.db.recreate", havingValue = "true")
public class DBGenerator implements CommandLineRunner {

    private NotebookRepository notebookRepository;
    private NoteRepository noteRepository;

    @Autowired
    public DBGenerator(NotebookRepository notebookRepository, NoteRepository noteRepository) {
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Remove all existing entities
        this.notebookRepository.deleteAll();
        this.noteRepository.deleteAll();

        // Save a default notebook
        var defaultNotebook = new Notebook("Default");
        this.notebookRepository.save(defaultNotebook);

        var quotesNotebook = new Notebook("Quotes");
        this.notebookRepository.save(quotesNotebook);

        // Save the welcome note
        var note = new Note("Hello", "Welcome to Note It", defaultNotebook);
        this.noteRepository.save(note);

        // Save a quote note
        var quoteNote = new Note("Latin Quote", "Carpie Diem", quotesNotebook);
        this.noteRepository.save(quoteNote);

        System.out.println("Initialized database");
    }
}
