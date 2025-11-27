package com.myeatclub.eatClubApp.controller;

import com.myeatclub.eatClubApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")   // class per entry
@CrossOrigin("http://localhost:3000")
public class EatClubEntryController {

    private Map<Long , JournalEntry> journalEntries = new HashMap<>();

    public EatClubEntryController() {
        journalEntries.put(1L, new JournalEntry(1L, "First Entry", "This is my first journal entry."));
        journalEntries.put(2L, new JournalEntry(2L, "Second Entry", "This is my second journal entry."));
    }


    @GetMapping
    public List<JournalEntry> getAll() {
        // Logic to retrieve all journal entries
        return new ArrayList<>(journalEntries.values());
    }

    @GetMapping("id/{myid}")
    public JournalEntry getById(@PathVariable Long myid) {
        // Logic to retrieve a journal entry by ID
        return journalEntries.get(myid);
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @DeleteMapping("id/{myid}")
    public boolean deleteEntry(@PathVariable Long myid) {
        journalEntries.remove(myid);
        return true;
    }

    @PutMapping("id/{myid}")
    public boolean updateEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }


}