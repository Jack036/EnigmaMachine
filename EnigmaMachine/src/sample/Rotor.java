package sample;

public class Rotor {

    // Defines what each letter maps to. In alphabetical order - A maps to the 1st element, B 2nd, etc.
    private char[] wiring = new char[26];

    // Rotor names and settings
    private String name;
    private String ringSettingString;

    // Rotor offset
    private int offset;
    private int chOff;

    // Rotor position
    private char position;

    // When to increment next rotor
    private int turnover1;
    private int turnover2;
    private int tt1 = 0;
    private int tt2 = 0;

    // Increment next rotor?
    private boolean turnoverNext = false;

    // Two away from increment? Used for double-stepping middle rotor
    private boolean twoAway;

    /**
     * Constructor for the rotor
     * @param wiringBpNotation The wiring string, using Bletchley Park notation
     * @param turnover1 The first point at which the next rotor turns over
     * @param turnover2 The second point at which the next rotor turns over
     * @param position The rotor position
     * @param ringSetting The rotor ring setting
     * @param rotorName The name of the rotor
     */
    Rotor(String wiringBpNotation, char turnover1, char turnover2, char position, char ringSetting, String rotorName) {
        this.name = rotorName;
        this.ringSettingString = Character.toString(ringSetting) + String.format("-%01d", ringSetting - 64);

        this.turnover1 = Character.toUpperCase(turnover1) - 65;
        this.turnover2 = Character.toUpperCase(turnover2) - 65;

        for (int i = 0; i < 26; i++) {
            wiring[i] = wiringBpNotation.charAt(i);
        }

        this.position = Character.toUpperCase(position);
        offset = position - 65;

        chOff = offset;

        // Ringstellung
        offset = (offset - (Character.toUpperCase(ringSetting) - 65)) % 26;
        if (offset < 0) {
            offset += 26;
        }

    }

    /**
     * Gets the letter going through the rotor from right to left
     * @param letter The input letter
     * @return The letter to pass left
     */
    char getLetterForwards(char letter) {
        char ret;

        //System.out.println("Forwards - letter uppercase: " + Character.toUpperCase(letter));
        //System.out.println("Forwards - letter int: " + (int) Character.toUpperCase(letter));

        // Offset the entry
        int index = (Character.toUpperCase(letter) - 65); // + offset) % 26;
        //System.out.println("Forwards - index1: " + index);

        index += offset;
        //System.out.println("Forwards - index2: " + index);

        // Get the index of the offset entry
        index %= 26;
        //System.out.println("Forwards - index1: " + index);




        // Offset the exit
        ret = (char) ((((wiring[index] - 65) - offset) % 26) + 65);

        // Modulo doesn't always work
        if (ret < 65) {
            ret += 26;
        }

        return ret;
    }

    /**
     * Gets the letter going through the rotor from left to right
     * @param letter The input letter
     * @return The letter to pass right
     */
    char getLetterBackwards(char letter) {
        // Remove the offset of this character
        char ret = 0;
        char l = (char) (((((Character.toUpperCase(letter)) - 65) + offset) % 26) + 65);

        // Find which index this maps to
        for (int i = 0; i < 26; i++) {
            if (wiring[i] == l) {

                // Modulo doesn't always work
                ret = (char) (((i - offset) % 26) + 65);

                if (ret < 65) {
                    ret += 26;
                }
                return ret;
            }
        }

        return '1';
    }

    /**
     * Turnover - Move the rotor by one place
     */
    void increaseOffset() {
        offset++;
        offset %= 26;

        chOff++;
        chOff %= 26;

        // Update position
        int posChange = (((int) position - 64) % 26);
        if (posChange < 0) {
            posChange += 26;
        }
        position = (char) (posChange + 65);

        turnoverNext =  (chOff == turnover1 || chOff == turnover2);
        twoAway = (chOff == tt1 || chOff == tt2);
    }

    /**
     * @return Whether we need to turn over the next rotor along to the left
     */
    boolean turnoverNext() {
        return turnoverNext;
    }

    /**
     * @return Whether we are two away from turning over
     */
    boolean twoAway() {
        return twoAway;
    }

    /**
     * Set this rotor as a middle rotor
     */
    void setMiddleRotor() {
        tt1 = (turnover1 - 1);
        if (tt1 < 0) {
            tt1 += 26;
        }

        tt2 = (turnover2 - 1);
        if (tt2 < 0) {
            tt2 += 26;
        }
    }

    /**
     * Forget that we need to turn over the next rotor, to avoid duplication
     */
    void turnOffTurnoverNext() {
        this.turnoverNext = false;
    }

    /**
     * @return The current position of the rotor
     */
    char getPosition () {
        return position;
    }

    /**
     * @return The name of the rotor
     */
    String getName () {
        return name;
    }

    /**
     * @return The current ring setting of the rotor
     */
    String getRingSettingString() {
        return ringSettingString;
    }
}
