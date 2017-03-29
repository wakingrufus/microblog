package com.github.wakingrufus.microblog.post;

import com.github.wakingrufus.microblog.api.TestObject;
import com.github.wakingrufus.microblog.api.TestResource;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class SampleTestResource implements TestResource {

    private PostService postService;

    @Inject
    public SampleTestResource(PostService postService) {
        this.postService = postService;
    }

    public String getValue(String value) {
        return postService.test(value).getTestProp();
    }


    public TestObject post(TestObject testObject) {
        return postService.test(testObject.getTestProp());
    }
}