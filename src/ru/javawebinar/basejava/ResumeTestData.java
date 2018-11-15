package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;

public class ResumeTestData {
    private static final String UUID = "uuid1";
    private static final String FULL_NAME = "Григорий Кислин";
    private static final Resume RESUME = new Resume(UUID, FULL_NAME);

    static {
        RESUME.addContact(ContactType.PHONE, "+7(921)855-04-82");
        RESUME.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        RESUME.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        RESUME.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        RESUME.addContact(ContactType.URL, "http://gkislin.ru/");

        RESUME.addSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );

        RESUME.addSection(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.")
        );

        RESUME.addSection(SectionType.ACHIEVEMENT,
                new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.")
        );

        RESUME.addSection(SectionType.QUALIFICATIONS,
                new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB"
                )
        );

        RESUME.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Org1", "https://url",
                                new Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1"),
                                new Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        ),
                        new Organization("Org2", "https://url2",
                                new Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        )
                )
        );

        RESUME.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Org1", "https://url",
                                new Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1"),
                                new Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        ),
                        new Organization("Org2", "https://url2",
                                new Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        )
                )
        );

    }

    public static void main(String[] args) {

        System.out.println("Contacts:");
        RESUME.getContacts().forEach((ContactType, String) -> System.out.println(ContactType + ": " + String));
        System.out.println();

        System.out.println(SectionType.OBJECTIVE.getTitle());
        System.out.println(RESUME.getSection(SectionType.OBJECTIVE));
        System.out.println();

        System.out.println(SectionType.PERSONAL.getTitle());
        System.out.println(RESUME.getSection(SectionType.PERSONAL));
        System.out.println();

        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        System.out.println(RESUME.getSection(SectionType.ACHIEVEMENT));
        System.out.println();

        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        System.out.println(RESUME.getSection(SectionType.QUALIFICATIONS));
        System.out.println();

        System.out.println(SectionType.EXPERIENCE.getTitle());
        System.out.println(RESUME.getSection(SectionType.EXPERIENCE));
        System.out.println();

        System.out.println(SectionType.EDUCATION.getTitle());
        System.out.println(RESUME.getSection(SectionType.EDUCATION));
        System.out.println();


    }
}