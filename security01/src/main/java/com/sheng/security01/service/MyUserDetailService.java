package com.sheng.security01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheng.security01.entity.Users;
import com.sheng.security01.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注意：这里的Service名字必须和配置类中我们注入的是一样的。
 */
@Service("userDetailServiceaaa")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String s) {
        // 调用内部方法，根据用户名查询数据库
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", s);
        //Users users = usersMapper.
        Users users = usersMapper.selectOne(wrapper);
        // 进行判断
        if(users == null){
            // 没有查询到该用户
            throw new UsernameNotFoundException("用户名不存在");
        }
            // 查询到了，就从得到的对象中获取用户名以及密码
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("aa, ROLE_AdA");
        System.out.println(s);
        return new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()), auths);
    }
}

