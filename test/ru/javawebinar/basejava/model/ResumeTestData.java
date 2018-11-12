package ru.javawebinar.basejava.model;

public class ResumeTestData {
    private static final String UUID = "uuid1";
    private static final String FULL_NAME = "Григорий Кислин";
    private static final Resume RESUME = new Resume(UUID, FULL_NAME);
    private static final TextSection OBJECTIVE = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
    private static final TextSection PERSONAL = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

    private static final ListSection ACHIEVEMENT = new ListSection();
    private static final ListSection QUALIFICATIONS = new ListSection();
    private static final OrganizationSection EXPERIENCE = new OrganizationSection();

    private static final Organization ORG1 = new Organization("Wrike", "10/2014", "01/2016");
    private static final Organization ORG2 = new Organization("Java Online Projects", "10/2013", "");

    private static final OrganizationSection EDUCATION = new OrganizationSection();

    private static final Organization ORG3 = new Organization("Coursera", "03/2013", "05/2013");
    private static final Organization ORG4 = new Organization("Luxsoft", "03/2011", "04/2011");

    static {
        ACHIEVEMENT.addContent("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        ACHIEVEMENT.addContent("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        ACHIEVEMENT.addContent("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        ACHIEVEMENT.addContent("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        ACHIEVEMENT.addContent("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        ACHIEVEMENT.addContent("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        QUALIFICATIONS.addContent("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        QUALIFICATIONS.addContent("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        QUALIFICATIONS.addContent("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");

        EXPERIENCE.addContent(ORG1);
        EXPERIENCE.addContent(ORG2);

        EDUCATION.addContent(ORG3);
        EDUCATION.addContent(ORG4);
    }

    public static void main(String[] args) {
        RESUME.addContact(ContactType.PHONE, "+7(921)855-04-82");
        RESUME.addContact(ContactType.SKYPE, "grigory.kislin");
        RESUME.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        RESUME.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        RESUME.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        RESUME.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        RESUME.addContact(ContactType.URL, "http://gkislin.ru/");

        RESUME.addSection(SectionType.OBJECTIVE, OBJECTIVE);
        RESUME.addSection(SectionType.PERSONAL, PERSONAL);
        RESUME.addSection(SectionType.ACHIEVEMENT, ACHIEVEMENT);
        RESUME.addSection(SectionType.QUALIFICATIONS, QUALIFICATIONS);
        RESUME.addSection(SectionType.EXPERIENCE, EXPERIENCE);
        RESUME.addSection(SectionType.EDUCATION, EDUCATION);

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