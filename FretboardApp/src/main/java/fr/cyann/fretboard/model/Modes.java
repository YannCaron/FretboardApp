/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.fretboard.model;

import static fr.cyann.fretboard.Utils.getRessource;
import static fr.cyann.fretboard.model.Tunes.TUNE_XML_PATH;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author cyann
 */
@Root
public class Modes {

    public static final String MODES_XML_PATH = "fr/cyann/fretboard/data/modes.xml";

    private static Modes singleton;

    @Root
    public static class Mode {

        @Attribute
        private String name;

        @Attribute
        private int[] intervals;

        public String getName () {
            return name;
        }

        public int[] getIntervals () {
            return intervals;
        }

        @Override
        public String toString () {
            return name;
        }

    }

    @ElementList(type = Mode.class)
    private ArrayList<Mode> modes;

    private Map<String, Mode> map;

    public ArrayList<Mode> getModes () {
        return modes;
    }

    public static Modes getInstance () {
        if (singleton == null) {

            try {
                Serializer serializer = new Persister();

                singleton = serializer.read(Modes.class, getRessource(MODES_XML_PATH));

            } catch (Exception ex) {
                Logger.getLogger(Tunes.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return singleton;
    }

    public Mode valueOf (String name) {
        if (map == null) {
            map = new HashMap<>();
            for (Mode mode : modes) {
                map.put(mode.getName(), mode);
            }
        }
        return map.get(name);
    }

    public static void main (String[] args) throws Exception {

        Modes modes = new Modes();
        modes.modes = new ArrayList<>();
        Mode dorian = new Mode();
        dorian.name = "dorian";
        dorian.intervals = new int[]{2, 2, 1, 2, 2, 2, 1};
        modes.modes.add(dorian);

        Serializer serializer = new Persister();
        serializer.write(modes, System.out);
    }

}
