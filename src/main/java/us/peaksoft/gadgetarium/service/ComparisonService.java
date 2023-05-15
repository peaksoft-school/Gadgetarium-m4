package us.peaksoft.gadgetarium.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import us.peaksoft.gadgetarium.dto.ComparisonResponse;
import us.peaksoft.gadgetarium.enums.Subcat;

public interface ComparisonService {

    ResponseEntity<String> addToComparison(@RequestBody Long id);

    List<ComparisonResponse> getComparisonListBySubCategory(@PathVariable Subcat subcat);

    ResponseEntity<String> clearList();
}
