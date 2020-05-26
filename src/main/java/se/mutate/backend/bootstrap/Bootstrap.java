package se.mutate.backend.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.mutate.backend.model.fileobject.FileObject;
import se.mutate.backend.model.formdata.FormData;
import se.mutate.backend.model.jobdetail.Job;
import se.mutate.backend.model.user.AppUser;
import se.mutate.backend.repositories.AppUserRepository;
import se.mutate.backend.repositories.ApplicationRepository;
import se.mutate.backend.repositories.DownloadReposity;
import se.mutate.backend.repositories.JobRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class Bootstrap implements CommandLineRunner {

    private final JobRepository jobRepository;
    private final DownloadReposity downloadReposity;
    private final ApplicationRepository applicationRepository;
    private final AppUserRepository appUserRepository;

    public Bootstrap(JobRepository jobRepository, DownloadReposity downloadReposity,
                     ApplicationRepository applicationRepository, AppUserRepository appUserRepository) {
        this.jobRepository = jobRepository;
        this.downloadReposity = downloadReposity;
        this.applicationRepository = applicationRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadJobs();
        //loadJobSpecifics();
        loadDownloadFile();
        loadApplications();
        loadUsers();
    }
    private void loadJobs() {

        Job job1 = new Job();
        job1.setRole("Senior UI/UX Designer");
        job1.setField("Design");
        job1.setJobDescription(new String[]{"You will develop a desktop client application based on Electron." +
                "You will be taking on a role as frontend lead where scalability is important to you," +
                "developing a codebase that can scale to a large number of developers." +
                "You will work with designers and together implement the visual and functional aspects of our client.",
                "Lead our Data Platform team to build a scalable data platform that powers features and informs teams. " +
                        "Help us build scalable distributed systems like you'd " +
                        "construct additional pylons in your base. You build those in your sleep, amirite?"});
        job1.setDoing(new String[]{
                "Do everything",
                "Make me coffee",
                "Point small fingers",
                "Blame everyone except yourself",
                "Pretend to work",
                "Hide from the boss"
        });
        job1.setShouldHave(new String[]{
                "3+ years of experience in developing frontend client/web applications. Working with Node.js and any modern framework such as Angular and React.js.",
                "Experience with common front-end development tools such as Webpack, NPM, etc.",
                "Able to sort of work independently as well as help the organization in improving workflows and processes.",
                "Strong understanding of user experience and interactions."
        });
        job1.setBonus(new String[]{
                "Experience with Electron, TypeScript and React.",
                "Experience with C-- and building native modules.",
                "Experience with CI/CD, static code analysis tools and Gitflow."
        });
        jobRepository.save(job1);


        Job job2 = new Job();
        job2.setRole("Junior Frontend Developer");
        job2.setField("Engineering");
        job2.setJobDescription(new String[]{
                "You will learn from the best and sit back and enjoy the ride.",
                "Thats right."
        });
        job2.setDoing(new String[]{});
        job2.setShouldHave(new String[]{});
        job2.setBonus(new String[]{});
        jobRepository.save(job2);

        Job job3 = new Job();
        job3.setRole("Frontend Developer");
        job3.setField("Engineering");
        job3.setJobDescription(new String[]{});
        job3.setDoing(new String[]{});
        job3.setShouldHave(new String[]{});
        job3.setBonus(new String[]{});
        jobRepository.save(job3);

        Job job4 = new Job();
        job4.setRole("Data Analytic");
        job4.setField("Data");
        job4.setJobDescription(new String[]{});
        job4.setDoing(new String[]{});
        job4.setShouldHave(new String[]{});
        job4.setBonus(new String[]{});
        jobRepository.save(job4);

        Job job5 = new Job();
        job5.setRole("World of Data");
        job5.setField("Data");
        job5.setJobDescription(new String[]{});
        job5.setDoing(new String[]{});
        job5.setShouldHave(new String[]{});
        job5.setBonus(new String[]{});
        jobRepository.save(job5);

    }

    private void loadUsers() {

        AppUser adminUser = new AppUser();
        adminUser.setUsername("admin");
        adminUser.setEmail("admin@a.com");
        adminUser.setPassword("admin");
        adminUser.setClaims("admin");
        appUserRepository.save(adminUser);

        AppUser user = new AppUser();
        user.setUsername("MrStrongBad");
        user.setEmail("a@a.com");
        user.setClaims("normal");
        user.setPassword("123");
        appUserRepository.save(user);
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

        Job job = new Job();
        job.setId(1l);

        FormData formdata1 = new FormData(1l,"Peter", "Larsson", "070-123 456", "Stockholm",
                "because i want to", "abc@abc.abc", "hockeyspelare", job);
        applicationRepository.save(formdata1);
    }
}
