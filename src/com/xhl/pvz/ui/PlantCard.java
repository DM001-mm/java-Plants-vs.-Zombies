package com.xhl.pvz.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PlantCard {
    private final String plantType;
    
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private final int cost;

    private final int cooldown; // 冷却时间 ， 单位 先按 帧来计算
    private int currentCooldown =0;

    private BufferedImage image;
    private BufferedImage cooldownMask;

    private boolean selected = false;

    public PlantCard(String plantType,int x,int y,int width,int height,int cost,int cooldown,BufferedImage image,BufferedImage cooldownMask){
        this.plantType = plantType;
        this.x=x;
        this.y=y;
        this.width = width;
        this.height = height;
        this.cost =cost;
        this.cooldown = cooldown;
        this.image= image;
        this.cooldownMask=cooldownMask;
    }

    public void update(){
        if(currentCooldown>0){
            currentCooldown--;
        }
    }

    public void render(Graphics2D g){
        if(image !=null){
            g.drawImage(image,x,y,width,height,null);
        }
        
        drawCooldownMask(g);
        drawCost(g);
        drawSelectedBorder(g);
    }

    private void drawCooldownMask(Graphics2D g){
        if(!isCoolingDown()){
            return ;
        }
        double ratio =(double) currentCooldown/cooldown;
        int maskHeight = (int)(height*ratio);
        
        if(cooldownMask!=null){
            g.drawImage(cooldownMask,x,y,width,maskHeight,null);
        }else {
            Color oldColor =g.getColor();
            g.setColor(new Color(0,0,0,120));
            g.fillRect(x,y,width,maskHeight);
            g.setColor(oldColor);
        }
    }

    private void drawCost(Graphics2D g) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        int costBoxHeight = 22;
        int costBoxY = y + height - costBoxHeight;

        g.setColor(new Color(245, 220, 120, 220));
        g.fillRect(x, costBoxY, width, costBoxHeight);

        g.setColor(Color.BLACK);
        g.drawRect(x, costBoxY, width, costBoxHeight);

        String costText = String.valueOf(cost);

        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
        int textWidth = g.getFontMetrics().stringWidth(costText);

        int textX = x + (width - textWidth) / 2;
        int textY = costBoxY + 16;

        g.setColor(Color.BLACK);
        g.drawString(costText, textX, textY);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    private void drawSelectedBorder(Graphics2D g){
        if(!selected){
            return ;
        }

        Color oldColor = g.getColor();

        g.setColor(Color.YELLOW);
        g.drawRect(x,y,width,height);
        g.drawRect(x+1,y+1,width-2,height-2);

        g.setColor(oldColor);
    }

    public boolean contains(int mouseX,int mouseY){
        return getBounds().contains(mouseX,mouseY);
    }

    public void startCooldown(){
        currentCooldown = cooldown;
    }
    
    public boolean isCoolingDown(){
        return currentCooldown>0;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }

    public String getPlantType(){
        return plantType;
    }
    public int getCost(){
        return cost;
    }
    public int getCooldown(){
        return cooldown;
    }
    public int getCurrentCooldown(){
        return currentCooldown;
    }
    public void setSelected(boolean selected){
        this.selected =selected;
    }
    public boolean isSelected(){
        return selected;
    }
    public boolean canUse(int currentSun) {
        return currentSun >= cost && !isCoolingDown();
    }
}
