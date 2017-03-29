package com.github.wakingrufus.microblog.post;

import com.github.wakingrufus.microblog.api.TestObject;
import org.jvnet.hk2.annotations.Service;

@Service
public class SamplePostService implements PostService {
    @Override
    public TestObject test(String input) {
        return TestObject.builder().testProp(input).build();
    }
}
