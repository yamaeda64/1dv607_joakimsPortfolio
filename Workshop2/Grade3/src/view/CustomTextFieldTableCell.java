package view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import model.Boat;
import model.Member;

import java.util.Iterator;

/**
 * Helper class to show the popup for boats. It overwrite some settings of the TableCell to give it a action
 * That the mouseover will pop-up the list of Boats if in verbose list.
 * The class both hold function to setup the pop-up (verbose view) and
 * to remover it (normal view).
 * The base of this class uses an implementation fromStack Overflow
 * "https://stackoverflow.com/questions/44094265/adding-hover-listener-to-cells-of-a-specific-column-in-javafx-table
 * @param <S>
 * @param <T>
 */
public final class CustomTextFieldTableCell<S, T> extends TextFieldTableCell<S, T> {

    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn() {
        
        return forTableColumn(new DefaultStringConverter());
    }
    
    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn(final StringConverter<T> converter) {
        return new Callback<TableColumn<S, T>, TableCell<S, T>>() {
            @Override
            public TableCell<S, T> call(TableColumn<S, T> list) {
                final TextFieldTableCell<S, T> result = new TextFieldTableCell<S, T>(converter);
                final Popup popup = new Popup();
              
                
                popup.setAutoHide(true);
                
                
                final EventHandler<MouseEvent> hoverListener = new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                        String boatListString = "";
                        Member temp = (Member)result.getTableRow().getItem();
                     
                        if(temp != null)
                        {
                            
                            Iterator<Boat> iterator = temp.getBoatIterator();

                            while(iterator.hasNext())
                            {
                                Boat boat = iterator.next();
                                boatListString += boat.getBoatType() + ", " + boat.getLength() + "cm, "+ boat.getBoatID() + "\n";
                            }
                            
                            final Label popupContent = new Label(boatListString);
    
                            popupContent.setStyle("-fx-background-color: #64b5f6; -fx-border-color: #000000; -fx-border-width: 1px; -fx-padding: 5px; -fx-text-fill: white;");
    
                            popup.getContent().clear();
                            
                            popup.getContent().addAll(popupContent); //popupContent);
                        }
                        if(temp != null && temp.getBoatCount() == 0)  // removed .. && temp.getBoatList().isEmpty()
                        {
                            if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                                popup.hide();
                            } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                                popup.hide();
                            }
                        }
                        else
                        {
                            if(event.getEventType() == MouseEvent.MOUSE_EXITED)
                            {
                                popup.hide();
                            } else if(event.getEventType() == MouseEvent.MOUSE_ENTERED)
                            {
                                popup.show(result, event.getScreenX() + 10, event.getScreenY());
                            }
                        }
                    }
                };
                
                result.setOnMouseEntered(hoverListener);
                result.setOnMouseExited(hoverListener);
                
                return result;
            }
        };
    }
    
    
    // TODO, These should be rewritten to not have all this code duplication
    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> revertTableColumn() {
    
        return revertTableColumn(new DefaultStringConverter());
    }
    
    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> revertTableColumn(final StringConverter<T> converter) {
        return new Callback<TableColumn<S, T>, TableCell<S, T>>() {
            @Override
            public TableCell<S, T> call(TableColumn<S, T> list) {
                final TextFieldTableCell<S, T> result = new TextFieldTableCell<S, T>(converter);
                final Popup popup = new Popup();
                
                
                popup.setAutoHide(true);
                
                
                final EventHandler<MouseEvent> hoverListener = new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                        String boatListString = "";
                        Member temp = (Member)result.getTableRow().getItem();
                        
                        if(temp != null)
                        {
                            final Label popupContent = new Label(temp.getFirstName() + "this is a popup showing the content = ");
                            
                            popupContent.setStyle("-fx-background-color: #64b5f6; -fx-border-color: #000000; -fx-border-width: 1px; -fx-padding: 5px; -fx-text-fill: white;");
                            
                            popup.getContent().clear();
                            popup.getContent().addAll(popupContent);
                        }
                        if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                            popup.hide();
                        } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                            popup.hide();
                        }
                    }
                };
                result.setOnMouseEntered(hoverListener);
                result.setOnMouseExited(hoverListener);
                
                return result;
            }
        };
    }
}