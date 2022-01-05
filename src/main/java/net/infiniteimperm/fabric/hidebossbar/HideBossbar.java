package net.infiniteimperm.fabric.hidebossbar;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.loader.api.FabricLoader;
import net.infiniteimperm.fabric.hidebossbar.command.BossbarHideCommand;
import net.infiniteimperm.fabric.hidebossbar.command.BossbarShowCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;

public class HideBossbar implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("fabric-hide-bossbar");
    public static final File BASE_CONFIGURATION_FILE = Paths.get(FabricLoader.getInstance().getConfigDir().toFile().getAbsolutePath(), "imperm", "hide-bossbar", "base.config").toFile();

    private static HideBossbar INSTANCE = null;

    public static HideBossbar getInstance() {
        if (INSTANCE == null) INSTANCE = new HideBossbar();
        return INSTANCE;
    }

    private boolean hideBossbar;

    public HideBossbar() {
        this.hideBossbar = true;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("initializing fabric-hide-bossbar");
        try {
            Configuration.getInstance().loadBaseConfiguration();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        ClientCommandManager.DISPATCHER.register(literal("imperm").then(literal("bossbar").then(literal("hide").executes(new BossbarHideCommand()))));
        ClientCommandManager.DISPATCHER.register(literal("imperm").then(literal("bossbar").then(literal("show").executes(new BossbarShowCommand()))));
    }

    public boolean isHideBossbar() {
        return hideBossbar;
    }

    public void hide() {
        hideBossbar = true;
    }

    public void show() {
        hideBossbar = false;
    }
}
