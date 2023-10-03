package com.wom.ttchat.chatroom.domain;

public enum ChatStat {
    DIRECT(false), GROUP(true);

    private boolean stat;

    ChatStat(boolean stat) {
        this.stat = stat;
    }

    public boolean getStat() { return stat; }
}
