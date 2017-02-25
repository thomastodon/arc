package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    Temperature findById(Long id);
}
