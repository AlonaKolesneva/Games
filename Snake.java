package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public void setDirection(Direction direction) {
        if (this.direction.equals(Direction.LEFT)){
            if (snakeParts.get(0).x== snakeParts.get(1).x) return;
            this.direction = direction.equals(Direction.RIGHT) ? this.direction : direction;
        }
        else if (this.direction.equals(Direction.RIGHT)){
            if (snakeParts.get(0).x== snakeParts.get(1).x) return;
            this.direction = direction.equals(Direction.LEFT) ? this.direction : direction;
        }
        else if (this.direction.equals(Direction.UP)) {
            if (snakeParts.get(0).y== snakeParts.get(1).y) return;
            this.direction = direction.equals(Direction.DOWN) ? this.direction : direction;
        }
        else if (this.direction.equals(Direction.DOWN)) {
            if (snakeParts.get(0).y == snakeParts.get(1).y) return;
            this.direction = direction.equals(Direction.UP) ? this.direction : direction;
        }
    }

    public  Snake (int x, int y){
        GameObject part1 = new GameObject (x,y);
        GameObject part2 = new GameObject (x+1,y);
        GameObject part3 = new GameObject (x+2,y);
        snakeParts.add(part1);
        snakeParts.add(part2);
        snakeParts.add(part3);
    }

    public void draw(Game game){
        boolean flag = true;
        if (isAlive) {
            for (GameObject part: snakeParts) {
                    if (flag) {
                        game.setCellValueEx(part.x, part.y, Color.NONE, HEAD_SIGN, Color.BLACK, 75);
                        flag = false;
                    } else
                        game.setCellValueEx(part.x, part.y, Color.NONE, BODY_SIGN, Color.BLACK, 75);
            }
        }
        else {
            for (GameObject part: snakeParts) {
                if (flag) {
                    game.setCellValueEx(part.x, part.y, Color.NONE, HEAD_SIGN, Color.RED, 75);
                    flag = false;
                } else
                    game.setCellValueEx(part.x, part.y, Color.NONE, BODY_SIGN, Color.RED, 75);
            }
        }
    }

    public void move(Apple apple){
        GameObject temp = createNewHead();
        if (apple.x == temp.x && apple.y == temp.y){
            apple.isAlive=false;

            if (checkCollision(temp)){
                isAlive = false;
                return;
            }
            snakeParts.add(0, temp);
        }
        else {

            if (temp.x < 0 || temp.x >= SnakeGame.WIDTH || temp.y < 0 || temp.y >= SnakeGame.HEIGHT) {
                isAlive = false;
                return;
            }
            if (checkCollision(temp)){
                isAlive = false;
                return;
            }
            snakeParts.add(0, temp);
            removeTail();
        }
    }

    public GameObject createNewHead() {
        switch (direction){
            case UP:return new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
            case DOWN:return new GameObject(snakeParts.get(0).x, snakeParts.get(0).y +1);
            case LEFT:return new GameObject(snakeParts.get(0).x-1, snakeParts.get(0).y);
            case RIGHT:return new GameObject(snakeParts.get(0).x+1, snakeParts.get(0).y);
        }
        return null;
    }

    public void removeTail(){
        snakeParts.remove(snakeParts.size()-1);
    }

    public boolean checkCollision(GameObject element){
       return snakeParts.stream().anyMatch(part->(part.x== element.x && part.y== element.y));
    }

    public int getLength(){
        return snakeParts.size();
    }

}
