/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.fretboard.model;

import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.SettingsService;
import fr.cyann.fretboard.views.ConfigurationPresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cyann
 */
public class Settings {

    private static final Logger LOGGER = Logger.getLogger(Settings.class.getName());

    public static final String SETTING_TUNE_KEY = "tune";
    public static final String SETTING_NOTE_KEY = "note";
    public static final String SETTING_MODE_KEY = "mode";

    private static final IntegerProperty TUNE_PROPERTY = new SimpleIntegerProperty();
    private static final ObjectProperty<Note> NOTE_PROPERTY = new SimpleObjectProperty<>(Note.A);
    private static final IntegerProperty MODE_PROPERTY = new SimpleIntegerProperty();

    public static int getTune () {

        Services.get(SettingsService.class).ifPresent(service -> {
            String str = service.retrieve(SETTING_TUNE_KEY);
            if (str != null) {
                TUNE_PROPERTY.set(Integer.valueOf(str));
            }
        });

        return TUNE_PROPERTY.get();
    }

    public static void setTune (int value) {

        Services.get(SettingsService.class).ifPresent(service -> {
            service.store(SETTING_TUNE_KEY, String.valueOf(value));
        });

    }

    public static Note getNode () {

        Services.get(SettingsService.class).ifPresent(service -> {
            String note = service.retrieve(SETTING_NOTE_KEY);
            if (note != null) {
                NOTE_PROPERTY.set(Note.valueOf(note));
            }
        });

        return NOTE_PROPERTY.get();
    }

    public static void setNode (Note note) {

        Services.get(SettingsService.class).ifPresent(service -> {
            service.store(SETTING_NOTE_KEY, note.name());
        });

    }

    public static int getMode () {

        Services.get(SettingsService.class).ifPresent(service -> {
            String str = service.retrieve(SETTING_MODE_KEY);
            if (str != null) {
                MODE_PROPERTY.set(Integer.valueOf(str));
            }
        });

        return MODE_PROPERTY.get();
    }

    public static void setMode (int value) {

        Services.get(SettingsService.class).ifPresent(service -> {
            service.store(SETTING_MODE_KEY, String.valueOf(value));
        });

    }
}
