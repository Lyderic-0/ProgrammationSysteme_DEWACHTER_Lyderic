public class VirtualFileSystem {

    private MemoryManager memoryManager;

    public VirtualFileSystem() {
        this.memoryManager = new MemoryManager();
    }

    private int allocateInode() {
        for (int compteur = 0; compteur < MemoryManager.MAX_INODES; compteur++) {
            Inode inode = new Inode(memoryManager, compteur);
            if (inode.getFileType() == 0) {
                return compteur;
            }
        }
        return -1;
    }

    public boolean createFile(String directory, String filename) {
        int inodeNum = allocateInode();

        if (inodeNum == -1) {
            return false;
        }

        Inode inode = new Inode(memoryManager, inodeNum);
        inode.writeToMemory(1,0, 0L,0L, new int[Inode.DIRECT_POINTERS], 0,(short) 0644,1);

        return true;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }
	

    public boolean writeFile(int inodeNum, byte[] data) {
        int blocksNeeded = (data.length + MemoryManager.BLOCK_SIZE - 1) / MemoryManager.BLOCK_SIZE;

        if (blocksNeeded > Inode.DIRECT_POINTERS) {
            return false;
        }

        int[] blockPointers = new int[Inode.DIRECT_POINTERS];

        for (int compteur = 0; compteur < blocksNeeded; compteur++) {
            int blockNum = memoryManager.allocateBlock();
            if (blockNum == -1) {
                return false;
            }
            blockPointers[compteur] = blockNum;
        }

        byte[] memory = memoryManager.getFilesystemMemory();
        int bytesRemaining = data.length;
        int dataSrcOffset = 0;

        for (int compteur = 0; compteur < blocksNeeded; compteur++) {
            int bytesToCopy = (bytesRemaining < MemoryManager.BLOCK_SIZE) ? bytesRemaining : MemoryManager.BLOCK_SIZE;
            int blockOffset = blockPointers[compteur] * MemoryManager.BLOCK_SIZE;

            System.arraycopy(data, dataSrcOffset, memory, blockOffset, bytesToCopy);

            dataSrcOffset += bytesToCopy;
            bytesRemaining -= bytesToCopy;
        }

        Inode inode = new Inode(memoryManager, inodeNum);
        inode.writeToMemory(1, data.length, 0L, 0L, blockPointers, 0, (short) 0644, 1);

        return true;
    }

    public byte[] readFile(int inodeNum) {
        Inode inode = new Inode(memoryManager, inodeNum);
        int fileSize = inode.getFileSize();

        if (fileSize == 0) {
            return new byte[0];
        }

        byte[] fileData = new byte[fileSize];
        byte[] memory = memoryManager.getFilesystemMemory();
        int[] blockPointers = inode.getDirectPointers();

        int bytesRemaining = fileSize;
        int destOffset = 0;
        int blockIndex = 0;

        while (bytesRemaining > 0 && blockIndex < Inode.DIRECT_POINTERS) {
            int bytesToCopy = (bytesRemaining < MemoryManager.BLOCK_SIZE) ? bytesRemaining : MemoryManager.BLOCK_SIZE;
            int blockOffset = blockPointers[blockIndex] * MemoryManager.BLOCK_SIZE;

            System.arraycopy(memory, blockOffset, fileData, destOffset, bytesToCopy);

            destOffset += bytesToCopy;
            bytesRemaining -= bytesToCopy;
            blockIndex++;
        }

        return fileData;
    }
}