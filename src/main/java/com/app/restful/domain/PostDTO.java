package com.app.restful.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PostDTO {
    Long id;
    String postTitle;
    String postContent;
    Long memberId;
    Long postReadCount;
    String memberName;
}
