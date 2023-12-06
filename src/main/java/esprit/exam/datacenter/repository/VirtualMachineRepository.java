package esprit.exam.datacenter.repository;

import esprit.exam.datacenter.entities.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtualMachineRepository extends JpaRepository<VirtualMachine,Integer> {
}
