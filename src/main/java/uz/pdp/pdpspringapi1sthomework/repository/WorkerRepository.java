package uz.pdp.pdpspringapi1sthomework.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdpspringapi1sthomework.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
    Page<Worker> findAllByDepartmentId(Integer department_id, Pageable pageable);
}
