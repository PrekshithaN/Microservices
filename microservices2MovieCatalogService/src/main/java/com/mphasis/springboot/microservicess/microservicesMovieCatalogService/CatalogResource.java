package com.mphasis.springboot.microservicess.microservicesMovieCatalogService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.mphasis.springboot.microservicess.models.CatalogItem;
import com.mphasis.springboot.microservicess.models.Movie;
import com.mphasis.springboot.microservicess.models.Rating;
import com.mphasis.springboot.microservicess.models.UserRating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping("/catalog")
//public class CatalogResource {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    WebClient.Builder webClientBuilder;
//
//    @RequestMapping("/{userId}")
//    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
//    	System.out.println("1");
//        UserRating userRating = restTemplate.getForObject("http://microservice3-RatingDataService/ratingsdata/user/" + userId, UserRating.class);
//
//        return userRating.getRatings().stream()
//                .map(rating -> {
//                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
//                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
//                })
//                .collect(Collectors.toList());
//
//    }
//}



/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/





@RestController
@RequestMapping("/Catalog")
public class CatalogResource {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate restTemplate;
	
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		logger.info("Print used id ",userId);
		logger.debug("Debugging code ",userId);
		
		RestTemplate restTemplate = new RestTemplate(); 
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),	
				new Rating("3456", 3)
				);
			
		ratings.stream().map((Function<? super Rating, ?>) rating ->{
////			Movie movie = restTemplate.getForObject("http://localhost:8091/Movies/" +rating.getMovieId(), Movie.class);	
//			
//			
//			Movie movie = restTemplate.getForObject("http://movie-info-service/Movies/" +rating.getMovieId(), Movie.class);	
//	        
////			// or using web client builder
////			Movie movie=builder.build()
////			.get()
////			.uri("http://localhost:8082/Movies/" +rating.getMovieId())
////			.retrieve()
////			.bodyToMono(Movie.class)
////			.block();
//					
//			
//			return new CatalogItem(movie.getName(), "test", rating.getRating());
//				
//		}).collect(Collectors.toList());
//		
//		return Collections.singletonList(
//			new CatalogItem("Transformers", "Test", 4)
//			);
//		
			 UserRating userRating = restTemplate.getForObject("http://microservice3-RatingDataService/ratingsdata/user/" + userId, UserRating.class);

	        return userRating.getRatings().stream()
	                .map(rating1 -> {
	                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
	                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	                }).collect(Collectors.toList());
	        
			
		});
		
		return Collections.singletonList(
				new CatalogItem("Transformers", "Test", 4)
				);
	}
}
		
		
				
	

