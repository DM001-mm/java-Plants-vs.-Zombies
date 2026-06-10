package src.scene;

import src.core.GameConfig;
import src.core.SceneManager;
import src.manager.AudioManager;
import src.manager.ImageManager;
import src.resource.ImageKeys;
import src.ui.UIButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameOverScene extends BaseScene {

    private final SceneManager sceneManager;

    private BufferedImage background;
    private UIButton restartButton;
    private UIButton menuButton;
    private UIButton exitButton;

    public GameOverScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void onEnter() {
        if (ImageManager.hasImage(ImageKeys.BACKGROUND_GAME_OVER)) {
            background = ImageManager.getImage(ImageKeys.BACKGROUND_GAME_OVER);
        }

        restartButton = new UIButton(
                330,
                320,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_RESTART_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_RESTART_BUTTON)
                        : null);

        menuButton = new UIButton(
                330,
                400,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_MENU_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_MENU_BUTTON)
                        : null);

        exitButton = new UIButton(
                330,
                480,
                220,
                60,
                ImageManager.hasImage(ImageKeys.UI_EXIT_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_EXIT_BUTTON)
                        : null);

        AudioManager.playBGM("game_over");
    }
    @Override
    public void render(Graphics2D g) {
        if (background != null) {
            g.drawImage(
                    background,
                    0,
                    0,
                    GameConfig.WINDOW_WIDTH,
                    GameConfig.WINDOW_HEIGHT,
                    null
            );
        }

        restartButton.render(g);
        menuButton.render(g);
        exitButton.render(g);
        drawFallbackButtons(g);
    }
     @Override
    public void onMousePressed(int x, int y) {
        if (restartButton.contains(x, y)) {
            AudioManager.playEffect("click");
            sceneManager.changeScene(new LevelScene(sceneManager));
            return;
        }

        if (menuButton.contains(x, y)) {
            AudioManager.playEffect("click");
            sceneManager.changeScene(new MainMenuScene(sceneManager));
            return;
        }

        if (exitButton.contains(x, y)) {
            AudioManager.playEffect("click");
            System.exit(0);
        }
    }

    private void drawFallbackButtons(Graphics2D g) {
        if (!ImageManager.hasImage(ImageKeys.UI_RESTART_BUTTON)) {
            drawTextButton(g, restartButton, "再来一局");
        }

        if (!ImageManager.hasImage(ImageKeys.UI_MENU_BUTTON)) {
            drawTextButton(g, menuButton, "返回主菜单");
        }

        if (!ImageManager.hasImage(ImageKeys.UI_EXIT_BUTTON)) {
            drawTextButton(g, exitButton, "退出游戏");
        }
    }

    private void drawTextButton(Graphics2D g, UIButton button, String text) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        int x = button.getBounds().x;
        int y = button.getBounds().y;
        int width = button.getBounds().width;
        int height = button.getBounds().height;

        g.setColor(new Color(75, 45, 35, 220));
        g.fillRoundRect(x, y, width, height, 18, 18);

        g.setColor(new Color(245, 220, 130));
        g.drawRoundRect(x, y, width, height, 18, 18);

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 24));
        int textWidth = g.getFontMetrics().stringWidth(text);

        g.setColor(Color.WHITE);
        g.drawString(text, x + (width - textWidth) / 2, y + 38);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }
}
