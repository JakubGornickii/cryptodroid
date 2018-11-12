package jg.cryptodroid.initTEST;

import jg.cryptodroid.enums.CoinList;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public class TestPaser {
    public void test(String url) {
        List<String> stringList = new ArrayList<>();
        EnumSet<CoinList> set = EnumSet.allOf(CoinList.class);
        System.out.println(set.size());
        for (CoinList coinList : set) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                Object o = restTemplate.getForObject(url.replace("{TAG}", coinList.name()).replace("{LTAG}", coinList.name().toLowerCase()), Object.class);
                Map<?,?> map = (Map)o;
                if ((map.get("status").equals("ok"))){
                stringList.add(coinList.name());
                   System.out.println("Dodano" +coinList.name());}
                   else {
                    System.out.println("Nie dodano " + coinList.name());
                }
            } catch (RestClientResponseException e) {
                System.out.println(e.getLocalizedMessage());
            }
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("newExchange.txt", "UTF-8");
                writer.println(stringList.size());
                writer.println("EXCHENGE NAME");
                writer.println(url);
                for (String s : stringList) {
                    writer.println(s+",0.0,0.0");
                }
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }
}
