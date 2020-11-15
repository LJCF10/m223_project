package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entries")
public class EntryController {

    private EntryService entryService;
    private UserDetailsServiceImpl UserService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable long id) {
        
        Optional<Entry> entry = entryService.findById(id);
        if (entry.isEmpty()) return;
        entryService.deleteEntry(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entry updateEntry(@PathVariable long id, @Valid @RequestBody Entry entry) {

        Optional<Entry> editentry = entryService.findById(id);
        if (editentry.isEmpty()) return null;
        entry.setId(id);
        return entryService.updateEntry(entry);
    }
}
