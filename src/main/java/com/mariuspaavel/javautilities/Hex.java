package com.mariuspaavel.javautilities;

import java.io.PrintStream;

public class Hex {
    public static void print(byte[] bytes, PrintStream output){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < bytes.length; i++){
            int[] chars = new int[2];
            int b = bytes[i] & 0b0111_1111;
            if((bytes[i] & 0b1000_0000) != 0) b |= 0b1000_0000;
            chars[1] = b % 16;
            b /= 16;
            chars[0] = b % 16;
            for(int c : chars){
                char ch = 'x';
                switch(c){
                    case 0: ch = '0'; break;
                    case 1: ch = '1'; break;
                    case 2: ch = '2'; break;
                    case 3: ch = '3'; break;
                    case 4: ch = '4'; break;
                    case 5: ch = '5'; break;
                    case 6: ch = '6'; break;
                    case 7: ch = '7'; break;
                    case 8: ch = '8'; break;
                    case 9: ch = '9'; break;
                    case 10: ch = 'A'; break;
                    case 11: ch = 'B'; break;
                    case 12: ch = 'C'; break;
                    case 13: ch = 'D'; break;
                    case 14: ch = 'E'; break;
                    case 15: ch = 'F'; break;
                }
                output.append(ch);
            }
            output.append(' ');
            if((i+1) % 16 == 0)sb.append('\n');
        }
    }
}
