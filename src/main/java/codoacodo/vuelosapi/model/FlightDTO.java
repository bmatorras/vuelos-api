package codoacodo.vuelosapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightDTO {
    private Long id;
    private String origin;
    private String destiny;
    private String departureDate;
    private String arrivalDate;
    private double convertedPrice;
    private String frequency;
}
