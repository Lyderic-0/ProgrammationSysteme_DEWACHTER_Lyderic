public class Gradient {
    public static void main(String[] args) {
        Image img = new Image(200, 100);

        // Génération du dégradé de bleu
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int bleu = (255 * x) / (img.getWidth() - 1);
                img.setPixel(x, y, 0, 0, bleu);
            }
        }

        try {
            img.save_txt("gradient.ppm");
			img.save_raw("gradientByte.ppm");
            System.out.println("Dégradé créé avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du dégradé : " + e.getMessage());
        }
    }
}