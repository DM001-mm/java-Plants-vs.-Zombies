package src.entity.zombie;

import src.core.GameConfig;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class ConeheadZombie extends NormalZombie {

    public ConeheadZombie(int row, double x, double y) {
        super(
                row,
                x,
                y,
                GameConfig.CONEHEAD_ZOMBIE_HP,
                GameConfig.CONEHEAD_ZOMBIE_SPEED,
                GameConfig.CONEHEAD_ZOMBIE_DAMAGE
        );
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        if (canBeTargeted()) {
            drawConeFallback(g);
        }
    }

    private void drawConeFallback(Graphics2D g) {
        Color oldColor = g.getColor();

        int centerX = (int) x + width / 2;
        int topY = (int) y + 5;

        Polygon cone = new Polygon();
        cone.addPoint(centerX, topY);
        cone.addPoint(centerX - 14, topY + 35);
        cone.addPoint(centerX + 14, topY + 35);

        g.setColor(new Color(230, 120, 20));
        g.fillPolygon(cone);

        g.setColor(Color.BLACK);
        g.drawPolygon(cone);

        g.setColor(oldColor);
    }
}
