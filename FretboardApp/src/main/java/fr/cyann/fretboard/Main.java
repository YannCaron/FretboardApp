package fr.cyann.fretboard;

import com.gluonhq.charm.down.Platform;
import fr.cyann.fretboard.views.ConfigurationView;
import fr.cyann.fretboard.views.FretboardView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.visual.Swatch;
import fr.cyann.fretboard.views.PianoboardView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends MobileApplication {

    public static final String CONFIGURATION_VIEW = HOME_VIEW;
    public static final String FRETBOARD_VIEW = "Fretboard View";
    public static final String PIANOBOARD_VIEW = "Pianoboard View";
    public static final String MENU_LAYER = "Side Menu";
    
    @Override
    public void init() {
        addViewFactory(CONFIGURATION_VIEW, () -> new ConfigurationView(CONFIGURATION_VIEW).getView());
        addViewFactory(FRETBOARD_VIEW, () -> new FretboardView(FRETBOARD_VIEW).getView());
        addViewFactory(PIANOBOARD_VIEW, () -> new PianoboardView(PIANOBOARD_VIEW).getView());
        
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);
        
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    }
}
