package jg.smartit.cryptodroid.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class TxtFileReader {
    public String[] read(String url) {
        InputStream in = getClass().getResourceAsStream(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        try {
            String l1 = br.readLine();
            String[] data = new String[Integer.parseInt(l1)+2];
            data[0] = l1;
            for (int i = 1; i < data.length; i++) {
                data[i] = br.readLine();
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
