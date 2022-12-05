package com.huhoot.repository;

import com.huhoot.organize.AnswerResultResponse;
import com.huhoot.organize.PublishAnswer;
import com.huhoot.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Optional<Answer> findOneById(int id);


    /**
     * @param questionId {@link com.huhoot.model.Question} id
     * @return List of {@link Integer} as correct {@link Answer} ids
     */
    @Query("SELECT a.id " +
            "FROM Answer a " +
            "WHERE a.question.id = :questionId AND a.isCorrect = TRUE")
    List<Integer> findAllCorrectAnswerIds(@Param("questionId") int questionId);

    /**
     * @param questionId {@link com.huhoot.model.Question} id
     * @return List of {@link PublishAnswer}
     */
    @Query("SELECT new com.huhoot.organize.PublishAnswer(n.id,n.ordinalNumber, n.answerContent) " +
            "FROM Answer n " +
            "WHERE n.question.id = :questionId")
    List<PublishAnswer> findAllPublishAnswerByQuestionId(@Param("questionId") int questionId);

    /**
     * @param questionId {@link com.huhoot.model.Question} id
     * @return List of {@link PublishAnswer}
     */
    @Query("SELECT new com.huhoot.organize.PublishAnswer(n.id, n.ordinalNumber, n.answerContent,n.isCorrect) " +
            "FROM Answer n " +
            "WHERE n.question.id = :questionId")
    List<PublishAnswer> findAllAnswerByQuestionIdAndAdminId(@Param("questionId") int questionId);


    @Query("SELECT new com.huhoot.organize.AnswerResultResponse(n.id, n.ordinalNumber, n.answerContent) " +
            "FROM Answer n " +
            "WHERE n.question.id = :questionId")
    List<AnswerResultResponse> findAllPublishAnswer(@Param("questionId") int questionId);

    @Query("SELECT new com.huhoot.organize.PublishAnswer(n.id, n.ordinalNumber, n.answerContent, n.isCorrect, false, n.createdDate, n.createdBy, n.modifiedDate, n.modifiedBy) " +
            "FROM Answer n " +
            "WHERE n.question.id = :questionId " +
            "AND n.question.challenge.admin.id = :adminId " +
            "ORDER BY n.ordinalNumber ASC ")
    Page<PublishAnswer> findAllByQuestionIdAndAdminId(@Param("questionId") int questionId, @Param("adminId") int adminId, Pageable pageable);

    @Query("SELECT COALESCE(MAX (n.ordinalNumber + 1), 0) " +
            "FROM Answer n " +
            "WHERE n.question.id = :questionId")
    int getNextOrdinalNumber(@Param("questionId") int questionId);




}
