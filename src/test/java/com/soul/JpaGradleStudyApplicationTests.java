package com.soul;

import com.soul.entity.Article;
import com.soul.entity.SearchResult;
import com.soul.entity.User;
import com.soul.entity.UserArticleDTO;
import com.soul.repository.ArticleRepository;
import com.soul.repository.UserArticleDao;
import com.soul.repository.UserRepository;
import com.soul.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class JpaGradleStudyApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserArticleDao userArticleDao;


    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void testSave() {
        User user = new User();
        user.setId(3);
        user.setName("Daniel");
        user.setPwd("123456");
        user.setCountryId(3);
        userRepository.save(user);
        this.testFindAll();
    }

    @Test
    public void testService() {
        userService.helloWorld();
    }

    @Test
    public void testDynamicQuery() {
        List<User> users = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;

                Path<String> pwdPath = root.get("pwd");
                Predicate pwdPredicate = criteriaBuilder.equal(pwdPath, "123");
                predicate = criteriaBuilder.and(predicate, pwdPredicate);
                Path<String> namePath = root.get("name");
                Predicate namePredicate = criteriaBuilder.like(namePath, "%Jack%");
                predicate = criteriaBuilder.and(pwdPredicate, namePredicate);
                Path<Integer> idPath = root.get("id");
                Predicate idPredicate = criteriaBuilder.equal(idPath, 2);
                predicate = criteriaBuilder.and(predicate, idPredicate);
                return predicate;
            }
        });
        for (User user : users) {
            System.out.println(user);
        }
    }

//    @Test
//    public void testArticle() {
//        List<Article> articles = articleRepository.findAll();
//        for (Article article : articles) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    public void testDynamicQueryOfTwoTables() {
//        List<User> users = userRepository.findAll(new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Path<String> pwd = root.get("pwd");
//                Predicate pwdPredicate = criteriaBuilder.equal(pwd, "123");
//                return pwdPredicate;
//            }
//        });
//
////        List<User> users = userRepository.findAll();
//
//        for (User user : users) {
//            System.out.println(user);
//            for (Article article : user.getArticles()) {
//                System.out.println(article);
//            }
//            System.out.println("\n\n\n");
//        }
//    }

//    @Test
//    public void testJoinTwoTables() {
//        List<User> users = userRepository.findAll(new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Join<User, Article> userArticleMap = root.join("articles", JoinType.LEFT);
//                ArrayList<Predicate> predicates = new ArrayList<>();
//                predicates.add(cb.equal(root.get("pwd").as(String.class), "123"));
////                predicates.add(cb.equal(userArticleMap.get("articleName").as(String.class), "macbook"));
//                query.distinct(true);
//
//                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        });
//
////        List<User> users = userRepository.findAll();
//
//        for (User user : users) {
//            System.out.println(user);
//            for (Article article : user.getArticles()) {
//                System.out.println(article);
//            }
//            System.out.println("\n\n\n");
//        }
//    }

    @Test
    public void testUserArticleDao() {
        List<UserArticleDTO> userArticle = userArticleDao.findUserArticle();
        for (UserArticleDTO userArticleDTO : userArticle) {
            System.out.println(userArticleDTO);
        }

    }
}
