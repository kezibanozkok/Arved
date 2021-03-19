package compeng.arved.controller;

import com.lowagie.text.DocumentException;
import compeng.arved.domain.Article;
import compeng.arved.domain.Project;
import compeng.arved.pdf.ArticlePdfExporter;
import compeng.arved.pdf.ProjectPdfExporter;
import compeng.arved.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserConfirmationService userConfirmationService;
    private final StaffInformationService staffInformationService;
    private final ArticleService articleService;
    private final ProjectService projectService;
    List<Article> articleList;
    List<Project> projectList;

    @Autowired
    public AdminController(UserConfirmationService userConfirmationService, StaffInformationService staffInformationService, ArticleService articleService, ProjectService projectService) {
        this.userConfirmationService = userConfirmationService;
        this.staffInformationService = staffInformationService;
        this.articleService = articleService;
        this.projectService = projectService;
    }

    @GetMapping("/onayBekleyenler")
    public String getAdminPage(Model model) {
        model.addAttribute("pendingApproval", userConfirmationService.getPendingApproval());
        return "admin";
    }

    @PostMapping("/onayBekleyenler/confirm/{id}")
    public String confirmation(@PathVariable String id) {
        userConfirmationService.confirm(id);
        return "redirect:/admin/onayBekleyenler";
    }

    @PostMapping("/onayBekleyenler/delete/{id}")
    public String rejection(@PathVariable String id) {
        userConfirmationService.reject(id);
        return "redirect:/admin/onayBekleyenler";
    }

    @GetMapping("/allStaff")
    public String getAllStaff(Model model) {
        model.addAttribute("staff", staffInformationService.getAllStaff());
        return "allStaff";
    }

    @GetMapping("/allArticles")
    public String getAllArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "allArticles";
    }

    @GetMapping("/allProjects")
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "allProjects";
    }

    @GetMapping("/createReport")
    public String getReportPage(Model model, @Param("yil") String yil, @Param("endeksTuru") String endeksTuru, @Param("uluslararasiYayin") boolean uluslararasiYayin, @Param("bap") boolean bap,
                             @Param("projeYil") String projeYil, @Param("kurumIciProje") boolean kurumIciProje, @Param("uluslararasi") boolean ululuslararasi, @Param("kontratliProje") boolean kontratliProje) {
        articleList = articleService.getReport(yil, endeksTuru, uluslararasiYayin, bap);
        projectList = projectService.getReport(projeYil, kurumIciProje, ululuslararasi, kontratliProje);
        model.addAttribute("articles", articleList);
        model.addAttribute("projects", projectList);
        return "reports";
    }

    @GetMapping("/createReport/export/articlePdf")
    public void exportArticleToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=articles_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        //articleList = articleService.getReport(yil, endeksTuru, uluslararasiYayin, bap);

        ArticlePdfExporter exporter = new ArticlePdfExporter(articleList);
        exporter.export(response);
    }

    @GetMapping("/createReport/export/projectPdf")
    public void exportProjectToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=projects_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        //articleList = articleService.getReport(yil, endeksTuru, uluslararasiYayin, bap);

        ProjectPdfExporter exporter = new ProjectPdfExporter(projectList);
        exporter.export(response);
    }
}