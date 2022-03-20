package com.moc.schedules;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moc.entity.Post;
import com.moc.service.PostService;
import com.moc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ViewCountSyncTask {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    PostService postService;


    @Scheduled(cron = "* 0/2 * * * ?")
    public void task() {

        Set<String> keys = redisTemplate.keys("rank:post:*");

        List<String> ids = new ArrayList<>();

        if (null == keys || keys.isEmpty()) return;
        for (String key : keys) {
            if(redisUtil.hHasKey(key, "post:viewCount")){
                ids.add(key.substring("rank:post:".length()));
            }
        }

        if(ids.isEmpty()) return;

        // 需要更新阅读量
        List<Post> posts = postService.list(new QueryWrapper<Post>().in("id", ids));
        posts.forEach(post -> post.setViewCount((Integer) redisUtil.hget("rank:post:" + post.getId(), "post:viewCount")));

        if(posts.isEmpty()) return;
        boolean isSuccess = postService.updateBatchById(posts);

        if(isSuccess) {
            ids.forEach(id -> {
                redisUtil.hdel("rank:post:" + id, "post:viewCount");
                System.out.println("Post Id: " + id + " 同步成功阅读数成功.");
            });
        }
    }

}
