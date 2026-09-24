 import java.lang.reflect.Array;

public class Utils {

    public static int writeInt(byte[] memory, int offset, int value) {
        memory[offset]     = (byte) (value >> 24);
        memory[offset + 1] = (byte) (value >> 16);
        memory[offset + 2] = (byte) (value >> 8);
        memory[offset + 3] = (byte) value;
		

        return 4;
    }

   public static int readInt(byte[] memory, int offset) {
		byte b1 = memory[offset];
		byte b2 = memory[offset + 1];
		byte b3 = memory[offset + 2];
		byte b4 = memory[offset + 3];

		int i1 = (b1 & 0xFF) << 24;
		int i2 = (b2 & 0xFF) << 16;
		int i3 = (b3 & 0xFF) << 8;
		int i4 = (b4 & 0xFF);

		int valeur = i1 | i2 | i3 | i4;

		return valeur;
	}

    public static int writeShort(byte[] memory, int offset, short value) {
		
		memory[offset] = (byte) (value >> 8); 
        memory[offset + 1] = (byte) value;
        
        return 2;
    }

    public static short readShort(byte[] memory, int offset) {
		byte b1 = memory[offset];
		byte b2 = memory[offset + 1];

		int i1 = (b1 & 0xFF) << 8;
		int i2 = (b2 & 0xFF);

		int valeur = i1 | i2;

		return (short) valeur;
	}
		
	public static int writeLong(byte[] memory, int offset, long value) {
		memory[offset]      = (byte) (value >> 56);
        memory[offset + 1] = (byte) (value >> 48);
        memory[offset + 2] = (byte) (value >> 40);
        memory[offset + 3] = (byte) (value >> 32);
		memory[offset + 4] = (byte) (value >> 24);
        memory[offset + 5] = (byte) (value >> 16);
        memory[offset + 6] = (byte) (value >> 8);
        memory[offset + 7] = (byte) value;
		
		return 8;
	}

	public static long readLong(byte[] memory, int offset) {
		byte b1 = memory[offset];
		byte b2 = memory[offset + 1];
		byte b3 = memory[offset + 2];
		byte b4 = memory[offset + 3];
		byte b5 = memory[offset + 4];
		byte b6 = memory[offset + 5];
		byte b7 = memory[offset + 6];
		byte b8 = memory[offset + 7];

		long l1 = ((long) b1 & 0xFF) << 56;
		long l2 = ((long) b2 & 0xFF) << 48;
		long l3 = ((long) b3 & 0xFF) << 40;
		long l4 = ((long) b4 & 0xFF) << 32;
		long l5 = ((long) b5 & 0xFF) << 24;
		long l6 = ((long) b6 & 0xFF) << 16;
		long l7 = ((long) b7 & 0xFF) << 8;
		long l8 = ((long) b8 & 0xFF);

		long valeur = l1 | l2 | l3 | l4 | l5 | l6 | l7 | l8;
		return valeur;
	}

	public static int writeString(
			byte[] memory,
			int offset,
			String str,
			int maxLength) {
				
		byte[] bytesStr = str.getBytes();
		int whoIsSmaller;
		
		whoIsSmaller = bytesStr.length < maxLength ? bytesStr.length : maxLength;
		
		for (int compteur = 0 ; compteur < whoIsSmaller ; compteur++){
			memory[offset + compteur] = bytesStr[compteur];
		}
		
		for (int compteur = whoIsSmaller ; compteur < maxLength ; compteur++){
			memory[offset + compteur] = 0;
		}

		return maxLength;
	}

	public static String readString(
			byte[] memory,
			int offset,
			int maxLength) {
				
		int length = 0;
		
		while ( length< maxLength && memory[offset + length] != 0) {
			length++;
		}
		
		String résultat= new String(memory, offset, length);


		return résultat;
	}
}
