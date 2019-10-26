package org.devtechsolution.resources;

import java.util.Collections;
import java.util.List;

import org.devtechsolution.model.CatalogItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		return Collections.singletonList(
				new CatalogItem("Transformer", "Test", 4)
				);
	}

}
