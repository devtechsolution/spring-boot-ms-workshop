package org.devtechsolution.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.devtechsolution.model.CatalogItem;
import org.devtechsolution.model.Movie;
import org.devtechsolution.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		// get all rated movie id
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("1234", 5)
		);
		// For each movie id call movie-info-service and get details
		return ratings.stream().map(rating -> {	
			//Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+ rating.getMovieId(), Movie.class);
			
			Movie movie = webClientBuilder.build()
				.get()
				.uri("http://localhost:8082/movies/"+ rating.getMovieId())
				.retrieve()
				.bodyToMono(Movie.class)
				.block();
			
			
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		})		
		.collect(Collectors.toList());
		//Put them all together
		
	}

}
