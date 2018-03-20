/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.fretboard.model;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 *
 * @author cyann
 */
public class DefaultPianoboardModel extends PianoboardModel implements BoardNoteAddable {

    private final Map<Note, Color> scale;

    public DefaultPianoboardModel() {
        scale = new HashMap<>();
    }

    @Override
    public void addNote(Note note, Color color) {
        scale.put(note, color);
    }

    public void clearNotes() {
        scale.clear();
    }

    @Override
    public boolean isVisible(Note note) {
        return scale.containsKey(note);
    }

    @Override
    public Color getTipColor(Note note) {
        return scale.get(note);
    }

}
