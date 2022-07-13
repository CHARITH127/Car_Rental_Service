package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.AdminDTO;
import lk.carRentalSystem.entity.Admin;

public interface AdminLoginService {
   public AdminDTO getAdminObject(String userName , String password);
}
