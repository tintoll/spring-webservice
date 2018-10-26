package com.tintoll.webservice.service;

import com.tintoll.webservice.domain.posts.Posts;
import com.tintoll.webservice.domain.posts.PostsRepository;
import com.tintoll.webservice.dto.posts.PostsSaveRequestDto;
import com.tintoll.webservice.dto.posts.PostsSaveResponseDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;
    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void save_posts() {

        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                                        .title("제목")
                                        .content("내용")
                                        .author("blue@tigrison.com").build();

        postsService.save(dto);

        Posts post = postsRepository.findAll().get(0);
        assertThat(post.getContent()).isEqualTo(dto.getContent());
        assertThat(post.getTitle()).isEqualTo(dto.getTitle());
        assertThat(post.getAuthor()).isEqualTo(dto.getAuthor());

    }

    @Test
    public void findAllDesc_순서확인 () {
        // when
        List<PostsSaveResponseDto> postsList = postsService.findAllDesc();


        // then
        PostsSaveResponseDto posts = postsList.get(0);
        Assert.assertThat(posts.getTitle(), is("테스트2"));
    }
}