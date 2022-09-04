package rerfb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rerfb.model.BuildingJPA;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingJPA, Long> {

	@Query(value = "Select * from buildings where owner_id = ?1", nativeQuery = true)
	List<BuildingJPA> getBuildingsByOwnerId(Long id);	
}
