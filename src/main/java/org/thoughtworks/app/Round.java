package org.thoughtworks.app;

/**
 * User: crane
 * Date: 13-4-15
 * Time: 下午8:32
 */
public class Round {
    private int firstHit;
    private int secondHit;

    public Round(int firstHit, int secondHit) {
        this.firstHit = firstHit;
        this.secondHit = secondHit;
    }

    public int hitDown() {
        return firstHit + secondHit;
    }
}
