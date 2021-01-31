package compeng.arved.service;

import compeng.arved.domain.Article;
import compeng.arved.domain.Project;
import compeng.arved.domain.StaffInformation;
import compeng.arved.domain.User;
import compeng.arved.payload.ArticlePayload;
import compeng.arved.payload.ProjectPayload;
import compeng.arved.payload.StaffInformationPayload;
import compeng.arved.payload.UserPayload;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void register(UserPayload userPayload);
    User save (User user);
    void changePassword(UserPayload userPayload, String id);
    Optional<User> findByEmail(String email);
    StaffInformation getStaffInformation(Authentication authentication);
    void updateStaffInformation(StaffInformationPayload staffInformationPayload, Authentication authentication);
    List<Article> getArticles(Authentication authentication);
    //void addArticle (ArticlePayload articlePayload, Authentication authentication);
    //void updateArticle (ArticlePayload articlePayload, Authentication authentication, String id);
    //void deleteArticle(String id);
    List<Project> getProjects(Authentication authentication);
    //void addProject (ProjectPayload projectPayload, Authentication authentication);
    //void updateProject (ProjectPayload projectPayload, Authentication authentication, String id);
}
