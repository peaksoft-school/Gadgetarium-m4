package us.peaksoft.gadgetarium.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.ComparisonResponse;
import us.peaksoft.gadgetarium.enums.Subcat;
import us.peaksoft.gadgetarium.service.ComparisonService;

@RestController
@RequestMapping("api/public/compare")
@Tag(name = "CompareController", description = "API endpoints for comparison products")
@RequiredArgsConstructor
public class ComparisonController {

    private final ComparisonService comparison;

    @PostMapping()
    @Operation(description = "All users have access to adding products for comparison")
    public ResponseEntity<String> addToComparison(@RequestBody Long id) {
        return comparison.addToComparison(id);
    }

    @GetMapping("{subcat}")
    @Operation(description = "All users have access to products for comparison")
    public List<ComparisonResponse> getComparisonListBySubCategory(@PathVariable Subcat subcat) {
        return comparison.getComparisonListBySubCategory(subcat);
    }

    @DeleteMapping()
    @Operation(description = "All users have access to delete comparison list.")
    public ResponseEntity<String> clearList() {
        return comparison.clearList();
    }
}