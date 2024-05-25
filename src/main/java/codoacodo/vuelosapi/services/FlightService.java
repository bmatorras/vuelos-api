package codoacodo.vuelosapi.services;

import codoacodo.vuelosapi.exceptions.ResourceNotFoundException;
import codoacodo.vuelosapi.model.Company;
import codoacodo.vuelosapi.model.Dolar;
import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDTO;
import codoacodo.vuelosapi.repository.CompanyRepository;
import codoacodo.vuelosapi.repository.FlightsRepository;

import codoacodo.vuelosapi.utils.FlightUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightsRepository flightsRepository;

    @Autowired
    FlightUtils flightUtils;

    @Autowired
    CompanyRepository companyRepository;

    public Flight createFlight(Flight flight, Long companyID){
        Company company = companyRepository.findById(companyID)
                .orElseThrow(() -> new ResourceNotFoundException("flight", "id", companyID));
        flight.setCompany(company);
        return flightsRepository.save(flight);
    }

    public List<FlightDTO> getAllFlights(){
        double dolarPrice = getDolar();
        List<Flight> flights = flightsRepository.findAll();
        return flightUtils.flightMapper(flights, dolarPrice);
    }

    public Optional<Flight> findFlightByID(Long id) {
        return flightsRepository.findById(id);
    }

    public Optional<Flight> updateFlight(Flight flight) {
        flightsRepository.save(flight);
        return flightsRepository.findById(flight.getId());
    }

    public void deleteFlightByID(Long id) {
        flightsRepository.deleteById(id);
    }

    public List<Flight> getByOrigin(String origin) {
        return flightsRepository.getByOrigin(origin);
    }

//recibe un nro a traves de requestparam y
// hay q retornar los vuelos con precio menor o igual a ese valor
    public List<Flight> getDeals(Integer dealPrice) {
        List<Flight> flights = flightsRepository.findAll();
                return flightUtils.detectDeals(flights, dealPrice);
    }

    private double getDolar() {
        Dolar dolar = flightUtils.fetchDolar();
        return dolar.getAverage();
    }
}
