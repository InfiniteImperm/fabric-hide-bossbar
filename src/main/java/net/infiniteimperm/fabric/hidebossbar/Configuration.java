package net.infiniteimperm.fabric.hidebossbar;


import java.io.*;

public class Configuration {

    private static Configuration INSTANCE = null;

    public static Configuration getInstance() {
        if (INSTANCE == null) INSTANCE = new Configuration();
        return INSTANCE;
    }

    public void saveBaseConfiguration() throws IOException {
        if (!HideBossbar.BASE_CONFIGURATION_FILE.getParentFile().isDirectory()
                && !HideBossbar.BASE_CONFIGURATION_FILE.getParentFile().mkdirs()
                && !HideBossbar.BASE_CONFIGURATION_FILE.exists()
                && !HideBossbar.BASE_CONFIGURATION_FILE.createNewFile())
            throw new IOException();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HideBossbar.BASE_CONFIGURATION_FILE))) {
            writer.write("hide: " + HideBossbar.getInstance().isHideBossbar());
        } catch (IOException e) {
            HideBossbar.LOGGER.error("hide-bossbar: couldn't safe base configuration");
            HideBossbar.LOGGER.error(e);
        }
    }

    public void loadBaseConfiguration() throws IOException {
        if (!HideBossbar.BASE_CONFIGURATION_FILE.getParentFile().isDirectory()
                && !HideBossbar.BASE_CONFIGURATION_FILE.getParentFile().mkdirs()
                && !HideBossbar.BASE_CONFIGURATION_FILE.exists()
                && !HideBossbar.BASE_CONFIGURATION_FILE.createNewFile())
            throw new IOException();
        try (BufferedReader reader = new BufferedReader(new FileReader(HideBossbar.BASE_CONFIGURATION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!"".equals(line)) {
                    String[] split = line.split(":");
                    if (split.length == 2) {
                        switch (split[0]) {
                            case "hide":
                                if ("true".equals(split[1].trim()))
                                    HideBossbar.getInstance().hide();
                                else
                                    HideBossbar.getInstance().show();
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

}
