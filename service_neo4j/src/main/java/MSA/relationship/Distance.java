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
    String id;
    @Property
    private Float dist;
    private Float travelTime;
    @TargetNode
    private Site site;

    public Distance(Float dist, Float travelTime, Site to) {
        this.dist = dist;
        this.travelTime = travelTime;
        this.site = to;
    }
}
