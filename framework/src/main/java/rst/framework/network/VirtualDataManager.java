package rst.framework.network;

/**
 * Created by iceman on 16/4/7 17:12
 * 邮箱：xubin865@pingan.com.cn
 */
public class VirtualDataManager {
    private static VirtualDataConfig config = new VirtualDataConfig();

    public static boolean isUseAssetData() {
        return config.isAsset;
    }

    public static String getDataFolder() {
        return config.folderName;
    }

    public VirtualDataConfig getConfig() {
        return config;
    }

    public static void init(boolean isasset, String folder) {
        config.isAsset = isasset;
        config.folderName = folder;
    }

    public static class VirtualDataConfig {
        public boolean isAsset = true;
        public String folderName;
    }
}
