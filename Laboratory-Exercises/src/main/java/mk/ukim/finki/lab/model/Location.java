package mk.ukim.finki.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    public static Long ID = 1L;

    private Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;

    public Location (String name, String address, String capacity, String description) {
        this.id = ID++;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }
}


