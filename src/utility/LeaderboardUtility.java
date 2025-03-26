package utility;

import model.Leaderboard;
import model.Profile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderboardUtility {
    private static final String DIRECTORY = "resources/userdata/";
    private static final String FILE_NAME = "leaderboard.txt";

    public static void saveProfileRecord(Profile profile) {
        String path = DIRECTORY + FILE_NAME;
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            String record = profile.getNickname() + "," + profile.getAvatar() + "," + profile.getMaxScore() + "," + profile.getMaxLevel();
            writer.write(record);
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Leaderboard.LeaderboardRecord> loadLeaderboardRecords() {
        Path path = Paths.get(DIRECTORY, FILE_NAME);
        List<Leaderboard.LeaderboardRecord> records = new ArrayList<>();
        File file = new File(DIRECTORY + FILE_NAME);
        if (!file.exists()) {
            return records;
        }

        try {
            return Files.lines(path)
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length >= 4)
                    .map(parts -> new Leaderboard.LeaderboardRecord(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])))
                    .collect(Collectors.toList());

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
