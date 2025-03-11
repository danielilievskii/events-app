package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Location;
import mk.ukim.finki.lab.repository.inmemory.InMemoryLocationRepository;
import mk.ukim.finki.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final InMemoryLocationRepository locationRepository;

    public LocationServiceImpl(InMemoryLocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
