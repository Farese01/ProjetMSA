package MSA.repository;

import MSA.node.Sport;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SportRepository extends ReactiveNeo4jRepository<Sport, Long> {
    @Query("Match(n) return n")
    Flux<Sport> findAll();

    Mono<Sport> findOneByName(String name);
}