package demo_4;

public enum SoundType {
    // 跟github的写法
    /*
        ZOMBIE("sound/zombie.mp3");
     */
    ZOMBIE("E:\\植物大战僵尸\\sound\\zombie.mp3"),
    SHOOT("E:\\植物大战僵尸\\sound\\shoot.mp3"),
    HIT("E:\\植物大战僵尸\\sound\\hit.mp3");

    private final String path;

    SoundType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}