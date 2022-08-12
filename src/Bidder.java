class Bidder {
    private final Player player;
    private int bid;

    Bidder(Player p) {
        player = p;
        bid = 0;
    }

    Player getPlayer() {
        return player;
    }

    int getBid() {
        return bid;
    }

    void setBid(int bid) {
        this.bid = bid;
    }
}