package me.eths.player.processors;

import lombok.Getter;
import me.eths.check.Check;
import me.eths.check.checks.combat.clicker.ClickerA;
import me.eths.check.checks.combat.range.RangeA;
import me.eths.check.checks.player.packets.*;
import me.eths.packet.BoltPacket;
import me.eths.player.PlayerData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
public final class CheckProcessor {

    private final PlayerData data;

    private HashSet<Check> checks;

    public CheckProcessor(final PlayerData data) { this.data = data; init(); }

    private void init() {
        checks = new HashSet<>();
        checks.add(new PacketA(data));
        checks.add(new PacketB(data));
        checks.add(new PacketC(data));
        checks.add(new PacketD(data));
        checks.add(new PacketE(data));
        checks.add(new RangeA(data));
        checks.add(new ClickerA(data));
    }

    public void handle(BoltPacket packet) {
        for (Check c : checks) {
            c.handle(packet);
        }
    }
}
