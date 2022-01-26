package com.example.soccergame.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class TransferListRequest {

    private String country;
    private Integer age;
    private String firstName;
    private String teamName;

    public TransferListRequest(Map<String, String> customQuery) {
        if (customQuery.containsKey("country")){
            this.country = customQuery.get("country");
        }
        if (customQuery.containsKey("age")){
            this.age = (Integer.valueOf(customQuery.get("age")));
        }
        if (customQuery.containsKey("firstName")){
            this.firstName = customQuery.get("firstName");
        }
        if (customQuery.containsKey("teamName")){
            this.teamName = customQuery.get("teamName");
        }
    }
}
