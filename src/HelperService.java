public class HelperService {
    public static boolean oppositePlayer(String player1, String player2) {
        if ((player1.equals(Vars.WHITE) && player2.equals(Vars.BLACK))
                || (player1.equals(Vars.BLACK) && player2.equals(Vars.WHITE))) {
            return true;
        }
        return false;
    }

    public static int positionAtX(String position) {
        String posX = position.substring(0, 1);
        switch (posX) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
        }
        return -1;
    }

    public static int positionAtY(String position) {
        return Integer.parseInt(position.substring(1, 2)) - 1;
    }

    public static String switchPlayers(String player) {
        if (player.equals(Vars.WHITE)) {
            return Vars.BLACK;
        }
        return Vars.WHITE;
    }
}
