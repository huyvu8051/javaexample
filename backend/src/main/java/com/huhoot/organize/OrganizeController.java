package com.huhoot.organize;

import com.huhoot.host.manage.studentInChallenge.StudentInChallengeResponse;
import com.huhoot.model.Admin;
import com.huhoot.repository.QuestionRepository;
import com.huhoot.vue.vdatatable.paging.PageResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController("hostOrganizeController")
@RequestMapping("api/organizer")
public class OrganizeController {


    private final QuestionRepository questionRepository;
    private final OrganizeService organizeService;

    @GetMapping("/openChallenge")
    public void openChallenge(@RequestParam int challengeId) throws Exception {
        Admin userDetails = (Admin) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

       organizeService.openChallenge(userDetails, challengeId);
    }

    @GetMapping("/participant")
    public PageResponse<StudentInChallengeResponse> updateStudentOnline(@RequestParam int challengeId) {
        return organizeService.getAllStudentInChallengeIsLogin(challengeId);
    }

    /**
     * Start challenge
     *
     * @param challengeId challenge id
     * @return List of QuestionResponse
     */
    @GetMapping("/startChallenge")
    public void startChallenge(@RequestParam int challengeId) {
        Admin userDetails = (Admin) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        organizeService.startChallenge(challengeId, userDetails.getId());
    }


    /**
     * Show correct answer
     *
     * @param challengeId {@link com.huhoot.model.Challenge} id
     * @throws NullPointerException not found
     */
    @GetMapping("/showCorrectAnswer")
    public ParticipantsRankResp showCorrectAnswer(@RequestParam int challengeId) throws NullPointerException {
        PageResponse pageResponse = organizeService.showCorrectAnswer(challengeId);
        return ParticipantsRankResp.builder()
                .participantsRank(pageResponse.getList())
                .build();
    }

    /**
     * @param challengeId  {@link com.huhoot.model.Challenge} id
     * @param itemsPerPage size
     * @return List of {@link StudentScoreResponse}
     */
    @GetMapping("/getTopStudent")
    public ResponseEntity<PageResponse<StudentScoreResponse>> getTopStudent(@RequestParam int challengeId,
                                                                            @RequestParam(defaultValue = "1") int page,
                                                                            @RequestParam(defaultValue = "20") int itemsPerPage) {

        Pageable pageable = PageRequest.of(page - 1, itemsPerPage);
        return ResponseEntity.ok(organizeService.getTopStudent(challengeId, pageable));
    }


    /**
     * @param challengeId {@link com.huhoot.model.Challenge} id
     * @return List of {@link StudentScoreResponse}
     * @throws NullPointerException not found
     */
    @GetMapping("/endChallenge")
    public void endChallenge(@RequestParam int challengeId) throws NullPointerException {
        Admin userDetails = (Admin) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        organizeService.endChallenge(challengeId);
    }


    /**
     * Kick student
     *
     * @param req {@link KickRequest}
     */
    @PostMapping("/kickStudent")
    public void kickStudent(@RequestBody KickRequest req) {
        Admin userDetails = (Admin) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        organizeService.kickStudent(req.getStudentIds(), req.getChallengeId(), userDetails.getId());
    }

    @GetMapping("/request")
    public void publishNextQuestion(@RequestParam int challengeId){
        try {
            organizeService.publishNextQuestion(challengeId);
        } catch (Exception e) {
            organizeService.endChallenge(challengeId);
        }
    }





}
