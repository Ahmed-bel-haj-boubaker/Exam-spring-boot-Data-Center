package esprit.exam.datacenter.repository;

import esprit.exam.datacenter.entities.DataCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;

public interface DataCenterRepository extends JpaRepository<DataCenter, Integer> {
}
