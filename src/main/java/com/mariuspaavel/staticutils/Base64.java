package com.mariuspaavel.staticutils;

public class Base64 {
	public static String encode(byte[] input) {
		int octetLen = input.length;
		int numOfOctetBlocks = octetLen / 3;
		int octetRemainder = octetLen % 3;
		
		int b64Remainder = octetRemainder;
		if(octetRemainder > 0)b64Remainder++;
		
		int b64StrLen = numOfOctetBlocks*4 + b64Remainder;
		
		byte[] output = new byte[b64StrLen];
		
		int octetBuf = 0;
		int octetBufSize = 0;
		
		for(int octetIndex = 0, b64index = 0; octetIndex < octetLen; octetIndex++) {
			octetBuf <<= 8;
			octetBuf |= input[octetIndex] & 0xFF;
			octetBufSize += 8;
			
			boolean lastOctet = octetIndex == octetLen-1;
			boolean wholeSextet;
			
			while((wholeSextet = octetBufSize >=6) || (lastOctet && octetBufSize > 0)) {
				int sextetBits;
				if(wholeSextet)sextetBits = (octetBuf >>> (octetBufSize - 6)) & 0x3F;
				else sextetBits = (octetBuf << (6-octetBufSize)) & 0x3F;
				
				octetBufSize -= 6;
				
				int b64output = 0;
				if(sextetBits < 26) b64output = 65 + sextetBits;
				else if(sextetBits < 52) b64output = 97 + sextetBits-26;
				else if(sextetBits < 62) b64output = 48 + sextetBits-52;
				else if(sextetBits == 62) b64output = 43;
				else b64output = 47;
				
				output[b64index++] = (byte)b64output;
			}
			
		}
		return new String(output);
	}
	
	public static byte[] decode(String input) {
		byte[] bytes = input.getBytes();
		int b64Len = bytes.length;
		
		int numOfBlocks = b64Len /4;
		int b64Remainder = b64Len % 4;
		
		int octetRemainder = b64Remainder;
		if (b64Remainder > 0)octetRemainder--;
		int octetLen = numOfBlocks * 3 + octetRemainder;
		byte[] octet = new byte[octetLen];
		
		int b64Buf = 0;
		int b64BufSize = 0;
		
		for(int b64Index = 0, octetIndex = 0; b64Index < b64Len; b64Index++) {
			int b64byte = bytes[b64Index];
			
			int b64bits = 0;
			if(b64byte >= 65 && b64byte < 91) b64bits = b64byte - 65;
			else if(b64byte >= 97 && b64byte < 123) b64bits = 26 + b64byte - 97;
			else if(b64byte >= 48 && b64byte < 58)b64bits = 52 + b64byte - 48;
			else if(b64byte == 43)b64bits = 62;
			else b64bits = 63;
			
			
			
			b64Buf <<= 6;
			b64Buf |= b64bits;
			b64BufSize += 6;
			
			while(b64BufSize >= 8) {
				int octetBits = (b64Buf >> (b64BufSize - 8)) & 0xFF;
				b64BufSize-=8;
				octet[octetIndex++] = (byte)octetBits;
	
			}
		}
		return octet;
	}
}
