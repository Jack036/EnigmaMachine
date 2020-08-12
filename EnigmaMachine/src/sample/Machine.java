package sample;

class Machine {

    // Rotors and reflector
    private Rotor[] rotors= new Rotor[3];
    private Reflector reflector;

    // True if a double step is taking place
    private boolean doubleStep;

    /**
     * Constructor for Machine
     * Sets the machine to the default rotor setting
     */
    Machine() {
        // 2 is leftmost
        // 0 is rightmost
        // 0, 1, 2, ref, 2, 1, 0.

        // Default rotor settings
        setRotorSettings(new RotorSetting());
    }

    /**
     * Set the machine to the supplied rotor settings
     * @param rs The rotor settings to apply
     */
    void setRotorSettings (RotorSetting rs) {
        for (int i = 0; i < 3; i++) {
            rotors[i] = rs.getRotor(i);
        }

        reflector = rs.getReflector();
    }

    /**
     * Get the position of the rotor with the given index as a char
     * @param rotorIndex The index of the rotor to get the position of
     * @return The position of the rotor
     */
    char getRotorPosition (int rotorIndex) {
        return rotors[rotorIndex].getPosition();
    }

    /**
     * Get the name of the rotor with the given index as a string
     * @param rotorIndex The index of the rotor to get the position of
     * @return The name of the rotor
     */
    String getRotorName (int rotorIndex) {
        return rotors[rotorIndex].getName();
    }

    /**
     * Get the name of the reflector as a string
     * @return The name of the rotor
     */
    String getReflectorName () {
        return reflector.getName();
    }

    /**
     * Get the ring setting of the rotor with the given index as a char
     * @param rotorIndex The index of the rotor to get the position of
     * @return The ring setting of the rotor
     */
    String getRingSetting (int rotorIndex) {
        return rotors[rotorIndex].getRingSettingString();
    }

    /**
     * Encode a letter press
     * @param letter The letter to encode
     * @return The encoded letter
     */
    char computeLetter(char letter) {
        char ret = letter;

        // Increment the rotors BEFORE ENCODING
        if (doubleStep) {
            rotors[2].increaseOffset();
            rotors[1].increaseOffset();
            doubleStep = false;
        }

        // Increase the rotor offset
        rotors[0].increaseOffset();
        if (rotors[0].turnoverNext()){
            // If we need to turn over the middle one, do so
            rotors[1].increaseOffset();

            // Check whether the middle rotor is two away from stepping, indicating middle rotor double step
            if (rotors[1].twoAway()) {
                // we need to double step next time

                // Double-step the middle rotor
                /*
                 * https://crypto.stackexchange.com/questions/71389/was-the-enigmas-double-stepping-mechanism-intentional
                 * https://www.cryptomuseum.com/crypto/enigma/working.htm
                 * http://www.intelligenia.org/downloads/rotors1.pdf
                 * https://en.wikipedia.org/wiki/Enigma_rotor_details
                 */
                doubleStep = true;
            }
        }

        // Ensure they don't step next time
        rotors[0].turnOffTurnoverNext();
        rotors[1].turnOffTurnoverNext();

        // Compute forwards
        ret = rotors[0].getLetterForwards(letter);
        ret = rotors[1].getLetterForwards(ret);
        ret = rotors[2].getLetterForwards(ret);

        // Reflect
        ret = reflector.getLetter(ret);

        // Compute backwards
        ret = rotors[2].getLetterBackwards(ret);
        ret = rotors[1].getLetterBackwards(ret);
        ret = rotors[0].getLetterBackwards(ret);

        //System.out.println("Machine entered with: " + letter);
        //System.out.println("Machine exited with:  " + ret);
        return ret;
    }


}
