package sample;

public class Reflector {

    // Reflector name
    private String name;

    // Pairs to reflect between
    private String[] pairs = new String[13];

    /**
     * Constructor for Reflector
     * @param letterPairs The paired letter string with consecutive letters paired up - entered as e.g AYBRCU: (A-Y), (B-R), (C-U)
     * @param reflectorName The name of the reflector (e.g. B, B-THIN)
     */
    Reflector(String letterPairs, String reflectorName) {
        name = reflectorName;
        for(int i = 0; i < 13; i++) {
            pairs[i] = ((
                    String.valueOf(letterPairs.charAt(2*i))) +
                    (String.valueOf(letterPairs.charAt((2*i)+1)))).toUpperCase();
        }
    }

    /**
     * Reflects a letter
     * @param letter The letter to reflect
     * @return The reflected letter
     */
    char getLetter(char letter) {
        char l = Character.toUpperCase(letter);

        // Return the letter that this rotor wires to
        for(String pair : pairs) {
            if(pair.charAt(0) == l) {
                //System.out.println("I am a reflector. I received: " + letter + ". I outputted: " + pair.charAt(1));
                return pair.charAt(1);
            }
            if(pair.charAt(1) == l) {
                //System.out.println("I am a reflector. I received: " + letter + ". I outputted: " + pair.charAt(0));
                return pair.charAt(0);
            }
        }

        // If it's not a letter. This can be seen in the console as garbage
        return '1';
    }

    /**
     *
     * @return The name of the reflector
     */
    String getName() {
        return name;
    }
}
