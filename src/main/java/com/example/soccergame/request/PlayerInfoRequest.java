package com.example.soccergame.request;

import com.example.soccergame.enumeration.EnumPlayerPosition;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerInfoRequest {
    private String firstName;
    private String lastName;
    private String country;
    private EnumPlayerPosition position = EnumPlayerPosition.UNKNOWN;
    private Boolean isInTransferList;
}
