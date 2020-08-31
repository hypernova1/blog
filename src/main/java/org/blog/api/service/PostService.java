package org.blog.api.service;

import lombok.RequiredArgsConstructor;
import org.blog.api.repository.PostRepository;

/**
 * Created by melchor
 * Date: 2020/08/31
 * Time: 10:32 PM
 */

@RequiredArgsConstructor
public class PostService {

    private final PostRepository posts;

}
