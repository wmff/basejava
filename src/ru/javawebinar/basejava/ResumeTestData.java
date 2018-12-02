package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;

public class ResumeTestData {
    private static final String FULL_NAME = "Григорий Кислин";
    public static final Resume RESUME_1 = new Resume(FULL_NAME);
    public static final Resume RESUME_2 = new Resume(FULL_NAME);
    public static final Resume RESUME_3 = new Resume(FULL_NAME);
    public static final Resume RESUME_4 = new Resume(FULL_NAME);

    static {
        RESUME_1.addContact(ContactType.PHONE, "+7(921)855-04-82");
        RESUME_1.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME_1.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME_1.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        RESUME_1.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        RESUME_1.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        RESUME_1.addContact(ContactType.URL, "http://gkislin.ru/");
        RESUME_1.addSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );
        RESUME_1.addSection(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.")
        );
        RESUME_1.addSection(SectionType.ACHIEVEMENT,
                new ListSection("achivement1",
                        "achivement2")
        );
        RESUME_1.addSection(SectionType.QUALIFICATIONS,
                new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB"
                )
        );

        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Org1", "https://url",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1"),
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        ),
                        new Organization("Org2", "https://url2",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        )
                )
        );
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Org1", "https://url",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1"),
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        ),
                        new Organization("Org2", "https://url2",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        )
                )
        );

        RESUME_2.addContact(ContactType.PHONE, "+7(921)855-04-82");
        RESUME_2.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME_2.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME_2.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        RESUME_2.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        RESUME_2.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        RESUME_2.addContact(ContactType.URL, "http://gkislin.ru/");
        RESUME_2.addSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );
        RESUME_2.addSection(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.")
        );
        RESUME_2.addSection(SectionType.ACHIEVEMENT,
                new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.")
        );
        RESUME_2.addSection(SectionType.QUALIFICATIONS,
                new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB"
                )
        );
        RESUME_2.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Org1", "https://url",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1"),
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        ),
                        new Organization("Org2", "https://url2",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        )
                )
        );
        RESUME_2.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Org1", "https://url",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1"),
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        ),
                        new Organization("Org2", "https://url2",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        )
                )
        );

        RESUME_3.addContact(ContactType.PHONE, "+7(921)855-04-82");
        RESUME_3.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME_3.addSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );
        RESUME_3.addSection(SectionType.ACHIEVEMENT,
                new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.")
        );


        RESUME_4.addContact(ContactType.PHONE, "+7(921)855-04-82");
        RESUME_4.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME_4.addSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям")
        );
        RESUME_4.addSection(SectionType.ACHIEVEMENT,
                new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.", "ahive2")
                );
        RESUME_4.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Org1", "https://url",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1"),
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        ),
                        new Organization("Org2", "https://url2",
                                new Organization.Position(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2014, Month.DECEMBER), "pos1", "descr1")
                        )
                )
        );

    }

}