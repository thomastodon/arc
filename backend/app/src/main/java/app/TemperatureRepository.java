package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    Temperature findById(Long id);

    @Query(value = "SELECT * FROM temperature ORDER BY time DESC LIMIT 1", nativeQuery = true)
    Temperature findLatest();
}
