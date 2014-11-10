package mb.impostor.namegenerator;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * NameGenerator Class is used to generate names of different types
 * requires internet connection
 * 
 * @author mb
 */
public class NameGenerator {
    public static void main(String[] args) throws IOException {
        NameGenerator name = new NameGenerator();
        System.out.println(name.randomNameGenerator());
    }
    /**
     * Generates a random name of either gender 
     * @return random name
     * @throws IOException 
     */
    public String randomNameGenerator() throws IOException {

        //Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        //String url = args[0];
        String returnName = null;
        String url = "http://www.behindthename.com/random/random.php?number=2&gender=m&surname=&all=yes";
        print("Fetching %s...", url);
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img")) {
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            } else {
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
            }
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            if (link.attr("abs:href").equalsIgnoreCase("http://www.behindthename.com/name/" + trim(link.text(), 35))) {
                print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
                if (returnName == null) {
                    returnName = trim(link.text(), 35);
                } else {
                    returnName = returnName + " " + trim(link.text(), 35);
                }
            }
        }
        return returnName;
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }

    }
}
