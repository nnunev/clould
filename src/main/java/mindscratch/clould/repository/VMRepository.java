package mindscratch.clould.repository;

import mindscratch.clould.entity.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VMRepository extends JpaRepository<VirtualMachine, Long> {
}
