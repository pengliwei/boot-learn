package com.learn.system.util;

import com.learn.system.model.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;


public class UserUtils {
    public static User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
