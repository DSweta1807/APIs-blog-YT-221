package com.CWS.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.CWS.blog.entity.Category;
import com.CWS.blog.entity.Post;
import com.CWS.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
//Although JpaRepository its also extends ListPagingAndSortingRepository which helps us to sorting nd pagination, 
//	it has a method of findAll(Sort sort), findAll(Pageable pageable)
//	search "findBy methods in JPA" on google
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key")String title);
	
}
