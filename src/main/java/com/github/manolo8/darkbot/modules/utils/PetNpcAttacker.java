package com.github.manolo8.darkbot.modules.utils;

import com.github.manolo8.darkbot.Main;
import com.github.manolo8.darkbot.config.Config;
import com.github.manolo8.darkbot.core.entities.FakeNpc;

import static com.github.manolo8.darkbot.Main.API;

public class PetNpcAttacker extends NpcAttacker {
    private final Config.KeyBinds KEY_BINDS;

    public PetNpcAttacker(Main main) {
        super(main);
        this.KEY_BINDS = main.config.KEY_BINDS;
    }

    public String status() {
        return target != null ? "Killing npc" + (hero.isAttacking(target) ? " S" : "") + (ability != null ? " A" : "") + (sab ? " SAB" : "") : "Idle";
    }

    public boolean castingAbility() {
        return ability != null;
    }

    public boolean hasTarget() {
        return target != null && !target.removed;
    }

    public void doKillTargetTick() {
        if (target == null || target instanceof FakeNpc) return;
        if (!mapManager.isTarget(target)) {
            lockAndSetTarget();
            return;
        }
        tryAttackOrFix();
    }

    protected void tryAttackOrFix() {
        boolean bugged = !target.health.hpDecreasedIn(3000) && hero.locationInfo.distance(target) < 350
                && System.currentTimeMillis() > (5000 + laserTime + (fixTimes * 5000));
        if (!hero.pet.isAttacking(target) && System.currentTimeMillis() > laserTime) {
            if (bugged) {
                API.rawKeyboardClick(KEY_BINDS.PET_KEY);
                fixTimes++;
            } else if (hero.locationInfo.distance(target) > 800) {
                API.rawKeyboardClick(KEY_BINDS.ROCKET_KEY);
                laserTime = System.currentTimeMillis() + 5000;
            }
        }
    }

    @Override
    public double modifyRadius(double radius) {
        if (hero.target == target && !hero.pet.isAttacking(target) && System.currentTimeMillis() > laserTime) return 850;
        return radius;
    }
}
