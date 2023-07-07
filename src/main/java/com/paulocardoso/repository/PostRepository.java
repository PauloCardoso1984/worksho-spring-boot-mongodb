package com.paulocardoso.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.paulocardoso.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// BUSCA LISTA DE POSTS em URL
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	// BUSCA LISTA DE POSTS 
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	// BUSCA POR TEXTO E DATA
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
