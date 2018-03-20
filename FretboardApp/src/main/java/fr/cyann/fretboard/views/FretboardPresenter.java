package fr.cyann.fretboard.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.LifecycleEvent;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import fr.cyann.fretboard.Main;
import fr.cyann.fretboard.controls.Fretboard;
import fr.cyann.fretboard.model.BoardNoteAddable;
import fr.cyann.fretboard.model.DefaultFretboardModel;
import fr.cyann.fretboard.model.Note;
import fr.cyann.fretboard.model.Settings;
import java.util.Arrays;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class FretboardPresenter {

    private static final Logger LOGGER = Logger.getLogger(FretboardPresenter.class.getName());

    @FXML
    private View fretboard;

    @FXML
    private AnchorPane apContainer;

    @FXML
    private Fretboard fbFretboard;

    DefaultFretboardModel model = new DefaultFretboardModel();

    public void initialize () {
        fretboard.setShowTransitionFactory(BounceInRightTransition::new);

        //fretboard.getLayers().add(new FloatingActionButton(MaterialDesignIcon.INFO.text,
        //        e -> System.out.println("Info")).getLayer());

        fretboard.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(Main.MENU_LAYER)));
                appBar.setTitleText("Fretboard");
                //appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e
                //        -> System.out.println("Favorite")));
            }
        });

        fbFretboard.scaleXProperty().set(1.5f);
        fbFretboard.scaleYProperty().set(1.5f);
        fbFretboard.rotateProperty().set(90);
        fbFretboard.translateXProperty().set(-335);
        fbFretboard.translateYProperty().set(750);
        apContainer.setMinHeight(2000);

        fbFretboard.setModel(model);

        fretboard.setOnShowing(this::fretboard_onShowing);
    }

    protected void fretboard_onShowing (LifecycleEvent event) {
        model.setFretCount(Settings.getTune().getFretcount());
        model.getTunes().clear();
        model.getTunes().addAll(Arrays.asList(Settings.getTune().getNotes()));

        model.clearNotes();
        BoardNoteAddable.apply(model);

        fbFretboard.update();
    }

}
