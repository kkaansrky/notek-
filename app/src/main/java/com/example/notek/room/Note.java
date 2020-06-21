package com.example.notek.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteID;

    private String title;

    private String date;

    private String noteDet;

    public Note(String title, String date, String noteDet) {
        this.title = title;
        this.date = date;
        this.noteDet = noteDet;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public int getNoteID() { return noteID; }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getNoteDet() {
        return noteDet;
    }


}
