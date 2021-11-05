package CS285Project;

import java.io.*;
import java.util.Scanner;

public class EncryptionAndDecryption {

    public static void main(String[] args) throws FileNotFoundException {
        ///User Prime Number input:
        Scanner pInput = new Scanner(System.in);
        System.out.println("Enter The First Prime Number: ");
        int p1 = pInput.nextInt();

        System.out.println("Enter The Second Prime Number: ");
        int p2 = pInput.nextInt();



        /*
            Encrypting a File:
        */
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        String input = "";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted.txt"));
            // Reading
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                input= input.concat(line) + "\r"; // "\r" -> Marks a new line in the string.
            }
            scan.close();
            input = input.substring(0, input.length() - 1); // To fix the over writing on the line
            writer.write(encrypt(input, p1, p2));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
            Decrypting The Encrypted file:
        */
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            file = new File("encrypted.txt");
            scan = new Scanner(file);
            String out = "";
            while (scan.hasNextLine()) { // Take one line at a time.
                String line = scan.nextLine();
                out = out.concat(line) + "\n";
            }
            scan.close();
            out = out.substring(0, out.length()-1);
            writer.write(decrypt(out, p1, p2));
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String encrypt(String line, int p1, int p2) {
        String encrypted = "";
        for (int i = 0; i < line.length(); i++) {
            int index = getAsciiIndex(line.charAt(i));
            int encryptionIndex = ((p1) * (index) + (p2)) % 128;
            encrypted = encrypted.concat(append(getAscii((encryptionIndex)))); // append -> converts char to string
        }
        return encrypted;
    }


    public static String decrypt(String line, int p1, int p2) {
        String decrypted = "";
        int alpha;
        int result;


        for (int i = 0; i < line.length(); i++) {
            int k = getAsciiIndex(line.charAt(i));
            result = -1;
            alpha = -1;
            while (result != 0) {
                alpha++;
                result = (k - (p1 * alpha + p2)) % 128;
            }

            decrypted = decrypted.concat(append(getAscii((alpha))));
        }
        return decrypted;
    }


    public static int getAsciiIndex(char c) {
        return c;
    }


    public static char getAscii(int index) {
        return (char) index;
    }


    public static String append(char chars) {
        StringBuilder s = new StringBuilder(1);
        s.append(chars);
        return s.toString();
    }
}
