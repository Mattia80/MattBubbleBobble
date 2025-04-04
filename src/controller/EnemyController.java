package controller;

import model.*;
import utility.GameConstants;
import utility.HelpMethods;

import java.time.Duration;

public class EnemyController extends AbstractController implements ControllerInterface {

    private int flipX = 0;
    private int flipW = 1;
    private boolean removed;

    public int getFlipX() {
        return flipX;
    }

    public int getFlipW() {
        return flipW;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public AbstractEnemy getEnemy() {
        return (AbstractEnemy) this.model;
    }

    public void removeEnemy() {
        AbstractEnemy enemy = this.getEnemy();
        enemy.setWidth(0);
        enemy.setHeight(0);
        enemy.getHitbox().height = 0;
        enemy.getHitbox().width = 0;
        this.setRemoved(true);
    }

    public void death() {
        AbstractEnemy enemy = this.getEnemy();
        enemy.setDead(true);
        Game.getInstance().updatePoints(enemy.getPoints());
        this.playing.getLevelController().updateNumberOfEnemiesKilledByOne();
    }

    public void updateMove() {
        AbstractEnemy enemy = this.getEnemy();
        boolean[][] level = this.playing.getLevelController().getLevelTiles();

        Player player = this.playing.getPlayerController().getPlayer();

        // se il nemico non e' morto
        if (!enemy.isDead()) {
            // se il nemico non e' intrappolato in una bolla
            if (!enemy.isBubbled()) {
                // se il nemico non e' in aria
                if (!enemy.isInAir()) {
                    if (!HelpMethods.isEntityOnFloor(enemy.getHitbox(), level))
                        enemy.setInAir(true);
                } else {
                    updateInAir();
                }
                float xSpeed = 0;

                if (enemy instanceof BonnieBo) {

                    float direction = (player.getHitbox().x < enemy.getHitbox().x) ? 1 : -1;

                    if (direction < 0) {
                        this.flipX = 0;
                        this.flipW = 1;
                    } else {
                        this.flipX = (int) enemy.getHitbox().width;
                        this.flipW = -1;
                    }

                    float horizontalStep = enemy.getSpeed() * 0.5f;
                    float newHorizontalPosition = enemy.getHitbox().x + horizontalStep * direction;
                    if (HelpMethods.canMoveHere(newHorizontalPosition, enemy.getHitbox().y,
                            (int) enemy.getHitbox().width, (int) enemy.getHitbox().height, level, true)) {
                        enemy.getHitbox().x = newHorizontalPosition;
                        enemy.setX(newHorizontalPosition);
                    }
                    if (HelpMethods.isEntityOnFloor(enemy.getHitbox(), level) && !enemy.isInAir()) {
                        enemy.setInAir(true);
                        enemy.setAirspeed(GameConstants.Enemy.JUMP_SPEED * 0.4f);
                        enemy.setJumping(true);
                    }

                }

                if (enemy instanceof Blubba) {
                    Blubba blubba = (Blubba) enemy;

                    if (blubba.getHorizontalDirection() == 0)
                        blubba.setHorizontalDirection(-1);
                    if (blubba.getVerticalDirection() == 0)
                        blubba.setVerticalDirection(-1);

                    enemy.setInAir(false);
                    enemy.setAirspeed(0);

                    float diagonalSpeed = enemy.getSpeed() * 0.7071f;

                    float proposedX = enemy.getHitbox().x + diagonalSpeed * blubba.getHorizontalDirection();
                    float proposedY = enemy.getHitbox().y + diagonalSpeed * blubba.getVerticalDirection();

                    if (!HelpMethods.canMoveHere(proposedX, enemy.getHitbox().y, (int) enemy.getHitbox().width,
                            (int) enemy.getHitbox().height, level, true)) {

                        blubba.setHorizontalDirection(-blubba.getHorizontalDirection());
                        proposedX = enemy.getHitbox().x + diagonalSpeed * blubba.getHorizontalDirection();

                        if (!HelpMethods.canMoveHere(proposedX, enemy.getHitbox().y, (int) enemy.getHitbox().width,
                                (int) enemy.getHitbox().height, level, true)) {
                            proposedX = enemy.getHitbox().x;
                        }
                    }

                    if (!HelpMethods.canMoveHere(enemy.getHitbox().x, proposedY, (int) enemy.getHitbox().width,
                            (int) enemy.getHitbox().height, level, true)) {

                        blubba.setVerticalDirection(-blubba.getVerticalDirection());
                        proposedY = enemy.getHitbox().y + diagonalSpeed * blubba.getVerticalDirection();
                        if (!HelpMethods.canMoveHere(enemy.getHitbox().x, proposedY, (int) enemy.getHitbox().width,
                                (int) enemy.getHitbox().height, level, true)) {
                            proposedY = enemy.getHitbox().y;
                        }
                    }

                    enemy.getHitbox().x = proposedX;
                    enemy.setX(proposedX);
                    enemy.getHitbox().y = proposedY;
                    enemy.setY(proposedY);

                    this.checkIfPlayerIsHit(enemy);
                    this.checkIfHitByBubble();

                    return;

                }

                if (enemy.getWalkDirection() == GameConstants.Directions.LEFT) {
                    xSpeed = -GameConstants.Enemy.SPEED;
                    this.flipX = 0;
                    this.flipW = 1;
                } else {
                    xSpeed = GameConstants.Enemy.SPEED;
                    this.flipX = (int) enemy.getHitbox().width;
                    this.flipW = -1;
                }
                boolean isFalling = enemy.getAirspeed() > 0;
                if (HelpMethods.canMoveHere(enemy.getHitbox().x + xSpeed, enemy.getHitbox().y,
                        (int) enemy.getHitbox().width, (int) enemy.getHitbox().height, level, isFalling)) {
                    enemy.getHitbox().x += xSpeed;
                    this.checkIfPlayerIsHit(enemy);
                    this.checkIfHitByBubble();
                    return;
                }

                changeDirection();

                if (enemy.getHitbox().y + enemy.getAirspeed() > GameConstants.Game.GAME_HEIGHT) {
                    int xIndex = (int) (enemy.getHitbox().x / GameConstants.Tiles.TILES_SIZE);
                    int newYIndex = 0;

                    while (newYIndex < level.length && level[newYIndex][xIndex]) {
                        newYIndex++;
                    }
                    enemy.getHitbox().y = newYIndex * GameConstants.Tiles.TILES_SIZE;
                    enemy.setAirspeed(0);
                }

            } else {
                // il nemico e' intrappolato nella bolla
                float newYPos = enemy.getHitbox().y - GameConstants.Enemy.BOXED_SPEED;
                boolean isFalling = enemy.getAirspeed() > 0;
                if (HelpMethods.canMoveHere(enemy.getHitbox().x, newYPos, (int) enemy.getHitbox().width,
                        (int) enemy.getHitbox().height, level, isFalling)) {
                    enemy.getHitbox().y = newYPos;
                }
                this.checkIfKilled();
                long currentTime = System.nanoTime();
                long seconds = Duration.ofNanos(currentTime - enemy.getBubbledAt()).toSeconds();
                if (seconds >= 10) {
                    enemy.setBubbledAt(0);
                    enemy.setBubbled(false);

                    if (!enemy.isAngry()) {
                        enemy.setAngry(true);
                        enemy.setSpeed(enemy.getSpeed() + 0.3f);
                    }

                }

            }

        } else {
            // il nemico e' stato ucciso
            if (!HelpMethods.isEntityOnFloor(enemy.getHitbox(), level) && !this.removed) {
                this.updateInAir();
            } else {
                this.removeEnemy();
//                this.showItemToDrop(enemy);
            }
        }
    }

    private void changeDirection() {
        AbstractEnemy enemy = this.getEnemy();
        if(enemy.getWalkDirection() == GameConstants.Directions.LEFT) {
            enemy.setWalkDirection(GameConstants.Directions.RIGHT);
        } else {
            enemy.setWalkDirection(GameConstants.Directions.LEFT);
        }
    }

    private void updateInAir() {
        AbstractEnemy enemy = this.getEnemy();
        boolean[][] level = this.playing.getLevelController().getLevelTiles();
        boolean isFalling = enemy.getAirspeed() > 0;
        if (HelpMethods.canMoveHere(enemy.getHitbox().x, enemy.getHitbox().y, (int) enemy.getHitbox().width,
                (int) enemy.getHitbox().height, level, isFalling)) {
            enemy.getHitbox().y += enemy.getAirspeed();
            enemy.setAirspeed(enemy.getAirspeed() + GameConstants.Game.GRAVITY);

            if (enemy.getHitbox().y + enemy.getAirspeed() > GameConstants.Game.GAME_HEIGHT) {
                int xIndex = (int) (enemy.getHitbox().x / GameConstants.Tiles.TILES_SIZE);
                int newYIndex = 0;

                while (newYIndex < level.length && level[newYIndex][xIndex]) {
                    newYIndex++;
                }
                enemy.getHitbox().y = newYIndex * GameConstants.Tiles.TILES_SIZE;
                enemy.setAirspeed(0);
            }

        } else {
            enemy.setInAir(false);
            enemy.getHitbox().y = HelpMethods.getEntityYPosUnderRoofOrAboveFloor(enemy.getHitbox(),
                    enemy.getAirspeed());
        }
    }

    private void checkIfPlayerIsHit(AbstractEnemy enemy) {
        Player player = this.playing.getPlayerController().getPlayer();
        if (enemy.getHitbox().intersects(player.getHitbox())) {
            this.playing.getPlayerController().hit();
        }
    }

    private void checkIfKilled() {
        AbstractEnemy enemy = this.getEnemy();
        Player player = this.playing.getPlayerController().getPlayer();

        if (HelpMethods.areEntitiesTouching(enemy.getHitbox(), player.getHitbox())) {
            this.death();
        }
    }

    private void checkIfHitByBubble() {
        AbstractEnemy enemy = this.getEnemy();

        this.playing.getBubbleControllers().stream()
                .filter(bubbleController -> bubbleController.getBubble().canDamage())
                .findFirst()
                .ifPresent(bubbleController -> {
                    Bubble bubble = bubbleController.getBubble();
                    if (HelpMethods.areEntitiesTouching(bubble.getHitbox(), enemy.getHitbox())) {
                        if (bubble instanceof FireBubble) {
                            this.death();
                        } else {
                            enemy.setBubbled(true);
                            enemy.setBubbledAt(System.nanoTime());
                            enemy.setMoving(false);
                            bubble.setExpired(true);
                            bubbleController.removeBubble();
                        }
                    }
                });
    }

    @Override
    public void update() {
        this.updateMove();
        this.model.notifyUpdate();
    }
}
