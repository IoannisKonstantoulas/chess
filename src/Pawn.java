import exceptions.*;

public class Pawn extends Piece {
    public Pawn(String position, String player) {
        super(position, player);
    }

    @Override
    public String movePiece(String currentPosition, String newPosition, Board board, String player) throws MoveException {
        int curX = HelperService.positionAtX(currentPosition);
        int curY = HelperService.positionAtY(currentPosition);
        int newX = HelperService.positionAtX(newPosition);
        int newY = HelperService.positionAtY(newPosition);

        if (!exists) {
            throw new NoPieceException();
        }
        if (!player.equals(this.player)) {
            throw new WrongPlayerException();
        }
        if (currentPosition.equals(newPosition)) {
            throw new PieceCantMoveLikeThatException();
        }


        if ((curX - 1 == newX) || (curX + 1 == newX)) {
            if (curY + 1 == newY) {
                if (player.equals(Vars.BLACK)) {
                    if (!board.ownPieceAtPosition(newX, newY, player)) {
                        if (board.enemyPieceAtPosition(newX, newY, player, true)) {
                            finalMovePawn(board, newPosition, curX, curY, newX, newY);
                            return newPosition;
                        }
                    }
                }
            } else if (curY - 1 == newY) {
                if (player.equals(Vars.WHITE)) {
                    if (!board.ownPieceAtPosition(newX, newY, player)) {
                        if (board.enemyPieceAtPosition(newX, newY, player, true)) {
                            finalMovePawn(board, newPosition, curX, curY, newX, newY);
                            return newPosition;
                        }
                    }
                }
            }
        } else if (curX == newX) {
            if (player.equals(Vars.WHITE)) {
                if ((curY + 2 == newY) && (curY == 1)) {
                    if ((!board.ownPieceAtPosition(newX, newY, player)) && (!board.ownPieceAtPosition(newX, newY - 1, player))) {
                        if ((!board.enemyPieceAtPosition(newX, newY, player, false)) && (!board.enemyPieceAtPosition(newX, newY - 1, player, false))) {
                            finalMovePawn(board, newPosition, curX, curY, newX, newY);
                            return newPosition;
                        }
                    }
                } else if (curY + 1 == newY) {
                    if (!board.ownPieceAtPosition(newX, newY, player)) {
                        if (!board.enemyPieceAtPosition(newX, newY, player, false)) {
                            finalMovePawn(board, newPosition, curX, curY, newX, newY);
                            return newPosition;
                        }
                    }
                }
            } else if (player.equals(Vars.BLACK)) {
                if ((curY - 2 == newY) && (curY == 6)) {
                    if ((!board.ownPieceAtPosition(newX, newY, player)) && (!board.ownPieceAtPosition(newX, newY + 1, player))) {
                        if ((!board.enemyPieceAtPosition(newX, newY, player, false)) && (!board.enemyPieceAtPosition(newX, newY + 1, player, false))) {
                            finalMovePawn(board, newPosition, curX, curY, newX, newY);
                            return newPosition;
                        }
                    }
                } else if (curY - 1 == newY) {
                    if (!board.ownPieceAtPosition(newX, newY, player)) {
                        if (!board.enemyPieceAtPosition(newX, newY, player, false)) {
                            finalMovePawn(board, newPosition, curX, curY, newX, newY);
                            return newPosition;
                        }
                    }
                }
            }
        }
        throw new PieceCantMoveLikeThatException();
    }

    private void finalMovePawn(Board board, String newPosition, int curX, int curY, int newX, int newY) throws OwnPieceAtPositionException {
        board.getTableArray()[newX][newY] = board.getTableArray()[curX][curY];
        board.getTableArray()[curX][curY] = null;
        this.position = newPosition;
    }

    @Override
    public String toString() {
        if (player.equals(Vars.WHITE)) {
            return Vars.WHITE_PAWN;
        }
        if (player.equals(Vars.BLACK)) {
            return Vars.BLACK_PAWN;
        }
        return "";
    }
}
