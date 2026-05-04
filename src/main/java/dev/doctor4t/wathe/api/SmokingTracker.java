package dev.doctor4t.wathe.api;

import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SmokingTracker {
    private static final Map<UUID, Integer> smokeCounts = new HashMap<>();

    public static int getSmokeCount(PlayerEntity player) {
        return smokeCounts.getOrDefault(player.getUuid(), 0);
    }

    public static void incrementSmokeCount(PlayerEntity player) {
        smokeCounts.merge(player.getUuid(), 1, Integer::sum);
    }

    public static void setSmokeCount(PlayerEntity player, int count) {
        smokeCounts.put(player.getUuid(), count);
    }

    public static void resetSmokeCount(PlayerEntity player) {
        smokeCounts.remove(player.getUuid());
    }
}
