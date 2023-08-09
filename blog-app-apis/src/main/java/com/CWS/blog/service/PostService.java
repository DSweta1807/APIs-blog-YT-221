package com.CWS.blog.service;

import java.util.List;
import com.CWS.blog.dto.PostDto;
import com.CWS.blog.responce.PostResponce;

public interface PostService {

	PostDto createPost (PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost (PostDto postDto, Integer postId);

	void deletePost (Integer postId);

	PostResponce getAllPost (Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPostById (Integer postId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPosts(String keywords);
	
}
