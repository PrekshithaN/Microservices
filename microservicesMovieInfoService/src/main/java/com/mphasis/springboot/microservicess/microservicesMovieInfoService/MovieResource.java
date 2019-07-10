package com.mphasis.springboot.microservicess.microservicesMovieInfoService;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mphasis.springboot.microservicess.models.Movie;

@RestController
@RequestMapping("/Movies")
public class MovieResource {
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable String movieId) {
		return new Movie(movieId, "testname");
		
	}

	
}
