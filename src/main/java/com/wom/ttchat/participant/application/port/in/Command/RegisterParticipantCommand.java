package com.wom.ttchat.participant.application.port.in.Command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RegisterParticipantCommand {

    private final UUID MemberId;
    private final UUID roomId; //obejctid?

}
