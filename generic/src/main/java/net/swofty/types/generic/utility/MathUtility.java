package net.swofty.types.generic.utility;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.timer.TaskSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MathUtility {
    public static double normalizeAngle(double angle, double maximum) {
        return (angle % maximum + maximum) % maximum - (maximum / 2);
    }

    public static <T> T getRandomElement(List<T> list) {
        return list.get(random(0, list.size() - 1));
    }

    public static void delay(Runnable runnable, int ticks) {
        MinecraftServer.getSchedulerManager().scheduleTask(runnable, TaskSchedule.tick(ticks), TaskSchedule.stop());
    }

    public static List<Pos> getCircleAroundPos(Pos pos, double radius, int steps) {
        List<Pos> positions = new ArrayList<>();
        double angleIncrement = 2 * Math.PI / steps;

        for (int i = 0; i < steps; i++) {
            double angle = angleIncrement * i;
            double x = pos.x() + radius * Math.cos(angle);
            double z = pos.z() + radius * Math.sin(angle);
            positions.add(new Pos(x, pos.y(), z));
        }

        return positions;
    }

    public static List<Pos> lookSteps(Pos from, Pos to, int steps) {
        List<Pos> positions = new ArrayList<>();
        double circleRadius = from.distance(to) + 2;

        List<Pos> circlePerimeter = getCircleAroundPos(from, circleRadius, 40);
        Pos multipliedFirstPos = from.add(from.withPitch(0).direction().normalize().mul(3));
        Pos firstPos = getClosestPos(circlePerimeter, multipliedFirstPos);
        Pos lastPos = getClosestPos(circlePerimeter, to);

        positions.add(firstPos);

        double deltaX = (lastPos.x() - firstPos.x()) / (steps - 1);
        double deltaY = (lastPos.y() - firstPos.y()) / (steps - 1);
        double deltaZ = (lastPos.z() - firstPos.z()) / (steps - 1);

        for (int i = 0; i < steps - 1; i++) {
            double newX = firstPos.x() + i * deltaX;
            double newY = firstPos.y() + i * deltaY;
            double newZ = firstPos.z() + i * deltaZ;

            positions.add(getClosestPos(circlePerimeter, new Pos(newX, newY, newZ)));
        }
        positions.add(lastPos);

        return positions;
    }

    public static Pos getClosestPos(List<Pos> allPositions, Pos pos) {
        Pos closestPos = null;
        double distance = Integer.MAX_VALUE;

        for (Pos position : allPositions) {
            if (position.distance(pos) < distance) {
                closestPos = position;
                distance = position.distance(pos);
            }
        }

        return closestPos;
    }

    public static List<Pos> getRangeExcludingSelf(Pos pos, double range) {
        List<Pos> positions = new ArrayList<>();
        for (double x = pos.blockX() - range; x <= pos.blockX() + range; x++) {
            for (double z = pos.blockZ() - range; z <= pos.blockZ() + range; z++) {
                if (!(x == pos.blockX() && z == pos.blockZ())) { // Exclude the center position
                    positions.add(new Pos(x, pos.blockY(), z, 0, 0));
                }
            }
        }

        return positions;
    }

    public static <T> T arrayDValue(Object[] array, int index, T defaultValue) {
        try {
            return (T) array[index];
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Float fromDouble(double value) {
        return (float) value;
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static int random(int min, int max) {
        return (int) Math.round(Math.random() * (max - min) + min);
    }
}