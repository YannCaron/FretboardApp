/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.fretboard.model;

import static fr.cyann.fretboard.model.SerializationUtils.getRessource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class Tunes {

    public static final String TUNE_XML_PATH = "fr/cyann/fretboard/data/tunes.xml";

    private static Tunes singleton;

    @Root
    public static class Tune {

        @Attribute
        private String name;

        @Attribute
        private int fretcount;

        @Attribute
        private Note[] notes;

        public String getName () {
            return name;
        }

        public int getFretcount () {
            return fretcount;
        }

        public Note[] getNotes () {
            return notes;
        }

        @Override
        public String toString () {
            return name;
        }

    }

    @ElementList(type = Tune.class)
    private ArrayList<Tune> tunes;

    private Map<String, Tune> map;

    public ArrayList<Tune> getTunes () {
        return tunes;
    }

    public static Tunes getInstance () {
        if (singleton == null) {

            try {
                Serializer serializer = new Persister();

                singleton = serializer.read(Tunes.class, getRessource(TUNE_XML_PATH));

            } catch (Exception ex) {
                Logger.getLogger(Tunes.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return singleton;
    }

    public Tune valueOf (String name) {
        if (map == null) {
            map = new HashMap<>();
            for (Tune tune : tunes) {
                map.put(tune.getName(), tune);
            }
        }
        return map.get(name);
    }

    public static void main (String[] args) throws Exception {
        Tunes tunes = new Tunes();
        Tune guitareEStandard = new Tune();
        guitareEStandard.name = "Guitare E Standard";
        guitareEStandard.notes = new Note[]{Note.E, Note.A, Note.D, Note.G, Note.A, Note.E};
        tunes.tunes = new ArrayList<>();
        tunes.tunes.add(guitareEStandard);

        Serializer serializer = new Persister();
        serializer.write(tunes, System.out);
    }

}
