package codoacodo.vuelosapi.services;

import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightsRepository flightsRepository;

    public void createFlight(Flight flight){
        flightsRepository.save(flight);
    }

    public List<Flight> getAllFlights(){
        return flightsRepository.findAll();
    }

    public Flight findFlightByID(Long id) {
        return flightsRepository.findById(id).orElse(null);
    }

    public Flight updateFlight(Flight flight) {
        flightsRepository.save(flight);
        return flightsRepository.findById(flight.getId()).orElse(null);
    }

    public void deleteFlightByID(Long id) {
        flightsRepository.deleteById(id);
    }

}
