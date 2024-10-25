import exceptions.MoveException;
import exceptions.PieceCantMoveLikeThatException;

public class Knight extends Piece {
    public Knight(String position, String player) {
        super(position, player);
    }

    @Override
    public String movePiece(String currentPosition, String newPosition, Board board, String player) throws MoveException {
        int absMovedX = Math.abs(HelperService.positionAtX(newPosition) - HelperService.positionAtX(currentPosition));
        int absMovedY = Math.abs(HelperService.positionAtY(newPosition) - HelperService.positionAtY(currentPosition));

        if (((absMovedX == 2 && absMovedY == 1)) || ((absMovedX == 1) && (absMovedY == 2))) {
            return super.movePiece(currentPosition, newPosition, board, player);
        }
        throw new PieceCantMoveLikeThatException();
    }

    @Override
    public String toString() {
        if (player.equals(Vars.WHITE)) {
            return Vars.WHITE_KNIGHT;
        }
        if (player.equals(Vars.BLACK)) {
            return Vars.BLACK_KNIGHT;
        }
        return "";
    }
}
