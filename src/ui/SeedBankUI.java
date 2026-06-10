package src.ui;

import src.manager.ImageManager;
import src.resource.ImageKeys;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SeedBankUI {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private BufferedImage image;

    public SeedBankUI(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if (ImageManager.hasImage(ImageKeys.UI_SEED_BANK)) {
            image = ImageManager.getImage(ImageKeys.UI_SEED_BANK);
        }
    }

    public void render(Graphics2D g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            drawFallback(g);
        }
    }

    private void drawFallback(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(new Color(95, 62, 32));
        g.fillRoundRect(x, y, width, height, 18, 18);

        g.setColor(new Color(170, 120, 65));
        g.drawRoundRect(x, y, width, height, 18, 18);
        g.drawRoundRect(x + 2, y + 2, width - 4, height - 4, 14, 14);

        g.setColor(new Color(65, 42, 24));
        g.fillRoundRect(x + 10, y + 10, width - 20, height - 20, 12, 12);

        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 14));
        g.drawString("Seed Bank", x + 18, y + height - 14);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }
}
