package server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.util.List;

import commons.Activity;

@Transactional
public interface ActivitiesRepository extends JpaRepository<Activity, String> {

    @Query("select a from Activity a where a.energyConsumption <= ?1 order by a.energyConsumption desc")
    public List<Activity> findByEnergyConsumptionDesc(long energyConsumption );

}