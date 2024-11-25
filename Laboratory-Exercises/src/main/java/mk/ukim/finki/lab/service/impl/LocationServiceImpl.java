package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Location;
import mk.ukim.finki.lab.repository.LocationRepository;
import mk.ukim.finki.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
