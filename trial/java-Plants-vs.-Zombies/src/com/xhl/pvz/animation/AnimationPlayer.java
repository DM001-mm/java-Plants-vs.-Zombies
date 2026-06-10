package com.xhl.pvz.animation;

import java.awt.image.BufferedImage;

public class AnimationPlayer {

    private Animation animation;

    private int currentFrameIndex = 0;
    private int tick = 0;
    private boolean finished = false;

    public AnimationPlayer(Animation animation) {
        setAnimation(animation);
    }

    public void setAnimation(Animation newAnimation) {
        if (newAnimation == null) {
            return;
        }

        if (this.animation == newAnimation) {
            return;
        }

        this.animation = newAnimation;
        this.currentFrameIndex = 0;
        this.tick = 0;
        this.finished = false;
    }

    public void update() {
        if (animation == null || finished) {
            return;
        }

        tick++; // 这个是为了实现动画帧更新的间隔的

        if (tick < animation.getFrameDelay()) {
            return;
        }
        // 这种写法 很不错, 代码组织能力太强了,很简洁 清晰
        tick = 0;
        currentFrameIndex++;

        if (currentFrameIndex >= animation.getFrameCount()) {
            if (animation.isLoop()) {
                currentFrameIndex = 0;
            }else {
                currentFrameIndex = animation.getFrameCount() - 1;
                finished = true;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        if (animation == null) {
            return null;
        }

        return animation.getFrame(currentFrameIndex);
    }

    public boolean isFinished() {
        return finished;
    }

    public void reset() {
        currentFrameIndex = 0;
        tick = 0;
        finished = false;
    }
}