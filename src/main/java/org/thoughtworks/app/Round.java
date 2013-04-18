package org.thoughtworks.app;

/**
 * User: crane
 * Date: 13-4-15
 * Time: 下午8:32
 */
public class Round {
    public static final int FULL = 10;
    private int firstHit;
    private int secondHit;

    public Round(int firstHit, int secondHit) {
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    public int hitDownAll() {
        return firstHit + secondHit;
    }

    public boolean isSpare() {
        return firstHit+secondHit == FULL;
    }

    public int getFirstHit() {
        return firstHit;
    }
}
