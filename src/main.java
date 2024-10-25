import exceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) throws IOException {
        Board board = new Board(false);
        Scanner scanner = new Scanner(System.in);
        FileWriter writer;
        String name;
        String command;
        String player = Vars.WHITE;
        System.out.println("===========================");
        System.out.println("Chess!");
        System.out.println("===========================");
        System.out.println("to start playing type the square you want to move from, to the square you want to move to");
        while (true) {
            System.out.println(player + " to play");
            System.out.println("Please enter your command (type help to show the help menu)");
            System.out.println(board);

            command = scanner.nextLine();
            if (command.equals("quit")) {
                break;
            }
            if (command.equals("new")) {
                board = new Board(false);
            }
            if (command.equals("help")) {
                System.out.println("===========================");
                System.out.println("Help");
                System.out.println("===========================");
                System.out.println("");
                System.out.println("To start a new game type new");
                System.out.println("To save a game type save <game_name>");
                System.out.println("To load a game type load <game_name>");
                System.out.println("To quit type quit");
            }
            if (Pattern.compile("save .*").matcher(command).matches()) {
                name = command.substring(5);
                writer = new FileWriter(name);
                writer.write(board.toString());
                writer.close();
                System.out.println("===========================");
                System.out.println("Save");
                System.out.println("===========================");
                System.out.println("game saved successfully");
                System.out.println("");
                System.out.println("New Game started");
                board = new Board(false);
                System.out.println(board);
            }
            if (Pattern.compile("load .*").matcher(command).matches()) {
                board = new Board(true);
                System.out.println("===========================");
                System.out.println("Load");
                System.out.println("===========================");
                name = command.substring(5);
                try (Stream<String> lines = Files.lines(Paths.get(name))) {
                    int i = 9;
                    for (String line : lines.collect(Collectors.toList())) {
                        if (i == 9) {
                            i--;
                            continue;
                        }
                        if (i == 0) {
                            break;
                        }
                        for (int j = 1; j < 9; j++) {
                            board.setPiece(String.valueOf(line.charAt(j)), j, i);
                        }
                        i--;
                    }
                    System.out.println("game loaded successfully");
                    System.out.println("---------------------------");
                    System.out.println(board);
                } catch (IOException e) {
                    System.out.println("");
                    System.out.println("no such file");
                    board = new Board(false);
                }
            }
            if (Pattern.compile("[a-h]\\d[a-h]\\d").matcher(command.substring(0, 4)).matches()) {
                int curX = HelperService.positionAtX(command.substring(0, 2));
                int curY = HelperService.positionAtY(command.substring(0, 2));
                try {
                    board.getTableArray()[curX][curY].movePiece(command.substring(0, 2), command.substring(2, 4), board, player);
                    player = HelperService.switchPlayers(player);
                } catch (WrongPlayerException e) {
                    System.out.println("---------------------------");
                    System.out.println("You tried to move the other players piece");
                    continue;
                } catch (NoPieceException e) {
                    System.out.println("---------------------------");
                    System.out.println("No piece in the origin square");
                    continue;
                } catch (PieceCantMoveLikeThatException e) {
                    System.out.println("---------------------------");
                    System.out.println("This piece cannot move like that");
                    continue;
                } catch (OwnPieceAtPositionException e) {
                    System.out.println("---------------------------");
                    System.out.println("You tried to move to a square your piece occupied already");
                    continue;
                } catch (PieceIsObstructedException e) {
                    System.out.println("---------------------------");
                    System.out.println("The move you tried to make is obstructed by another piece");
                    continue;
                } catch (MoveException e) {
                    System.out.println("---------------------------");
                    System.out.println("This is an illegal move");
                    continue;
                }

            }
        }
    }
}
