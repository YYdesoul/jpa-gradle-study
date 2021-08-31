package com.soul.repository;

import com.soul.entity.Article;
import com.soul.entity.User;
import com.soul.entity.UserArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserArticleDao {
    @Autowired
    @PersistenceContext
    private EntityManager em;

    public List<UserArticleDTO> findUserArticle() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserArticleDTO> query = builder.createQuery(UserArticleDTO.class);

        // from table
        Root<User> rootUser = query.from(User.class);
        Root<Article> rootArticle = query.from(Article.class);

        // where conditions
        List<Predicate> predicates = new ArrayList<>();

        Predicate predicate1 = builder.equal(rootUser.get("id"), rootArticle.get("userId"));
        Predicate predicate2 = builder.equal(rootArticle.get("articleName"), "macbook");
        Predicate predicate3 = builder.like(rootArticle.get("articleName"), "%ms surface%");
        Predicate predicateOr = builder.or(predicate2, predicate3);
        predicates.add(predicate1);
        predicates.add(predicateOr);

        Predicate finalPredicate = builder.and(predicates.toArray(new Predicate[predicates.size()]));


        query.multiselect(rootUser.get("id").as(Integer.class), rootUser.get("name").as(String.class),
                rootArticle.get("articleName").as(String.class))
                .where(finalPredicate);
        List<UserArticleDTO> resultList = em.createQuery(query).getResultList();
        return resultList;
    }
}
