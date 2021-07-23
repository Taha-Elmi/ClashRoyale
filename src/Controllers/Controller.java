package Controllers;

import javafx.event.ActionEvent;

/**
 * The interface Controller.
 */
public interface Controller {
    /**
     * Action handler. handle the events.
     *
     * @param ae the ActionEvents.
     * @throws Exception when some unknown event happened.
     */
    void actionHandler(ActionEvent ae) throws Exception;

}
