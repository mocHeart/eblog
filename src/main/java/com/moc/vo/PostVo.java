package com.moc.vo;

import com.moc.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostVo extends Post {

    private Long authorId;
    private String authorName;
    private String authorAvatar;
    private String categoryName;

}
