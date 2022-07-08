package com.example.user.service;

import com.example.user.VO.Department;
import com.example.user.VO.ReponseTemplateVO;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside save user of User Service");
        return userRepository.save(user);
    }


    public ReponseTemplateVO getUserWithDepartment(Long userId)
    {
        log.info("Inside getUserWithDepartment of User Service");
        ReponseTemplateVO vo = new ReponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
