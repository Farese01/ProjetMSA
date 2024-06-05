package MSA.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SiteDTO {
    private String name;
    private Float latitude;
    private Float longitude;
    private Boolean isParalympic;
}

