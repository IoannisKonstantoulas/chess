import exceptions.MoveException;
import exceptions.PieceCantMoveLikeThatException;

public class King extends Piece {
    public King(String position, String player) {
        super(position, player);
    }

    @Override
    public String movePiece(String currentPosition, String newPosition, Board board, String player) throws MoveException {
        int curX = HelperService.positionAtX(currentPosition);
        int curY = HelperService.positionAtY(currentPosition);
        int newX = HelperService.positionAtX(newPosition);
        int newY = HelperService.positionAtY(newPosition);
        int absMovedX = Math.abs(newX - curX);
        int absMovedY = Math.abs(newY - curY);

        if (absMovedX <= 1 && absMovedY <= 1) {
            return super.movePiece(currentPosition, newPosition, board, player);
        }

        throw new PieceCantMoveLikeThatException();
    }

    @Override
    public String toString() {
        if (player.equals(Vars.WHITE)) {
            return Vars.WHITE_KING;
        }
        if (player.equals(Vars.BLACK)) {
            return Vars.BLACK_KING;
        }
        return "";
    }
}
