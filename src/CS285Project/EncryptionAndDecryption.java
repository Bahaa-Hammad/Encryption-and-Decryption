package CS285Project;

import java.io.*;

import java.util.Scanner;

public class EncryptionAndDecryption {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        String line = "";
        String encryptLine;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            while (scan.hasNextLine()) {
                encryptLine = encrypt(line.concat(""+ scan.nextLine()));
                writer.write(decrypt(encryptLine));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted.txt"));
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                encryptLine = encrypt(line.concat(""+ scan.nextLine()));
                writer.write(encryptLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String encrypt(String line){
        int p1 = 7;
        int p2 = 3;
        char[] arr = line.toCharArray();
        char[] encryptedLine = new char[line.length()];
        for(int i = 0; i < arr.length; i++){
            int index = getAsciiIndex(arr[i]);
            int encryptionIndex = ((p1) * (index) + (p2)) % 256;
            encryptedLine[i] = getAscii(encryptionIndex);
        }
        return concat(encryptedLine);
    }


    public static String decrypt(String line){
        char[] arr = line.toCharArray();
        char[] decryptionLine = new char[line.length()];
        int alpha;
        int p1 = 7, p2 = 3;
        int result;


        for (int i = 0; i < arr.length; i++) {
            int  k = getAsciiIndex(arr[i]);
            result = -1;
            alpha = -1;
            while (result != 0){
                alpha++;
                result = (k - (p1 * alpha + p2)) % 256;
            }

            decryptionLine[i] = getAscii((alpha));
        }
        return concat(decryptionLine);
    }


    public static int getAsciiIndex(char c){
        int a = c;
        return a;
    }


    public static char getAscii(int index){
        char x = (char) index;
        return x;
    }


    public static String concat(char[] chars) {
        if (chars.length == 0) {
            return "";
        }
        StringBuilder s = new StringBuilder(chars.length);
        for (char c : chars) {
            s.append(c);
        }
        return s.toString();
    }
}
