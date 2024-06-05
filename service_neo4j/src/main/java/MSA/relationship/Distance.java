package MSA.relationship;

import MSA.node.Site;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RelationshipProperties
public class Distance {
    @RelationshipId
    Long id;
    @Property
    private float dist;
    private float travelTime;
    @TargetNode
    private Site site;

    public Distance(float dist, float travelTime, Site to) {
        this.dist = dist;
        this.travelTime = travelTime;
        this.site = to;
    }
}
