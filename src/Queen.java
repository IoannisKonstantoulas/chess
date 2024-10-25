import exceptions.MoveException;
import exceptions.PieceCantMoveLikeThatException;
import exceptions.PieceIsObstructedException;

public class Queen extends Piece {
    public Queen(String position, String player) {
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

        if ((Math.abs(movedX) != Math.abs(movedY)) && (curX != newX) && (curY != newY)) {
            throw new PieceCantMoveLikeThatException();
        }

        if (Math.abs(movedX) == Math.abs(movedY)) {
            int i = movedX / Math.abs(movedX);
            int j = movedY / Math.abs(movedY);

            for (int k = 1; k < Math.abs(movedX); k++) {
                int x = curX + k + i;
                int y = curX + k + j;
                if (board.getTableArray()[x][y] != null) {
                    throw new PieceIsObstructedException();
                }
            }
        }
        if ((curX == newX) || (curY == newY)) {
            if (movedX != 0) {
                int i = movedX / Math.abs(movedX);
                for (int x = curX + i; x != newX; x += i) {
                    if (board.getTableArray()[x][curY] != null) {
                        throw new PieceIsObstructedException();
                    }
                }
            }
            if (movedY != 0) {
                int i = movedY / Math.abs(movedY);
                for (int y = curY + i; y != newX; y += i) {
                    if (board.getTableArray()[curX][y] != null) {
                        throw new PieceIsObstructedException();
                    }
                }
            }
        }

        return super.movePiece(currentPosition, newPosition, board, player);
    }

    @Override
    public String toString() {
        if (player.equals(Vars.WHITE)) {
            return Vars.WHITE_QUEEN;
        }
        if (player.equals(Vars.BLACK)) {
            return Vars.BLACK_QUEEN;
        }
        return "";
    }
}
