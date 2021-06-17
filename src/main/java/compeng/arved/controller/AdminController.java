package compeng.arved.controller;

import com.lowagie.text.DocumentException;
import compeng.arved.domain.Article;
import compeng.arved.domain.Bildiri;
import compeng.arved.domain.Parameter;
import compeng.arved.domain.Project;
import compeng.arved.pdf.PdfExporter;
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
    private final BildiriService bildiriService;
    private final ParameterService parameterService;
    List<Article> articleList;
    List<Project> projectList;
    List<Bildiri> bildiriList;

    @Autowired
    public AdminController(UserConfirmationService userConfirmationService, StaffInformationService staffInformationService, ArticleService articleService, ProjectService projectService, BildiriService bildiriService, ParameterService parameterService) {
        this.userConfirmationService = userConfirmationService;
        this.staffInformationService = staffInformationService;
        this.articleService = articleService;
        this.projectService = projectService;
        this.bildiriService = bildiriService;
        this.parameterService = parameterService;
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

    @GetMapping("/allBildiri")
    public String getAllBildiri(Model model) {
        model.addAttribute("bildiriler", bildiriService.getAllBildiri());
        return "allBildiriler";
    }

    @GetMapping("/createReport")
    public String getReportPage(Model model, @Param("yil") String yil, @Param("endeksTuru") String endeksTuru, @Param("uluslararasiYayin") boolean uluslararasiYayin, @Param("bap") boolean bap,
                                @Param("projeYil") String projeYil, @Param("kurumIciProje") boolean kurumIciProje, @Param("uluslararasi") boolean uluslararasi, @Param("kontratliProje") boolean kontratliProje, @Param("bildiriYil") String bildiriYil) {
        articleList = articleService.getReport(yil, endeksTuru, uluslararasiYayin, bap);
        projectList = projectService.getReport(projeYil, kurumIciProje, uluslararasi, kontratliProje);
        bildiriList = bildiriService.getReport(bildiriYil);
        model.addAttribute("articles", articleList);
        model.addAttribute("projects", projectList);
        model.addAttribute("bildiriler", bildiriList);
        return "reports";
    }

    @GetMapping("/createReport/export/pdf")
    public void exportArticleToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Parameter> parameters = parameterService.findAll();
        PdfExporter exporter = new PdfExporter(articleList, projectList, bildiriList);
        exporter.export(response, parameters);
    }
}