package api.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.AutoPopulatingList;

import java.util.List;

public class SortList {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Sort> sortList = new AutoPopulatingList<>(Sort.class);

}
