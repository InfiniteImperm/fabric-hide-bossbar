package net.infiniteimperm.fabric.hidebossbar.command;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.infiniteimperm.fabric.hidebossbar.Configuration;
import net.infiniteimperm.fabric.hidebossbar.HideBossbar;
import net.minecraft.text.LiteralText;

import java.io.IOException;

public class BossbarShowCommand implements com.mojang.brigadier.Command<net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource> {

    @Override
    public int run(CommandContext<FabricClientCommandSource> context) {
        HideBossbar.getInstance().show();
        try {
            Configuration.getInstance().saveBaseConfiguration();
        } catch (IOException e) {
            HideBossbar.LOGGER.error(e);
            context.getSource().sendError(new LiteralText("§4could not save base configuration. see log for more information."));
        }
        return 0;
    }
}
