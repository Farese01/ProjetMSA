package MSA.controller;

import MSA.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/neo4j/relations")
public class RelationshipController {

    private final RelationshipService relationshipService;

    @Autowired
    public RelationshipController(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @PostMapping("/createRelationship")
    public void createRelationship(@RequestBody Map<String, String> request) {
        String startNodeId = request.get("startNodeId");
        String endNodeId = request.get("endNodeId");
        String relationshipType = request.get("relationshipType");
        relationshipService.createRelationships(startNodeId, endNodeId, relationshipType);
    }
}

