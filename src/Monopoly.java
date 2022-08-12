import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

class Monopoly {

    private final List<BoardSpace> boardSpaces;
    private final List<Player> players;
    private final Dice dice;
    private final Map<String, Consumer<String>> commands;
    private final Scanner s;
    private final Deck chance;
    private final Deck communityChest;
    private List<Player> inAuction;
    private Auction auction;
    private int houses, hotels;
    private boolean playing;

    Monopoly() {
        boardSpaces = BoardSpace.importBoardSpaces();
        players = makePlayers();
        commands = makeCommands();
        dice = new Dice();
        s = new Scanner(System.in);
        playing = true;
        chance = new Deck(CardType.CHANCE);
        communityChest = new Deck(CardType.COMMUNITY_CHEST);
        houses = 32;
        hotels = 12;
        play();
    }

    private static String question(String prompt, Predicate<String> check) {
        Scanner s = new Scanner(System.in);

        //Cycle until a correct response is given.
        while (true) {
            System.out.print(prompt);
            String response = s.next();
            if (check.test(response)) {
                return response;
            }
            System.out.printf("Incorrect response.%n");
        }
    }

    List<BoardSpace> getBoardSpaces() {
        return boardSpaces;
    }

    Dice getDice() {
        return dice;
    }

    Player getCurrentPlayer() {
        //Go through players looking for the one whose turn it is.
        for (Player p : players)
            if (p.isTurn()) {
                return p;
            }
        return null;
    }

    List<Player> getPlayers() {
        return players;
    }

    Deck getChance() {
        return chance;
    }

    Deck getCommunityChest() {
        return communityChest;
    }

    int getHouses() {
        return houses;
    }

    void setHouses(int n) {
        houses = n;
    }

    boolean useHouse() {
        //Use a house if there is one to use.
        if (houses > 0) {
            houses--;
        }
        return houses >= 0;
    }

    void unuseHouse() {
        houses++;
    }

    int getHotels() {
        return hotels;
    }

    void setHotels(int n) {
        hotels = n;
    }

    boolean useHotel() {
        //Use a hotel and unuse 4 houses if there is a hotel to use.
        if (hotels > 0) {
            houses += 4;
            hotels--;
        }
        return hotels >= 0;
    }

    boolean unuseHotel() {
        //Unuse a hotel and use 4 houses if there are 4 houses to use.
        if (houses > 3) {
            hotels++;
            houses -= 4;
        }
        return houses >= 0;
    }

    boolean isAuctioning() {
        return !(auction == null) && auction.isRunning();
    }

    Auction getAuction() {
        return auction;
    }

    void startAuction(Player auctioneer, Property property) {
        auction = new Auction(auctioneer, property, players);
    }

    private void play() {
        //Until the game is exited
        while (playing)

            //Cycle through the player's turns
            for (Player p : players) {

                //If someone has won, quit the game.
                if (!playing || winCheck()) {
                    return;
                }
                p.initializeTurn();

                //Interpret the players commands until they end their turn.
                while (p.isTurn()) {
                    dictateCommand(s.nextLine());
                }
            }
    }

    private void dictateCommand(String cmd) {
        Scanner cs = new Scanner(cmd);

        //If there is no command, stop.
        if (!cs.hasNext()) {
            return;
        }

        String first = cs.next();
        //If the command starts with a player's name, execute it through the Player class.
        for (Player p : players)
            if (p.getName().equals(first)) {
                if (cs.hasNext()) {
                    p.executeCommand(this, cs.nextLine());
                } else {
                    System.out.println("No command entered.");
                }
                return;
            }

        executeCommand(cmd);
    }

    private void executeCommand(String cmd) {
        Scanner cs = new Scanner(cmd);

        //If there is no command, stop.
        if (!cs.hasNext()) {
            System.out.println("No command entered.");
            return;
        }

        String first = cs.next();
        //If the command has been defined, execute it.
        if (commands.containsKey(first)) {
            commands.get(first).accept(cs.hasNext() ? cs.nextLine() : null);
        } else {
            System.out.println("Command not recognized.");
        }
    }

