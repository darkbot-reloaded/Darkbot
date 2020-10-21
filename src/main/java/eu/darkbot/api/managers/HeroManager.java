package eu.darkbot.api.managers;

import eu.darkbot.api.entities.Npc;
import eu.darkbot.api.entities.Ship;
import eu.darkbot.config.ShipConfig;

/**
 * From here you can manage your ship.
 * Where to move, whats your target etc.
 * TODO more docs
 */
public interface HeroManager extends Ship {

    boolean hasTarget();
    Ship getTarget();
    void setTarget(Ship target);

    boolean isInMode(int configuration, Character formation);

    default boolean isInMode(ShipConfig shipConfig) {
        return isInMode(shipConfig.CONFIG, shipConfig.FORMATION);
    }

    boolean setMode(int configuration, Character formation);

    default boolean setMode(ShipConfig shipConfig) {
        return setMode(shipConfig.CONFIG, shipConfig.FORMATION);
    }

    boolean setAttackMode(Npc target);
    boolean setAttackMode();
    boolean setRoamMode();
    boolean setRunMode();
}
