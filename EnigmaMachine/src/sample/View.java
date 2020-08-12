package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


class View {

    private final Color lampOn  = Color.rgb(211,211,0);
    private final Color lampOff = Color.rgb(90,90,90);

    private String inputs = "";
    private String outputs = "";
    private int chunker = 0;

    private Circle onLamp;

    /**
     * Constructor for view
     */
    View() {
    }

    /**
     * Add an input letter to the inputs box
     * @param letter Letter to add
     * @param label Label to which to add it
     */
    void addInputLetter(char letter, Label label) {
        // Add letter to the inputs box
        inputs +=  Character.toUpperCase(letter);
        chunker++;
        if (chunker % 5 == 0) {
            inputs += " ";
        }
        label.setText(inputs);

    }

    /**
     * Add output letter to the outputs box
     * @param letter Letter to add
     * @param label Label to which to add it
     * @param lamp The lamp to turn on
     */
    void addOutputLetter (char letter, Label label, Circle lamp) {
        outputs += Character.toUpperCase(letter);
        if (chunker % 5 == 0) {
            outputs += " ";
        }

        label.setText(outputs);

        // Turn on the lamp
        turnOnLamp(lamp);
    }

    /**
     * Clear all the text
     * @param inp Input label to clear
     * @param outp Output label to clear
     */
    void clearText(Label inp, Label outp) {
        chunker = 0;
        outputs = "";
        inputs = "";

        inp.setText(inputs);
        outp.setText(outputs);
    }

    /**
     * Turn on a lamp
     * @param lamp The lamp to turn on
     */
    private void turnOnLamp(Circle lamp) {

        // Try turn the lamp off
        try {
            turnOffLamp(onLamp);
        } catch (NullPointerException npe) {
            // Lamp wasn't on to begin with
        }

        lamp.setFill(lampOn);
        onLamp = lamp;
    }

    /**
     * Turn off a lamp
     * @param lamp The lamp to turn off
     */
    private void turnOffLamp (Circle lamp) {
        lamp.setFill(lampOff);
    }

}
