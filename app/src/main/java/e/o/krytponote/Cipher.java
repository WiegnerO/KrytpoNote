package e.o.krytponote;

public class Cipher {
    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Cipher(String k) {
        this.key = k;
    }

    private String makePad(String note) {
        String pad;
        // if key is the number 1234
        // rewrites 1234 over and over again until it has the same size or greater than the note
        for (pad = this.key; pad.length() < note.length(); pad += this.key) ;
        return pad;
    }

    public String encrypt(String note) {
        String pad = makePad(note);
        String result = "";
        for (int i = 0; i < note.length(); i++) {
            // takes the char in the note string at positon i
            String c = note.substring(i, i + 1);
            // figures out where the char is in the alphabet string
            int position = ALPHABET.indexOf(c);
            // finds the digit at index i of the pad string and then is converted into an int
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            /*
            where the positon of char c is in the alphabet string you add the shift to it
            if the newPosition is greater than the length of the string 26 then you mod it to keep
            it inbound
            */
            int newPosition = (position + shift) % ALPHABET.length();
            // the new char is found and then added on the encrpted note string
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    public String decrypt(String note) {
        String pad = makePad(note);
        String result = "";
        for (int i = 0; i < note.length(); i++) {
            String c = note.substring(i, i + 1);
            int position = ALPHABET.indexOf(c);
            int shift = Integer.parseInt(pad.substring(i, i + 1));
            /*
            to find the new char index in the alphabet string
            we subtrsact the inital char index in the alphabet string
            by the shift to get the value of the char index value
            */
            int newPosition = (position - shift);
            /* if the char index value is less than 1 we need to have this or else with
            will be out of bounds error */
            if (newPosition<0){
                newPosition = newPosition + ALPHABET.length();
            }
            result = result + ALPHABET.substring(newPosition, newPosition + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Cipher myModel = new Cipher("1234");
        String x = myModel.encrypt("XYZ");
        System.out.println(myModel.encrypt("XYZ"));
       System.out.println(myModel.decrypt(x));
    }
}


