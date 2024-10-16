package com.jelly.farmhelperv2.config;

import com.google.gson.Gson;
import com.jelly.farmhelperv2.FarmHelper;
import com.jelly.farmhelperv2.config.page.CustomFailsafeMessagesPage;
import com.jelly.farmhelperv2.config.page.FailsafeNotificationsPage;
import com.jelly.farmhelperv2.config.struct.Rewarp;
import com.jelly.farmhelperv2.failsafe.Failsafe;
import com.jelly.farmhelperv2.failsafe.FailsafeManager;
import com.jelly.farmhelperv2.failsafe.impl.BedrockCageFailsafe;
import com.jelly.farmhelperv2.failsafe.impl.DirtFailsafe;
import com.jelly.farmhelperv2.feature.impl.*;
import com.jelly.farmhelperv2.feature.impl.Proxy.ProxyType;
import com.jelly.farmhelperv2.handler.GameStateHandler;
import com.jelly.farmhelperv2.handler.GameStateHandler.BuffState;
import com.jelly.farmhelperv2.handler.MacroHandler;
import com.jelly.farmhelperv2.util.BlockUtils;
import com.jelly.farmhelperv2.util.LogUtils;
import com.jelly.farmhelperv2.util.PlayerUtils;
import com.jelly.farmhelperv2.util.helper.AudioManager;
import gg.essential.api.EssentialAPI;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class FarmHelperConfig extends Vigilant {
    
    protected final transient Gson gson = new Gson();
    private transient static final Minecraft mc = Minecraft.getMinecraft();
    private transient static final String GENERAL = "General";
    private transient static final String MISCELLANEOUS = "Miscellaneous";
    private transient static final String FAILSAFE = "Failsafe";
    private transient static final String SCHEDULER = "Scheduler";
    private transient static final String JACOBS_CONTEST = "Jacobs Contest";
    private transient static final String VISITORS_MACRO = "Visitors Macro";
    private transient static final String PESTS_DESTROYER = "Pests Destroyer";
    private transient static final String AUTO_PEST_EXCHANGE = "Auto Pest Exchange";
    private transient static final String AUTO_GOD_POT = "Auto God Pot";
    private transient static final String AUTO_SELL = "Auto Sell";
    private transient static final String AUTO_REPELLANT = "Auto Repellant";
    private transient static final String AUTO_SPRAYONATOR = "Auto Sprayonator";
    private transient static final String DISCORD_INTEGRATION = "Discord Integration";
    private transient static final String DELAYS = "Delays";
    private transient static final String DEBUG = "Debug";
    private transient static final String EXPERIMENTAL = "Experimental";
    private transient static final File configRewarpFile = new File("farmhelper_rewarp.json");


    public static List<Rewarp> rewarpList = new ArrayList<>();

    public static boolean proxyEnabled = false;
    public static String proxyAddress = "";
    public static String proxyUsername = "";
    public static String proxyPassword = "";
    public static ProxyType proxyType = ProxyType.HTTP;

    @Property(
            type = PropertyType.SWITCH,
            name = "Streamer Mode",
            description = "Hides everything Farm Helper related from the screen.",
            category = GENERAL
    )
    public static boolean streamerMode = false;

    @Property(type = PropertyType.SELECTOR,
            name = "Macro Type", category = GENERAL,
            description = "Farm Types",
            options = {
                    "S Shape / Vertical - Crops (Wheat, Carrot, Potato, NW)", // 0
                    "S Shape - Pumpkin/Melon", // 1
                    "S Shape - Pumpkin/Melon Melongkingde", // 2
                    "S Shape - Pumpkin/Melon Default Plot", // 3
                    "S Shape - Sugar Cane", // 4
                    "S Shape - Cactus", // 5
                    "S Shape - Cactus SunTzu Black Cat", // 6
                    "S Shape - Cocoa Beans", // 7
                    "S Shape - Cocoa Beans (With Trapdoors)", // 8
                    "S Shape - Cocoa Beans (Left/Right)", // 9
                    "S Shape - Mushroom (45°)", // 10
                    "S Shape - Mushroom (30° with rotations)", // 11
                    "S Shape - Mushroom SDS", // 12
                    "Circle - Crops (Wheat, Carrot, Potato, NW)" // 13
            }
    )
    public static int macroType = 0;

    @Property(
            type = PropertyType.SWITCH,
            name = "Always hold W while farming", category = GENERAL,
            description = "Always hold W while farming"
    )
    public static boolean alwaysHoldW = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Rotate After Warped", category = GENERAL, subcategory = "Rotation",
            description = "Rotates the player after re-warping"
    )
    public static boolean rotateAfterWarped = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Rotate After Drop", category = GENERAL, subcategory = "Rotation",
            description = "Rotates after the player falls down"
    )
    public static boolean rotateAfterDrop = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Don not fix micro rotations after warp", category = GENERAL, subcategory = "Rotation",
            description = "The macro doesn not do micro-rotations after rewarp if the current yaw and target yaw are the same"
    )
    public static boolean dontFixAfterWarping = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Custom Pitch", category = GENERAL, subcategory = "Rotation",
            description = "Set pitch to custom level after starting the macro"
    )
    public static boolean customPitch = false;
    @Property(
            type = PropertyType.NUMBER,
            name = "Custom Pitch Level", category = GENERAL, subcategory = "Rotation",
            description = "Set custom pitch level after starting the macro",
            min = -90, max = 90
    )
    public static int customPitchLevel = 0;

    @Property(
            type = PropertyType.SWITCH,
            name = "Custom Yaw", category = GENERAL, subcategory = "Rotation",
            description = "Set yaw to custom level after starting the macro"
    )
    public static boolean customYaw = false;

    @Property(
            type = PropertyType.NUMBER,
            name = "Custom Yaw Level", category = GENERAL, subcategory = "Rotation",
            description = "Set custom yaw level after starting the macro",
            min = -180, max = 180
    )
    public static int customYawLevel = 0;
    @Property(
            type = PropertyType.SWITCH,
            name = "Highlight rewarp points", category = GENERAL, subcategory = "Rewarp",
            description = "Highlights all rewarp points you have added"
    )
    public static boolean highlightRewarp = true;

    @Property(type = PropertyType.BUTTON, 
            name = "Add Rewarp", category = GENERAL, subcategory = "Rewarp",
            description = "Adds a rewarp position"
    )
    public void _addRewarp() {
        FarmHelperConfig.addRewarp();
    }

    @Property(type = PropertyType.BUTTON, 
            name = "Remove Rewarp", category = GENERAL, subcategory = "Rewarp",
            description = "Removes a rewarp position"
    )
    public void _removeRewarp() {
        FarmHelperConfig.removeRewarp();
    }

    @Property(type = PropertyType.BUTTON, 
            name = "Remove All Rewarps", category = GENERAL, subcategory = "Rewarp",
            description = "Removes all rewarp positions"
    )
    public void _removeAllRewarps() {
        FarmHelperConfig.removeAllRewarps();
    }
    @Property(
            type = PropertyType.NUMBER,
            name = "SpawnPos X", category = GENERAL, subcategory = "Spawn Position",
            description = "The X coordinate of the spawn",
            min = -30000000, max = 30000000

    )
    public static int spawnPosX = 0;
    @Property(
            type = PropertyType.NUMBER,
            name = "SpawnPos Y", category = GENERAL, subcategory = "Spawn Position",
            description = "The Y coordinate of the spawn",
            min = -30000000, max = 30000000
    )
    public static int spawnPosY = 0;
    @Property(
            type = PropertyType.NUMBER,
            name = "SpawnPos Z", category = GENERAL, subcategory = "Spawn Position",
            description = "The Z coordinate of the spawn",
            min = -30000000, max = 30000000
    )
    public static int spawnPosZ = 0;

    @Property(type = PropertyType.BUTTON, 
            name = "Set SpawnPos", category = GENERAL, subcategory = "Spawn Position",
            description = "Sets the spawn position to your current position"
    )
    public void _setSpawnPos() {
        PlayerUtils.setSpawnLocation();   
    }
    
    @Property(type = PropertyType.BUTTON, 
            name = "Reset SpawnPos", category = GENERAL, subcategory = "Spawn Position",
            description = "Resets the spawn position"
    )
    public void _resetSpawnPos() {
        spawnPosX = 0;
        spawnPosY = 0;
        spawnPosZ = 0;
        LogUtils.sendSuccess("Spawn position has been reset!");
    }

    @Property(
            type = PropertyType.SWITCH,
            name = "Draw spawn location", category = GENERAL, subcategory = "Drawings",
            description = "Draws the spawn location"
    )
    public static boolean drawSpawnLocation = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Automatically choose a tool to destroy the block", category = MISCELLANEOUS, subcategory = "Plot Cleaning Helper",
            description = "Automatically chooses the best tool to destroy the block"
    )
    public static boolean autoChooseTool = false;

    public static boolean autoUpdaterDownloadBetaVersions = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Mute The Game", category = MISCELLANEOUS, subcategory = "Miscellaneous",
            description = "Mutes the game while farming"
    )
    public static boolean muteTheGame = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Cookie", category = MISCELLANEOUS, subcategory = "Miscellaneous",
            description = "Automatically purchases and consumes a booster cookie"
    )
    public static boolean autoCookie = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Hold left click when changing row", category = MISCELLANEOUS, subcategory = "Miscellaneous",
            description = "Hold left click when change row"
    )
    public static boolean holdLeftClickWhenChangingRow = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Ungrab Mouse", category = MISCELLANEOUS, subcategory = "Miscellaneous",
            description = "Automatically ungrabs your mouse, so you can safely alt-tab"
    )
    public static boolean autoUngrabMouse = true;
    @Property(
            type = PropertyType.SLIDER,
            name = "Anti Stuck Tries Until Rewarp", category = MISCELLANEOUS, subcategory = "Miscellaneous",
            description = "The number of tries until rewarp",
            min = 3, max = 10
    )
    public static int antiStuckTriesUntilRewarp = 5;

    @Property(
            type = PropertyType.SWITCH,
            name = "Performance Mode", category = MISCELLANEOUS, subcategory = "Performance Mode",
            description = "Set render distance to 2, set max fps to 15 and doesn not render crops"
    )
    public static boolean performanceMode = false;

    @Property(
            type = PropertyType.SWITCH,name = "Fast Render", category = MISCELLANEOUS, subcategory = "Performance Mode",
            description = "Using new fast render method to increase performance"
    )
    public static boolean fastRender = true;

    @Property(
            type = PropertyType.NUMBER,
            name = "Max FPS", category = MISCELLANEOUS, subcategory = "Performance Mode",
            description = "The maximum FPS to set when performance mode is enabled",
            min = 10, max = 60
    )
    public static int performanceModeMaxFPS = 20;

    @Property(
            type = PropertyType.SWITCH,
            name = "Increase Cocoa Hitboxes", category = MISCELLANEOUS, subcategory = "Crop Utils",
            description = "Allows you to farm cocoa beans more efficiently at higher speeds by making the hitboxes bigger"
    )
    public static boolean increasedCocoaBeans = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Increase Crop Hitboxes", category = MISCELLANEOUS, subcategory = "Crop Utils",
            description = "Allows you to farm crops more efficient by making the hitboxes bigger"
    )
    public static boolean increasedCrops = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Increase Nether Wart Hitboxes", category = MISCELLANEOUS, subcategory = "Crop Utils",
            description = "Allows you to farm nether warts more efficiently at higher speeds by making the hitboxes bigger"
    )
    public static boolean increasedNetherWarts = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Increase Mushroom Hitboxes", category = MISCELLANEOUS, subcategory = "Crop Utils",
            description = "Allows you to farm mushrooms more efficiently at higher speeds by making the hitboxes bigger"
    )
    public static boolean increasedMushrooms = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Pingless Cactus", category = MISCELLANEOUS, subcategory = "Crop Utils",
            description = "Allows you to farm cactus more efficiently at higher speeds by making the cactus pingless"
    )
    public static boolean pinglessCactus = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Send analytic data", category = MISCELLANEOUS, subcategory = "Analytics",
            description = "Sends analytic data to the server to improve the macro and learn how to detect staff checks"
    )
    public static boolean sendAnalyticData = true;
    @Property(
            type = PropertyType.SWITCH,name = "Pop-up Notifications", category = FAILSAFE, subcategory = "General",
            description = "Enable on-screen failsafe notifications")
    public static boolean popUpNotifications = true;

    @Property(
            type = PropertyType.SLIDER,name = "Failsafe Stop Delay", category = FAILSAFE, subcategory = "General",
            description = "Delay before stopping macro after failsafe (ms)",
            min = 1000, max = 7500)
    public static int failsafeStopDelay = 2000;

    // Automatic Actions
    @Property(
            type = PropertyType.SWITCH,name = "Auto Warp on World Change", category = FAILSAFE, subcategory = "Auto Actions",
            description = "Warp to garden after server reboot or update, disconnects if disabled")
    public static boolean autoWarpOnWorldChange = true;

    @Property(
            type = PropertyType.SWITCH,name = "Auto Evacuate on Server Reboot", category = FAILSAFE, subcategory = "Auto Actions",
            description = "Leave island during server reboot or update")
    public static boolean autoEvacuateOnServerReboot = true;

    @Property(
            type = PropertyType.SWITCH,name = "Auto Reconnect", category = FAILSAFE, subcategory = "Auto Actions",
            description = "Automatically reconnect after disconnect")
    public static boolean autoReconnect = true;

    @Property(
            type = PropertyType.SWITCH,name = "Pause on Guest Arrival", category = FAILSAFE, subcategory = "Auto Actions",
            description = "Pause macro when a guest joins your island")
    public static boolean pauseOnGuestArrival = false;

    // Detection Sensitivity
    @Property(
            type = PropertyType.SLIDER, name = "Teleport Lag Tolerance", category = FAILSAFE, subcategory = "Detection",
            description = "Variation in distance between expected and actual positions when lagging",
            min = 0, max = 2)
    public static int teleportLagTolerance = 1;

    @Property(
            type = PropertyType.SLIDER,name = "Detection Time Window", category = FAILSAFE, subcategory = "Detection",
            description = "Time frame for teleport/rotation checks (ms)",
            min = 50, max = 40000)
    public static int detectionTimeWindow = 500;

    @Property(
            type = PropertyType.SLIDER,name = "Pitch Sensitivity", category = FAILSAFE, subcategory = "Detection",
            description = "Pitch change sensitivity (lower = stricter)",
            min = 1, max = 30)
    public static int pitchSensitivity = 7;

    @Property(
            type = PropertyType.SLIDER,name = "Yaw Sensitivity", category = FAILSAFE, subcategory = "Detection",
            description = "Yaw change sensitivity (lower = stricter)",
            min = 1, max = 30)
    public static int yawSensitivity = 5;

    @Property(
            type = PropertyType.SLIDER,name = "Teleport Distance Threshold", category = FAILSAFE, subcategory = "Detection",
            description = "Minimum teleport distance to trigger failsafe (blocks)",
            min = 0, max = 20)
    public static int teleportDistanceThreshold = 4;

    @Property(
            type = PropertyType.SLIDER,name = "Vertical Knockback Threshold", category = FAILSAFE, subcategory = "Detection",
            description = "Minimum vertical knockback to trigger failsafe",
            min = 2000, max = 10000000)
    public static int verticalKnockbackThreshold = 4000;

    // BPS Check
    @Property(
            type = PropertyType.SWITCH,name = "Enable BPS Check", category = FAILSAFE, subcategory = "BPS",
            description = "Monitor for drops in blocks per second")
    public static boolean enableBpsCheck = true;

    @Property(
            type = PropertyType.SLIDER,name = "Minimum BPS", category = FAILSAFE, subcategory = "BPS",
            description = "Trigger failsafe if BPS falls below this value",
            min = 5, max = 15)
    public static int minBpsThreshold = 10;

    // Failsafe Testing
    @Property(type = PropertyType.BUTTON, name = "Test Failsafe", category = FAILSAFE, subcategory = "Testing",
            description = "Simulate a failsafe trigger")
    public void _testFailsafe() {
        if (!MacroHandler.getInstance().isMacroToggled()) {
            LogUtils.sendError("You need to start the macro first!");
            return;
        }
        LogUtils.sendWarning("Testing failsafe...");
        PlayerUtils.closeScreen();
        Failsafe testingFailsafe = FailsafeManager.getInstance().failsafes.get(testFailsafeType);
        if (testingFailsafe.equals(DirtFailsafe.getInstance()) || testingFailsafe.equals(BedrockCageFailsafe.getInstance())) {
            LogUtils.sendError("You can not test this failsafe because it requires specific conditions to trigger!");
            return;
        }
        FailsafeManager.getInstance().possibleDetection(testingFailsafe);
    }

    @Property(type = PropertyType.SELECTOR, name = "Test Failsafe Type", category = FAILSAFE, subcategory = "Testing",
            description = "Select failsafe scenario to test",
            options = {
                    "Bad Effects",
                    "Banwave",
                    "Bedrock Cage",
                    "Cobweb",
                    "Dirt",
                    "Disconnect",
                    "Evacuate",
                    "Full Inventory",
                    "Guest Visit",
                    "Item Change",
                    "Jacob",
                    "Knockback",
                    "Lower Average BPS",
                    "Rotation",
                    "Teleport",
                    "World Change"
            })
    public static int testFailsafeType = 0;

    @Property(
            type = PropertyType.SWITCH,
            name = "Check Desync", category = FAILSAFE, subcategory = "Desync",
            description = "If client desynchronization is detected, it activates a failsafe. Turn this off if the network is weak or if it happens frequently."
    )
    public static boolean checkDesync = true;
    @Property(
            type = PropertyType.SLIDER,
            name = "Pause for X milliseconds after desync triggered", category = FAILSAFE, subcategory = "Desync",
            description = "The delay to pause after desync triggered in milliseconds",
            min = 3000, max = 10000
    )
    public static int desyncPauseDelay = 5000;
    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Failsafe Trigger Sound", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "Makes a sound when a failsafe has been triggered"
    )
    public static boolean enableFailsafeSound = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Failsafe Sound Type", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "The failsafe sound type to play when a failsafe has been triggered (On=Custom, Off=Minecraft)"
    )
    public static boolean failsafeSoundType = false;
    @Property(type = PropertyType.SELECTOR, 
            name = "Minecraft Sound", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "The Minecraft sound to play when a failsafe has been triggered",
            options = {
                    "Ping", // 0
                    "Anvil" // 1
            }
    )
    public static int failsafeMcSoundSelected = 1;

    @Property(type = PropertyType.SELECTOR, 
            name = "Custom Sound", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "The custom sound to play when a failsafe has been triggered",
            options = {
                    "Custom", // 0
                    "Voice", // 1
                    "Metal Pipe", // 2
                    "AAAAAAAAAA", // 3
                    "Loud Buzz", // 4
            }
    )
    public static int failsafeSoundSelected = 1;
    @Property(
            type = PropertyType.NUMBER,
            name = "Number of times to play custom sound", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "The number of times to play custom sound when a failsafe has been triggered",
            min = 1, max = 10
    )
    public static int failsafeSoundTimes = 13;
    @Property(
            type = PropertyType.SLIDER,
            name = "Failsafe Sound Volume", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "The volume of the failsafe sound",
            min = 0, max = 100
    )
    public static int failsafeSoundVolume = 50;
    @Property(
            type = PropertyType.SWITCH,
            name = "Max out Master category sounds while pinging", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "Maxes out the sounds while failsafe"
    )
    public static boolean maxOutMinecraftSounds = false;

    @Property(type = PropertyType.BUTTON, 
            name = "", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "Plays the selected sound"
    )
    public void _playFailsafeSoundButton() {
        AudioManager.getInstance().playSound();
    }
    
    @Property(type = PropertyType.BUTTON, 
            name = "", category = FAILSAFE, subcategory = "Failsafe Trigger Sound",
            description = "Stops playing the selected sound"
    )
    public void _stopFailsafeSoundButton() {
        AudioManager.getInstance().resetSound();
    }
    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Restart After FailSafe", category = FAILSAFE, subcategory = "Restart After FailSafe",
            description = "Restarts the macro after a while when a failsafe has been triggered"
    )
    public static boolean enableRestartAfterFailSafe = true;
    @Property(
            type = PropertyType.SLIDER,
            name = "Restart Delay", category = FAILSAFE, subcategory = "Restart After FailSafe",
            description = "The delay to restart after failsafe in minutes",
            min = 0, max = 20
    )
    public static int restartAfterFailSafeDelay = 0;

    @Property(
            type = PropertyType.SWITCH,
            name = "Always teleport to garden after the failsafe",
            category = FAILSAFE, subcategory = "Restart After FailSafe",
            description = "Always teleports to garden after the failsafe"
    )
    public static boolean alwaysTeleportToGarden = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Banwave Checker", category = FAILSAFE, subcategory = "Banwave Checker",
            description = "Checks for banwave and shows you the number of players banned in the last 15 minutes"
    )
    public static boolean banwaveCheckerEnabled = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Leave or pause during banwave", category = FAILSAFE, subcategory = "Banwave Checker",
            description = "Automatically disconnects from the server or pauses the macro when a banwave is detected"
    )
    public static boolean enableLeavePauseOnBanwave = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Banwave Action", category = FAILSAFE, subcategory = "Banwave Checker",
            description = "The action taken when banwave detected (On=Pause, Off=Leave)"
    )
    public static boolean banwaveAction = false;
    @Property(type = PropertyType.SELECTOR, 
            name = "Base Threshold on", category = FAILSAFE, subcategory = "Banwave Checker",
            options = {"Global bans", "FarmHelper bans", "Both"}
    )
    public static int banwaveThresholdType = 0;
    @Property(
            type = PropertyType.SLIDER,
            name = "Banwave Disconnect Threshold", category = FAILSAFE, subcategory = "Banwave Checker",
            description = "The threshold to disconnect from the server on banwave",
            min = 1, max = 100
    )
    public static int banwaveThreshold = 50;
    @Property(
            type = PropertyType.NUMBER,
            name = "Delay Before Reconnecting", category = FAILSAFE, subcategory = "Banwave Checker",
            description = "The delay before reconnecting after leaving on banwave in seconds",
            min = 1, max = 20
    )
    public static int delayBeforeReconnecting = 5;
    @Property(
            type = PropertyType.SWITCH,
            name = "Don not leave during Jacobs Contest", category = FAILSAFE, subcategory = "Banwave Checker",
            description = "Prevents the macro from leaving during Jacobs Contest even when banwave detected"
    )
    public static boolean banwaveDontLeaveDuringJacobsContest = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Send Chat Message During Failsafe", category = FAILSAFE, subcategory = "Failsafe Messages",
            description = "Sends a chat message when a failsafe has been triggered"
    )
    public static boolean sendFailsafeMessage = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Scheduler", category = SCHEDULER, subcategory = "Scheduler",
            description = "Farms for X amount of minutes then takes a break for X amount of minutes"
    )
    public static boolean enableScheduler = true;
    @Property(
            type = PropertyType.SLIDER,
            name = "Farming time in minutes", category = SCHEDULER, subcategory = "Scheduler",
            description = "How long to farm",
            min = 1, max = 300
    )
    public static int schedulerFarmingTime = 60;
    @Property(
            type = PropertyType.SLIDER,
            name = "Farming time randomness in minutes", category = SCHEDULER, subcategory = "Scheduler",
            description = "How much randomness to add to the farming time",
            min = 0, max = 15
    )
    public static int schedulerFarmingTimeRandomness = 5;
    @Property(
            type = PropertyType.SLIDER,
            name = "Break time in minutes", category = SCHEDULER, subcategory = "Scheduler",
            description = "How long to take a break",
            min = 1, max = 120
    )
    public static int schedulerBreakTime = 5;
    @Property(
            type = PropertyType.SLIDER,
            name = "Break time randomness in minutes", category = SCHEDULER, subcategory = "Scheduler",
            description = "How much randomness to add to the break time",
            min = 0, max = 15
    )
    public static int schedulerBreakTimeRandomness = 5;
    @Property(
            type = PropertyType.SWITCH,
            name = "Pause the scheduler during Jacobs Contest", category = SCHEDULER, subcategory = "Scheduler",
            description = "Pauses and delays the scheduler during Jacobs Contest"
    )
    public static boolean pauseSchedulerDuringJacobsContest = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Open inventory on scheduler breaks", category = SCHEDULER, subcategory = "Scheduler",
            description = "Opens inventory on scheduler breaks"
    )
    public static boolean openInventoryOnSchedulerBreaks = true;
    @Property(
            type = PropertyType.SWITCH,
        name = "Disconnect during break", category = SCHEDULER, subcategory = "Scheduler",
        description = "Logs out of game and logs back in after break ends"
    )
    public static boolean schedulerDisconnectDuringBreak = false;
    @Property(
            type = PropertyType.SWITCH,
        name = "Wait Until Rewarp Point for break", category = SCHEDULER, subcategory = "Scheduler",
        description = "Waits until player is standing on rewarp point to take break"
    )
    public static boolean schedulerWaitUntilRewarp = false;
    @Property(
            type = PropertyType.SWITCH,
        name = "Reset Scheduler on Macro Disabled", category = SCHEDULER, subcategory = "Scheduler",
        description = "Resets Scheduler When macro is disabled"
    )
    public static boolean schedulerResetOnDisable = true;
    @Property(type = PropertyType.BUTTON, 
        name = "Reset Scheduler", category = SCHEDULER, subcategory = "Scheduler",
        description = "Resets Scheduler (Only works when macro is of)"
    )
    public void schedulerReset() {
        if(!MacroHandler.getInstance().isMacroToggled()){
            boolean old = FarmHelperConfig.schedulerResetOnDisable;
            FarmHelperConfig.schedulerResetOnDisable = true;
            Scheduler.getInstance().stop();
            FarmHelperConfig.schedulerResetOnDisable = old;
        }
    }
    @Property(
            type = PropertyType.SWITCH,
            name = "Enable leave timer", category = SCHEDULER, subcategory = "Leave Timer",
            description = "Leaves the server after the timer has ended"
    )
    public static boolean leaveTimer = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Leave time", category = SCHEDULER, subcategory = "Leave Timer",
            description = "The time to leave the server in minutes",
            min = 15, max = 720
    )
    public static int leaveTime = 60;
    @Property(
            type = PropertyType.SWITCH,
            name = "Swap pet during Jacobs contest", category = JACOBS_CONTEST, subcategory = "Pet Swapper",
            description = "Swaps pet to the selected pet during Jacobs contest. Selects the first one from the pet list."
    )
    public static boolean enablePetSwapper = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Pet Swap Delay", category = JACOBS_CONTEST, subcategory = "Pet Swapper",
            description = "The delay between clicking GUI during swapping the pet in milliseconds",
            min = 200, max = 3000
    )
    public static int petSwapperDelay = 1000;
    @Property(
            type = PropertyType.TEXT,
            name = "Pet Name", placeholder = "Type your pet name here",
            category = JACOBS_CONTEST, subcategory = "Pet Swapper"
    )
    public static String petSwapperName = "";

    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Jacob Failsafes", category = JACOBS_CONTEST, subcategory = "Jacobs Contest",
            description = "Stops farming once a crop threshold has been met"
    )
    public static boolean enableJacobFailsafes = false;

    // ASDHAOSUDHAUSOD
    @Property(
            type = PropertyType.SWITCH,
            name = "Jacob Failsafe Action", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The action to take when a failsafe has been triggered (On=Pause, Off=Leave)"
    )
    public static boolean jacobFailsafeAction = true;
    @Property(
            type = PropertyType.SLIDER,
            name = "Nether Wart Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The nether wart cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobNetherWartCap = 80;
    @Property(
            type = PropertyType.SLIDER,
            name = "Potato Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The potato cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobPotatoCap = 83;
    @Property(
            type = PropertyType.SLIDER,
            name = "Carrot Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The carrot cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobCarrotCap = 86;
    @Property(
            type = PropertyType.SLIDER,
            name = "Wheat Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The wheat cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobWheatCap = 26;
    @Property(
            type = PropertyType.SLIDER,
            name = "Sugar Cane Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The sugar cane cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobSugarCaneCap = 57;
    @Property(
            type = PropertyType.SLIDER,
            name = "Mushroom Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The mushroom cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobMushroomCap = 25;
    @Property(
            type = PropertyType.SLIDER,
            name = "Melon Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The melon cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobMelonCap = 123;

    @Property(
            type = PropertyType.SLIDER,
            name = "Pumpkin Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The pumpkin cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobPumpkinCap = 24;

    @Property(
            type = PropertyType.SLIDER,
            name = "Cocoa Beans Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The cocoa beans cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobCocoaBeansCap = 72;
    @Property(
            type = PropertyType.SLIDER,
            name = "Cactus Cap", category = JACOBS_CONTEST, subcategory = "Jacob's Contest",
            description = "The cactus cap (multiplied by 10000)",
            min = 1, max = 200
    )
    public static int jacobCactusCap = 47;
    // ASDHAOSUDHAUSOD

    @Property(
            type = PropertyType.SWITCH,
            name = "Enable visitors macro", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "Enables visitors macro"
    )
    public static boolean visitorsMacro = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Minimum Visitors to start the macro", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "The minimum amount of visitors to start the macro",
            min = 1, max = 5
    )
    public static int visitorsMacroMinVisitors = 5;
    @Property(
            type = PropertyType.SWITCH,
            name = "Autosell before serving visitors", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "Automatically sells crops before serving visitors"
    )
    public static boolean visitorsMacroAutosellBeforeServing = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Pause the visitors macro during Jacobs contests", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "Pauses the visitors macro during Jacobs contests"
    )
    public static boolean pauseVisitorsMacroDuringJacobsContest = true;

    @Property(
            type = PropertyType.SLIDER,
            name = "The minimum amount of coins to start the macro in thousands", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "The minimum amount of coins you need to have in your purse to start the visitors macro",
            min = 1000, max = 20000
    )
    public static int visitorsMacroMinMoney = 2000;

    @Property(
            type = PropertyType.SLIDER,
            name = "Max Spend Limit in Millions Per Purchase", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            min = 0, max = 7
    )
    public static int visitorsMacroMaxSpendLimit = 1;

    @Property(
            type = PropertyType.SWITCH,
            name = "Visitors Macro Afk Infinite mode",
            description = "Will turn on Visitors Macro automatically when you are not farming and in the barn. Click macro toggle button to disable this option",
            category = VISITORS_MACRO, subcategory = "Visitors Macro"
    )
    public static boolean visitorsMacroAfkInfiniteMode = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Travel method", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "The travel method to use to get to the visitor stand (On=Walk, Off=Fly)"
    )
    public static boolean visitorsExchangeTravelMethod = false;

    @Property(type = PropertyType.BUTTON, 
            name = "Start the macro manually", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "Triggers the visitors macro"
    )
    public static void triggerVisitorsMacro() {
        if (!PlayerUtils.isInBarn()) {
            LogUtils.sendError("[Visitors Macro] You need to be in the barn to start the macro!");
            return;
        }
        VisitorsMacro.getInstance().setManuallyStarted(true);
        VisitorsMacro.getInstance().start();
    }

    @Property(
            type = PropertyType.SWITCH,
            name = "Visitors Filtering Method", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "On=By Name, Off=By Rarity"
    )
    public static boolean visitorsFilteringMethod = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Full Inventory Action", category = VISITORS_MACRO, subcategory = "Visitors Macro",
            description = "The action to take when the items don not fit in your inventory (On=Ignore, Off=Reject)"
    )
    public static boolean fullInventoryAction = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Filter by name", category = VISITORS_MACRO, subcategory = "Name Filtering",
            description = "Filters visitors by name"
    )
    public static boolean filterVisitorsByName = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Name Filtering Type", category = VISITORS_MACRO, subcategory = "Name Filtering",
            description = "The name filtering method to use (On=Whitelist, Off=Blacklist)"
    )
    public static boolean nameFilteringType = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Name Action Type", category = VISITORS_MACRO, subcategory = "Name Filtering",
            description = "The action to execute when a visitors name does not match your set filter (On=Ignore, Off=Reject)"
    )
    public static boolean nameActionType = true;

    @Property(
            type = PropertyType.TEXT,
            name = "Name Filter", category = VISITORS_MACRO, subcategory = "Name Filtering",
            description = "Visitor names to filter. Use | to split the messages.",
            placeholder = "Visitor names to filter. Use | to split the messages."
    )
    public static String nameFilter = "Librarian|Maeve|Spaceman";
    @Property(
            type = PropertyType.SWITCH,
            name = "Filter by rarity", category = VISITORS_MACRO, subcategory = "Rarity Filtering",
            description = "Filters visitors by rarity"
    )
    public static boolean filterVisitorsByRarity = true;

    @Property(type = PropertyType.SELECTOR, 
            name = "Uncommon", category = VISITORS_MACRO, subcategory = "Rarity Filtering",
            description = "The action taken when an uncommon visitor arrives",
            options = {"Accept", "Accept if profitable only", "Decline", "Ignore"}
    )
    public static int visitorsActionUncommon = 0;
    @Property(type = PropertyType.SELECTOR, 
            name = "Rare", category = VISITORS_MACRO, subcategory = "Rarity Filtering",
            description = "The action taken when a rare visitor arrives",
            options = {"Accept", "Accept if profitable only", "Decline", "Ignore"}
    )
    public static int visitorsActionRare = 0;
    @Property(type = PropertyType.SELECTOR, 
            name = "Legendary", category = VISITORS_MACRO, subcategory = "Rarity Filtering",
            description = "The action taken when a legendary visitor arrives",
            options = {"Accept", "Accept if profitable only", "Decline", "Ignore"}
    )
    public static int visitorsActionLegendary = 0;
    @Property(type = PropertyType.SELECTOR, 
            name = "Mythic", category = VISITORS_MACRO, subcategory = "Rarity Filtering",
            description = "The action taken when a mythic visitor arrives",
            options = {"Accept", "Accept if profitable only", "Decline", "Ignore"}
    )
    public static int visitorsActionMythic = 0;
    @Property(type = PropertyType.SELECTOR, 
            name = "Special", category = VISITORS_MACRO, subcategory = "Rarity Filtering",
            description = "The action taken when a special visitor arrives",
            options = {"Accept", "Accept if profitable only", "Decline", "Ignore"}
    )
    public static int visitorsActionSpecial = 3;
    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Pests Destroyer", category = PESTS_DESTROYER, subcategory = "Pests Destroyer",
            description = "Destroys pests"
    )
    public static boolean enablePestsDestroyer = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Start killing pests at X pests", category = PESTS_DESTROYER, subcategory = "Pests Destroyer",
            description = "The amount of pests to start killing pests",
            min = 1, max = 8
    )
    public static int startKillingPestsAt = 3;
    @Property(
            type = PropertyType.SLIDER,
            name = "Additional GUI Delay (ms)", category = PESTS_DESTROYER, subcategory = "Pests Destroyer",
            description = "Extra time to wait between clicks. By default its 500-1000 ms.",
            min = 0, max = 5000
    )
    public static int pestAdditionalGUIDelay = 0;

    @Property(
            type = PropertyType.SWITCH,
            name = "Sprint while flying", category = PESTS_DESTROYER, subcategory = "Pests Destroyer",
            description = "Sprints while flying"
    )
    public static boolean sprintWhileFlying = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Use AOTE/V in Pests Destroyer", category = PESTS_DESTROYER, subcategory = "Pests Destroyer",
            description = "Uses AOTE/V in Pests Destroyer"
    )
    public static boolean useAoteVInPestsDestroyer = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Don not teleport to plots when the spawn is not obstructed", category = PESTS_DESTROYER, subcategory = "Pests Destroyer",
            description = "Prevents the macro from teleporting to plots"
    )
    public static boolean dontTeleportToPlots = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Fly Pathfinder Oringo Compatible",
            description = "Makes the fly pathfinder compatible with Oringo, but worse, zzz...",
            category = PESTS_DESTROYER, subcategory = "Pests Destroyer"
    )
    public static boolean flyPathfinderOringoCompatible = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Pause the Pests Destroyer during Jacobs contests", category = PESTS_DESTROYER, subcategory = "Pests Destroyer",
            description = "Pauses the Pests Destroyer during Jacobs contests"
    )
    public static boolean pausePestsDestroyerDuringJacobsContest = true;

    @Property(type = PropertyType.BUTTON, 
            name = "Trigger now Pests Destroyer", category = PESTS_DESTROYER, subcategory = "Pests Destroyer",
            description = "Triggers the pests destroyer manually"
    )
    public static void triggerManuallyPestsDestroyer() {
        if (PestsDestroyer.getInstance().canEnableMacro(true)) {
            PestsDestroyer.getInstance().start();
        }
    }

    @Property(
            type = PropertyType.SWITCH,
            name = "Pests Destroyer Afk Infinite mode",
            description = "Will turn on Pests Destroyer automatically when you are not farming. Click macro toggle button to disable this option",
            category = PESTS_DESTROYER, subcategory = "Pests Destroyer"
    )
    public static boolean pestsDestroyerAfkInfiniteMode = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Pests Destroyer on the track",
            description = "Will kill pests if they are in your range while farming",
            category = PESTS_DESTROYER, subcategory = "Pests Destroyer on the track"
    )
    public static boolean pestsDestroyerOnTheTrack = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "Pests Destroyer on the track FOV",
            description = "The field of view of the pests destroyer on the track",
            category = PESTS_DESTROYER, subcategory = "Pests Destroyer on the track",
            min = 1, max = 360
    )
    public static int pestsDestroyerOnTheTrackFOV = 360;

    @Property(
            type = PropertyType.SLIDER,
            name = "Time for the pest to stay in range to activate (ms)",
            description = "The time for the pest to stay in range to activate the macro",
            category = PESTS_DESTROYER, subcategory = "Pests Destroyer on the track",
            min = 0, max = 2000
    )
    public static int pestsDestroyerOnTheTrackTimeForPestToStayInRange = 750;

    @Property(
            type = PropertyType.SLIDER,
            name = "Stuck timer (ms)",
            description = "The time after which macro will count as being stuck",
            category = PESTS_DESTROYER, subcategory = "Pests Destroyer on the track",
            min = 4000, max = 25000
    )
    public static int pestsDestroyerOnTheTrackStuckTimer = 5_000;

    @Property(
            type = PropertyType.SWITCH,
            name = "Don not kill pests on track during Jacobs Contest",
            description = "Prevents the macro from killing pests on the track during Jacobs Contest",
            category = PESTS_DESTROYER, subcategory = "Pests Destroyer on the track"
    )
    public static boolean dontKillPestsOnTrackDuringJacobsContest = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Pests ESP", category = PESTS_DESTROYER, subcategory = "Drawings",
            description = "Draws a box around pests"
    )
    public static boolean pestsESP = true;
    @Property(
            type = PropertyType.COLOR,
            name = "ESP Color", category = PESTS_DESTROYER, subcategory = "Drawings",
            description = "The color of the pests ESP"
    )
    public static Color pestsESPColor = new Color(0, 255, 217, 171);
    @Property(
            type = PropertyType.SWITCH,
            name = "Tracers to Pests", category = PESTS_DESTROYER, subcategory = "Drawings",
            description = "Draws a line to pests"
    )
    public static boolean pestsTracers = true;
    @Property(
            type = PropertyType.COLOR,
            name = "Tracers Color", category = PESTS_DESTROYER, subcategory = "Drawings",
            description = "The color of the pests tracers"
    )
    public static Color pestsTracersColor = new Color(0, 255, 217, 171);
    @Property(
            type = PropertyType.SWITCH,
            name = "Highlight borders of Plot with pests", category = PESTS_DESTROYER, subcategory = "Drawings",
            description = "Highlights the borders of the plot with pests"
    )
    public static boolean highlightPlotWithPests = true;
    @Property(
            type = PropertyType.COLOR,
            name = "Plot Highlight Color", category = PESTS_DESTROYER, subcategory = "Drawings",
            description = "The color of the plot highlight"
    )
    public static Color plotHighlightColor = new Color(0, 255, 217, 40);

    @Property(
            type = PropertyType.SWITCH,
            name = "Send Webhook log if pests detection number has been exceeded", category = PESTS_DESTROYER, subcategory = "Logs",
            description = "Sends a webhook log if pests detection number has been exceeded"
    )
    public static boolean sendWebhookLogIfPestsDetectionNumberExceeded = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Ping @everyone", category = PESTS_DESTROYER, subcategory = "Logs",
            description = "Pings @everyone on pests detection number exceeded"
    )
    public static boolean pingEveryoneOnPestsDetectionNumberExceeded = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Send notification if pests detection number has been exceeded", category = PESTS_DESTROYER, subcategory = "Logs",
            description = "Sends a notification if pests detection number has been exceeded"
    )
    public static boolean sendNotificationIfPestsDetectionNumberExceeded = true;
    @Property(
            type = PropertyType.SWITCH,name = "Send Webhook log when pest destroyer starts/stops", category = PESTS_DESTROYER, subcategory = "Logs",
            description = "Sends a webhook log when pest destroyer starts/stops"
    )
    public static boolean sendWebhookLogWhenPestDestroyerStartsStops = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Webhook Messages", category = DISCORD_INTEGRATION, subcategory = "Discord Webhook",
            description = "Allows to send messages via Discord webhooks"
    )
    public static boolean enableWebHook = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Send Logs", category = DISCORD_INTEGRATION, subcategory = "Discord Webhook",
            description = "Sends all messages about the macro, staff checks, etc"
    )
    public static boolean sendLogs = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Send Status Updates", category = DISCORD_INTEGRATION, subcategory = "Discord Webhook",
            description = "Sends messages about the macro, such as profits, harvesting crops, etc"
    )
    public static boolean sendStatusUpdates = false;
    @Property(
            type = PropertyType.NUMBER,
            name = "Status Update Interval in minutes", category = DISCORD_INTEGRATION, subcategory = "Discord Webhook",
            description = "The interval between sending messages about status updates",
            min = 1, max = 60
    )
    public static int statusUpdateInterval = 5;
    @Property(
            type = PropertyType.SWITCH,
            name = "Send Visitors Macro Logs", category = DISCORD_INTEGRATION, subcategory = "Discord Webhook",
            description = "Sends messages about the visitors macro, such as which visitor got rejected or accepted and with what items"
    )
    public static boolean sendVisitorsMacroLogs = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Ping everyone on Visitors Macro Logs", category = DISCORD_INTEGRATION, subcategory = "Discord Webhook",
            description = "Pings everyone on Visitors Macro Logs"
    )
    public static boolean pingEveryoneOnVisitorsMacroLogs = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Send Macro Enable/Disable Logs", category = DISCORD_INTEGRATION, subcategory = "Discord Webhook",
            description = "Sends messages when the macro has been enabled or disabled"
    )
    public static boolean sendMacroEnableDisableLogs = true;
    @Property(
            type = PropertyType.TEXT,
            name = "WebHook URL", category = DISCORD_INTEGRATION, subcategory = "Discord Webhook",
            description = "The URL to use for the webhook",
            placeholder = "https://discord.com/api/webhooks/..."
    )
    public static String webHookURL = "";
    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Remote Control", category = DISCORD_INTEGRATION, subcategory = "Remote Control",
            description = "Enables remote control via Discord messages"
    )
    public static boolean enableRemoteControl = false;
    @Property(
            type = PropertyType.TEXT,
            name = "Discord Remote Control Bot Token",
            category = DISCORD_INTEGRATION, subcategory = "Remote Control",
            description = "The bot token to use for remote control"
    )
    public static String discordRemoteControlToken = "";
    @Property(
            type = PropertyType.TEXT,
            name = "Discord Remote Control Address",
            category = DISCORD_INTEGRATION, subcategory = "Remote Control",
            description = "The address to use for remote control. If you are unsure what to put there, leave \"localhost\".",
            placeholder = "localhost"
    )
    public static String discordRemoteControlAddress = "localhost";

    @Property(
            type = PropertyType.NUMBER,
            name = "Remote Control Port", category = DISCORD_INTEGRATION, subcategory = "Remote Control",
            description = "The port to use for remote control. Change this if you have port conflicts.",
            min = 1, max = 65535
    )
    public static int remoteControlPort = 21370;

    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Auto Pest Exchange", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Automatically hunts pests"
    )
    public static boolean autoPestExchange = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Pause the Auto Pest Exchange during Jacobs contests", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Pauses the Auto Pest Exchange during Jacobs contests"
    )
    public static boolean pauseAutoPestExchangeDuringJacobsContest = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Ignore Jacobs Contest", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Start the Auto Pest Exchange regardless of the next Jacobs contests"
    )
    public static boolean autoPestExchangeIgnoreJacobsContest = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Only start on relevant Jacobs Contests", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Only start the Auto Pest Exchange if the next Jacobs contest contains the current crop you are farming"
    )
    public static boolean autoPestExchangeOnlyStartRelevant = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Travel method", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "The travel method to use to get to the pest exchange desk (On=Walk, Off=Fly)"
    )
    public static boolean autoPestExchangeTravelMethod = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Trigger before contest starts in minutes", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "The time before the contest starts to trigger the auto pest exchange",
            min = 1, max = 40
    )
    public static int autoPestExchangeTriggerBeforeContestStarts = 5;
    @Property(
            type = PropertyType.SLIDER,
            name = "Pests amount required", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "The amount of pests in a vacuum required to start the auto pest exchange",
            min = 1, max = 40
    )
    public static int autoPestExchangeMinPests = 10;
    @Property(
            type = PropertyType.SWITCH,
            name = "Send Webhook Log", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Logs all events related to the auto pest exchange"
    )
    public static boolean logAutoPestExchangeEvents = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Highlight desk location", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Highlights the pest exchange desk location"
    )
    public static boolean highlightPestExchangeDeskLocation = true;

    @Property(type = PropertyType.BUTTON, 
            name = "Trigger now Auto Pest Exchange", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Triggers the auto pest exchange manually"
    )
    public static void triggerManuallyAutoPestExchange() {
        AutoPestExchange.getInstance().setManuallyStarted(true);
        AutoPestExchange.getInstance().start();
    }

    @Property(type = PropertyType.BUTTON, 
            name = "Set the pest exchange location", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Sets the pest exchange location"
    )
    public static void setPestExchangeLocation() {
        if (!PlayerUtils.isInBarn()) {
            LogUtils.sendError("[Auto Pest Exchange] You need to be in the barn to set the pest exchange location!");
            return;
        }
        pestExchangeDeskX = mc.thePlayer.getPosition().getX();
        pestExchangeDeskY = mc.thePlayer.getPosition().getY();
        pestExchangeDeskZ = mc.thePlayer.getPosition().getZ();
        LogUtils.sendSuccess("[Auto Pest Exchange] Set the pest exchange location to "
                + FarmHelperConfig.pestExchangeDeskX + ", "
                + FarmHelperConfig.pestExchangeDeskY + ", "
                + FarmHelperConfig.pestExchangeDeskZ);
    }

    @Property(type = PropertyType.BUTTON, 
            name = "Reset the pest exchange location", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            description = "Resets the pest exchange location"
    )
    public static void resetPestExchangeLocation() {
        pestExchangeDeskX = 0;
        pestExchangeDeskY = 0;
        pestExchangeDeskZ = 0;
        LogUtils.sendSuccess("[Auto Pest Exchange] Reset the pest exchange location");
    }

    @Property(
            type = PropertyType.NUMBER,
            name = "Pest Exchange Desk X", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            min = -300, max = 300
    )
    public static int pestExchangeDeskX = 0;
    @Property(
            type = PropertyType.NUMBER,
            name = "Pest Exchange Desk Y", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            min = 50, max = 150
    )
    public static int pestExchangeDeskY = 0;
    @Property(
            type = PropertyType.NUMBER,
            name = "Pest Exchange Desk Z", category = AUTO_PEST_EXCHANGE, subcategory = "Auto Pest Exchange",
            min = -300, max = 300
    )
    public static int pestExchangeDeskZ = 0;

    @Property(
            type = PropertyType.SWITCH,
            name = "Auto God Pot", category = AUTO_GOD_POT, subcategory = "God Pot",
            description = "Automatically purchases and consumes a God Pot"
    )
    public static boolean autoGodPot = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Get God Pot from Backpack", category = AUTO_GOD_POT, subcategory = "God Pot"
    )
    public static boolean autoGodPotFromBackpack = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Storage Type", category = AUTO_GOD_POT, subcategory = "God Pot",
            description = "The storage type to get god pots from (On=Ender Chest, Off, Backpack)"
    )
    public static boolean autoGodPotStorageType = true;

    @Property(
            type = PropertyType.NUMBER,
            name = "Backpack Number", category = AUTO_GOD_POT, subcategory = "God Pot",
            description = "The backpack number, that contains god pots",
            min = 1, max = 18
    )
    public static int autoGodPotBackpackNumber = 1;

    @Property(
            type = PropertyType.SWITCH,
            name = "Buy God Pot using Bits", category = AUTO_GOD_POT, subcategory = "God Pot"
    )
    public static boolean autoGodPotFromBits = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Get God Pot from Auction House", category = AUTO_GOD_POT, subcategory = "God Pot",
            description = "If the user doesn not have a cookie, it will go to the hub and buy from AH"
    )
    public static boolean autoGodPotFromAH = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Enable Auto Sell", category = AUTO_SELL, subcategory = "Auto Sell",
            description = "Enables auto sell"
    )
    public static boolean enableAutoSell = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Market type", category = AUTO_SELL, subcategory = "Auto Sell",
            description = "The market type to sell crops to (On=NPC, Off=BZ)"
    )
    public static boolean autoSellMarketType = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Sell Items In Sacks", category = AUTO_SELL, subcategory = "Auto Sell",
            description = "Sells items in your sacks and inventory"
    )
    public static boolean autoSellSacks = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Sacks placement",
            category = AUTO_SELL, subcategory = "Auto Sell",
            description = "The sacks placement (On=Sack of sacks, Off=Inventory)"
    )
    public static boolean autoSellSacksPlacement = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Pause Auto Sell during Jacobs contest", category = AUTO_SELL, subcategory = "Auto Sell",
            description = "Pauses auto sell during Jacobs contest"
    )
    public static boolean pauseAutoSellDuringJacobsContest = false;

    @Property(
            type = PropertyType.NUMBER,
            name = "Inventory Full Time", category = AUTO_SELL, subcategory = "Auto Sell",
            description = "The time to wait to test if inventory fullness ratio is still the same (or higher)",
            min = 1, max = 20
    )
    public static int inventoryFullTime = 6;

    @Property(
            type = PropertyType.NUMBER,
            name = "Inventory Full Ratio", category = AUTO_SELL, subcategory = "Auto Sell",
            description = "After reaching this ratio, the macro will start counting from 0 to Inventory Full Time. If the fullness ratio is still the same (or higher) after the time has passed, it will start selling items.",
            min = 1, max = 100
    )
    public static int inventoryFullRatio = 65;
    @Property(type = PropertyType.BUTTON, 
            name = "Sell Inventory Now", category = AUTO_SELL, subcategory = "Auto Sell",
            description = "Sells crops in your inventory"
    )
    public void autoSellFunction() {
        PlayerUtils.closeScreen();
        AutoSell.getInstance().enable(true);
    }

    @Property(
            type = PropertyType.SWITCH,name = "Runes", category = AUTO_SELL, subcategory = "Customize items sold to NPC")
    public static boolean autoSellRunes = true;

    @Property(
            type = PropertyType.SWITCH,name = "Dead Bush", category = AUTO_SELL, subcategory = "Customize items sold to NPC")
    public static boolean autoSellDeadBush = true;

    @Property(
            type = PropertyType.SWITCH,name = "Iron Hoe", category = AUTO_SELL, subcategory = "Customize items sold to NPC")
    public static boolean autoSellIronHoe = true;

    @Property(
            type = PropertyType.SWITCH,name = "Pest Vinyls", category = AUTO_SELL, subcategory = "Customize items sold to NPC")
    public static boolean autoSellPestVinyls = true;

    @Property(
            type = PropertyType.TEXT,
            name = "Custom Items", category = AUTO_SELL, subcategory = "Customize items sold to NPC",
            description = "Add custom items to AutoSell here. Use | to split the messages.",
            placeholder = "Custom items to auto sell. Use | to split the messages."
    )
    public static String autoSellCustomItems = "";
    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Pest Repellent", category = AUTO_REPELLANT, subcategory = "Pest Repellent",
            description = "Automatically uses pest repellent when its not active"
    )
    public static boolean autoPestRepellent = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Pest Repellent Type", category = AUTO_REPELLANT, subcategory = "Pest Repellent",
            description = "The pest repellent type to use (On=MAX, Off=Regular)"
    )
    public static boolean pestRepellentType = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Pause Auto Pest Repellent during Jacobs contest", category = AUTO_REPELLANT, subcategory = "Pest Repellent",
            description = "Pauses auto pest repellent during Jacobs contest"
    )
    public static boolean pauseAutoPestRepellentDuringJacobsContest = false;

    @Property(type = PropertyType.BUTTON, 
            name = "Reset Failsafe", category = AUTO_REPELLANT, subcategory = "Pest Repellent",
            description = "Resets the failsafe timer for repellent"
    )
    public void resetFailsafe() {
        AutoRepellent.repellentFailsafeClock.schedule(0);
    }
    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Sprayonator", category = AUTO_SPRAYONATOR, subcategory = "Auto Sprayonator"
    )
    public static boolean autoSprayonator = false;

    @Property(type = PropertyType.SELECTOR, 
            name = "Type", category = AUTO_SPRAYONATOR, subcategory = "Auto Sprayonator",
            description = "Item to spray plot with",
            options = {
                    "Fine Flour (+20 Farming Fortune)",
                    "Compost (Earthworm & Mosquito)",
                    "Honey Jar (Moth & Cricket)",
                    "Dung (Beetle & Fly)",
                    "Plant Matter (Locust & Slug)",
                    "Tasty Cheese (Rat & Mite)"
            }
    )
    public static int autoSprayonatorSprayMaterial = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Additional Delay", category = AUTO_SPRAYONATOR, subcategory = "Auto Sprayonator",
            description = "Additional delay between actions in milliseconds",
            min = 0, max = 5000
    )
    public static int autoSprayonatorAdditionalDelay = 500;

    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Buy item from Bazaar", category = AUTO_SPRAYONATOR, subcategory = "Auto Sprayonator",
            description = "Auto buy necessary sprayonator item from bazaar if none is in the inventory"
    )
    public static boolean autoSprayonatorAutoBuyItem = false;

    @Property(
            type = PropertyType.NUMBER,
            name = "Buy Amount", category = AUTO_SPRAYONATOR, subcategory = "Auto Sprayonator",
            description = "Amount of item to buy from bazaar",
            min = 1, max = 64
    )
    public static int autoSprayonatorAutoBuyAmount = 1;

    @Property(
            type = PropertyType.SLIDER,
            name = "Time between changing rows", category = DELAYS, subcategory = "Changing rows",
            description = "The minimum time to wait before changing rows in milliseconds",
            min = 0, max = 2000
    )
    public static int timeBetweenChangingRows = 400;
    @Property(
            type = PropertyType.SLIDER,
            name = "Additional random time between changing rows", category = DELAYS, subcategory = "Changing rows",
            description = "The maximum time to wait before changing rows in milliseconds",
            min = 0, max = 2000
    )
    public static int randomTimeBetweenChangingRows = 200;
    @Property(
            type = PropertyType.SWITCH,
            name = "Custom row change delays during Jacobs Contest", category = DELAYS, subcategory = "Changing rows",
            description = "Custom row change delays during Jacobs Contest"
    )
    public static boolean customRowChangeDelaysDuringJacob = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Time between changing rows during Jacobs Contest", category = DELAYS, subcategory = "Changing rows",
            description = "The minimum time to wait before changing rows in milliseconds",
            min = 0, max = 2000
    )
    public static int timeBetweenChangingRowsDuringJacob = 400;
    @Property(
            type = PropertyType.SLIDER,
            name = "Additional random time between changing rows during Jacobs Contest", category = DELAYS, subcategory = "Changing rows",
            description = "The maximum time to wait before changing rows in milliseconds",
            min = 0, max = 2000
    )
    public static int randomTimeBetweenChangingRowsDuringJacob = 200;
    @Property(
            type = PropertyType.SLIDER,
            name = "Rotation Time", category = DELAYS, subcategory = "Rotations",
            description = "The time it takes to rotate the player",
            min = 200, max = 2000
    )
    public static int rotationTime = 500;
    @Property(
            type = PropertyType.SLIDER,
            name = "Additional random Rotation Time", category = DELAYS, subcategory = "Rotations",
            description = "The maximum random time added to the delay time it takes to rotate the player in milliseconds",
            min = 0, max = 2000
    )
    public static int rotationTimeRandomness = 300;
    @Property(
            type = PropertyType.SWITCH,
            name = "Custom rotation delays during Jacobs Contest", category = DELAYS, subcategory = "Rotations",
            description = "Custom rotation delays during Jacobs Contest"
    )
    public static boolean customRotationDelaysDuringJacob = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Rotation Time during Jacobs Contest", category = DELAYS, subcategory = "Rotations",
            description = "The time it takes to rotate the player",
            min = 200, max = 2000
    )
    public static int rotationTimeDuringJacob = 500;
    @Property(
            type = PropertyType.SLIDER,
            name = "Additional random Rotation Time during Jacobs Contest", category = DELAYS, subcategory = "Rotations",
            description = "The maximum random time added to the delay time it takes to rotate the player in milliseconds",
            min = 0, max = 2000
    )
    public static int rotationTimeRandomnessDuringJacob = 300;
    @Property(
            type = PropertyType.SLIDER,
            name = "Fly PathExecutioner Rotation Time", category = DELAYS, subcategory = "Fly PathExecutioner",
            description = "The time it takes to rotate the player",
            min = 200, max = 2000
    )
    public static int flyPathExecutionerRotationTime = 500;
    @Property(
            type = PropertyType.SLIDER,
            name = "Fly PathExecutioner Additional random Rotation Time", category = DELAYS, subcategory = "Fly PathExecutioner",
            description = "The maximum random time added to the delay time it takes to rotate the player in milliseconds",
            min = 0, max = 2000
    )
    public static int flyPathExecutionerRotationTimeRandomness = 300;
    @Property(
            type = PropertyType.SLIDER,
            name = "Pests Destroyer Stuck Time in minutes", category = DELAYS, subcategory = "Pests Destroyer",
            description = "Pests Destroyer Stuck Time in minutes for single pest",
            min = 1, max = 7
    )
    public static int pestsKillerStuckTime = 3;
    @Property(
            type = PropertyType.SLIDER,
            name = "Pests Destroyer Ticks of not seeing pest", category = DELAYS, subcategory = "Pests Destroyer",
            description = "Pests Destroyer Ticks of not seeing pest while attacking (1 tick == 50ms) to trigger Escape to Hub. 0 to disable",
            min = 20, max = 200
    )
    public static int pestsKillerTicksOfNotSeeingPestWhileAttacking = 100;

    @Property(
            type = PropertyType.SLIDER,
            name = "GUI Delay", category = DELAYS, subcategory = "GUI Delays",
            description = "The delay between clicking during GUI macros in milliseconds",
            min = 50, max = 2000
    )
    public static int macroGuiDelay = 400;
    @Property(
            type = PropertyType.SLIDER,
            name = "Additional random GUI Delay", category = DELAYS, subcategory = "GUI Delays",
            description = "The maximum random time added to the delay time between clicking during GUI macros in milliseconds",
            min = 0, max = 2000
    )
    public static int macroGuiDelayRandomness = 350;

    @Property(
            type = PropertyType.SLIDER,
            name = "Plot Cleaning Helper Rotation Time", category = DELAYS, subcategory = "Plot Cleaning Helper",
            description = "The time it takes to rotate the player",
            min = 20, max = 500
    )
    public static int plotCleaningHelperRotationTime = 50;
    @Property(
            type = PropertyType.SLIDER,
            name = "Additional random Plot Cleaning Helper Rotation Time", category = DELAYS, subcategory = "Plot Cleaning Helper",
            description = "The maximum random time added to the delay time it takes to rotate the player in milliseconds",
            min = 0, max = 500
    )

    public static int plotCleaningHelperRotationTimeRandomness = 50;

    @Property(
            type = PropertyType.SLIDER,
            name = "Rewarp Delay", category = DELAYS, subcategory = "Rewarp",
            description = "The delay between rewarping in milliseconds",
            min = 250, max = 2000
    )
    public static int rewarpDelay = 400;
    @Property(
            type = PropertyType.SLIDER,
            name = "Additional random Rewarp Delay", category = DELAYS, subcategory = "Rewarp",
            description = "The maximum random time added to the delay time between rewarping in milliseconds",
            min = 0, max = 2000
    )
    public static int rewarpDelayRandomness = 350;

    @Property(
            type = PropertyType.SWITCH,
            name = "Debug Mode", category = DEBUG, subcategory = "Debug",
            description = "Prints to chat what the bot is currently executing. Useful if you are having issues."
    )
    public static boolean debugMode = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Automatically switch recognized crop", category = EXPERIMENTAL, subcategory = "Auto Switch",
            description = "Macro will be recognizing farming crop, which will lead to auto switching tool to the best one"
    )
    public static boolean autoSwitchTool = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Count only current crops for Jacobs Contest excludes", category = EXPERIMENTAL, subcategory = "Jacobs Contest",
            description = "Counts only current crops for Jacobs Contest excludes"
    )
    public static boolean jacobContestCurrentCropsOnly = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Show Debug logs about PD OTT", category = EXPERIMENTAL, subcategory = "Debug",
            description = "Shows debug logs about PD OTT"
    )
    public static boolean showDebugLogsAboutPDOTT = false;

    @Property(
            type = PropertyType.BUTTON,
            name = "Failsafe Notifications", category = FAILSAFE, subcategory = "Failsafe Notifications", 
            description = "Click here to customize failsafe notifications"
    )
    public static void _failsafeNotificationsPage() {
        FailsafeNotificationsPage.getInstance().gui();
    }

    @Property(
            type = PropertyType.BUTTON,
            name = "Custom Failsafe Messages", category = FAILSAFE, subcategory = "Failsafe Messages", 
            description = "Click here to edit custom failsafe messages"
    )
    public static void _customFailsafeMessagesPage() {
        CustomFailsafeMessagesPage.getInstance().gui();
    }    

    public static int configVersion = 6;
    public static KeyBinding toggleMacro = new KeyBinding("Toggle Macro", Keyboard.KEY_GRAVE, "Farm Helper");
    public static KeyBinding openGuiKeybind = new KeyBinding("Open Gui", Keyboard.KEY_F, "Farm Helper");
    public static KeyBinding freelookKeybind = new KeyBinding("Freelook", Keyboard.KEY_NONE, "Farm Helper");
    public static KeyBinding cancelFailsafeKeybind = new KeyBinding("Cancel Failsafe", Keyboard.KEY_NONE, "Farm Helper");
    public static KeyBinding plotCleaningHelperKeybind = new KeyBinding("Plot Cleaning Helper", Keyboard.KEY_NONE, "Farm Helper");
    public static KeyBinding enablePestsDestroyerKeyBind = new KeyBinding("Enable Pests Destroyer", Keyboard.KEY_NONE, "Farm Helper");

    public FarmHelperConfig() {
        super(new File("./farmhelper/config.toml"), "Farm Helper");
        initialize();

        ClientRegistry.registerKeyBinding(toggleMacro);
        ClientRegistry.registerKeyBinding(openGuiKeybind);
        ClientRegistry.registerKeyBinding(freelookKeybind);
        ClientRegistry.registerKeyBinding(cancelFailsafeKeybind);
        ClientRegistry.registerKeyBinding(plotCleaningHelperKeybind);
        ClientRegistry.registerKeyBinding(enablePestsDestroyerKeyBind);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.theWorld == null || mc.thePlayer == null) return;

        if (openGuiKeybind.isPressed()) EssentialAPI.getGuiUtil().openScreen(gui());
        if (toggleMacro.isPressed()) MacroHandler.getInstance().toggleMacro();
        if (freelookKeybind.isPressed()) Freelook.getInstance().toggle();
        if (plotCleaningHelperKeybind.isPressed()) PlotCleaningHelper.getInstance().toggle();
        if (enablePestsDestroyerKeyBind.isPressed()) {
            if (PestsDestroyer.getInstance().canEnableMacro(true)) {
                PestsDestroyer.getInstance().start();
            }
        }
        if (cancelFailsafeKeybind.isPressed()) {
            if (FailsafeManager.getInstance().getChooseEmergencyDelay().isScheduled()) {
                FailsafeManager.getInstance().stopFailsafes();
                LogUtils.sendWarning("[Failsafe] Emergency has been cancelled!");
            }
        }
    }

    public static void loadRewarpConfig() {
        try {
            if (!configRewarpFile.exists()) return;
            rewarpList = Arrays.asList(FarmHelper.gson.fromJson(new String(Files.readAllBytes(configRewarpFile.toPath())), Rewarp[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addRewarp() {
        if (FarmHelperConfig.rewarpList.stream().anyMatch(rewarp -> rewarp.isTheSameAs(BlockUtils.getRelativeBlockPos(0, 0, 0)))) {
            LogUtils.sendError("Rewarp location has already been set!");
            return;
        }
        Rewarp newRewarp = new Rewarp(BlockUtils.getRelativeBlockPos(0, 0, 0));
        if (newRewarp.getDistance(new BlockPos(PlayerUtils.getSpawnLocation())) < 2) {
            LogUtils.sendError("Rewarp location is too close to the spawn location! You must put it AT THE END OF THE FARM!");
            return;
        }
        rewarpList.add(newRewarp);
        LogUtils.sendSuccess("Added rewarp: " + newRewarp);
        saveRewarpConfig();
    }

    public static void removeRewarp() {
        Rewarp closest = null;
        if (rewarpList.isEmpty()) {
            LogUtils.sendError("No rewarp locations set!");
            return;
        }
        double closestDistance = Double.MAX_VALUE;
        for (Rewarp rewarp : rewarpList) {
            double distance = rewarp.getDistance(BlockUtils.getRelativeBlockPos(0, 0, 0));
            if (distance < closestDistance) {
                closest = rewarp;
                closestDistance = distance;
            }
        }
        if (closest != null) {
            rewarpList.remove(closest);
            LogUtils.sendSuccess("Removed the closest rewarp: " + closest);
            saveRewarpConfig();
        }
    }

    public static void removeAllRewarps() {
        rewarpList.clear();
        LogUtils.sendSuccess("Removed all saved rewarp positions");
        saveRewarpConfig();
    }

    public static void saveRewarpConfig() {
        try {
            if (!configRewarpFile.exists())
                Files.createFile(configRewarpFile.toPath());

            Files.write(configRewarpFile.toPath(), FarmHelper.gson.toJson(rewarpList).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MacroEnum getMacro() {
        return MacroEnum.values()[macroType];
    }

    public static long getRandomTimeBetweenChangingRows() {
        if (customRowChangeDelaysDuringJacob && GameStateHandler.getInstance().inJacobContest())
            return (long) (timeBetweenChangingRowsDuringJacob + (int) Math.random() * randomTimeBetweenChangingRowsDuringJacob);
        return (long) (timeBetweenChangingRows + (int) Math.random() * randomTimeBetweenChangingRows);
    }

    public static long getMaxTimeBetweenChangingRows() {
        return (long) (timeBetweenChangingRows + randomTimeBetweenChangingRows);
    }

    public static long getRandomRotationTime() {
        if (customRotationDelaysDuringJacob && GameStateHandler.getInstance().inJacobContest())
            return (long) (rotationTimeDuringJacob + (int) Math.random() * rotationTimeRandomnessDuringJacob);
        return (long) (rotationTime + (int) Math.random() * rotationTimeRandomness);
    }

    public static long getRandomFlyPathExecutionerRotationTime() {
        return (long) (flyPathExecutionerRotationTime + (int) Math.random() * flyPathExecutionerRotationTimeRandomness);
    }

    public static long getRandomGUIMacroDelay() {
        return (long) (macroGuiDelay + (int) Math.random() * macroGuiDelayRandomness);
    }

    public static long getRandomPlotCleaningHelperRotationTime() {
        return (long) (plotCleaningHelperRotationTime + (int) Math.random() * plotCleaningHelperRotationTimeRandomness);
    }

    public static long getRandomRewarpDelay() {
        return (long) (rewarpDelay + (int) Math.random() * rewarpDelayRandomness);
    }

    public String getJson() {
        String json = gson.toJson(this);
        if (json == null || json.equals("{}")) {
            json = gson.toJson(this);
        }
        return json;
    }

    public enum MacroEnum {
        S_V_NORMAL_TYPE,
        S_PUMPKIN_MELON,
        S_PUMPKIN_MELON_MELONGKINGDE,
        S_PUMPKIN_MELON_DEFAULT_PLOT,
        S_SUGAR_CANE,
        S_CACTUS,
        S_CACTUS_SUNTZU,
        S_COCOA_BEANS,
        S_COCOA_BEANS_TRAPDOORS,
        S_COCOA_BEANS_LEFT_RIGHT,
        S_MUSHROOM,
        S_MUSHROOM_ROTATE,
        S_MUSHROOM_SDS,
        C_NORMAL_TYPE
    }

    @Getter
    public enum CropEnum {
        NONE("None"),
        CARROT("Carrot"),
        NETHER_WART("Nether Wart"),
        POTATO("Potato"),
        WHEAT("Wheat"),
        SUGAR_CANE("Sugar Cane"),
        MELON("Melon"),
        PUMPKIN("Pumpkin"),
        PUMPKIN_MELON_UNKNOWN("Pumpkin/Melon"),
        CACTUS("Cactus"),
        COCOA_BEANS("Cocoa Beans"),
        MUSHROOM("Mushroom"),
        MUSHROOM_ROTATE("Mushroom"),
        ;

        final String localizedName;

        CropEnum(String localizedName) {
            this.localizedName = localizedName;
        }
    }
}
