package MSA.repository;

import MSA.node.Event;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventRepository extends ReactiveNeo4jRepository<Event, Long> {
    @Query("Match(n) return n")
    Flux<Event> findAll();

    Mono<Event> findOneById(Long id);
}