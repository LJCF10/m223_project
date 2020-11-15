package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private String usage;

    @OneToMany
    private List<Entry> entries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getfloor() {
        return floor;
    }

    public void setfloor(int floor) {
        this.floor = floor;
    }

    public String getusage() {
        return usage;
    }

    public void setusage(String usage) {
        this.usage = usage;
    }
}
