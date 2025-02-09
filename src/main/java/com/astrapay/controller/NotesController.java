package com.astrapay.controller;

import com.astrapay.dto.NotesDto;
import com.astrapay.exception.ExampleException;
import com.astrapay.service.NotesService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/notes")
@Api(value = "NotesController")
@Slf4j
public class NotesController {
    private final NotesService notesService;
    private final NotesDto notesDto;
    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
        this.notesDto = new NotesDto();
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<String> getListData() {
        try {
            return ResponseEntity.ok(notesService.showNotesList(notesDto));
        } catch (ExampleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> addNotes(@RequestParam(required = false) String key, @RequestParam(required = false) String value) {
        try {
            if (key == null){
                throw new ExampleException("The key params missing");
            }
            if (value == null){
                throw new ExampleException("The value params missing");
            }
            boolean isSuccess = notesDto.addNote(key, value);
            if(!isSuccess){
                throw new ExampleException("The Key must be unique");
            }
            return ResponseEntity.ok(notesService.successAdd(key, value));
        } catch (ExampleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteNotes(@RequestParam(required = false) String key) {
        try {
            if (key == null){
                throw new ExampleException("The key params missing");
            }
            boolean isSuccess = notesDto.removeNote(key);
            if (!isSuccess) {
                throw new ExampleException("The Key doesnt exist");
            }
            return ResponseEntity.ok(notesService.successRemove(key));
        } catch (ExampleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}