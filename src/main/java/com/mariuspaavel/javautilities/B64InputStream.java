package com.mariuspaavel.javautilities;
import java.io.*;

/**
*Reads base64 encoded data as octets.
* The source for base64 characters must be argumented to the constructor
*/

public class B64InputStream implements InputStream{
	private InputStream source;
	/**
	*Create and instance of the InputStream.
	*
	*/
	public B64InputStream(InputStream source){
		this.source = source;
	}
	private int translateBits(int input{

		int b64bits = 0;
		if(b64byte >= 65 && b64byte < 91) b64bits = b64byte - 65;
		else if(b64byte >= 97 && b64byte < 123) b64bits = 26 + b64byte - 97;
		else if(b64byte >= 48 && b64byte < 58)b64bits = 52 + b64byte - 48;
		else if(b64byte == 43)b64bits = 62;
		else b64bits = 63;
		
		return b64bits;	
	}
	private int buf;
	private int bufSize;
	private void fillBuf(){
		int rawInput = source.read();
		int translatedBits = translateBits(rawInput);
		buf <<= 6;
		buf |= translatedBits;
		bufSize += 6;	
	}
	int readBuf(){
		return (buf >> (bufSize - 8)) & 0xFF;
	}
	@Override
	public int read(){
		if(buflen < 6)fillBuf();
		readBuf();
	}
	@Override
	public void read(byte[] b, int off, int len){
		for(int i = off; i < off+len; i++){
			b[i] = read();
		}
	}
	@Override
	public void read(byte[] b){
		read(b, 0, b.length);
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
	public void reset(){
		source.reset();
		buf = markBuf;
		bufSize = markBufSize;
	}
}
