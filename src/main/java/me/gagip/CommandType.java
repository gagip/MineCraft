package me.gagip;

import java.util.Arrays;
import java.util.Optional;

public enum CommandType {
    NONE("", new String[] {}),
    SAVE_LOCATION("좌표저장", new String[] {}),
    TELEPORT("텔레포트", new String[] {"좌표이동"});

    public final String value;
    public final String[] aliases;

    CommandType(String value, String[] aliases) {
        this.value = value;
        this.aliases = aliases;
    }

    public static CommandType getType(String value) {
        for (CommandType type : CommandType.values()) {
            if (type.value.equals(value) || Arrays.asList(type.aliases).contains(value))
                return type;
        }
        return NONE;
    }
}
