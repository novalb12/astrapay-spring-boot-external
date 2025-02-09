package com.astrapay.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

@Data
public class NotesDto {
    @NotEmpty
    private Map<String, String> notesList = new HashMap<String, String>();

    public Map<String, String> getNotes(){
        return notesList;
    }

    public boolean addNote(String key, String data) {
        if(notesList.containsKey(key)){
            return false;
        }
        notesList.put(key, data);
        return true;
    }

    public boolean removeNote(String key) {
        if(notesList.containsKey(key)){
            notesList.remove(key);
            return false;
        }
        return true;
    }
}
