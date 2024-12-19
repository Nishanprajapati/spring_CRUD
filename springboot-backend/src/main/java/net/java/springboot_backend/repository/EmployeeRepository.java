package net.java.springboot_backend.repository;

import jakarta.persistence.QueryHint;
import net.java.springboot_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  //all Crud database methods


    //Using Query
   @Query("select u From Employee u WHERE u.firstName =:n")
   @QueryHints({
           @QueryHint(name = "org.hibernate.readOnly",value = "true"),
           @QueryHint(name = "org.hibernate.fetchSize",value = "5"),
           @QueryHint(name = "org.hibernate.cacheable",value = "true"),
           @QueryHint(name = "javax.persistence.cache.retrieveMode",value = "USE"),
           @QueryHint(name = "javax.persistence.cache.storeMode",value = "USE"),

   })
    public List<Employee> getByFirstName(@Param("n") String name);
}
