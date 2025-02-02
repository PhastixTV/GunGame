package dev.phastixtv.gungame.MySQL;

public enum MySQLDataType {

    CHAR(255),
    INT(255);

    private final long size;


    MySQLDataType() {
        this.size = -1;
    }

    MySQLDataType(int size) {
        this.size = size;
    }

    public String toSQL() {
        if(size > 0)
            return this.name().toUpperCase() + "(" + this.size + ")";
        return this.name().toUpperCase();
    }
}
