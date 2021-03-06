package com.mariuspaavel.javautilities;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class NumberSerializer {

	public static boolean byteArrayToBoolean(byte[] bytes) {
		return bytes[0] != 0;
	}
	public static boolean readBoolean(InputStream stream) throws IOException{
		return stream.read() != 0;
	}
	public static byte[] booleanToByteArray(boolean value) {
		return new byte[] {value ? (byte)1 : (byte)0};
	}
	public static void writeBoolean(boolean value, OutputStream stream) throws IOException {
		stream.write(value ? 1 : 0);
	}
	

	
	public static byte byteArrayToByte(byte[] bytes) {
		return bytes[0];
	}
	public static byte readByte(InputStream stream) throws IOException{
		return (byte)stream.read();
	}
	public static byte[] byteToByteArray(byte value) {
		return new byte[] {value};
	}
	public static void writeByte(byte value, OutputStream stream) throws IOException{
		stream.write(value & 0xFF);
	}
	

	
	public static int byteArrayToShort(byte[] bytes) {
		return ((bytes[0] & 0xFF) << 8 ) |
				(bytes[1] & 0xFF);
	}
	public static short readShort(InputStream stream) throws IOException {
		short output = 0;
		for(int byteI = 1; byteI >= 0; byteI--) {
			output |= stream.read() << byteI*8;
		}
		return output;
	}
	public static byte[] shortToByteArray(short value) {
	    return new byte[] {
	            (byte)(value >>> 8),
	            (byte)value};
	}
	public static void writeShort(short value, OutputStream stream) throws IOException{
		for(int byteI = 0; byteI < 2; byteI++) {
			stream.write((byte)(value >>> byteI*8));
		}
	}



	public static int byteArrayToInt(byte[] bytes) {
		return ((bytes[0] & 0xFF) << 24) |
				((bytes[1] & 0xFF) << 16) |
				((bytes[2] & 0xFF) << 8 ) |
				((bytes[3] & 0xFF) << 0 );
	}
	public static int readInt(InputStream stream) throws IOException{
		int output = 0;
		for(int byteI = 3; byteI >= 0; byteI--) {
			output |= stream.read() << byteI*8;
		}
		return output;
	}
	
	public static byte[] intToByteArray(int value) {
	    return new byte[] {
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
	}

	public static void writeInt(int value, OutputStream stream) throws IOException{
		for(int byteI = 0; byteI < 4; byteI++) {
			stream.write((byte)(value >>> byteI*8));
		}
	}
	


	public static long byteArrayToLong(byte[] bytes) {
		return ((bytes[0] & 0xFFl) << 56) |
				((bytes[1] & 0xFFl) << 48) |
				((bytes[2] & 0xFFl) << 40) |
				((bytes[3] & 0xFFl) << 32) |
				((bytes[4] & 0xFFl) << 24) |
				((bytes[5] & 0xFFl) << 16) |
				((bytes[6] & 0xFFl) << 8 ) |
				((bytes[7] & 0xFFl) << 0 );
	}
	public static int readLong(InputStream stream) throws IOException{
		int output = 0;
		for(int byteI = 7; byteI >= 0; byteI--) {
			output |= stream.read() << byteI*8;
		}
		return output;
	}
	
	public static byte[] longToByteArray(long value) {
	    return new byte[] {
	    		(byte)(value >>> 56),
	    		(byte)(value >>> 48),
	    		(byte)(value >>> 40),
	    		(byte)(value >>> 32),
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
	}

	public static void writeLong(long value, OutputStream stream) throws IOException{
		for(int byteI = 0; byteI < 8; byteI++) {
			stream.write((byte)(value >>> byteI*8));
		}
	}
	
	
	public static float byteArrayToFloat(byte[] bytes) {
		return Float.intBitsToFloat(byteArrayToInt(bytes));
	}
	public static float readFloat(InputStream stream) throws IOException {
		return Float.intBitsToFloat(readInt(stream));
	}
	public static byte[] floatToByteArray(float value) {
		return intToByteArray(Float.floatToIntBits(value));
	}
	public static void writeFloat(float value, OutputStream stream) throws IOException {
		writeInt(Float.floatToIntBits(value), stream);
	}
	
	
	public static float byteArrayToDouble(byte[] bytes) {
		return Double.doubleToLongBits(byteArrayToLong(bytes));
	}
	public static double readDouble(InputStream stream) throws IOException{
		return Double.longBitsToDouble(readLong(stream));
	}
	public static byte[] doubleToByteArray(double value) {
		return longToByteArray(Double.doubleToLongBits(value));
	}
	public static void writeDouble(double value, OutputStream stream) throws IOException {
		writeLong(Double.doubleToLongBits(value), stream);
	}
	
}
