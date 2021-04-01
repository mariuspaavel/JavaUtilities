package com.mariuspaavel.javautilities;
import java.io.*;

/**
*Reads base64 encoded data as octets.
* The source for base64 characters must be argumented to the constructor
*/

public class B64InputStream extends InputStream{
	private InputStream source;
	/**
	*Create and instance of the InputStream.
	*
	*/
	public B64InputStream(InputStream source){
		this.source = source;
	}
	private int translateBits(int input){

		int b64bits = 0;
		if(input >= 65 && input < 91) b64bits = input - 65;
		else if(input >= 97 && input < 123) b64bits = 26 + input - 97;
		else if(input >= 48 && input < 58)b64bits = 52 + input - 48;
		else if(input == 43)b64bits = 62;
		else b64bits = 63;
		
		return b64bits;	
	}
	private int buf;
	private int bufSize;
	private boolean fillBuf() throws IOException{
		int rawInput = source.read();
		if(rawInput == -1) return false;
		int translatedBits = translateBits(rawInput);
		buf <<= 6;
		buf |= translatedBits;
		bufSize += 6;	
		return true;
	}
	int readBuf(){
		return (buf >> (bufSize - 8)) & 0xFF;
	}
	@Override
	public int read() throws IOException{
		if(bufSize < 6 && fillBuf())return readBuf();
		else return -1;
	}
	@Override
	public int read(byte[] b, int off, int len) throws IOException{
		int i = off;
		while(true){
			int bits = read();
			if(bits == -1)return off-i;
			b[i] = (byte)read();
			if(i >= off+len)return off-i;
			i++;
		}
	}
	@Override
	public int read(byte[] b) throws IOException{
		return read(b, 0, b.length);
	}
	private int markBuf;
	private int markBufSize;

	@Override
	public void mark(int readlimit){
		source.mark(readlimit*3/4);
		markBuf = buf;
		markBufSize = bufSize;
	}
	@Override
	public boolean markSupported(){
		return source.markSupported();
	}
	@Override
	public void reset() throws IOException{
		source.reset();
		buf = markBuf;
		bufSize = markBufSize;
	}
	@Override
	public int available() throws IOException{
		return source.available()*3/4;
	}
}
