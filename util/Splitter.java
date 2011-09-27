package goosey;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class Splitter {

    public static void main(String[] args) throws IOException {
        if (args.length != 1)
            return;
        String alphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
        String locale = "ru";
        String charset = "CP1251";
        ArrayList<ArrayList<String>> volumes = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++)
            volumes.add(new ArrayList<String>());
        
        try (Scanner scanner = new Scanner(
                Files.newBufferedReader(Paths.get(args[0]), Charset.forName(charset)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                   int index = alphabet.indexOf(line.charAt(0));
                   volumes.get(index).add(line);
                }
            }
        }
        
        Path path = Paths.get(args[0]).getParent().resolve(locale);
        if (!Files.isDirectory(path))
            Files.createDirectory(path);
        for (int i = 0; i < volumes.size(); i++) 
            try (BufferedWriter writer = Files.newBufferedWriter(
                   path.resolve("volume." + i), Charset.forName(charset), StandardOpenOption.CREATE)) {
                for (String line : volumes.get(i)) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
