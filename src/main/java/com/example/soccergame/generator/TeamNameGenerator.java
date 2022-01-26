package com.example.soccergame.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;
import java.util.Scanner;

public class TeamNameGenerator {

    public static String getRandomTeamName()
    {
        try
        {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://names.drycodes.com/1?nameOptions=objects");
            HttpResponse httpresponse = httpclient.execute(httpget);
            Scanner sc = new Scanner(httpresponse.getEntity().getContent());
            while(sc.hasNext()) {
                return new ObjectMapper().readValue(sc.nextLine().replace('_',' '), List.class).stream().findFirst().get().toString();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

}
