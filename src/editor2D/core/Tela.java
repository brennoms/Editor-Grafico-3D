package editor2D.core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Tela extends JPanel implements Runnable {

    private final int largura = 800;
    private final int altura = 600;

    // imagem atualmente exibida
    private BufferedImage frontBuffer;

    // imagem onde desenhamos
    private BufferedImage backBuffer;

    private int[] pixels;

    public Tela() {

        frontBuffer = criarBuffer();
        backBuffer = criarBuffer();

        pixels = getPixels(backBuffer);

        new Thread(this).start();
    }

    private BufferedImage criarBuffer() {

        return new BufferedImage(
                largura,
                altura,
                BufferedImage.TYPE_INT_ARGB
        );
    }

    private int[] getPixels(BufferedImage img) {

        return ((DataBufferInt)
                img.getRaster()
                        .getDataBuffer())
                .getData();
    }

    private void renderizar() {

        // desenha no back buffer
        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                int r = x % 256;
                int g = y % 256;
                int b = 100;

                int cor =
                        (255 << 24) |
                                (r << 16) |
                                (g << 8) |
                                b;

                pixels[y * largura + x] = cor;
            }
        }
    }

    private void trocarBuffers() {

        BufferedImage temp = frontBuffer;
        frontBuffer = backBuffer;
        backBuffer = temp;

        // atualiza referência dos pixels
        pixels = getPixels(backBuffer);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(frontBuffer, 0, 0, null);
    }

    @Override
    public void run() {

        while (true) {

            renderizar();

            trocarBuffers();

            repaint();

            try {
                Thread.sleep(16);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Double Buffer");

        frame.add(new Tela());

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
