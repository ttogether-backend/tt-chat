package com.wom.ttchat.message.domain;

public enum SystemMessage {
    ENTER("님이 입장하셨습니다."),
    QUIT("님이 퇴장하셨습니다."),
    BAN("님이 강퇴되었습니다.");
    private String msg;

    SystemMessage(String msg) {
        this.msg = msg;
    }

    public String getNamedMsg(String nickname) {
        return nickname + msg;
    }
    public String getMsg() {
        return msg;
    }
}
