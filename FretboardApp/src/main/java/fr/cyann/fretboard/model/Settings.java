/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.fretboard.model;

import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.SettingsService;
import fr.cyann.fretboard.model.Modes.Mode;
import fr.cyann.fretboard.model.Tunes.Tune;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author cyann
 */
public class Settings {

    private static final Logger LOGGER = Logger.getLogger(Settings.class.getName());

    public static final String SETTING_TUNE_KEY = "tune";
    public static final String SETTING_NOTE_KEY = "note";
    public static final String SETTING_MODE_KEY = "mode";

    private static final ObjectProperty<Tune> TUNE_PROPERTY = new SimpleObjectProperty<>();
    private static final ObjectProperty<Note> NOTE_PROPERTY = new SimpleObjectProperty<>(Note.A);
    private static final ObjectProperty<Mode> MODE_PROPERTY = new SimpleObjectProperty<>();

    public static Tune getTune () {

        Services.get(SettingsService.class).ifPresent(service -> {
            String str = service.retrieve(SETTING_TUNE_KEY);
            if (str != null) {
                TUNE_PROPERTY.set(Tunes.getInstance().valueOf(str));
            }
        });

        return TUNE_PROPERTY.get();
    }

    public static void setTune (Tune value) {

        Services.get(SettingsService.class).ifPresent(service -> {
            service.store(SETTING_TUNE_KEY, value.getName());
        });

    }

    public static Note getNote () {

        Services.get(SettingsService.class).ifPresent(service -> {
            String note = service.retrieve(SETTING_NOTE_KEY);
            if (note != null) {
                NOTE_PROPERTY.set(Note.valueOf(note));
            }
        });

        return NOTE_PROPERTY.get();
    }

    public static void setNote (Note note) {

        Services.get(SettingsService.class).ifPresent(service -> {
            service.store(SETTING_NOTE_KEY, note.name());
        });

    }

    public static Mode getMode () {

        Services.get(SettingsService.class).ifPresent(service -> {
            String str = service.retrieve(SETTING_MODE_KEY);
            if (str != null) {
                MODE_PROPERTY.set(Modes.getInstance().valueOf(str));
            }
        });

        return MODE_PROPERTY.get();
    }

    public static void setMode (Mode value) {

        Services.get(SettingsService.class).ifPresent(service -> {
            service.store(SETTING_MODE_KEY, value.getName());
        });

    }
}
