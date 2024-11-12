package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepository {

    public List<Location> findAll() {
        return DataHolder.locations;
    }

    public Location findById(Long id) {
        return DataHolder.locations.stream().filter(location -> location.getId().equals(id)).findFirst().orElse(null);
    }
}
