package com.javarush.games.snake;

import com.javarush.engine.cell.*;


public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Apple apple;
    private Snake snake;
    private int turnDelay;
    private boolean isGameStopped;
    private static final int GOAL = 20;
    private int score;
    private void createGame(){
        snake =new Snake( WIDTH / 2, HEIGHT / 2);
        createNewApple();
        isGameStopped = false;
        score=0;
        setScore(score);
        drawScene();
        turnDelay = 200;
        setTurnTimer(turnDelay);



    }
    private void drawScene(){
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.WHITE,"");
            }
        }
        snake.draw(this);
        apple.draw(this);

    }
    public void initialize() {
        this.setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        if (apple.isAlive == false) {
            score+=5;
            setScore(score);
            turnDelay-=7;
            setTurnTimer(turnDelay);
            createNewApple();
        }
        if (snake.isAlive == false) gameOver();
        if (snake.getLength()>GOAL) win();
        snake.move(apple);
        drawScene();

    }

    @Override
    public void onKeyPress(Key key) {

        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }
        else if (key == Key.SPACE) {
            if (isGameStopped == true) createGame();
        }


    }

    private void createNewApple() {
        do { apple = new Apple(getRandomNumber (WIDTH), getRandomNumber(HEIGHT) );}
        while (snake.checkCollision(apple));
     }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped=true;
        showMessageDialog(Color.BLACK, "You lose! Push space to restart", Color.BISQUE, 40);

    }

    private void win(){
        stopTurnTimer();
        isGameStopped=true;
        showMessageDialog(Color.BLACK, "You win! Push space to restart", Color.BISQUE, 40);
    }

}
