package io.backend.springBoot_DTO.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends
        JpaRepository<Location,Long> {

}
