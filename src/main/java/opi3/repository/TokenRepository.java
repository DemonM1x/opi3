package opi3.repository;

import opi3.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join NewUser u
      on t.user.id = u.id
      where u.id = :userId and (t.expired = false or t.revoked = false)
      """)
    List<Token> findAllValidTokenByUser(@Param("userId") Long id);

    Optional<Token> findByToken(String token);
}
