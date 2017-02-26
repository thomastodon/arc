package app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime time;
    private Double degrees;

    public Temperature(LocalDateTime time, Double degrees) {
        this.time = time;
        this.degrees = degrees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getDegrees() {
        return degrees;
    }

    public void setDegrees(Double degrees) {
        this.degrees = degrees;
    }
}
