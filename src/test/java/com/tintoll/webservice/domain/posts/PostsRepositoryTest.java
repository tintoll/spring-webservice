package com.tintoll.webservice.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        /*
            테스트가 끝날때마다 Repository를 비워준다.
         */
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        postsRepository.save(Posts.builder()
                                .title("제목")
                                .content("내용")
                                .author("tintoll").build());

        // when
        List<Posts> postsList = postsRepository.findAll();


        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("제목"));
        assertThat(posts.getContent(), is("내용"));
    }

    @Test
    public void BaseTimeEntity_등록 () {
        LocalDateTime now = LocalDateTime.now();
        // given
        postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .author("tintoll").build());

        // when
        List<Posts> postsList = postsRepository.findAll();


        // then
        Posts posts = postsList.get(0);
        // isAfter는 getCreateDate보다 now보다 뒤의 시간인지 여부를 호출해줌
        assertTrue(posts.getCreateDate().isAfter(now));
        assertTrue(posts.getUpdateDate().isAfter(now));
    }

}