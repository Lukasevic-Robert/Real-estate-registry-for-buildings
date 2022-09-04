package rerfb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rerfb.model.AddressJPA;

@Repository
public interface AddressRepository extends JpaRepository<AddressJPA, Long> {

}
