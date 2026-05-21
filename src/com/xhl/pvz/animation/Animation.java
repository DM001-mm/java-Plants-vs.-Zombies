package com.xhl.pvz.animation;

import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {

    private final List<BufferedImage> frames;

    /**
     * 每隔多少帧切换一次图片。
     * 比如 frameDelay = 5，表示游戏 update 5 次才切换下一张动画帧。
     */
    private final int frameDelay;

    /**
     * 是否循环播放。
     * 待机、走路动画一般循环。
     * 死亡动画一般不循环。
     */
    private final boolean loop;

    public Animation(List<BufferedImage> frames, int frameDelay, boolean loop) {
        if (frames == null || frames.isEmpty()) {
            throw new IllegalArgumentException("动画帧不能为空");
        }

        if (frameDelay <= 0) {
            throw new IllegalArgumentException("frameDelay 必须大于 0");
        }

        this.frames = frames;
        this.frameDelay = frameDelay;
        this.loop = loop;
    }

    public BufferedImage getFrame(int index) {
        return frames.get(index);
    }

    public int getFrameCount() {
        return frames.size();
    }

    public int getFrameDelay() {
        return frameDelay;
    }

    public boolean isLoop() {
        return loop;
    }
}
// 这里还没有播放,只是set了一些 动画的 属性