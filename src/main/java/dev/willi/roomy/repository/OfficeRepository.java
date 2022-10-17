package dev.willi.roomy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.willi.roomy.model.entity.Office;

@Repository
@Transactional(readOnly = true)
public interface OfficeRepository extends JpaRepository<Office, Long>{

	Optional<Office> findByAdress(String adress);

}
