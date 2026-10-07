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
}