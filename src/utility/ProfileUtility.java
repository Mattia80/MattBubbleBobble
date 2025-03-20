package utility;

import model.Profile;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileUtility {
    private static final String DIRECTORY_PATH ="resources/userdata";
    private static final String FONT_PATH ="/font/PressStart2P-Regular.ttf";
    private static Font arcadeBaseFont = null;

    public static List<Profile> loadFromFile(String fileName) {
        String path = DIRECTORY_PATH + "/" + fileName;

        try (var lines = Files.lines(Paths.get(path))) {
            return lines.map(line -> line.split(",")).filter(parts -> parts.length == 4).map(parts -> {
                Profile profile = new Profile();
                profile.setNickname(parts[0]);
                profile.setAvatar(parts[1]);
                return profile;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public static void saveToFile(Profile profile, String fileName) {
        try {
            Path path = Paths.get(DIRECTORY_PATH);
            if(!Files.exists(path)) {
                Files.createDirectory(path);
            }
            Path filePath = Paths.get(DIRECTORY_PATH, fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
                writer.write(profile.getNickname() + "," + profile.getAvatar() + "," + profile.getMaxScore() + ","
                        + profile.getMaxLevel());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Font getArcadeFont(float size) {
        if (arcadeBaseFont == null) {
            arcadeBaseFont = loadArcadeFont();
        }
        return arcadeBaseFont.deriveFont(size);
    }

    private static Font loadArcadeFont() {
        try (InputStream is = ProfileUtility.class.getResourceAsStream(FONT_PATH)) {
            if (is == null) {
                return new Font("Monospaced", Font.BOLD, 18);
            }
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            return baseFont;
        } catch (IOException e) {
           e.printStackTrace();
           return new Font("Monospaced", Font.BOLD, 18);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
