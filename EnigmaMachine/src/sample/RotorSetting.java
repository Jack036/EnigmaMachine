package sample;

class RotorSetting {

    // Default rotor settings
    private final String[] ROTORI    = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ", "R", "R"};
    private final String[] ROTORII   = {"AJDKSIRUXBLHWTMCQGZNPYFVOE", "F", "F"};
    private final String[] ROTORIII  = {"BDFHJLCPRTXVZNYEIWGAKMUSQO", "W", "W"};
    private final String[] ROTORIV   = {"ESOVPZJAYQUIRHXLNFTGKDCMWB", "K", "K"};
    private final String[] ROTORV    = {"VZBRGITYUPSDNHLXAWMJQOFECK", "A", "A"};
    private final String[] ROTORVI   = {"JPGVOUMFYQBENHZRDKASXLICTW", "A", "N"};
    private final String[] ROTORVII  = {"NZJHGRCXMYSWBOUFAIVLPEKQDT", "A", "N"};
    private final String[] ROTORVIII = {"FKQHTLXOCBJSPDZRAMEWNIUYGV", "A", "N"};
    private final String[][] rotorsIndex = {ROTORI, ROTORII, ROTORIII, ROTORIV, ROTORV, ROTORVI, ROTORVII, ROTORVIII};
    private final String[] rotorNames = {"ROTORI", "ROTORII", "ROTORIII", "ROTORIV", "ROTORV", "ROTORVI", "ROTORVII", "ROTORVIII"};

    // Default reflector settings
    private final String REFLECTORB  = "AYBRCUDHEQFSGLIPJXKNMOTZVW";
    private final String REFLECTORBT = "AEBNCKDQFUGYHWIJLOMPRXSZTV";
    private final String REFLECTORC  = "AFBVCPDJEIGOHYKRLZMXNWTQSU";
    private final String REFLECTORCT = "ARBDCOEJFNGTHKIVLMPWQZSXUY";

    private Rotor[] rotors= new Rotor[3];
    private Reflector reflector;

    /**
     * Default constructor
     * Will load the default rotor and reflector settings
     */
    RotorSetting () {
        // 2 is leftmost
        // 0 is rightmost
        // 0, 1, 2, ref, 2, 1, 0.

        rotorBuilder(1, 'A', 'A', 2, 'A', 'A', 3, 'A', 'A', 'B', false);
    }

    /**
     * Manual Rotor Setup - Define the strings and reflector for custom, self-made rotors
     * @param rotorStringL Left rotor string
     * @param turnoverL1 Left rotor first turnover
     * @param turnoverL2 Left rotor second turnover
     * @param positionL Left ring position
     * @param ringSettingL Left ring setting
     * @param rotorStringM Middle rotor string
     * @param turnoverM1 Middle rotor first turnover
     * @param turnoverM2 Middle rotor second turnover
     * @param positionM Middle rotor position
     * @param ringSettingM Middle rotor ring setting
     * @param rotorStringR Right rotor string
     * @param turnoverR1 Right rotor first turnover
     * @param turnoverR2 Right rotor second turnover
     * @param positionR Right rotor position
     * @param ringSettingR Right rotor ring setting
     * @param reflectorPairs Reflector string
     */
    RotorSetting (String rotorStringL, char turnoverL1, char turnoverL2, char positionL, char ringSettingL, String rotorStringM, char turnoverM1, char turnoverM2, char positionM, char ringSettingM, String rotorStringR, char turnoverR1, char turnoverR2, char positionR, char ringSettingR, String reflectorPairs) {
        // 2 is leftmost
        // 0 is rightmost
        // 0, 1, 2, ref, 2, 1, 0.

        rotors[0] = new Rotor(rotorStringR, turnoverR1, turnoverR2, positionR, ringSettingR, "ROTORIII");
        rotors[1] = new Rotor(rotorStringM, turnoverM1, turnoverM2, positionM, ringSettingM, "ROTORII");
        rotors[2] = new Rotor(rotorStringL, turnoverL1, turnoverL2, positionL, ringSettingL, "ROTORI");

        rotors[1].setMiddleRotor();

        reflector = new Reflector(reflectorPairs, "REFLECTOR-B");
    }

