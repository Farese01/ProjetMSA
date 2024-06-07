package MSA.service;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {

    private final Neo4jClient neo4jClient;

    public RelationshipService(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    public void createRelationships(String startNodeId, String endNodeId, String relationshipType) {
        String cypherQuery = String.format("MATCH (start), (end) WHERE id(start) = %s AND id(end) = %s CREATE (start)-[:%s]->(end)", startNodeId, endNodeId, relationshipType);
        neo4jClient.query(cypherQuery).run();
    }
}