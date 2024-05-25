package codoacodo.vuelosapi.controller;

import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDTO;
import codoacodo.vuelosapi.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")

public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/add")
    public Flight createFlight(@RequestBody Flight flight, @RequestParam Long companyID){
        return flightService.createFlight(flight, companyID);
    }

    @GetMapping("")
    public List<FlightDTO> getAllFlights(){
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Optional<Flight> findFlightByID(@PathVariable Long id){
        return flightService.findFlightByID(id);
    }

    @PutMapping("/update")
    public Optional<Flight> updateFlight(@RequestBody Flight flight){
        return flightService.updateFlight(flight);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlight(@PathVariable Long id){
        flightService.deleteFlightByID(id);
    }

    @GetMapping("/origin")
    public List<Flight> getByOrigin(@RequestParam String origin){
        return flightService.getByOrigin(origin);
    }

    @GetMapping("/deals")
    public List<Flight> getDeals(@RequestParam Integer price) {
        return flightService.getDeals(price);
    }


}
