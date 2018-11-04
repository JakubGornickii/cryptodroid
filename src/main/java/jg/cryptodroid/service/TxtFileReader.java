package jg.cryptodroid.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class TxtFileReader {
    public String[] readExchangeData(String url) {
        InputStream in = getClass().getResourceAsStream(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        try {
            String l1 = br.readLine();
            String[] data = new String[Integer.parseInt(l1)+3];
            data[0] = l1;
            for (int i = 1; i < data.length; i++) {
                data[i] = br.readLine();
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public String[] readExchangeList(){
        InputStream in = getClass().getResourceAsStream("/static/exchange.list");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        try {
            String numberOfLines = br.readLine();
            String[] data = new String[Integer.parseInt(numberOfLines)];
            for (int i = 0; i < data.length; i++) {
                data[i] = br.readLine();
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
