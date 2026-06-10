package src.ui;

// 这个类的作用是 将原本输出到控制台的信息 显示在画面中
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class StatusMessageUI {
    private String message = "";
    private int remainTicks = 0; // 又是一个update没跑的了
    private final int x;
    private final int y;
    public StatusMessageUI(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void showMessage(String message) {
        showMessage(message, 90);
    }
    public void showMessage(String message, int durationTicks) {
        this.message = message;
        this.remainTicks = durationTicks;
    }
    public void update() {
        if (remainTicks > 0) {
            remainTicks--;
        }
    }
    public void render(Graphics2D g) {
        if (remainTicks <= 0 || message == null || message.isEmpty()) {
            return;
        }

        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setFont(new Font("微软雅黑", Font.BOLD, 24));

        g.setColor(new Color(0, 0, 0, 160));
        g.fillRoundRect(x - 20, y - 35, 260, 50, 20, 20);

        g.setColor(Color.WHITE);
        g.drawString(message, x, y);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }
}   
