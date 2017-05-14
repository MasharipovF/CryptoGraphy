package com.example.farrukh.labs;


import android.util.Log;

import java.util.Locale;

/**
 * Created by Farrukh on 13.05.2017.
 */


public class RC4Engine {

    private int[] key;
    private int[] keySchedule;
    private int x, y;


    public RC4Engine(String key) {
        int i = 0;
        this.key = new int[key.length()];
        while (i < key.length()) {
            this.key[i] = (int) key.charAt(i++);
        }
        x = 0;
        y = 0;
        KSA();
    }


    public byte[] encode(byte[] dataB) {

        byte[] data = dataB;
        byte[] cipher = new byte[data.length];
        int keyyy;

        for (int m = 0; m < data.length; m++) {
            keyyy = keyItem();
            cipher[m] = (byte) (data[m] ^ keyyy);
            Log.d("SCHEDULE", data[m] + " + " + keyyy + "   CIPHER  " + cipher[m]);
        }

        return cipher;
    }

    public byte[] decode(byte[] data) {
        return encode(data);
    }

    private int keyItem() // PRGA
    {
        int swapper;
        x = (x + 1) % 256;
        y = (y + keySchedule[x]) % 256;


        swapper = keySchedule[x];
        keySchedule[x] = keySchedule[y];
        keySchedule[y] = swapper;

        return keySchedule[(keySchedule[x] + keySchedule[y]) % 256];
    }

    private void KSA() {
        int keyLength = key.length;
        keySchedule = new int[256];
        for (int i = 0; i < 256; i++) {
            keySchedule[i] = i;
            //Log.d("SCHEDULE", "KEY = " + keySchedule[i]);
        }

        int j = 0;
        int swapper;
        for (int i = 0; i < 256; i++) {
            j = (j + keySchedule[i] + key[i % keyLength]) % 256;
            swapper = keySchedule[i];
            keySchedule[i] = keySchedule[j];
            keySchedule[j] = swapper;
            Log.d("SCHEDULE", "SWAPPED = " + keySchedule[i] + "  WITH  " + keySchedule[j] + "   INDEXES " + i);
        }
    }

}