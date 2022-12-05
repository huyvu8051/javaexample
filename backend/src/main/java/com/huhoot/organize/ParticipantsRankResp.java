package com.huhoot.organize;

import com.huhoot.config.mvc.CustomBodyResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@CustomBodyResponse
@Builder
public class ParticipantsRankResp {
    @Getter
    private List<StudentScoreResponse> participantsRank;
}
