package se.mutate.backend.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.mutate.backend.model.fileobject.FileObject;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.model.jobdetail.JobDetail;
import se.mutate.backend.model.jobspecifics.JobSpecifics;
import se.mutate.backend.repositories.ApplicationRepository;
import se.mutate.backend.repositories.DownloadReposity;
import se.mutate.backend.repositories.JobDetailRepository;
import se.mutate.backend.repositories.JobSpecificsRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private final JobDetailRepository jobDetailRepository;
    private final JobSpecificsRepository jobSpecificsRepository;
    private final DownloadReposity downloadReposity;
    private final ApplicationRepository applicationRepository;

    public Bootstrap(JobDetailRepository jobDetailRepository, JobSpecificsRepository jobSpecificsRepository, DownloadReposity downloadReposity, ApplicationRepository applicationRepository) {
        this.jobDetailRepository = jobDetailRepository;
        this.jobSpecificsRepository = jobSpecificsRepository;
        this.downloadReposity = downloadReposity;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadJobDetails();
        loadJobSpecifics();
        loadDownloadFile();
        loadApplications();
    }
    private void loadJobDetails() {

        JobSpecifics jobSp = new JobSpecifics();
        jobSp.setId(1l);

        JobDetail job1 = new JobDetail(1l,"Senior UI/UX Designer", "Design", jobSp);
        jobDetailRepository.save(job1);

        jobSp.setId(2l);
        JobDetail job2 = new JobDetail(2l,"Junior Frontend Developer", "Engineering", jobSp);
        jobDetailRepository.save(job2);

        jobSp.setId(3l);
        JobDetail job3 = new JobDetail(3l,"Frontend Developer", "Engineering", jobSp);
        jobDetailRepository.save(job3);

        jobSp.setId(4l);
        JobDetail job4 = new JobDetail(4l,"Data Analytic", "Data", jobSp);
        jobDetailRepository.save(job4);

        jobSp.setId(5l);
        JobDetail job5 = new JobDetail(5l,"World of Data", "Data", jobSp);
        jobDetailRepository.save(job5);

    }
    private void loadJobSpecifics() {

        JobDetail job = new JobDetail();
        job.setId(1l);
        job.setRole("Senior UI/UX Designer");

        Set<FormData> fdSet = new HashSet<>();

        //FormData fd1 = new FormData();
        //fdSet.add(fd1);

        String[] jobDesc = {"You will develop a desktop client application based on Electron." +
                "You will be taking on a role as frontend lead where scalability is important to you," +
                "developing a codebase that can scale to a large number of developers." +
                "You will work with designers and together implement the visual and functional aspects of our client.",
                "Lead our Data Platform team to build a scalable data platform that powers features and informs teams. " +
                        "Help us build scalable distributed systems like you'd " +
                        "construct additional pylons in your base. You build those in your sleep, amirite?"};

        String[] doing = new String[]{
                "Do everything",
                "Make me coffee",
                "Point small fingers",
                "Blame everyone except yourself",
                "Pretend to work",
                "Hide from the boss"};
        String[] shouldHave = {
                "3+ years of experience in developing frontend client/web applications. Working with Node.js and any modern framework such as Angular and React.js.",
                "Experience with common front-end development tools such as Webpack, NPM, etc.",
                "Able to sort of work independently as well as help the organization in improving workflows and processes.",
                "Strong understanding of user experience and interactions."};
        String[] bonus = {
                "Experience with Electron, TypeScript and React.",
                "Experience with C-- and building native modules.",
                "Experience with CI/CD, static code analysis tools and Gitflow."};
        JobSpecifics js1 = new JobSpecifics(1l, job,
                job.getRole(), jobDesc, doing, shouldHave, bonus, fdSet);
        jobSpecificsRepository.save(js1);

        jobDesc = new String[]{"You will learn from the best and sit back and enjoy the ride.",
                               "Thats right."};
        doing = new String[]{""};
        shouldHave = new String[]{""};
        bonus = new String[]{""};
        job.setId(2l);
        job.setRole("Junior Frontend Developer");
        js1 = new JobSpecifics(2l, job, job.getRole(), jobDesc, doing, shouldHave, bonus, fdSet);
        jobSpecificsRepository.save(js1);

        jobDesc = new String[]{""};
        doing = new String[]{""};
        shouldHave = new String[]{""};
        bonus = new String[]{""};
        job.setId(3l);
        job.setRole("Frontend Developer");
        js1 = new JobSpecifics(3l, job, job.getRole(), jobDesc, doing, shouldHave, bonus, fdSet);
        jobSpecificsRepository.save(js1);

        jobDesc = new String[]{""};
        doing = new String[]{""};
        shouldHave = new String[]{""};
        bonus = new String[]{""};
        job.setId(4l);
        job.setRole("Data Analytic");
        js1 = new JobSpecifics(4l, job, job.getRole(), jobDesc, doing, shouldHave, bonus, fdSet);
        jobSpecificsRepository.save(js1);

        jobDesc = new String[]{""};
        doing = new String[]{""};
        shouldHave = new String[]{""};
        bonus = new String[]{""};
        job.setId(5l);
        job.setRole("World of Data");
        js1 = new JobSpecifics(5l, job, job.getRole(), jobDesc, doing, shouldHave, bonus, fdSet);
        jobSpecificsRepository.save(js1);


    }

    private void loadDownloadFile() throws IOException {
        // create file object
        String filePath = "Learn Microservices with Spring Boot.pdf";
        File file = new File(filePath);
        byte[] fileContent = new byte[(int) file.length()];

        FileInputStream inputStream = null;
        try {
            // create an input stream pointing to the file
            inputStream = new FileInputStream(file);
            // read the contents of file into byte array
            inputStream.read(fileContent);
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " +
                    e.getMessage());
        } finally {
            // close input stream
            if (inputStream != null) {
                inputStream.close();
            }
        }
        System.out.println(fileContent);



        //System.out.println("LULUL: " + fileContent);

        FileObject fileO = new FileObject(1l, "windoes", fileContent);
        downloadReposity.save(fileO);
        //fileO.setFile(fileContent);

    }

    private void loadApplications() {
        JobSpecifics js = new JobSpecifics();
        js.setId(1l);

        FormData formdata1 = new FormData(1l,"Peter", "Larsson", "070-123 456", "Stockholm",
                "because i want to", "abc@abc.abc", "hockeyspelare", js);
        applicationRepository.save(formdata1);
    }
}