    /**
     * Guided rotor settings, using rotor indices
     * @param rotorLindex Left rotor index
     * @param positionL Left rotor position
     * @param ringSettingL Left rotor ring setting
     * @param rotorMindex Middle rotor index
     * @param positionM Middle rotor position
     * @param ringSettingM Middle rotor ring setting
     * @param rotorRindex Right rotor index
     * @param positionR Right rotor position
     * @param ringSettingR Right rotor ring setting
     * @param reflectorIndex Reflector index
     * @param reflectorThin Reflector is thin
     */
    RotorSetting (int rotorLindex, char positionL, char ringSettingL, int rotorMindex, char positionM, char ringSettingM, int rotorRindex, char positionR, char ringSettingR, char reflectorIndex, boolean reflectorThin) {
       rotorBuilder(rotorLindex, ringSettingL, positionL, rotorMindex, ringSettingM, positionM, rotorRindex, ringSettingR, positionR, reflectorIndex, reflectorThin);
    }

    /**
     * Build the desired rotor
     * @param rotorLindex Left rotor index
     * @param positionL Left rotor position
     * @param ringSettingL Left rotor ring setting
     * @param rotorMindex Middle rotor index
     * @param positionM Middle rotor position
     * @param ringSettingM Middle rotor ring setting
     * @param rotorRindex Right rotor index
     * @param positionR Right rotor position
     * @param ringSettingR Right rotor ring setting
     * @param reflectorIndex Reflector index
     * @param reflectorThin Reflector is thin
     */
    private void rotorBuilder(int rotorLindex, char ringSettingL, char positionL, int rotorMindex, char ringSettingM, char positionM, int rotorRindex, char ringSettingR, char positionR, char reflectorIndex, boolean reflectorThin) {
        // 2 is leftmost
        // 0 is rightmost
        // 0, 1, 2, ref, 2, 1, 0.

        String[] rL = rotorsIndex[rotorLindex - 1];
        String[] rM = rotorsIndex[rotorMindex - 1];
        String[] rR = rotorsIndex[rotorRindex - 1];

        rotors[0] = new Rotor(rR[0], rR[1].charAt(0), rR[2].charAt(0),positionR, ringSettingR, rotorNames[rotorRindex - 1]);
        rotors[1] = new Rotor(rM[0], rM[1].charAt(0), rM[2].charAt(0),positionM, ringSettingM, rotorNames[rotorMindex - 1]);
        rotors[2] = new Rotor(rL[0], rL[1].charAt(0), rL[2].charAt(0),positionL, ringSettingL, rotorNames[rotorLindex - 1]);

        if(reflectorIndex == 'C') {
            reflector = (reflectorThin ?
                    new Reflector(REFLECTORCT, "REFLECTOR-C THIN") :
                    new Reflector(REFLECTORC, "REFLECTOR-C"));

        } else {
            reflector = (reflectorThin ?
                    new Reflector(REFLECTORBT, "REFLECTOR-B THIN") :
                    new Reflector(REFLECTORB, "REFLECTOR-B"));
        }

        rotors[1].setMiddleRotor();
    }

    /**
     * Set an individual rotor
     * @param index Index to set
     * @param rotorString Rotor string
     * @param turnover1 First turnover position
     * @param turnover2 Second turnover position
     * @param position Ring position
     * @param ringSetting Ring setting
     * @param rotorName Name of the rotor
     */
    void setRotor(
            int index,
            String rotorString,
            char turnover1, char turnover2,
            char position,
            char ringSetting,
            String rotorName) {
        this.rotors[index] = new Rotor(rotorString, turnover1, turnover2, position, ringSetting, rotorName);


        if(index == 1) {
            rotors[1].setMiddleRotor();
        }
    }

    /**
     * Set the reflector
     * @param reflectorPairs Reflector string
     * @param reflectorName Name of the reflector
     */
    void setReflector(String reflectorPairs, String reflectorName) {
        reflector = new Reflector(reflectorPairs, reflectorName);
    }

    /**
     * Get the rotor at the index
     * @param index Index of the rotor to get
     * @return The rotor
     */
    Rotor getRotor(int index) {
        return rotors[index];
    }

    /**
     * Get the reflector
     * @return the reflector
     */
    Reflector getReflector() {
        return reflector;
    }

}
