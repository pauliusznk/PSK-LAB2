package model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity

public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float weight;
    private float volume;
    private float amount;
    private String contents;
    private String AdditionalNotes;

    public Cargo(float weight, float volume, float amount, String contents, String AdditionalNotes, Destination cargoDestination) {
        this.weight = weight;
        this.volume = volume;
        this.amount = amount;
        this.contents = contents;
        this.AdditionalNotes = AdditionalNotes;
        this.cargoDestination = cargoDestination;
    }

    public Cargo(float weight, float volume, float amount, String contents, String destination, String AdditionalNotes) {
        this.weight = weight;
        this.volume = volume;
        this.amount = amount;
        this.contents = contents;
        this.AdditionalNotes = AdditionalNotes;
    }

    @Override
    public String toString()
    {
        return "cargoID "+id + ": |" + contents +"| weighting: " + weight + "kg, takes up: " + volume +"m^3 of space."
                + "\nAmount of cargo: " + amount
                + "\nNotes: " + AdditionalNotes;

//                "Cargo{" +
//                "id=" + id +
//                ", weight=" + weight +
//                ", volume=" + volume +
//                ", amount=" + amount +
//                ", contents='" + contents + '\'' +
//                ", additionalNotes='" + additionalNotes + '\'' +
//                ", cargoDestination=" + cargoDestination +
//                '}';
    }

    public float calculateTotalValue()
    {
        float total = weight * amount;
        return total;
    }
    @ManyToOne
    private Destination cargoDestination;
}
