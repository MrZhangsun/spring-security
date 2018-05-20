package site.zhangsun.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.zhangsun.security.mapper.SysRoleMapper;
import site.zhangsun.security.mapper.SysUserMapper;
import site.zhangsun.security.mapper.SysUserRoleMapper;
import site.zhangsun.security.pojo.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Function: </p>
 *
 * @author : zhangsunjiankun 2018/5/18 下午10:23
 */
@Service
public class SysUserServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    public SysUserServiceImpl(SysUserMapper sysUserMapper, SysRoleMapper sysRoleMapper, SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户ID
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUsernameEqualTo(username);
        SysUser sysUser = sysUserMapper.selectByExample(sysUserExample).get(0);

        // 根据用户ID查询用户角色ID
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUser.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        List<Integer> roleIds = new ArrayList<>(sysUserRoles.size());
        for (SysUserRole sysUserRole : sysUserRoles) {
            roleIds.add(sysUserRole.getRoleId());
        }

        // 根据用户角色ID查询用户角色
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(roleIds);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);

        // 将角色添加到用户实体中
        SysUserDetails userDetails = new SysUserDetails();
        BeanUtils.copyProperties(sysUser, userDetails);
        userDetails.setRoles(sysRoles);
        return userDetails;
    }

    @Bean
    public PasswordEncoder encoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123");
        logger.info("Password 123 encrypted by BCrypt is : {}", encode);
        return encoder;
    }
}
