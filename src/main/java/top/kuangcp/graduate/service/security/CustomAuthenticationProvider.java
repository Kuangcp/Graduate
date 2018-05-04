package top.kuangcp.graduate.service.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import top.kuangcp.graduate.config.custom.CoreConfig;
import top.kuangcp.graduate.dao.LeaderDao;
import top.kuangcp.graduate.dao.SecretaryDao;
import top.kuangcp.graduate.domain.Leader;
import top.kuangcp.graduate.domain.Secretary;
import top.kuangcp.graduate.service.role.RoleService;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author kcp
 * 自定义身份认证验证组件
 */
@Log4j2
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final RoleService roleService;
    private final LeaderDao leaderDao;
    private final SecretaryDao secretaryDao;

    @Autowired
    public CustomAuthenticationProvider(RoleService roleService, LeaderDao leaderDao, SecretaryDao secretaryDao) {
        this.roleService = roleService;
        this.leaderDao = leaderDao;
        this.secretaryDao = secretaryDao;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String username = name.split(CoreConfig.DELIMITER)[0];
        String role = name.split(CoreConfig.DELIMITER)[1];
        String password = authentication.getCredentials().toString();
        Long resultId = null;
//        log.info("roleService "+roleService);
        // 查询登录 TODO 设计对应的权限
        switch (role){
            case "student" :
                resultId = roleService.loginWithStudent(username, password);
                break;
            case "teacher" :
                resultId = roleService.loginWithTeacher(username, password);
                break;
            case "admin":
                resultId = roleService.loginWithAdmin(username, password);
                break;
            default:
                break;
        }
        // TODO 授权， 权限需要 ROLE_ 开头
        if(resultId != null){
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            switch (role){
                case "student" :
                    authorities.add( new GrantedAuthorityImpl("ROLE_STUDENT") );
                    authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
                    break;
                case "teacher" :
                    Optional<Leader> leader = leaderDao.findById(resultId);
                    Optional<Secretary> secretary = secretaryDao.findById(resultId);
                    leader.ifPresent(item-> authorities.add(new GrantedAuthorityImpl("ROLE_LEADER")));
                    secretary.ifPresent(item-> authorities.add(new GrantedAuthorityImpl("ROLE_SECRETARY")));
                    authorities.add( new GrantedAuthorityImpl("ROLE_TEACHER") );
//                    authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
                    break;
                case "admin" :
                    authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
//                    authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
                    break;
                default:
                    break;
            }
            log.info("用户登录成功 name="+username+" | pa="+password+" | role="+role+" | id="+resultId);
            return new UsernamePasswordAuthenticationToken(resultId, password, authorities);
        }else{
            log.info("用户登录失败 "+username+" | "+password+" | "+role);
            throw new BadCredentialsException("用户名或密码错误！");
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}