public class Inode {

    private MemoryManager memoryManager;
    private int inodeNumber;

    public static final int INODE_SIZE = 128;
    public static final int DIRECT_POINTERS = 10;

    public Inode(
            MemoryManager memoryManager,
            int inodeNumber) {

        this.memoryManager = memoryManager;
        this.inodeNumber = inodeNumber;
    }

    public int getInodeOffset() {
        return MemoryManager.INODE_TABLE_OFFSET + (this.inodeNumber * INODE_SIZE);
    }

    public int getFileType() {
        byte[] memory = memoryManager.getFilesystemMemory();
        int offset = getInodeOffset() + 4;
        
        return ByteBuffer.wrap(memory).getInt(offset);
    }

    public int getFileSize() {
        byte[] memory = memoryManager.getFilesystemMemory();
        int offset = getInodeOffset() + 8;
        
        return ByteBuffer.wrap(memory).getInt(offset);
    }

    public int[] getDirectPointers() {
        byte[] memory = memoryManager.getFilesystemMemory();
        int[] pointers = new int[DIRECT_POINTERS];

        int startOffset = getInodeOffset() + 12;
        ByteBuffer buffer = ByteBuffer.wrap(memory);

        for (int i = 0; i < DIRECT_POINTERS; i++) {
            pointers[i] = buffer.getInt(startOffset + (i * Integer.BYTES));
        }

        return pointers;
    }
}