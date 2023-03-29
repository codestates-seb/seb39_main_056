package com.noterror.app;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGptApiConsole {
    public static void chatGPT(String text) throws Exception {

        String url = "https://api.openai.com/v1/completions";

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + "openAiKey");

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 2048);
        data.put("temperature", 0.3);
        data.put("top_p",1);
        data.put("frequency_penalty", 0);
        data.put("presence_penalty",0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        System.out.println("AI : " + new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("나 : ");
        String line;
        while( (line = br.readLine()) != null) {

            chatGPT(line);
            System.out.println();
            System.out.print("나 : ");
        }
        br.close();
    }
}
