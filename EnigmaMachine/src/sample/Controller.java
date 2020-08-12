package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Controller {

    @FXML
    private Label inputLabel;
    @FXML
    private Label outputLabel;
    @FXML
    private Label copiedLabel;

    @FXML
    private ComboBox<String> leftRotorChoice;
    @FXML
    private ComboBox<String> midRotorChoice;
    @FXML
    private ComboBox<String> rightRotorChoice;
    @FXML
    private ComboBox<String> leftPosition;
    @FXML
    private ComboBox<String> midPosition;
    @FXML
    private ComboBox<String> rightPosition;
    @FXML
    private ComboBox<String> leftRingSetting;
    @FXML
    private ComboBox<String> midRingSetting;
    @FXML
    private ComboBox<String> rightRingSetting;
    @FXML
    private ComboBox<String> reflectorChoice;

    @FXML
    private Circle aLamp;
    @FXML
    private Circle bLamp;
    @FXML
    private Circle cLamp;
    @FXML
    private Circle dLamp;
    @FXML
    private Circle eLamp;
    @FXML
    private Circle fLamp;
    @FXML
    private Circle gLamp;
    @FXML
    private Circle hLamp;
    @FXML
    private Circle iLamp;
    @FXML
    private Circle jLamp;
    @FXML
    private Circle kLamp;
    @FXML
    private Circle lLamp;
    @FXML
    private Circle mLamp;
    @FXML
    private Circle nLamp;
    @FXML
    private Circle oLamp;
    @FXML
    private Circle pLamp;
    @FXML
    private Circle qLamp;
    @FXML
    private Circle rLamp;
    @FXML
    private Circle sLamp;
    @FXML
    private Circle tLamp;
    @FXML
    private Circle uLamp;
    @FXML
    private Circle vLamp;
    @FXML
    private Circle wLamp;
    @FXML
    private Circle xLamp;
    @FXML
    private Circle yLamp;
    @FXML
    private Circle zLamp;

    private Circle[] lamps;

    // This allows us to index the rotor for the string to display on the drop-down
    private String[] rotorsIndex = {"ROTORI", "ROTORII", "ROTORIII", "ROTORIV", "ROTORV", "ROTORVI", "ROTORVII", "ROTORVIII"};

    // Keeping hold of the references to instances of other classes
    private View view;
    private Machine machine;



    void initialise(Machine machine, View view) {
        this.machine = machine;
        this.view = view;

        // Populate here to avoid Null Pointer Exceptions
        // Apologies for it looking like this
        this.lamps = new Circle[] {aLamp, bLamp, cLamp, dLamp, eLamp, fLamp, gLamp, hLamp, iLamp, jLamp, kLamp, lLamp, mLamp, nLamp, oLamp, pLamp, qLamp, rLamp, sLamp, tLamp, uLamp, vLamp, wLamp, xLamp, yLamp, zLamp};;
    }

    /**
     * Submit rotor settings to the machine based on form inputs
     */
    public void submitBtnPressed () {
        RotorSetting rs;

        // Default rotor settings
        int rLindex = 1;
        int rMindex = 2;
        int rRindex = 3;

        // Ring settings
        char rsL  = getComboBoxValue(leftRingSetting,  0);
        char rsM  = getComboBoxValue(midRingSetting,   0);
        char rsR  = getComboBoxValue(rightRingSetting, 0);

        // Ring positions
        char posL = getComboBoxValue(leftPosition,  0);
        char posM = getComboBoxValue(midPosition,   0);
        char posR = getComboBoxValue(rightPosition, 0);

        // Reflector choice
        char reflector = getComboBoxValue(reflectorChoice, 10);
        boolean thin = false;

        // Thin reflector?
        try {
            thin = (getComboBoxValue(reflectorChoice, 12) == 'T');
        } catch (Exception e) {
            // Not thin
        }

        // Find which rotors we want
        for(int i = 0; i < 8; i++){
            if (getComboBoxValue(leftRotorChoice).equals(rotorsIndex[i])) {
                rLindex = i + 1;
            }
            if (getComboBoxValue(midRotorChoice).equals(rotorsIndex[i])) {
                rMindex = i + 1;
            }
            if (getComboBoxValue(rightRotorChoice).equals(rotorsIndex[i])) {
                rRindex = i + 1;
            }
        }

        rs = new RotorSetting(rLindex, posL, rsL, rMindex, posM, rsM, rRindex, posR, rsR, reflector, thin);
        machine.setRotorSettings(rs);
    }

    /**
     *
     * @param box The ComboBox we want to read from
     * @param charAt The index of the Character we wish to read
     * @return The value in the ComboBox
     */
    private char getComboBoxValue (ComboBox<String> box, int charAt) {
        try {
            return box.getValue().toString().charAt(charAt);

        } catch (NullPointerException npe) {

            return box.getPromptText().charAt(charAt);
        }
    }

    /**
     *
     * @param box The ComboBox we want to read from
     * @return The value in the ComboBox
     */
    private String getComboBoxValue (ComboBox<String> box) {
        try {

            return box.getValue().toString();

        } catch (Exception npe) {

            return box.getPromptText().toString();
        }
    }


    /**
     * Reset rotor settings to default
     */
    public void resetBtnPressed () {
        // Default settings
        machine.setRotorSettings(new RotorSetting());
        updateForm();
    }

    /**
     * Clear the text fields
     */
    public void clearTextBtnPressed () {
        view.clearText(inputLabel, outputLabel);
        copiedLabel.setVisible(false);
    }

    /**
     * Copy the output text
     */
    public void copyTextBtnPressed () {
        StringSelection ss = new StringSelection(outputLabel.getText());
        Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
        cp.setContents(ss, null);
        copiedLabel.setVisible(true);
    }

    /**
     * Update the view of the controls to match the current settings
     * If the user changes some settings but doesn't submit, this will correct it once an input is pressed
     */
    private void updateForm() {
        // Update position settings
        leftPosition.setValue(Character.toString(machine.getRotorPosition(2)));
        midPosition.setValue(Character.toString(machine.getRotorPosition(1)));
        rightPosition.setValue(Character.toString(machine.getRotorPosition(0)));

        // Update rotor settings
        leftRotorChoice.setValue(machine.getRotorName(2));
        midRotorChoice.setValue(machine.getRotorName(1));
        rightRotorChoice.setValue(machine.getRotorName(0));

        // Update ring settings
        leftRingSetting.setValue(machine.getRingSetting(2));
        midRingSetting.setValue(machine.getRingSetting(1));
        rightRingSetting.setValue(machine.getRingSetting(0));

        // Update reflector
        reflectorChoice.setValue(machine.getReflectorName());
    }

    /**
     * Handle a letter input
     * @param letter The letter inputted
     */
    private void handler(char letter) {
        char outp;
        char inp = Character.toUpperCase(letter);

        // Add letter to input box
        view.addInputLetter(letter, inputLabel);

        // Compute letter and add to output box
        outp = machine.computeLetter(inp);

        // Output the letter
        view.addOutputLetter(outp, outputLabel, lamps[outp - 65]);

        // Update the form with the correct settings
        updateForm();

        // Indicates that the current text hasn't been copied, as they've just changed it
        copiedLabel.setVisible(false);
    }

    // Letter button handlers
    public void aBtnPressed() {
        handler('a');
    }
    public void bBtnPressed() {
        handler('b');
    }
    public void cBtnPressed() {
        handler('c');
    }
    public void dBtnPressed() {
        handler('d');
    }
    public void eBtnPressed() {
        handler('e');
    }
    public void fBtnPressed() {
        handler('f');
    }
    public void gBtnPressed() {
        handler('g');
    }
    public void hBtnPressed() {
        handler('h');
    }
    public void iBtnPressed() {
        handler('i');
    }
    public void jBtnPressed() {
        handler('j');
    }
    public void kBtnPressed() {
        handler('k');
    }
    public void lBtnPressed() {
        handler('l');
    }
    public void mBtnPressed() {
        handler('m');
    }
    public void nBtnPressed() {
        handler('n');
    }
    public void oBtnPressed() {
        handler('o');
    }
    public void pBtnPressed() {
        handler('p');
    }
    public void qBtnPressed() {
        handler('q');
    }
    public void rBtnPressed() {
        handler('r');
    }
    public void sBtnPressed() {
        handler('s');
    }
    public void tBtnPressed() {
        handler('t');
    }
    public void uBtnPressed() {
        handler('u');
    }
    public void vBtnPressed() {
        handler('v');
    }
    public void wBtnPressed() {
        handler('w');
    }
    public void xBtnPressed() {
        handler('x');
    }
    public void yBtnPressed() {
        handler('y');
    }
    public void zBtnPressed() {
        handler('z');
    }


}
