package codoacodo.vuelosapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String origin;
    private String destiny;
    private String departureDate;
    private String arrivalDate;
    private double price;
    private String frequency;
    @ManyToOne
    @JoinColumn(name = "companyID")
    private Company company;
}