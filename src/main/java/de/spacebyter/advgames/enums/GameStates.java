package de.spacebyter.advgames.enums;


public enum GameStates {

    LOBBY(true),
    PRELOBBY(false),
    INGAME(false),
    RESTART(false);

    private final boolean join;

    GameStates(boolean join) {
        this.join = join;
    }

    public boolean isJoin() {
        return join;
    }
}
