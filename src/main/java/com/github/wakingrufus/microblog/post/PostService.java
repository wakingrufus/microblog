package com.github.wakingrufus.microblog.post;

import com.github.wakingrufus.microblog.api.TestObject;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface PostService {
    TestObject test(String input);
}
