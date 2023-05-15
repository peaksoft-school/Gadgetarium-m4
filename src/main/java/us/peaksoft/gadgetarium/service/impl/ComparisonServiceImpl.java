package us.peaksoft.gadgetarium.service.impl;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import us.peaksoft.gadgetarium.dto.ComparisonResponse;
import us.peaksoft.gadgetarium.entity.*;
import us.peaksoft.gadgetarium.enums.Subcat;
import us.peaksoft.gadgetarium.exception.NotFoundException;
import us.peaksoft.gadgetarium.repository.*;
import us.peaksoft.gadgetarium.service.ComparisonService;

@Service
@RequiredArgsConstructor

public class ComparisonServiceImpl implements ComparisonService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final List<ComparisonResponse> smartphonesList = new ArrayList<>();
    private final List<ComparisonResponse> tabletList = new ArrayList<>();
    private final List<ComparisonResponse> laptopList = new ArrayList<>();

    public ResponseEntity<String> addToComparison(@PathVariable Long id) {
        Product product = productRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Product is not found"));
        Category category = categoryRepository.findById(product.getCategoryId()).
                orElseThrow(() -> new NotFoundException("The product does not contain any category"));
        Subcat subCategory = category.getSubcat();
        if (subCategory == null) {
            return ResponseEntity.badRequest().body("The category does not contain a subcategory");
        }
        ComparisonResponse request = new ComparisonResponse();
        request.setId(product.getCategoryId());
        request.setColor(product.getColor());
        request.setQuantityOfSim(product.getQuantityOfSim());
        request.setName(product.getName());
        request.setPrice(product.getPrice());
        request.setImage(product.getImage());
        request.setRom(product.getRom());
        request.setRam(product.getRam());
        request.setSim(product.getSim());

        Map<Subcat, List<ComparisonResponse>> categoryMap = new HashMap<>();
        categoryMap.put(Subcat.SMARTPHONES, smartphonesList);
        categoryMap.put(Subcat.LAPTOPS, laptopList);
        categoryMap.put(Subcat.TABLETS, tabletList);

        List<ComparisonResponse> productList = categoryMap.getOrDefault(subCategory, null);
        if (productList == null) {
            return ResponseEntity.badRequest().body("Unknown product enums");
        }

        if (productList.stream().anyMatch(p -> p.getId().equals(product.getId()))) {
            return ResponseEntity.badRequest().body(subCategory + " is already in comparison list");
        }
        productList.add(request);
        return ResponseEntity.ok("The product has been added to the comparison list.");
    }

    public List<ComparisonResponse> getComparisonListBySubCategory(@PathVariable Subcat subcat) {
        if (smartphonesList.isEmpty() && laptopList.isEmpty() && tabletList.isEmpty()) {
            return null;
        }
        Map<Subcat, List<ComparisonResponse>> categoryMap = new HashMap<>();
        categoryMap.put(Subcat.SMARTPHONES, smartphonesList);
        categoryMap.put(Subcat.LAPTOPS, laptopList);
        categoryMap.put(Subcat.TABLETS, tabletList);
        return categoryMap.getOrDefault(subcat, null);
    }

    public ResponseEntity<String> clearList() {
        if (smartphonesList.isEmpty() && laptopList.isEmpty() && tabletList.isEmpty()) {
            return ResponseEntity.ok("The comparison list is empty.");
        }
        for (List<ComparisonResponse> list : Arrays.asList(smartphonesList, laptopList, tabletList)) {
            list.clear();
        }
        return ResponseEntity.ok("The comparison list was successfully cleared.");
    }
}
