package src.manager;

import src.save.SaveData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveManager {

    private static final String SAVE_DIR = "resources/saves";

    private SaveManager() {
    }
    // 直接读写操作
    public static void save(SaveData saveData, String fileName) {
        if (saveData == null) {
            throw new IllegalArgumentException("saveData 不能为 null");
        }

        File dir = new File(SAVE_DIR);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, fileName);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(saveData);
            System.out.println("存档成功: " + file.getPath());
        } catch (Exception e) {
            throw new RuntimeException("存档失败: " + file.getPath(), e);
        }
    }

    public static SaveData load(String fileName) {
        File file = new File(SAVE_DIR, fileName);

        if (!file.exists()) {
            System.out.println("存档文件不存在: " + file.getPath());
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            SaveData saveData = (SaveData) in.readObject();
            System.out.println("读档成功: " + file.getPath());
            return saveData;
        } catch (Exception e) {
            throw new RuntimeException("读档失败: " + file.getPath(), e);
        }
    }
}