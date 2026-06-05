package com.xhl.pvz.ui;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class CardBarUI {
    private final List<PlantCard> cards = new ArrayList<>();

    public void addCard(PlantCard card) {
        if (card != null) {
            cards.add(card);
        }
    }
    public void update() {
        for (PlantCard card : cards) {
            card.update();
        }
    }
    public void render(Graphics2D g) {
        for (PlantCard card : cards) {
            card.render(g);
        }
    }
    public PlantCard getClickedCard(int x, int y) {
        for (PlantCard card : cards) {
            if (card.contains(x, y)) {
                return card;
            }
        }

        return null;
    }
    public void clearSelection() {
            for (PlantCard card : cards) {
                card.setSelected(false);
            }
    }

    public List<PlantCard> getCards() {
        return cards;
    }
    
}
