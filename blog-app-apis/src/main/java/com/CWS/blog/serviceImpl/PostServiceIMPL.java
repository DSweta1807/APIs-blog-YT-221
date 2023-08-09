package com.CWS.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.CWS.blog.dto.PostDto;
import com.CWS.blog.entity.Category;
import com.CWS.blog.entity.Post;
import com.CWS.blog.entity.User;
import com.CWS.blog.exception.ResourceNotFoundException;
import com.CWS.blog.repository.CategoryRepo;
import com.CWS.blog.repository.PostRepo;
import com.CWS.blog.repository.UserRepo;
import com.CWS.blog.responce.PostResponce;
import com.CWS.blog.service.PostService;

@Service
public class PostServiceIMPL implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User id", userId));
		Category category =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
//		post.setAddedDate((java.sql.Date) new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		this.postRepo.delete(post);
		}


	@Override
	public PostResponce getAllPost(Integer pageNumber, Integer pageSize ,String sortBy,String sortDir) {
		
//		Pagination
//		we will create object of Pageable and will send it through finAll()
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<PostDto> postDtos = pagePost.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce postResponse = new PostResponce();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
//		postResponse.setTotalElements(pagePost.getNumberOfElements());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post posts = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post Id", postId));
		
		return this.modelMapper.map(posts, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		List <Post> posts= this.postRepo.findByCategory(cat);
		List<PostDto> postDtos= posts.stream().map(post-> this.modelMapper.map(post,  PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User users = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
		List<Post> posts = this.postRepo.findByUser(users);
		List<PostDto> postDtos = posts.stream().map(post-> this.modelMapper.map(post,  PostDto.class)).collect(Collectors.toList());		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keywords) {
		List<Post> posts = this.postRepo.searchByTitle("%" +keywords+ "%");
		List <PostDto> postDtos= posts.stream().map(post-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
