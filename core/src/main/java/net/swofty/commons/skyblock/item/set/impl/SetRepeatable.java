package net.swofty.commons.skyblock.item.set.impl;

import net.minestom.server.timer.Scheduler;
import net.minestom.server.timer.Task;

public interface SetRepeatable {
    Task getTask(Scheduler scheduler);
}