package dev.doctor4t.wathe.api.event;

import dev.doctor4t.wathe.api.GameMode;
import dev.doctor4t.wathe.cca.GameWorldComponent;
import net.minecraft.server.network.ServerPlayerEntity;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.world.World;

import java.util.List;

import static net.fabricmc.fabric.api.event.EventFactory.createArrayBacked;

public final class GameEvents {

    private GameEvents() {
    }

    public static final Event<OnGameStart> ON_GAME_START = createArrayBacked(OnGameStart.class, listeners -> (gameMode) -> {
        for (OnGameStart listener : listeners) {
            listener.onGameStart(gameMode);
        }
    });

    public static final Event<OnGameStop> ON_GAME_STOP = createArrayBacked(OnGameStop.class, listeners -> (gameMode) -> {
        for (OnGameStop listener : listeners) {
            listener.onGameStop(gameMode);
        }
    });

    public static final Event<OnFinishInitialize> ON_FINISH_INITIALIZE = createArrayBacked(OnFinishInitialize.class, listeners -> (world, gameWorldComponent) -> {
        for (OnFinishInitialize listener : listeners) {
            listener.onFinishInitialize(world, gameWorldComponent);
        }
    });

    public static final Event<OnFinishFinalize> ON_FINISH_FINALIZE = createArrayBacked(OnFinishFinalize.class, listeners -> (world, gameWorldComponent) -> {
        for (OnFinishFinalize listener : listeners) {
            listener.onFinishFinalize(world, gameWorldComponent);
        }
    });

    public static final Event<OnInitializeRoleAnnouncement> ON_INITIALIZE_ROLE_ANNOUNCEMENT = createArrayBacked(OnInitializeRoleAnnouncement.class, listeners -> (world, gameWorldComponent, players, player, killerCount) -> {
        for (OnInitializeRoleAnnouncement listener : listeners) {
            if (listener.onInitializeRoleAnnouncement(world, gameWorldComponent, players, player, killerCount)) {
                return true;
            }
        }
        return false;
    });

    public interface OnGameStart {
        void onGameStart(GameMode gameMode);
    }

    public interface OnGameStop {
        void onGameStop(GameMode gameMode);
    }

    public interface OnFinishInitialize {
        void onFinishInitialize(World world, GameWorldComponent gameComponent);
    }

    public interface OnFinishFinalize {
        void onFinishFinalize(World world, GameWorldComponent gameComponent);
    }

    public interface OnInitializeRoleAnnouncement {
        boolean onInitializeRoleAnnouncement(World world, GameWorldComponent gameComponent, List<ServerPlayerEntity> players, ServerPlayerEntity player, int killerCount);
    }
}
