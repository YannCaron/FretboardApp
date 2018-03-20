/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.fretboard.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.LifecycleEvent;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import fr.cyann.fretboard.Main;
import fr.cyann.fretboard.controls.Pianoboard;
import fr.cyann.fretboard.model.BoardNoteAddable;
import fr.cyann.fretboard.model.DefaultPianoboardModel;
import java.util.logging.Logger;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author cyann
 */
public class PianoboardPresenter {

    private static final Logger LOGGER = Logger.getLogger(PianoboardPresenter.class.getName());

    @FXML
    private View pianoboard;

    @FXML
    private Pianoboard pbPianoboard;

    DefaultPianoboardModel model = new DefaultPianoboardModel();

    public void initialize () {
        pianoboard.setShowTransitionFactory(BounceInRightTransition::new);

        //pianoboard.getLayers().add(new FloatingActionButton(MaterialDesignIcon.INFO.text,
        //        e -> System.out.println("Info")).getLayer());

        pianoboard.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(Main.MENU_LAYER)));
                appBar.setTitleText("Pianoboard");
                //appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e
                //        -> System.out.println("Favorite")));
            }
        });

        pbPianoboard.scaleXProperty().set(1.75f);
        pbPianoboard.scaleYProperty().set(1.75f);
        pbPianoboard.rotateProperty().set(90);
        pbPianoboard.translateXProperty().set(25);
        pbPianoboard.translateYProperty().set(185);

        pbPianoboard.setModel(model);

        pianoboard.setOnShowing(this::fretboard_onShowing);
    }

    protected void fretboard_onShowing (LifecycleEvent event) {

        model.clearNotes();
        BoardNoteAddable.apply(model);

        pbPianoboard.update();
    }

}
