package uz.pdp.pdpspringapi1sthomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdpspringapi1sthomework.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
