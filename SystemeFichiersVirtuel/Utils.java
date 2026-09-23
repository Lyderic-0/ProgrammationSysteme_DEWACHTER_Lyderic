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
        // TODO: Lire le short sur 2 octets.
        return 0;
    }
}
