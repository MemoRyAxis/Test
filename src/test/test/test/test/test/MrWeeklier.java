package test.test.test.test.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.News;
import com.taskadapter.redmineapi.bean.NewsFactory;
import com.taskadapter.redmineapi.bean.Project;

public class MrWeeklier {

    private static final Logger log = LoggerFactory.getLogger(MrWeeklier.class);

    private static final String CONTEXT_REGEX = "[\n|\r]*([^-]*)[\n|\r]*--[\n|\r]*([^=]*)";

    private static final String CONTENT_TXT = "F://weekly//context.txt";

    private static final String TECH_WEEKLY_TEMPLATE = "F:\\weekly\\TechWeeklyTemplate.xls";

    private static final String ZCGL_WEEKLY_TEMPLATE = "F:\\weekly\\ZcglWeeklyTemplate.xls";

    private static final String OUT_PUT_DIR = "F:\\weekly\\out\\";

    private static final String REPORT_USER = "马奇正";

    private static final String REPORT_GROUP = "研发部-资产技术组";

    private static final BrProject[] REPORT_PROJECTS = {BrProject.DCP, BrProject.DCSP};

    public static void main(String[] args) throws Exception {
        Set<Task> taskSet = dealContext();
        genTeckWeekly(taskSet);
        genRedmineNews();
    }

    /**
     * 1. Read context file
     * <p>
     * 2. Convert file to task object
     * 
     * @see Task
     * 
     */
    public static Set<Task> dealContext() throws IOException {


        // ------- Read context text

        String allContext = "";
        Writer writer = new StringWriter();

        InputStream is = new FileInputStream(CONTENT_TXT);
        Reader reader = new InputStreamReader(is, "UTF-8");

        char[] buffer = new char[1024];
        int n = 0;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
        }

        allContext = writer.toString();



        // ------- Convert text to object use regex

        Set<Task> taskSet = new HashSet<Task>();

