import java.io.FileWriter;
import java.io.IOException;

public class Image {
    private int width;
    private int height;
    // pixels[y][x][0=R,1=G,2=B]
    private int[][][] pixels; // pixels[y][x][0=R,1=G,2=B]

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    /**
     * Constructeur : initialise une image vide.
     */
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[height][width][3];
    }

    /**
     * Définit la couleur d'un pixel à la position (x, y)
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[y][x][0] = r;
            pixels[y][x][1] = g;
            pixels[y][x][2] = b;
        }
    }

    /**
     * Sauvegarde l'image au format texte PPM (P3)
     */
    public void save_txt(String filename) throws IOException {
        
        FileWriter writer = new FileWriter(filename);         
  
        writer.write("P3\n");
        writer.write(width + " ");
        writer.write(height + "\n");
        writer.write("255" + "\n");
        for (int compteur = 0; compteur < height; compteur++){
            for (int indice = 0; indice < width; indice++){
                writer.write(pixels[compteur][indice][0] + " ");
                writer.write(pixels[compteur][indice][1] + " ");
                writer.write(pixels[compteur][indice][2] + " ");
            }
            writer.write("\n");
        }

        writer.close();
    }

    /*
     * Sauvegarde de l'image au format raw (P5)
     */
    public void save_raw(String filename) throws IOException{
		FileWriter writer = new FileWriter(filename);         
  
        writer.write("P5\n");
        writer.write(width + " ");
        writer.write(height + "\n");
        writer.write("255" + "\n");
        for (int compteur = 0; compteur < height; compteur++){
            for (int indice = 0; indice < width; indice++){
		Byte b = new byte[3];
                writer.write(pixels[compteur][indice][0] + " ");
                writer.write(pixels[compteur][indice][1] + " ");
                writer.write(pixels[compteur][indice][2] + " ");
            }
            writer.write("\n");
        }

        writer.close();
    }
}
