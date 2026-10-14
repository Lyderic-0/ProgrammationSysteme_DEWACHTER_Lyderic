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
        
        return Utils.readInt(memory, offset);
    }

    public int getFileSize() {
        byte[] memory = memoryManager.getFilesystemMemory();
        int offset = getInodeOffset() + 8;
        
        return Utils.readInt(memory, offset);
    }

    public int[] getDirectPointers() {
    byte[] memory = memoryManager.getFilesystemMemory();
    int[] pointers = new int[DIRECT_POINTERS];

    int startOffset = getInodeOffset() + 28;

    for (int compteur = 0; compteur < DIRECT_POINTERS; compteur++) {
        pointers[compteur] = Utils.readInt(memory, startOffset + (compteur * 4));
    }

    return pointers;
}
	
	public void writeToMemory(
        int fileType,
        int fileSize,
        long creationTime,
        long modificationTime,
        int[] directPointers,
        int indirectPointer,
        short permissions,
        int linkCount) {

    byte[] memory = memoryManager.getFilesystemMemory();
    int cursor = getInodeOffset();

    // 0..3
    cursor += Utils.writeInt(memory, cursor, this.inodeNumber);

    // 4..7
    cursor += Utils.writeInt(memory, cursor, fileType);

    // 8..11
    cursor += Utils.writeInt(memory, cursor, fileSize);

    // 12..19
    cursor += Utils.writeLong(memory, cursor, creationTime);

    // 20..27
    cursor += Utils.writeLong(memory, cursor, modificationTime);

    // 28..67
    for (int compteur = 0; compteur < DIRECT_POINTERS; compteur++) {
        int ptr = (directPointers != null && compteur < directPointers.length) ? directPointers[compteur] : 0;
        cursor += Utils.writeInt(memory, cursor, ptr);
    }

    // 68..71
    cursor += Utils.writeInt(memory, cursor, indirectPointer);

    // 72..73
    cursor += Utils.writeShort(memory, cursor, permissions);

    // 74..77
    cursor += Utils.writeInt(memory, cursor, linkCount);
}
}