package com.jelly.farmhelperv2.config.page;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

public class FailsafeNotificationsPage extends Vigilant {

    @Property(
            type = PropertyType.SWITCH,
            name = "Rotation Check Notifications",
            description = "Whether or not to send a notification when the rotation check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnRotationFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Teleportation Check Notifications",
            description = "Whether or not to send a notification when the teleportation check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnTeleportationFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Lag Back Notifications",
            description = "Whether or not to send a notification when the lag back failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnLagBackFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Knockback Check Notifications",
            description = "Whether or not to send a notification when the knockback check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnKnockbackFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Dirt Check Notifications",
            description = "Whether or not to send a notification when the dirt check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnDirtFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Cobweb Check Notifications",
            description = "Whether or not to send a notification when the cobweb check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnCobwebFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Item Change Check Notifications",
            description = "Whether or not to send a notification when the item change check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnItemChangeFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "World Change Check Notifications",
            description = "Whether or not to send a notification when the world change check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnWorldChangeFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Bedrock Cage Check Notifications",
            description = "Whether or not to send a notification when the bedrock cage check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnBedrockCageFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Bad Effects Check Notifications",
            description = "Whether or not to send a notification when the bad effects check failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnBadEffectsFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Evacuate Notifications",
            description = "Whether or not to send a notification when the evacuate failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnEvacuateFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Banwave Notifications",
            description = "Whether or not to send a notification when the banwave failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnBanwaveFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Disconnect Notifications",
            description = "Whether or not to send a notification when the disconnect failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnDisconnectFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Jacob Notifications",
            description = "Whether or not to send a notification when the Jacob failsafe is triggered.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnJacobFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Lower Average BPS Notifications",
            description = "Whether or not to send a notification when the average BPS is lower than the specified value.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnLowerAverageBPS = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Guest Visit Notifications",
            description = "Whether or not to send a notification when a guest visits your island.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnGuestVisit = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Full Inventory Notifications",
            description = "Whether or not to send a notification when your inventory is full.",
            category = "Failsafe Notifications"
    )
    public static boolean notifyOnInventoryFull = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Rotation Check Sound Alert",
            description = "Whether or not to play a sound when the rotation check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnRotationFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Teleportation Check Sound Alert",
            description = "Whether or not to play a sound when the teleportation check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnTeleportationFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Knockback Check Sound Alert",
            description = "Whether or not to play a sound when the knockback check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnKnockbackFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Dirt Check Sound Alert",
            description = "Whether or not to play a sound when the dirt check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnDirtFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Cobweb Check Sound Alert",
            description = "Whether or not to play a sound when the cobweb check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnCobwebFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Item Change Check Sound Alert",
            description = "Whether or not to play a sound when the item change check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnItemChangeFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "World Change Check Sound Alert",
            description = "Whether or not to play a sound when the world change check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnWorldChangeFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Bedrock Cage Check Sound Alert",
            description = "Whether or not to play a sound when the bedrock cage check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnBedrockCageFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Bad Effects Check Sound Alert",
            description = "Whether or not to play a sound when the bad effects check failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnBadEffectsFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Evacuate Alert",
            description = "Whether or not to play a sound when the evacuate failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnEvacuateFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Banwave Alert",
            description = "Whether or not to play a sound when the banwave failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnBanwaveFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Disconnect Alert",
            description = "Whether or not to play a sound when the disconnect failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnDisconnectFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Jacob Alert",
            description = "Whether or not to play a sound when the Jacob failsafe is triggered.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnJacobFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Full Inventory Alert",
            description = "Whether or not to play a sound when your inventory is full.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnFullInventory = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Lower Average BPS Alert",
            description = "Whether or not to play a sound when the average BPS is lower than the specified value.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnLowerAverageBPS = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Guest Visit Alert",
            description = "Whether or not to play a sound when a guest visits your island.",
            category = "Failsafe Sound Alerts"
    )
    public static boolean alertOnGuestVisit = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Rotation Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the rotation check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnRotationFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Teleportation Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the teleportation check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnTeleportationFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Lag Back Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the lag back failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnLagBackFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Knockback Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the knockback check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnKnockbackFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Dirt Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the dirt check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnDirtFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Cobweb Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the cobweb check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnCobwebFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Item Change Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the item change check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnItemChangeFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "World Change Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the world change check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnWorldChangeFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Bedrock Cage Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the bedrock cage check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnBedrockCageFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Bad Effects Check Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the bad effects check failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnBadEffectsFailsafe = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Evacuate Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the evacuate failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnEvacuateFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Banwave Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the banwave failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnBanwaveFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Disconnect Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the disconnect failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnDisconnectFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Jacob Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the Jacob failsafe is triggered.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnJacobFailsafe = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Lower Average BPS Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when the average BPS is lower than the specified value.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnLowerAverageBPS = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Guest Visit Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when a guest visits your island.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnGuestVisit = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Full Inventory Tag Everyone",
            description = "Whether or not to tag everyone in the webhook message when your inventory is full.",
            category = "Failsafe Tag Everyone"
    )
    public static boolean tagEveryoneOnFullInventory = false;

    public FailsafeNotificationsPage() {
        super(new File("./farmhelper/failsafenotifications.toml"), "Failsafe Notifications");
        initialize();
    }

    private static FailsafeNotificationsPage instance;
    public static FailsafeNotificationsPage getInstance() {
        if (instance == null) {
            instance = new FailsafeNotificationsPage();
        }

        return instance;
    }
    
}
