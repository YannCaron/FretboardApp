/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.fretboard.model;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ChoiceBox;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author cyann
 */
public class SerializationUtils {

    private static final Logger LOGGER = Logger.getLogger(SerializationUtils.class.getName());

    private SerializationUtils () {
    }

    public static InputStream getRessource (String path) {
        return SerializationUtils.class.getClassLoader().getResourceAsStream(path);
    }

    public static <T> void loadSystemXMLTo (Class<T> cls, ChoiceBox cb, String path, Function<T, List> accessor) {

        try {
            Serializer serializer = new Persister();

            T list = serializer.read(cls, getRessource(path));
            cb.getItems().addAll(accessor.apply(list));
            cb.getSelectionModel().selectFirst();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

}
