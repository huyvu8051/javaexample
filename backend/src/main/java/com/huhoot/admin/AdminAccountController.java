package com.huhoot.admin;

import com.huhoot.dto.ChangePasswordRequest;
import com.huhoot.exception.AccountException;
import com.huhoot.model.Admin;
import com.huhoot.host.HostAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/admin")
@AllArgsConstructor
public class AdminAccountController {
    private final HostAccountService hostAccountService;

    @PutMapping("/account")
    public void changePassword(@RequestBody ChangePasswordRequest request) throws AccountException {
        Admin userDetails = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        hostAccountService.changePassword(request, userDetails);
    }

}