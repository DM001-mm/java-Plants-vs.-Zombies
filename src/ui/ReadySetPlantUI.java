package src.ui;

import src.core.GameConfig;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ReadySetPlantUI {

    private int tick = 0;

    private static final int READY_DURATION = 60;
    private static final int SET_DURATION = 60;
    private static final int PLANT_DURATION = 80;

    private static final int TOTAL_DURATION =
            READY_DURATION + SET_DURATION + PLANT_DURATION;

    public void update() {
        if (!isFinished()) {
            tick++;
        }
    }

    public void render(Graphics2D g) {
        if (isFinished()) {
            return;
        }

        String text = getCurrentText();

        if (text == null || text.isEmpty()) {
            return;
        }

        drawText(g, text);
    }

    private String getCurrentText() {
        if (tick < READY_DURATION) {
            return "Ready...";
        }

        if (tick < READY_DURATION + SET_DURATION) {
            return "Set...";
        }

        return "PLANT!";
    }

    private void drawText(Graphics2D g, String text) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        Font font = new Font("Microsoft YaHei", Font.BOLD, 64);
        g.setFont(font);

        int textWidth = g.getFontMetrics().stringWidth(text);

        int x = (GameConfig.WINDOW_WIDTH - textWidth) / 2;
        int y = GameConfig.WINDOW_HEIGHT / 2;

        g.setColor(Color.BLACK);
        g.drawString(text, x + 4, y + 4);

        if ("PLANT!".equals(text)) {
            g.setColor(new Color(255, 230, 80));
        } else {
            g.setColor(Color.WHITE);
        }

        g.drawString(text, x, y);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    public boolean isFinished() {
        return tick >= TOTAL_DURATION;
    }

    public void skip() {
        tick = TOTAL_DURATION;
    }

    public void reset() {
        tick = 0;
    }
}
