package com.example.soccergame.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PlayerNameGenerator {

    private static Stack<String> fullNamePairStack = new Stack<>();

    private static void updatePlayerNameCache()
    {
        try
        {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://names.drycodes.com/50?nameOptions=boy_names");
            HttpResponse httpresponse = httpclient.execute(httpget);
            Scanner sc = new Scanner(httpresponse.getEntity().getContent());
            while(sc.hasNext()) {
                new ObjectMapper().readValue(sc.nextLine(), List.class).stream()
                        .forEach(fullName -> fullNamePairStack.push(fullName.toString().split("_")[0]));}
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static String getRandomPlayerFirstName()
    {
        if(fullNamePairStack.isEmpty())
            updatePlayerNameCache();
        return fullNamePairStack.pop();
    }

    public static String getRandomPlayerLastName()
    {
        if(fullNamePairStack.isEmpty())
            updatePlayerNameCache();
        return fullNamePairStack.pop();
    }
}
