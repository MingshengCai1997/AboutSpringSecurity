package com.sheng.security01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheng.security01.entity.Users;
import org.springframework.stereotype.Repository;

/**
 * 继承的这个接口内部有封装好增删改查方法，调用就可以使用了
 */
@Repository // 其实不加这个注解也是可以的，由于是一个接口，需要将其实现类注入到容器中，这步骤都在mp中做了
public interface UsersMapper extends BaseMapper<Users> {
}
