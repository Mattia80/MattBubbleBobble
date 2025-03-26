package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {
    private List<LeaderboardRecord> records;
    private static final String FILE_PATH = "";

    public Leaderboard() {
        records = new ArrayList<LeaderboardRecord>();
    }

    public void setRecords(List<LeaderboardRecord> records) {
        this.records = records;
        Collections.sort(records);
    }

    public List<LeaderboardRecord> getTopTenRecords() {
        if (records.size() < 10) {
            return records;
        }
        return records.subList(0, 10);
    }

    public static class LeaderboardRecord implements Comparable<LeaderboardRecord> {
        private String nickname;
        private String avatar;
        private int maxScore;
        private int maxLevel;

        public LeaderboardRecord(String nickname, String avatar, int maxScore, int maxLevel) {
            this.nickname = nickname;
            this.avatar = avatar;
            this.maxScore = maxScore;
            this.maxLevel = maxLevel;
        }

        public String getNickname() {
            return nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public int getMaxScore() {
            return maxScore;
        }

        public int getMaxLevel() {
            return maxLevel;
        }

        @Override
        public int compareTo(LeaderboardRecord o) {
            int compared = Integer.compare(this.getMaxScore(), o.getMaxScore());
            if (compared == 0) {
                compared = Integer.compare(this.getMaxLevel(), o.getMaxLevel());
            }
            return compared;
        }

        @Override
        public String toString() {
            return nickname + ", " + avatar + ", " + maxScore + ", " + maxLevel;
        }
    }

}
