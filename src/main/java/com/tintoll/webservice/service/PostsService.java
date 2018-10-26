package com.tintoll.webservice.service;

import com.tintoll.webservice.domain.posts.PostsRepository;
import com.tintoll.webservice.dto.posts.PostsSaveRequestDto;
import com.tintoll.webservice.dto.posts.PostsSaveResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostsService {

    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostsSaveResponseDto> findAllDesc() {
        return postsRepository.findAllByDesc()
                .map(PostsSaveResponseDto::new)
                .collect(Collectors.toList());
    }
}
