package CS285Project;

import java.io.*;
import java.util.Scanner;

public class EncryptionAndDecryption {

    public static void main(String[] args) throws FileNotFoundException {
        //User Prime Number input:
        Scanner pInput = new Scanner(System.in);
        System.out.println("Enter The First Prime Number: ");
        int p1 = pInput.nextInt();

        System.out.println("Enter The Second Prime Number: ");
        int p2 = pInput.nextInt();



        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        String line = "";
        String encryptedLine;
        // The Encrypted File:
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted.txt"));
            while (scan.hasNextLine()) { // Take one line at a time.
                encryptedLine = encrypt(scan.nextLine(),p1,p2);
                writer.write(encryptedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Decrypting The Encrypted file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            file = new File("encrypted.txt");
            scan = new Scanner(file);

            while (scan.hasNextLine()) { // Take one line at a time.
                encryptedLine = scan.nextLine(); // Encrypted
                writer.write(decrypt(encryptedLine, p1, p2));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String encrypt(String line, int p1, int p2){
        char[] arr = line.toCharArray();
        char[] encryptedLine = new char[line.length()];
        for(int i = 0; i < arr.length; i++){
            int index = getAsciiIndex(arr[i]);
            int encryptionIndex = ((p1) * (index) + (p2)) % 128;
            encryptedLine[i] = getAscii(encryptionIndex);
        }
        return concat(encryptedLine);
    }


    public static String decrypt(String line, int p1, int p2){
        char[] arr = line.toCharArray();
        char[] decryptionLine = new char[line.length()];
        int alpha;
        int result;

        for (int i = 0; i < arr.length; i++) {
            int  k = getAsciiIndex(arr[i]);
            result = -1;
            alpha = -1;
            while (result != 0){
                alpha++;
                result = (k - (p1 * alpha + p2)) % 128;
            }

            decryptionLine[i] = getAscii((alpha));
            System.out.println(alpha);
        }
        return concat(decryptionLine);
    }


    public static int getAsciiIndex(char c){
        return c;
    }


    public static char getAscii(int index){
        return (char) index;
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