        Pattern pattern;
        Matcher matcher;
        for (int i = 0; i < REPORT_PROJECTS.length; i++) {
            pattern =
                    Pattern.compile(REPORT_PROJECTS[i].getRedmineName() + CONTEXT_REGEX,
                            Pattern.DOTALL);
            matcher = pattern.matcher(allContext);

            if (matcher.find()) {
                for (int j = 0; j < matcher.groupCount(); j++) {

                    TaskType taskType;
                    if (j == 0) {
                        taskType = TaskType.DONE;
                    } else {
                        taskType = TaskType.TODO;
                    }

                    String tasks = matcher.group(j + 1).trim().replace("\t", " ");

                    String[] taskArr = tasks.split("\\r?\\n");

                    for (int k = 0; k < taskArr.length; k++) {

                        String taskStr = taskArr[k];
                        if (taskStr != null && !taskStr.trim().equals("")) {

                            Task task = new Task();
                            task.setProject(REPORT_PROJECTS[i]);
                            task.setType(taskType);
                            task.setSubject(taskStr.split("\\|")[0].trim());

                            Set<String> users = new HashSet<String>();
                            for (String string : taskStr.split("\\|")[1].trim().split(" ")) {
                                users.add(string);
                            }
                            task.setUsers(users);

                            taskSet.add(task);
                        }
                    }
                }
            }
        }
        return taskSet;
    }



    /**
     * Generate tech weekly
     */
    public static void genTeckWeekly(Set<Task> taskSet) throws IOException {

        String[][] data = new String[20][20];


        // ------- Stable info

        data[0][1] = REPORT_GROUP;
        data[1][1] = genStartTimeStamp().concat("~").concat(genEndTimeStamp());
        data[2][1] = REPORT_USER;



        // ------- Project task info

        int doneStartRow = 6;
        int todoStartRow = 11;



        // ------- Create multiply task

        Set<Task> techTask = new HashSet<Task>(REPORT_PROJECTS.length * 2);
        for (int i = 0; i < REPORT_PROJECTS.length; i++) {
            Task doneTask = new Task();
            doneTask.setProject(REPORT_PROJECTS[i]);
            doneTask.setType(TaskType.DONE);
            doneTask.setSubject("");
            doneTask.setUsers(new HashSet<>());
            techTask.add(doneTask);

            Task todoTask = new Task();
            todoTask.setProject(REPORT_PROJECTS[i]);
            todoTask.setType(TaskType.TODO);
            todoTask.setSubject("");
            todoTask.setUsers(new HashSet<>());
            techTask.add(todoTask);
        }

        for (Task task : taskSet) {
            for (Task task2 : techTask) {
                if (task.getProject().equals(task2.getProject())
                        && task.getType().equals(task2.getType())) {

                    task2.setSubject(task2.getSubject() + task.getSubject() + " \n");
                    task2.getUsers().addAll(task.getUsers());
                    task2.setUsers(task2.getUsers());

                    continue;
                }
            }
        }


        // ------- Fill task info to excel cells

        for (Task task : techTask) {
            String userStr = "";
            switch (task.getType()) {
                case DONE:
                    data[doneStartRow][0] = task.getProject().getProjectName();
                    data[doneStartRow][1] = task.getSubject();
                    for (String userName : task.getUsers()) {
                        userStr += userName + " ";
                    }
                    data[doneStartRow][3] = userStr;
                    doneStartRow++;
                    break;
                case TODO:
                    data[todoStartRow][0] = task.getProject().getProjectName();
                    data[todoStartRow][1] = task.getSubject();
                    for (String userName : task.getUsers()) {
                        userStr += userName + " ";
                    }
                    data[todoStartRow][3] = userStr;
                    todoStartRow++;
                    break;
                default:
                    break;
            }
        }



        // ------- Generate excel file

        String outputFileName =
                OUT_PUT_DIR.concat(genEndTimeStamp().replace("\\.", "")).concat("_")
                        .concat(REPORT_USER).concat(".xls");

        File outputFile = new File(outputFileName);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        OutputStream os = new FileOutputStream(outputFile);

        genExcelFromTemp(data, TECH_WEEKLY_TEMPLATE, os, 0, 0);

        log.info("Generate tech weekly success!\t--->\t{}", outputFileName);
    }

    /**
     * FIXME
     */
    public void genZCGLWeekly(Set<Task> taskSet) {

    }


    /**
     * TODO
     */
    public static void genRedmineNews() throws RedmineException {

        RedmineManager manager =
                RedmineManagerFactory.createWithUserAuth("http://redmine.100credit.cn/",
                        "qizheng.ma", "100credit.com");


        Project project = manager.getProjectManager().getProjectByKey("dcp");

        log.info("Sorry, redmine news APIS is offline!");
    }


    public void sendMail() {

    }


    /**
     * Get this week's Mon.
     */
    public static String genStartTimeStamp() {
        DateTime dateTime = new DateTime().withDayOfWeek(1);
        return dateTime.toString("yyyy.MM.dd");
    }


    /**
     * Get this week's Fri.
     */
    public static String genEndTimeStamp() {
        DateTime dateTime = new DateTime().withDayOfWeek(5);
        return dateTime.toString("yyyy.MM.dd");
    }


    public static void genExcelFromTemp(String[][] data, String tempFilePath, OutputStream os,
            int startX, int startY) throws IOException {
        InputStream in = new FileInputStream(tempFilePath);
        try {
            POIFSFileSystem fs = new POIFSFileSystem(in);
            Workbook wb = new HSSFWorkbook(fs);
            CellStyle style = wb.createCellStyle();
            style.setWrapText(true);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            Sheet sheet = wb.getSheetAt(0);
            for (int i = 0; i < data.length; i++) {
                Row row;
                if (sheet.getRow(i + startX) == null) {
                    row = sheet.createRow(i + startX);
                } else {
                    row = sheet.getRow(i + startX);
                }
                for (int j = 0; j < data[i].length; j++) {
                    Cell cell;
                    if (row.getCell(j + startY) == null) {
                        cell = row.createCell(j + startY);
                    } else {
                        cell = row.getCell(j + startY);
                    }
                    if (data[i][j] != null && !data[i][j].equals("")) {
                        if (data[i][j].contains("\n")) {
                            cell.setCellStyle(style);
                        }
                        cell.setCellValue(data[i][j]);
                    }
                }
            }
            wb.write(os);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


/**
 * Task object
 *
 *
 * @author memoryaxis@gmail.com
 * @date Apr 15, 2016
 *
 */
class Task {

    private BrProject project;

    private TaskType type;

    private String subject;

    private Set<String> users;

    public BrProject getProject() {
        return project;
    }

    public void setProject(BrProject project) {
        this.project = project;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Task [project=" + project + ", type=" + type + ", subject=" + subject + ", users="
                + users + "]";
    }

}


/**
 * Projects to send weeklys
 *
 * @author memoryaxis@gmail.com
 * @date Apr 15, 2016
 *
 */
enum BrProject {

    DCP("dcp", "催收项目一期"), // 催收项目一期
    DCSP("dcp2", "催收项目二期"), // 催收项目二期

    X("", "占个位置");

    private String redmineName;

    private String projectName;

    private BrProject(String redmineName, String projectName) {
        this.redmineName = redmineName;
        this.projectName = projectName;
    }

    public String getRedmineName() {
        return redmineName;
    }

    public void setRedmineName(String redmineName) {
        this.redmineName = redmineName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}


/**
 * Task types
 *
 * @author memoryaxis@gmail.com
 * @date Apr 15, 2016
 *
 */
enum TaskType {
    DOING, DONE, TODO;
}
