package controller;

import model.Game;
import model.Player;
import model.Profile;
import utility.GameConstants;
import utility.HelpMethods;
import utility.LeaderboardUtility;

import static utility.GameConstants.Item.*;

public class PlayerController extends AbstractController {

    private int flipX = 0;
    private int flipW = 1;

    public int getFlipX() {
        return flipX;
    }

    public int getFlipW() {
        return flipW;
    }

    public Player getPlayer() {
        return (Player) this.model;
    }


    @Override
    public void update() {
        this.updatePlayerPos();
        this.getPlayer().notifyUpdate();
    }

    public boolean hasItem(int item) {
        return this.getPlayer().hasItem(item);
    }

    public void stopAttack() {
        this.getPlayer().setAttacking(false);
    }

    public void dead() {
        Game g = Game.getInstance();
        g.setLives(g.getLives() - 1);
        if (g.getLives() > 0) {
            this.playing.respawnPlayer();
        } else {
//            GameManager game = GameManager.getInstance();
//            Profile currentProfile = game.getProfile().getProfile();
//            ScoreManager scoreManager = new ScoreManager(currentProfile);
//            scoreManager.updateScore((int) g.getPoints());
//            scoreManager.updateLevel(g.getLevel());
//            LeaderboardUtility.saveProfileRecord(currentProfile);
//            game.setGameOverState(new StateGameOver(game));
//            GameState.state = GameState.GAMEOVER;
        }
    }

    private void updatePlayerPos() {
        Player player = this.getPlayer();
        boolean[][] level = this.playing.getLevelController().getLevelTiles();

        player.setMoving(false);

        if (player.isJump())
            this.jump();

        if (!player.isInAir()) {
            if (!HelpMethods.isEntityOnFloor(player.getHitbox(), level))
                player.setInAir(true);
        }

        if (!player.isInAir())
            if ((!player.isLeft() && !player.isRight()) || (player.isLeft() && player.isRight()))
                return;

        float xSpeed = 0;
        float playerSpeed = player.hasItem(SHOE) ? player.getSpeed() + 1 : player.getSpeed();

        if (player.isLeft()) {
            xSpeed -= playerSpeed;
            flipX = player.getWidth();
            flipW = -1;
        }

        if (player.isRight()) {
            xSpeed += playerSpeed;
            flipX = 0;
            flipW = 1;
        }
        boolean isFalling = player.getAirspeed() > 0;

        if (player.isInAir()) {

            boolean canMove = HelpMethods.canMoveHere(player.getHitbox().x, player.getHitbox().y + player.getAirspeed(),
                    (int) player.getHitbox().width, (int) player.getHitbox().height, level, isFalling);
            if (canMove) {
                player.getHitbox().y += player.getAirspeed();
                player.setAirspeed(player.getAirspeed() + GameConstants.Game.GRAVITY);
            } else {
                if (isFalling) {
                    player.getHitbox().y = HelpMethods.getEntityYPosUnderRoofOrAboveFloor(player.getHitbox(),
                            player.getAirspeed());
                    this.resetInAir();
                }
            }
        }

        this.updateXPosition(xSpeed);
        player.setMoving(true);

        if (player.getHitbox().y + player.getAirspeed() > GameConstants.Game.GAME_HEIGHT) {
            int xIndex = (int) (player.getHitbox().x / GameConstants.Tiles.TILES_SIZE);
            int newYIndex = 0;

            while (newYIndex < level.length && level[newYIndex][xIndex]) {
                newYIndex++;
            }
            player.getHitbox().y = newYIndex * GameConstants.Tiles.TILES_SIZE;
            player.setAirspeed(0);

        }
    }

    private void resetInAir() {
        this.getPlayer().setInAir(false);
        this.getPlayer().setAirspeed(0);
    }

    private void updateXPosition(float speed) {
        Player player = this.getPlayer();

        boolean canMove = HelpMethods.canMoveHere(player.getHitbox().x + speed, player.getHitbox().y,
                (int) player.getHitbox().width, (int) player.getHitbox().height,
                this.playing.getLevelController().getLevelTiles(), true);
        if (canMove) {
            player.getHitbox().x += speed;
            if (player.hasItem(RING_CYAN)) {
                Game.getInstance().updatePoints(20);
            }
        } else {
            player.getHitbox().x = HelpMethods.getEntityXPosNextToWall(player.getHitbox(), speed);
        }
    }

    public void hit() {
        Player player = this.getPlayer();

        if (!player.isHit()) {
            player.setHit(true);
        }
    }

    private void jump() {
        Player player = this.getPlayer();
        if (!player.isInAir()) {
            player.setInAir(true);
            player.setAirspeed(GameConstants.Player.JUMP_SPEED);
            if (player.hasItem(RING_PINK)) {
                Game.getInstance().updatePoints(500);
            }
        }
    }


}
