package com.mariuspaavel.javautilities;

import java.io.*;

public class B64OutputStream implements OutputStream{
	iprivate OutputStream baseStream;
	public B64OutputStream(OutputStream baseStream){
		this.baseStream = baseStream;
	}
	int octetBuf = 0;
	int octetBufSize = 0;
	private void appendBuf(){
		octetBuf <<= 8;
		octetBuf |= octetArray[i];
		octetBufSize += 8;	
	}
	private void flushBuf(boolean end){
		while(octetBufSize >= 6){
			int sextetBits = (octetBuf >>> (octetBufSize - 6)) & 0x3F;
			octetBufSize -= 6;

			int b64output = 0;
			if(sextetBits < 26) b64output = 65 + sextetBits;
			else if(sextetBits < 52) b64output = 97 + sextetBits-26;
			else if(sextetBits < 62) b64output = 48 + sextetBits-52;
			else if(sextetBits == 62) b64output = 43;
			else b64output = 47;
			
			baseStream.write(b64Output);	
		}
	}
	@Override 
	public void write(byte[] octetArray, int offset, int length){
		int endpos = offset+length;
		for(int i = offset; i < endpos; i++){
			octetBuf <<= 8;
			octetBuf |= octetArray[i];
			octetBufSize += 8;
			while(octetBufSize >= 6){
				int sextetBits = (octetBuf >>> (octetBufSize - 6)) & 0x3F;
				octetBufSize -= 6;

				int b64output = 0;
				if(sextetBits < 26) b64output = 65 + sextetBits;
				else if(sextetBits < 52) b64output = 97 + sextetBits-26;
				else if(sextetBits < 62) b64output = 48 + sextetBits-52;
				else if(sextetBits == 62) b64output = 43;
				else b64output = 47;
				
				baseStream.write(b64Output);	
			}
		}
	}
	@Override 
	public void flush(){
			
	}
}
