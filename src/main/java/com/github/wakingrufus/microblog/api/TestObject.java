package com.github.wakingrufus.microblog.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TestObject {
    String testProp;
}
