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
public abstract class PianoboardModel extends BoardModel {
    
    public abstract boolean isVisible(Note node);

    public abstract Color getTipColor(Note node);
    
}
