package com.huhoot.host.manage.challenge;

import com.huhoot.dto.ChallengeResponse;
import com.huhoot.exception.NotYourOwnException;
import com.huhoot.functional.impl.CheckOwnerChallenge;
import com.huhoot.model.Admin;
import com.huhoot.model.Challenge;
import com.huhoot.vue.vdatatable.paging.PageResponse;
import com.huhoot.vue.vdatatable.paging.VDataTablePagingConverter;
import com.huhoot.vue.vdatatable.paging.VDataTablePagingRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/organizer")
public class ManageChallengeController {
    private final ManageChallengeService manageChallengeService;

    private final VDataTablePagingConverter vDataTablePagingConverter;


    @Cacheable("challenges")
    @GetMapping("/challenge")
    public PageResponse<ChallengeResponse> findAll(VDataTablePagingRequest request) {

        Admin userDetails = (Admin) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Pageable pageable = vDataTablePagingConverter.toPageable(request);

        return manageChallengeService.findAllOwnChallenge(userDetails.getId(), pageable);
    }


    @PostMapping("/challenge")
    public ResponseEntity<ChallengeResponse> add(@Valid @RequestBody ChallengeAddRequest request) throws IOException {

        Admin userDetails = (Admin) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok(manageChallengeService.addOneChallenge(userDetails, request));

    }

    @PatchMapping("/challenge")
    public void update(@Valid @RequestBody ChallengeUpdateRequest request) throws NotYourOwnException, NullPointerException {
        Admin userDetails = (Admin) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();


        Challenge c = manageChallengeService.findChallenge(request.getId());

        manageChallengeService.updateOneChallenge(request, c);

    }


}
