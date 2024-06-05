package MSA.repository;

import MSA.node.Site;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SiteRepository extends ReactiveNeo4jRepository<Site, Long> {
    @Query("Match(n) return n")
    Flux<Site> findAll();

    Mono<Site> findOneByName(String name);
}