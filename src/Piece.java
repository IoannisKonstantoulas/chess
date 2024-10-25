import exceptions.MoveException;
import exceptions.NoPieceException;
import exceptions.PieceCantMoveLikeThatException;
import exceptions.WrongPlayerException;

public class Piece {

    protected String position;
    protected final String player;
    protected boolean exists;

    public Piece(String position, String player) {
        this.position = position;
        this.player = player;
        this.exists = true;
    }

    public String movePiece(String currentPosition, String newPosition, Board board, String player) throws MoveException {
        int curX = HelperService.positionAtX(currentPosition);
        int curY = HelperService.positionAtY(currentPosition);
        int newX = HelperService.positionAtX(newPosition);
        int newY = HelperService.positionAtY(newPosition);
        if (!exists) {
            throw new NoPieceException();
        }
        if (!player.equals(this.player)){
            throw new WrongPlayerException();
        }
        if (currentPosition.equals(newPosition)) {
            throw new PieceCantMoveLikeThatException();
        }
        board.ownPieceAtPosition(newX, newY, player);
        board.enemyPieceAtPosition(newX, newY, player, true);
        this.position = newPosition;
        board.getTableArray()[newX][newY] = board.getTableArray()[curX][curY];
        board.getTableArray()[curX][curY] = null;
        return newPosition;
    }


    public String getPlayer() {
        return player;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String toString() {
        return "";
    }
}
