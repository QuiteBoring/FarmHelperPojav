package com.jelly.farmhelperv2.config.page;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

public class CustomFailsafeMessagesPage extends Vigilant {

    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Custom messages sent during Jacob's Contest",
            description = "The messages to send to the chat when the failsafe has been triggered and you are during Jacob's Contest (use '|' to split the messages)",
            placeholder = "Leave empty to disable"
    )
    public static String customJacobMessages = "";
    @Property(
            type = PropertyType.SLIDER,
            name = "Custom Jacob's Contest message chance",
            description = "The chance that the custom Jacob's Contest message will be sent to the chat",
            min = 0,
            max = 100
    )
    public static int customJacobChance = 50;

    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Custom continue messages",
            description = "The messages to send to the chat when the failsafe has been triggered and you want to ask if you can continue (use '|' to split the messages)",
            placeholder = "Leave empty to use a random message"
    )
    public static String customContinueMessages = "";
    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Rotation failsafe messages",
            description = "The messages to send to the chat when the rotation failsafe has been triggered (use '|' to split the messages)",
            placeholder = "Leave empty to use a random message"
    )
    public static String customRotationMessages = "";
    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Teleportation failsafe messages",
            description = "The messages to send to the chat when the teleportation failsafe has been triggered (use '|' to split the messages)",
            placeholder = "Leave empty to use a random message"
    )
    public static String customTeleportationMessages = "";
    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Knockback failsafe messages",
            description = "The messages to send to the chat when the knockback failsafe has been triggered (use '|' to split the messages)",
            placeholder = "Leave empty to use a random message"
    )
    public static String customKnockbackMessages = "";

    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Bedrock failsafe messages",
            description = "The messages to send to the chat when the bedrock failsafe has been triggered (use '|' to split the messages)",
            placeholder = "Leave empty to use a random message"
    )
    public static String customBedrockMessages = "";

    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Dirt failsafe messages",
            description = "The messages to send to the chat when the dirt failsafe has been triggered (use '|' to split the messages)",
            placeholder = "Leave empty to use a random message"
    )
    public static String customDirtMessages = "";

    public CustomFailsafeMessagesPage() {
        super(new File("./farmhelper/customfailsafemessages.toml"), "Custom Failsafe Messages");
        initialize();
    }

    private static CustomFailsafeMessagesPage instance;
    public static CustomFailsafeMessagesPage getInstance() {
        if (instance == null) {
            instance = new CustomFailsafeMessagesPage();
        }

        return instance;
    }

}
