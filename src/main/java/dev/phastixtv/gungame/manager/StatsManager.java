package dev.phastixtv.gungame.manager;

import dev.phastixtv.gungame.GunGame;
import dev.phastixtv.gungame.MySQL.MySQLDataType;
import dev.phastixtv.gungame.MySQL.MySQLTabel;

import java.util.HashMap;
import java.util.UUID;

public class StatsManager {

    private MySQLTabel tabel;
    private GunGame plugin;

    public StatsManager() {
        plugin = GunGame.INSTANCE;

        HashMap<String, MySQLDataType> colums = new HashMap<>();
        colums.put("uuid", MySQLDataType.CHAR);
        colums.put("kills streak", MySQLDataType.INT);
        colums.put("kills", MySQLDataType.INT);
        colums.put("deaths", MySQLDataType.INT);
        colums.put("level record", MySQLDataType.INT);

        tabel = new MySQLTabel(plugin.getMySqlConnection(), "stats", colums);
    }

    public void setAll(UUID uuid, int killsStreak, int kills, int deaths, int levelRecord, int killsStreakRecord) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            tabel.set("kills streak", killsStreak, condition);
            tabel.set("kills", kills, condition);
            tabel.set("deaths", deaths, condition);
            tabel.set("level record", levelRecord, condition);
            tabel.set("kills streak record", killsStreakRecord, condition);
        } else {
            tabel.set("uuid", uuid.toString(), condition);
            tabel.set("kills streak", killsStreak, condition);
            tabel.set("kills", kills, condition);
            tabel.set("deaths", deaths, condition);
            tabel.set("level record", levelRecord, condition);
        }
    }

    public void setKillsStreak(UUID uuid, int killsStreak) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            tabel.set("kills streak", killsStreak, condition);
        } else {
            tabel.set("uuid", uuid.toString(), condition);
            tabel.set("kills streak", killsStreak, condition);
        }
    }

    public void setDeaths(UUID uuid, int deaths) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            tabel.set("deaths", deaths, condition);
        } else {
            tabel.set("uuid", uuid.toString(), condition);
            tabel.set("deaths", deaths, condition);
        }
    }

    public void setKills(UUID uuid, int kills) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            tabel.set("kills", kills, condition);
        } else {
            tabel.set("uuid", uuid.toString(), condition);
            tabel.set("kills", kills, condition);
        }
    }

    public void setLevelRecord(UUID uuid, int levelReocrd) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            tabel.set("level record", levelReocrd, condition);
        } else {
            tabel.set("uuid", uuid.toString(), condition);
            tabel.set("level record", levelReocrd, condition);
        }
    }

    public int getKillsStreak(UUID uuid) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            return tabel.getInt("kills streak", condition);
        }
        return 0;
    }

    public int getKills(UUID uuid) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            return tabel.getInt("kills", condition);
        }
        return 0;
    }

    public int getDeaths(UUID uuid) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            return tabel.getInt("deaths", condition);
        }
        return 0;
    }

    public int getLevelRecord(UUID uuid) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            return tabel.getInt("level record", condition);
        }
        return 0;
    }

    public int getKillsStreakRecord(UUID uuid) {
        MySQLTabel.Condition condition = new MySQLTabel.Condition("uuid", uuid.toString());
        if (tabel.exits(condition)) {
            return tabel.getInt("kills streak record", condition);
        }
        return 0;
    }


}
