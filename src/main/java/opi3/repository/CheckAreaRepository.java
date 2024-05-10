package opi3.repository;

import opi3.model.NewUser;
import opi3.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckAreaRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByUser(NewUser user);

    void deleteByUser(NewUser user);
}
