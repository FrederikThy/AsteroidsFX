package dk.sdu.cbse.common;

import javafx.scene.paint.Color;
import java.util.UUID;

public class Entity {
    private final UUID id = UUID.randomUUID();

    private float x;
    private float y;

    private float velocityX;
    private float velocityY;
    private float rotation;
    private float radius;
    private Color color;
    private String type;
    private String owner;

    public UUID getId() {
        return id;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getVelocityX() {
        return velocityX;
    }
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }
    public float getVelocityY() {
        return velocityY;
    }

    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
