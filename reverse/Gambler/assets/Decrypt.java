/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Random;

public class Decrypt {
    private static final int[] CIPHER = {216, 72, 26, 238, 199, 85, 141, 94, 159, 249, 
                                         193, 77, 46, 251, 180, 19, 191, 114, 192, 189, 
                                         193, 88, 62, 161, 242, 62, 209, 79, 172, 164, 
                                         174, 117, 4, 166, 238, 81, 149, 91, 155, 183};

    public static void main(String[] args) {
        Random var2 = new Random(2025L);
        int[] var4 = new int[10];

        for (int i = 0; i < 10; i++) {
            var4[i] = var2.nextInt(256);
        }

        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < CIPHER.length; i++) {
            decrypted.append((char) ((CIPHER[i] ^ var4[i % 10]) & 255));
        }

        System.out.println(decrypted.toString());
    }
}

