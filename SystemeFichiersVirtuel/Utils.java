import java.lang.reflect.Array;

public class Utils {

    public static int writeInt(byte[] memory, int offset, int value) {
        // TODO: Écrire les 4 octets de 'value' dans 'memory'
        // à partir de 'offset', en big-endian.
        String valueString = Integer.toString(value);
        memory[offset] = Array.getByte(memory, value >>> 24);
        memory[offset + 1] = Array.getByte(memory, value >>> 16);
        memory[offset + 2] = Array.getByte(memory, value >>> 8);
        memory[offset + 3] = Array.getByte(memory, value);
	
        
	return 4;
    }

    public static int readInt(byte[] memory, int offset) {
        // TODO: Reconstituer le int sur 4 octets.
        return 0;
    }

    public static int writeShort(byte[] memory, int offset, short value) {
        // TODO: Écrire les 2 octets de 'value'.
        return 2;
    }

    public static short readShort(byte[] memory, int offset) {
        // TODO: Lire le short sur 2 octets.
        return 0;
    }
}
