/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.fretboard.model;

import javafx.scene.paint.Color;

/**
 *
 * @author cyann
 */
public interface BoardNoteAddable {

    public static void apply (BoardNoteAddable model) {
        int intervalFromRoot = 0;
        for (int interval : Settings.getMode().getIntervals()) {
            Note note = Note.valueOf((Settings.getNote().interval() + intervalFromRoot) % 12);
            Color color = Color.BLACK;
            if (intervalFromRoot == 0) {
                color = Color.RED;
            } else if (intervalFromRoot == 6) {
                color = Color.BLUE;
            } else if (intervalFromRoot == 3) {
                color = Color.BLUEVIOLET;
            } else if (intervalFromRoot == 4) {
                color = Color.DARKVIOLET;
            } else if (intervalFromRoot == 7) {
                color = Color.BROWN;
            }

            model.addNote(note, color);

            intervalFromRoot += interval;
        }
    }

    void addNote (Note note, Color color);

}
