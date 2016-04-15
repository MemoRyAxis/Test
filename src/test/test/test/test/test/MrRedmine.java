package test.test.test.test.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.ProjectManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

public class MrRedmine {

    private static final Logger log = LoggerFactory.getLogger(MrRedmine.class);

    public static void main(String[] args) throws RedmineException, EmailException {


        RedmineManager manager =
                RedmineManagerFactory.createWithUserAuth("http://redmine.100credit.cn/",
                        "qizheng.ma", "100credit.com");

        ProjectManager projectManager = manager.getProjectManager();

        List<Project> projectList = projectManager.getProjects();

        for (Project project : projectList) {
            // System.out.println(project);
        }


        IssueManager issueManager = manager.getIssueManager();

        Map<String, String> params = new HashMap<String, String>();
        params.put("project_id", "83");
        params.put("offset", "0");
        params.put("limit", "1000");
        params.put("priority_id", "4");

        List<Issue> issuesList = issueManager.getIssues(params);
        System.out.println(issuesList.size());

        Date dueDate = new DateTime().plusYears(1).toDate();
        for (Issue issue : issuesList) {
            System.out.println(issue.getDueDate());
            if (issue.getDueDate() != null) {
                if (dueDate.after(issue.getDueDate())) {
                    dueDate = issue.getDueDate();
                }
            }
        }
        System.out.println("\n" + dueDate);

        log.info("最近上线日期: {}", new DateTime(dueDate).toString("yyyy-MM-dd"));

        log.info("需要上线的任务: ");
        List<Issue> issuesToDo = new ArrayList<Issue>();
        for (Issue issue : issuesList) {
            if (issue.getDueDate() != null && issue.getDueDate().equals(dueDate)) {
                issuesToDo.add(issue);
                // log.info("[{}] [{}%] {}", issue.getId(), issue.getDoneRatio(),
                // issue.getSubject());
                log.info("http://redmine.100credit.cn/issues/{}\t{}", issue.getId(),
                        issue.getSubject());
            }
        }



        Email email = new SimpleEmail();
        email.setHostName("smtp.100credit.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("qizheng.ma@100credit.com", "fL8bBgaXKX"));
        email.setSSLOnConnect(true);
        email.setFrom("qizheng.ma@100credit.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("memoryaxis@163.com");
        // System.out.println(email.send());

    }

}
