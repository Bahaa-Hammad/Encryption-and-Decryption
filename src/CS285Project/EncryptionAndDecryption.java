package CS285Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EncryptionAndDecryption {

    public static void main(String[] args) {
        ArrayList<Character> arr = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    arr.add(getAscii(encrypt(c)));
                }
                arr.add(' ');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(arr.toString());
    }

    public static int encrypt(char c){
        int p1 = 7;
        int p2 = 3;
        int index = getAsciiIndex(c);
        int encryptionIndex = (p1 * index + p2) % 256;
        return encryptionIndex;
    }


    public static int getAsciiIndex(char c){
        int a = (int)c;
        return a;
    }


    public static char getAscii(int index){
        char x = (char) index;
        return x;
    }
}
