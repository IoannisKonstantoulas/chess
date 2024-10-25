import exceptions.OwnPieceAtPositionException;

public class Board {
    private Piece[][] tableArray;

    public Board(boolean oldGame) {
        tableArray = new Piece[8][8];
        if (!oldGame) {
            init();
        }
    }

    private void init() {
        //White init
        tableArray[0][0] = new Rook("1a", Vars.WHITE);
        tableArray[1][0] = new Knight("1b", Vars.WHITE);
        tableArray[2][0] = new Bishop("1c", Vars.WHITE);
        tableArray[3][0] = new Queen("1d", Vars.WHITE);
        tableArray[4][0] = new King("1e", Vars.WHITE);
        tableArray[5][0] = new Bishop("1f", Vars.WHITE);
        tableArray[6][0] = new Knight("1g", Vars.WHITE);
        tableArray[7][0] = new Rook("1h", Vars.WHITE);
        tableArray[0][1] = new Pawn("2a", Vars.WHITE);
        tableArray[1][1] = new Pawn("2b", Vars.WHITE);
        tableArray[2][1] = new Pawn("2c", Vars.WHITE);
        tableArray[3][1] = new Pawn("2d", Vars.WHITE);
        tableArray[4][1] = new Pawn("2e", Vars.WHITE);
        tableArray[5][1] = new Pawn("2f", Vars.WHITE);
        tableArray[6][1] = new Pawn("2g", Vars.WHITE);
        tableArray[7][1] = new Pawn("2h", Vars.WHITE);
        //Black init
        tableArray[0][7] = new Rook("8a", Vars.BLACK);
        tableArray[1][7] = new Knight("8b", Vars.BLACK);
        tableArray[2][7] = new Bishop("8c", Vars.BLACK);
        tableArray[3][7] = new Queen("8d", Vars.BLACK);
        tableArray[4][7] = new King("8e", Vars.BLACK);
        tableArray[5][7] = new Bishop("8f", Vars.BLACK);
        tableArray[6][7] = new Knight("8g", Vars.BLACK);
        tableArray[7][7] = new Rook("8h", Vars.BLACK);
        tableArray[0][6] = new Pawn("7a", Vars.BLACK);
        tableArray[1][6] = new Pawn("7b", Vars.BLACK);
        tableArray[2][6] = new Pawn("7c", Vars.BLACK);
        tableArray[3][6] = new Pawn("7d", Vars.BLACK);
        tableArray[4][6] = new Pawn("7e", Vars.BLACK);
        tableArray[5][6] = new Pawn("7f", Vars.BLACK);
        tableArray[6][6] = new Pawn("7g", Vars.BLACK);
        tableArray[7][6] = new Pawn("7h", Vars.BLACK);
    }

    public boolean ownPieceAtPosition(int x, int y, String player) throws OwnPieceAtPositionException {
        if (tableArray[x][y] == null){
            return false;
        }
        if (player.equals(tableArray[x][y].getPlayer())) {
            throw new OwnPieceAtPositionException();
        }
        return false;
    }

    public boolean enemyPieceAtPosition(int x, int y, String player, boolean canEat) {
        if (tableArray[x][y] == null){
            return false;
        }
        if (HelperService.oppositePlayer(player, tableArray[x][y].getPlayer())) {
            if (canEat) {
                tableArray[x][y].setExists(false);
            }
            return true;
        }
        return false;
    }

    public void setPiece(String piece, int x, int y) {
        String posY = Integer.toString(y - 1);
        String posX = "";
        switch (x) {
            case 1:
                posX = "a";
                break;
            case 2:
                posX = "b";
                break;
            case 3:
                posX = "c";
                break;
            case 4:
                posX = "d";
                break;
            case 5:
                posX = "e";
                break;
            case 6:
                posX = "f";
                break;
            case 7:
                posX = "g";
                break;
            case 8:
                posX = "h";
                break;
        }
        String position = posY + posX;
        if (piece.equals(Vars.WHITE_PAWN)) {
            tableArray[x - 1][y - 1] = new Pawn(position, Vars.WHITE);
        } else if ((piece.equals(Vars.WHITE_KING))) {
            tableArray[x - 1][y - 1] = new King(position, Vars.WHITE);
        } else if ((piece.equals(Vars.WHITE_QUEEN))) {
            tableArray[x - 1][y - 1] = new Queen(position, Vars.WHITE);
        } else if ((piece.equals(Vars.WHITE_BISHOP))) {
            tableArray[x - 1][y - 1] = new Bishop(position, Vars.WHITE);
        } else if ((piece.equals(Vars.WHITE_KNIGHT))) {
            tableArray[x - 1][y - 1] = new Knight(position, Vars.WHITE);
        } else if ((piece.equals(Vars.WHITE_ROOK))) {
            tableArray[x - 1][y - 1] = new Rook(position, Vars.WHITE);
        } else if (piece.equals(Vars.BLACK_PAWN)) {
            tableArray[x - 1][y - 1] = new Pawn(position, Vars.BLACK);
        } else if ((piece.equals(Vars.BLACK_KING))) {
            tableArray[x - 1][y - 1] = new King(position, Vars.BLACK);
        } else if ((piece.equals(Vars.BLACK_QUEEN))) {
            tableArray[x - 1][y - 1] = new Queen(position, Vars.BLACK);
        } else if ((piece.equals(Vars.BLACK_BISHOP))) {
            tableArray[x - 1][y - 1] = new Bishop(position, Vars.BLACK);
        } else if ((piece.equals(Vars.BLACK_KNIGHT))) {
            tableArray[x - 1][y - 1] = new Knight(position, Vars.BLACK);
        } else if ((piece.equals(Vars.BLACK_ROOK))) {
            tableArray[x - 1][y - 1] = new Rook(position, Vars.BLACK);
        }
    }

    public Piece[][] getTableArray() {
        return tableArray;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (int j = 9; j >= 0; j--) {
            for (int i = 0; i <= 9; i++) {
                if ((i == 0 || i == 9) || (j == 0 || j == 9)) {
                    if (i == j || (i == 0 && j == 9) || (i == 9 && j == 0)) {
                        returnString.append(" ");
                    } else if (i == 0 || i == 9) {
                        if (j > 0 && j < 9) {
                            returnString.append(j);
                        }
                    } else if (j == 0 || j == 9) {
                        if (i > 0 && i < 9) {
                            switch (i) {
                                case 1:
                                    returnString.append("a");
                                    break;
                                case 2:
                                    returnString.append("b");
                                    break;
                                case 3:
                                    returnString.append("c");
                                    break;
                                case 4:
                                    returnString.append("d");
                                    break;
                                case 5:
                                    returnString.append("e");
                                    break;
                                case 6:
                                    returnString.append("f");
                                    break;
                                case 7:
                                    returnString.append("g");
                                    break;
                                case 8:
                                    returnString.append("h");
                                    break;
                            }
                        }
                    }
                } else if ((i < 9 && i > 0) && (j < 9 && j > 0)) {
                    if (tableArray[i - 1][j - 1] == null) {
                        returnString.append(" ");
                    } else {
                        returnString.append(tableArray[i - 1][j - 1].toString());
                    }
                }
            }
            returnString.append("\n");
        }
        return returnString.toString();
    }
}