    public Map<String, Consumer<String>> makeCommands() {
        Map<String, Consumer<String>> ret = new HashMap<>();

        ret.put("board", this::CMDBoard);
        ret.put("exit", this::CMDExit);
        ret.put("help", this::CMDHelp);

        return ret;
    }

    private List<Player> makePlayers() {
        List<Player> ret = new ArrayList<>();
        int numOfPlayers = Integer
                .parseInt(question("Number of players: ", (in) ->
                        matchesPattern(in, "\\d*")
                                && !in.equals("0")));

        //Create as many players as is specified.
        for (int i = 0; i < numOfPlayers; i++) {
            String name = question("Player " + (i + 1) + "'s name: ", (in) ->
                    matchesPattern(in, "\\w*"));

            ret.add(new Player(name));
        }

        return ret;
    }

    private boolean matchesPattern(String in, String pattern) {
        return Pattern.compile(pattern).matcher(in).matches();
    }

    private boolean winCheck() {
        boolean won = players.size() == 1;

        //If someone has won, print that they won.
        if (won) {
            System.out.printf("\u001B[5m\u001B[1m\u001B[38;5;10m%s Wins!\u001B[0m%n", players.get(0).getName());
        }
        return won;
    }

    private void CMDBoard(String s) {

        //Print a line for each space on the board (to illustrate the game board in the console).
        for (BoardSpace b : boardSpaces) {
            StringBuilder out = new StringBuilder(b.getFormattedName());

            //Align the columns
            while (out.length() - b.getNameExtraLength() < 25) {
                out.append(" ");
            }

            //If its a property and is owned, print the owner
            if (b instanceof Property) {
                Property p = (Property) b;
                if (p.getOwner() != null) out.append("Owner: (").append(p.getOwner().getName()).append(")");
            }

            //Align the columns
            while (out.length() - b.getNameExtraLength() < 40) {
                out.append(" ");
            }

            //If it has houses/hotel, print that.
            if (b instanceof ColorProperty) {
                ColorProperty cp = (ColorProperty) b;
                if (cp.getHouses() == 5) {
                    out.append(" HOTEL");
                }
                else if (cp.getHouses() > 0) {
                    out.append(" ");
                    out.append("H".repeat(Math.max(0, cp.getHouses())));
                }
            }

            //Align the columns
            while (out.length() - b.getNameExtraLength() < 50) {
                out.append(" ");
            }
            int n = 0;

            //Print each player currently on this space on the board.
            for (Player p : players)
                if (boardSpaces.get(p.getPos()) == b) {
                    out.append(n++ > 0 ? ", " : "").append(p.getName());
                }
            System.out.println(out);
        }
    }

    private void CMDExit(String s) {
        getCurrentPlayer().forceEndTurn();
        playing = false;
    }

    public void CMDHelp(String s) {
        String help = "Commands:" +
                "\n" + "name roll                       roll the dice." +
                "\n" + "name buy                        buy property you are on." +
                "\n" + "name auction                    auction the property you are on." +
                "\n" + "name bid #                      bid $# in the current auction." +
                "\n" + "name pass                       remove yourself from the current auction." +
                "\n" + "name pay                        get out of jail by paying $50." +
                "\n" + "name card                       use a \"Get Out of Jail Free\" card." +
                "\n" + "name end                        end your turn." +
                "\n" + "name money                      see how much money you have." +
                "\n" + "name mortgage \"property name\"   mortgage a property." +
                "\n" + "name unmortgage \"property name\" unmortgage a property." +
                "\n" + "name build \"property name\"      build a house/hotel on a property." +
                "\n" + "name unbuild \"property name\"    unbuild a house/hotel from a property." +
                "\n" + "name forfeit                    forfeit the game." +
                "\n" + "board                           see the game board." +
                "\n" + "exit                            quit the game.";
        System.out.println(help);
    }
}
