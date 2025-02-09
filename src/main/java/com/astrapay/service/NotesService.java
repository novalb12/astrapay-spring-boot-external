package com.astrapay.service;

import com.astrapay.dto.NotesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotesService {
    public String successAdd(String key,String Data){
        return "Notes " + key + " Successfully Added";
    }
    public String successRemove(String key){
        return "Notes " + key + " Deleted";
    }
    public String showNotesList(NotesDto notesDto){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData= "";
        
        try {
            jsonData = objectMapper.writeValueAsString(notesDto.getNotesList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Current List :"+ jsonData;
    }
}
