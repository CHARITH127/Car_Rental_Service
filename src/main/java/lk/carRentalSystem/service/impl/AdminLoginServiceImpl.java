package lk.carRentalSystem.service.impl;

import lk.carRentalSystem.dto.AdminDTO;
import lk.carRentalSystem.entity.Admin;
import lk.carRentalSystem.repo.AdminLoginVerification;
import lk.carRentalSystem.service.AdminLoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    AdminLoginVerification loginVerification;

    @Autowired
    ModelMapper mapper;

    @Override
    public AdminDTO getAdminObject(String userName, String password) {
       return mapper.map(loginVerification.getAdminObject(userName,password),AdminDTO.class);
    }
}
