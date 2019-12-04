package task3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Monster {
    private String name;
    private int hit_points;

    public Monster() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHit_points() {
        return hit_points;
    }

    public void setHit_points(int hit_points) {
        this.hit_points = hit_points;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", hit_points=" + hit_points +
                '}';
    }
}
