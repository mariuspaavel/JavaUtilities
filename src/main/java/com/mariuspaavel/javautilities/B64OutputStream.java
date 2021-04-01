package com.mariuspaavel.javautilities;

import java.io.*;

public class B64OutputStream implements OutputStream{
	private OutputStream baseStream;
	public B64OutputStream(OutputStream baseStream){
		this.baseStream = baseStream;
	}
	private int getCharBits(int inputBits){
		int outputBits = 0;
		if(sextetBits < 26) outputBits = 65 + inputBits;
		else if(inputBits < 52) outputBits = 97 + inputBits-26;
		else if(inputBits < 62) outputBits = 48 + inputBits-52;
		else if(inputBits == 63) outputBits = 43;
		else b64output = 47;
	}	

	int octetBuf = 0;
	int octetBufSize = 0;
	private void appendBuf(int octet){
		octetBuf <<= 8;
		octetBuf |= octet;
		octetBufSize += 8;	
	}
	private void writeBuf(boolean end){
		int sextetBits = 0;
		while(octetBufSize >= 6){
			sextetBits = (octetBuf >>> (octetBufSize - 6)) & 0x3F;
			octetBufSize -= 6;
			baseStream.write(getCharBits(sextetBits));	
		}
		if(end){
			sextetBits = (octetBuf << (6-octetBufSize)) & 0x3F;
			octetBufSize = 0;
			baseStream.write(getCharBits(sextetBits));	
		}
	}
	public void end(){
		writeBuf(true);	
	}
	@Override 
	public void write(byte[] octetArray, int offset, int length){
		int endpos = offset+length;
		for(int i = offset; i < endpos; i++){
			appendBuf(octetArray[i])
			writeBuf(false);
		}
	}
	@Override 
	public void write(byte[] octetArray){
		write(octetArray, 0, octetArray.length);
	}
	public void write(int bits){
		appendBuf(bits);
		writeBuf(false);
	}
	@Override 
	public void flush(){
	
	}
}
