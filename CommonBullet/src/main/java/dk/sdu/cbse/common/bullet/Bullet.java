package dk.sdu.cbse.common.bullet;

import dk.sdu.cbse.common.data.Entity;

public class Bullet extends Entity {
    private float directionX;
    private float directionY;
    private float speed;

    public float getDirectionX() {
        return directionX;
    }

    public void setDirectionX(float directionX) {
        this.directionX = directionX;
    }

    public float getDirectionY() {
        return directionY;
    }

    public void setDirectionY(float directionY) {
        this.directionY = directionY;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
