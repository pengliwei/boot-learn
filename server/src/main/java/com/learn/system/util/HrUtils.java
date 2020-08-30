package com.learn.system.util;

import com.learn.system.model.entity.Hr;
import org.springframework.security.core.context.SecurityContextHolder;


public class HrUtils {
    public static Hr getCurrentHr() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
