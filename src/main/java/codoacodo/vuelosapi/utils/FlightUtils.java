package codoacodo.vuelosapi.utils;

import codoacodo.vuelosapi.model.Dollar;
import codoacodo.vuelosapi.model.Flight;
import codoacodo.vuelosapi.model.FlightDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightUtils {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public List<Flight> detectDeals(List<Flight> flights, Integer dealPrice) {
        return flights.stream()
                .filter(flight -> flight.getPrice() < dealPrice)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> flightMapper(List<Flight> flights, double dolarPrice){
        return flights.stream().map(flight -> new FlightDTO(flight.getId(), flight.getOrigin(), flight.getDestiny(),
                flight.getDepartureDate(), flight.getArrivalDate(), flight.getPrice() * dolarPrice,
                flight.getFrequency())).collect(Collectors.toList());
    }

    public Dollar fetchDollar(){
        RestTemplate restTemplate = restTemplate();
        return restTemplate.getForObject("https://dolarapi.com/v1/dolares/tarjeta", Dollar.class);
    }

}
