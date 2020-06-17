package pl.brzezinski.noteit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.noteit.Mapper;
import pl.brzezinski.noteit.api.viewModel.NoteViewModel;
import pl.brzezinski.noteit.api.viewModel.NotebookViewModel;
import pl.brzezinski.noteit.db.NotebookRepository;
import pl.brzezinski.noteit.model.Notebook;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {
    private NotebookRepository notebookRepository;
    private Mapper mapper;

    @Autowired
    public NotebookController(NotebookRepository notebookRepository, Mapper mapper) {
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Notebook> all(){
        var allCategories = this.notebookRepository.findAll();
        return allCategories;
    }

    @PostMapping
    public Notebook save(@RequestBody NotebookViewModel notebookViewModel, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()){
            throw new ValidationException("Error!");
        }

        var notebookEntity = this.mapper.convertToNotebookEntity(notebookViewModel);
        this.notebookRepository.save(notebookEntity);

        return notebookEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.notebookRepository.deleteById(UUID.fromString(id));
    }


}
