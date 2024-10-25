import exceptions.MoveException;
import exceptions.PieceCantMoveLikeThatException;
import exceptions.PieceIsObstructedException;

public class Bishop extends Piece {
    public Bishop(String position, String player) {
        super(position, player);
    }

    @Override
    public String movePiece(String currentPosition, String newPosition, Board board, String player) throws MoveException {
        int curX = HelperService.positionAtX(currentPosition);
        int curY = HelperService.positionAtY(currentPosition);
        int newX = HelperService.positionAtX(newPosition);
        int newY = HelperService.positionAtY(newPosition);
        int movedX = newX - curX;
        int movedY = newY - curY;

        if (Math.abs(movedX) != Math.abs(movedY)) {
            throw new PieceCantMoveLikeThatException();
        }

        int i = movedX / Math.abs(movedX);
        int j = movedY / Math.abs(movedY);

        for (int k = 1; k < Math.abs(movedX); k++) {
            int x = curX + k * i;
            int y = curY + k * j;
            if (board.getTableArray()[x][y] != null) {
                throw new PieceIsObstructedException();
            }
        }
        return super.movePiece(currentPosition, newPosition, board, player);
    }

    @Override
    public String toString() {
        if (player.equals(Vars.WHITE)) {
            return Vars.WHITE_BISHOP;
        }
        if (player.equals(Vars.BLACK)) {
            return Vars.BLACK_BISHOP;
        }
        return "";
    }
}
