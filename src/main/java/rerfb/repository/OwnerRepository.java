package rerfb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rerfb.model.OwnerJPA;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerJPA, Long> {

}
