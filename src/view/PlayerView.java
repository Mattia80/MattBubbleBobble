package view;

import controller.PlayerController;
import model.Player;
import utility.GameConstants;
import utility.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import static utility.GameConstants.Player.*;

public class PlayerView extends AbstractView implements Observer {

    private BufferedImage[][] playerAnimations;
    private int aniIndex;
    private int aniTick;
    private int aniSpeed;
    private int currentAnimation;

    public PlayerView(Player player, PlayerController playerController) {
        super(player, playerController);
        player.addObserver(this);
        this.loadAnimation();
    }

    public Player getPlayer() {
        return (Player) this.model;
    }

    public PlayerController getPlayerController() {
        return (PlayerController) this.controller;
    }

    private void loadAnimation() {
        BufferedImage image = LoadSave.loadSprite(LoadSave.PLAYER);
        this.playerAnimations = new BufferedImage[7][6];
        for (int i = 0; i < playerAnimations.length; i++) {
            for (int j = 0; j < playerAnimations[0].length; j++) {
                playerAnimations[i][j] = image.getSubimage(j * 18, i * 18, 18, 18);
            }
        }
    }

    private void updateAnimation() {
        Player player = this.getPlayer();
        int start = this.currentAnimation;
        if (player.isMoving()) {
            this.currentAnimation = RUN;
        } else {
            this.currentAnimation = IDLE;
        }

        if (player.isInAir()) {
            if (player.getAirspeed() < 0) {
                this.currentAnimation = JUMP;
            } else {
                this.currentAnimation = FALL;
            }
        }

        if (player.isAttacking()) {
            this.currentAnimation = ATTACK;
        }
        if (player.isHit()) {
            this.currentAnimation = HIT;
        }

        if (start != currentAnimation) {
            this.resetAniTick();
        }
    }

    public void updateAniTick() {
        this.aniTick++;
        if (this.aniTick >= aniSpeed) {
            this.aniTick = 0;
            this.aniIndex++;
            if (this.aniIndex >= getNumberOfSprites(this.currentAnimation)) {
                this.aniIndex = 0;
                this.getPlayerController().stopAttack();
                if (this.currentAnimation == HIT)
                    this.getPlayerController().dead();
            }
        }
    }

    private void resetAniTick() {
        this.aniIndex = 0;
        this.aniTick = 0;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void render(Graphics g) {

    }
}
