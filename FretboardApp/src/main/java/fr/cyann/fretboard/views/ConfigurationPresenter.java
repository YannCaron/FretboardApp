package fr.cyann.fretboard.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import fr.cyann.fretboard.Main;
import fr.cyann.fretboard.model.Modes;
import fr.cyann.fretboard.model.Note;
import fr.cyann.fretboard.model.SerializationUtils;
import fr.cyann.fretboard.model.Settings;
import fr.cyann.fretboard.model.Tunes;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ConfigurationPresenter {

    private static final Logger LOGGER = Logger.getLogger(ConfigurationPresenter.class.getName());

    @FXML
    private View configuration;

    @FXML
    public ChoiceBox<Note> cbRootNote;

    @FXML
    public ChoiceBox<Tunes.Tune> cbTune;

    @FXML
    public ChoiceBox<Modes.Mode> cbMode;

    public void initialize () {
        configuration.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(Main.MENU_LAYER)));
                appBar.setTitleText("Configuration");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e
                        -> System.out.println("Search")));
            }
        });

        cbRootNote.getItems().addAll(Note.values());
        cbRootNote.getSelectionModel().select(Settings.getNote());

        cbTune.getItems().addAll(Tunes.getInstance().getTunes());
        cbTune.getSelectionModel().select(Settings.getTune());

        cbMode.getItems().addAll(Modes.getInstance().getModes());
        cbMode.getSelectionModel().select(Settings.getMode());

        cbTune.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tunes.Tune> observable, Tunes.Tune oldValue, Tunes.Tune newValue) -> {
            cbTune_onSelectedItemChanged(oldValue, newValue);
        });

        cbRootNote.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Note> observable, Note oldValue, Note newValue) -> {
            cbRootNote_onSelectedItemChanged(oldValue, newValue);
        });

        cbMode.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Modes.Mode> observable, Modes.Mode oldValue, Modes.Mode newValue) -> {
            cbMode_onSelectedItemChanged(oldValue, oldValue);
        });

    }

    protected void cbTune_onSelectedItemChanged (Tunes.Tune oldValue, Tunes.Tune tune) {
        Settings.setTune(cbTune.getValue());
    }

    protected void cbRootNote_onSelectedItemChanged (Note oldValue, Note rootNote) {
        Settings.setNote(rootNote);
    }

    protected void cbMode_onSelectedItemChanged (Modes.Mode oldValue, Modes.Mode mode) {
        Settings.setMode(cbMode.getValue());
    }

}
