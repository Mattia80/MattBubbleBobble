package view;

import controller.EnemyController;
import model.AbstractEnemy;
import model.Blubba;
import utility.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import static utility.GameConstants.Enemy.*;

public class EnemyView extends AbstractView implements Observer {

    private BufferedImage[][] enemyAnimations;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 30;
    private int enemyState;

    public EnemyView(AbstractEnemy enemy, EnemyController enemyController) {
        super(enemy, enemyController);
        enemy.addObserver(this);
        this.loadAnimation();

    }

    private void loadAnimation() {
        BufferedImage sprites = LoadSave.loadSprite(LoadSave.ENEMIES);
        this.enemyAnimations = new BufferedImage[7][];
        int[] enemyStates = getEnemyStates();
        int start = 0;

        for (int i = 0; i < enemyStates.length; i++) {
            int sprite = getSprites(i);
            this.enemyAnimations[i] = new BufferedImage[sprite];
            for (int j = 0; j < sprite; j++) {
                int spriteIndex = start + j;
                this.enemyAnimations[i][j] = sprites.getSubimage(spriteIndex * 16, spriteIndex * 16, 16, 16);
            }
            start += sprite;
        }
    }

    private void updateAnimation() {
        AbstractEnemy enemy = this.getEnemy();
        int startAni = this.enemyState;
        if (enemy.isMoving())
            this.enemyState = RUNNING;
        if (enemy.isBubbled()) {
            this.enemyState = BOXED_GREEN;
            if (enemy.isAngry())
                this.enemyState = BOXED_RED;
        }
        if (enemy.isAngry() && !enemy.isBubbled())
            this.enemyState = ANGRY;

        if (enemy.isDead()) {
            this.enemyState = DEAD;
        }
        if (startAni != this.enemyState) {
            this.aniTick = 0;
            this.aniIndex = 0;
        }
    }

    private AbstractEnemy getEnemy() {
        return (AbstractEnemy) this.model;
    }

    private EnemyController getEnemyController() {
        return (EnemyController) this.controller;
    }

    private void updateAniTick() {
        this.aniTick++;
        if (this.aniTick <= this.aniSpeed) {
            this.aniTick = 0;
            this.aniIndex++;
            if (this.aniIndex >= getSprites(enemyState))
                this.aniIndex = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        AbstractEnemy enemy = this.getEnemy();
        if (!(enemy instanceof Blubba)) {
            int flipX = this.getEnemyController().getFlipX();
            int flipW = this.getEnemyController().getFlipW();

            g.drawImage(this.enemyAnimations[this.enemyState][this.aniIndex], (int) (enemy.getHitbox().x + flipX),
                    (int) enemy.getHitbox().y, (int) enemy.getHitbox().width * flipW, (int) enemy.getHitbox().height,
                    null);
            return;
        }

        Blubba blubba = (Blubba) enemy;
        int hDir = blubba.getHorizontalDirection();
        int flipW = (hDir == 1) ? -1 : 1;
        int flipX = (flipW == 1) ? 0 : (int) blubba.getHitbox().width;

        g.drawImage(this.enemyAnimations[this.enemyState][this.aniIndex], (int) (blubba.getHitbox().x + flipX),
                (int) blubba.getHitbox().y, (int) blubba.getHitbox().width * flipW, (int) blubba.getHitbox().height,
                null);

        g.drawImage(this.enemyAnimations[this.enemyState][this.aniIndex], (int) (enemy.getHitbox().x + flipX),
                (int) enemy.getHitbox().y, (int) enemy.getHitbox().width * flipW, (int) enemy.getHitbox().height, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.updateAniTick();
        this.updateAnimation();
    }
}
