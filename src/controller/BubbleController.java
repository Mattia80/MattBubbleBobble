package controller;

import model.Bubble;
import model.FireBubble;
import utility.GameConstants;
import utility.HelpMethods;

import static utility.GameConstants.Item.CANDY_BLUE;
import static utility.GameConstants.Item.CANDY_PINK;

public class BubbleController extends AbstractController implements ControllerInterface{

    private int flipX = 0;
    private int flipW = 0;

    public int getFlipX() {
        return flipX;
    }

    public int getFlipW() {
        return flipW;
    }

    public Bubble getBubble() {
        return (Bubble) this.getModel();
    }

    public void removeBubble() {
        Bubble bubble = this.getBubble();
        bubble.setWidth(0);
        bubble.setHeight(0);
        bubble.getHitbox().height = 0;
        bubble.getHitbox().width = 0;
    }

    private void updateBubblePos() {
        Bubble bubble = this.getBubble();
        boolean[][] level = this.playing.getLevelController().getLevelTiles();

        if (!bubble.isPopped()) {
            if (bubble.isFlying() && !(bubble instanceof FireBubble)) {
                float flyingSpeed = 0.3F;
                float newY = bubble.getHitbox().y - flyingSpeed;

                if (HelpMethods.canMoveHere(bubble.getHitbox().x, newY, (int) bubble.getHitbox().width,
                        (int) bubble.getHitbox().height, level, true)) {
                    bubble.getHitbox().y = newY;
                } else {
                    bubble.setPopped(true);
                }
            } else {
                int bubbleRoute = this.playing.getPlayerController().hasItem(CANDY_PINK)
                        ? GameConstants.Tiles.TILES_SIZE * 6
                        : GameConstants.Tiles.TILES_SIZE * 4;

                if (bubble instanceof FireBubble) {
                    if (bubble.isLeft()) {
                        bubbleRoute = (int) bubble.getShotX();
                    } else {
                        bubbleRoute = (int) (GameConstants.Game.GAME_WIDTH - bubble.getShotX());
                    }
                }

                if (Math.abs(bubble.getHitbox().x - bubble.getShotX()) >= bubbleRoute) {
                    if (bubble instanceof FireBubble) {
                        bubble.setPopped(true);
                    } else {
                        bubble.setFlying(true);
                    }

                } else {
                    int bonus;
                    float newX;

                    if (bubble.isLeft()) {
                        newX = bubble.getHitbox().x - bubble.getSpeed();
                        bonus = -1;
                        this.flipW = -1;
                        this.flipX = (int) bubble.getHitbox().width;
                    } else {
                        newX = bubble.getHitbox().x + bubble.getSpeed();
                        bonus = 1;
                        this.flipW = 1;
                    }

                    if (this.playing.getPlayerController().hasItem(CANDY_BLUE)) {
                        newX += bonus;
                    }

                    if (HelpMethods.canMoveHere(newX, bubble.getHitbox().y, (int) bubble.getHitbox().width,
                            (int) bubble.getHitbox().height, level, true)) {
                        bubble.getHitbox().x = newX;
                    } else {
                        bubble.setPopped(true);
                    }

                }
            }
        } else {
            float flyingSpeed = GameConstants.Bubble.FLYING_SPEED;
            float newY = bubble.getHitbox().y - flyingSpeed;
            if (HelpMethods.canMoveHere(bubble.getHitbox().x, newY, (int) bubble.getHitbox().width,
                    (int) bubble.getHitbox().height, level, true))
                bubble.getHitbox().y = newY;
            else {
                bubble.setPopped(true);
            }
        }

    }

    @Override
    public void update() {
        this.updateBubblePos();
        this.getModel().notifyUpdate();
    }
}
