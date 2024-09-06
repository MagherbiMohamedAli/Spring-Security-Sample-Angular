package tn.biramgroup.pointage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.biramgroup.pointage.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
