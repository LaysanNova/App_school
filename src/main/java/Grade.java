public class Grade {

    private Integer level;
    private String mark;

    Grade(Integer level) {
        this.level = level;
    }

    Grade(Integer level, String mark) {
        this.level = level;
        this.mark = mark;
    }

    Integer getLevel() {
        return level;
    }

    String getMark() {
        return mark;
    }

    void setLevel(Integer level) {
        this.level = level;
    }

    void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        if (mark == null) {
            mark = "";
        }
        return level.toString() + mark;
    }
}
