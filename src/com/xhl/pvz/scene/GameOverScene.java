package com.xhl.pvz.scene;

import com.xhl.pvz.core.GameConfig;
import com.xhl.pvz.core.SceneManager;
import com.xhl.pvz.manager.AudioManager;
import com.xhl.pvz.manager.ImageManager;
import com.xhl.pvz.resource.ImageKeys;
import com.xhl.pvz.ui.UIButton;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameOverScene extends BaseScene {

    private final SceneManager sceneManager;

    private BufferedImage background;
    private UIButton restartButton;
    private UIButton menuButton;

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
                380,
                220,
                70,
                ImageManager.hasImage(ImageKeys.UI_RESTART_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_RESTART_BUTTON)
                        : null);

        menuButton = new UIButton(
                330,
                470,
                220,
                70,
                ImageManager.hasImage(ImageKeys.UI_MENU_BUTTON)
                        ? ImageManager.getImage(ImageKeys.UI_MENU_BUTTON)
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
        }
    }
}
