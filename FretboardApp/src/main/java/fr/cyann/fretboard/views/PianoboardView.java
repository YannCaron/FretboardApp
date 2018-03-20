package fr.cyann.fretboard.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class PianoboardView {

    private final String name;

    public PianoboardView (String name) {
        this.name = name;
    }

    public View getView () {
        try {
            View view = FXMLLoader.load(PianoboardView.class.getResource("pianoboard.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
