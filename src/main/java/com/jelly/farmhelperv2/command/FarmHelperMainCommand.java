package com.jelly.farmhelperv2.command;

import com.jelly.farmhelperv2.FarmHelper;
import com.jelly.farmhelperv2.config.FarmHelperConfig;
import com.jelly.farmhelperv2.handler.GameStateHandler;
import com.jelly.farmhelperv2.pathfinder.FlyPathFinderExecutor;
import com.jelly.farmhelperv2.util.LogUtils;
import com.jelly.farmhelperv2.util.PlayerUtils;
import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

import java.util.Optional;
import java.util.Set;

public class FarmHelperMainCommand extends Command {

    public FarmHelperMainCommand() {
        super("fh");
    }

    @Nullable
    @Override
    public Set<Alias> getCommandAliases() {
        return Collections.singleton(new Alias("farmhelper"));
    }

    @DefaultHandler
    public void handle() {
        FarmHelper.config.openGui();
    }

}
