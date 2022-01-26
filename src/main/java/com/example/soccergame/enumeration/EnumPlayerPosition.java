package com.example.soccergame.enumeration;

public enum EnumPlayerPosition {
    UNKNOWN(0),
    GOALKEEPER(1),
    DEFENDER(2),
    MIDFIELDER(3),
    ATTACKER(4);

    int position;

    EnumPlayerPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }
}
