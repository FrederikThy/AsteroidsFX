package dk.sdu.cbse.commonAsteroid;

import dk.sdu.cbse.common.data.Entity;

public class Asteroid extends Entity {

    // Starting size
    private int size = 3;

    private boolean shouldSplit = false;

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public boolean isShouldSplit() {
        return shouldSplit;
    }
    public void setShouldSplit(boolean shouldSplit) {
        this.shouldSplit = shouldSplit;
    }
}
