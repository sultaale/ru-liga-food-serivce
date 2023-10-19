package ru.liga.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.postgresql.geometric.PGpoint;
import ru.liga.util.PGPointType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "type", typeClass = PGPointType.class)
@Table(name = "couriers")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courier_seq_gen")
    @SequenceGenerator(name = "courier_seq_gen", sequenceName = "couriers_seq", allocationSize = 1)
    private Long id;
    private String phone;
    private String status;
    @Type(type = "type")
    private PGpoint coordinates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PGpoint getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(PGpoint coordinates) {
        this.coordinates = coordinates;
    }
}
