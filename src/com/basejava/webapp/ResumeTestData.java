package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Test for your com.basejava.webapp.storage.ArrayStorage implementation
 */
public class ResumeTestData {
    static final Resume RESUME = new Resume("1", "Григорий Кислин");

    public static void main(String[] args) {
        Map<ContactType, String> contacts = RESUME.getContacts();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");

        ListSection achievement = new ListSection(new ArrayList<>(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \n" +
                        "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). \n" +
                        "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. \n" +
                        "Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike.\n" +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С,\n" +
                        "Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/\n" +
                        "JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция \n" +
                        "CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, \n" +
                        "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов\n" +
                        "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о \n" +
                        "состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и \n" +
                        "мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport,\n" +
                        "Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.")));
        ListSection qualification = new ListSection(new ArrayList<>(Arrays.asList(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,",
                "MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security,\n" +
                        "Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin,\n" +
                        "Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT,\n" +
                        "MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1,\n" +
                        "OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport,\n" +
                        "OpenCmis, Bonita, pgBouncer.",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных\n" +
                        "шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\"")));

        OrganizationSection experience = new OrganizationSection(new ArrayList<>(Arrays.asList(
                new Organization("Java Online Projects", "http://javaops.ru/",
                        LocalDate.of(2013, 10, 1),
                        LocalDate.now(),
                        "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок."),
                new Organization("Wrike", "https://www.wrike.com/",
                        LocalDate.of(2014, 10, 1),
                        LocalDate.of(2016, 1, 1),
                        "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API,\n" +
                                "Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная\n" +
                                "аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")
        )));

        OrganizationSection education = new OrganizationSection(new ArrayList<>(Arrays.asList(
                new Organization("Coursera", "https://www.coursera.org/course/progfun",
                        LocalDate.of(2013, 3, 1),
                        LocalDate.of(2013, 5, 1),
                        "\"Functional Programming Principles in Scala\" by Martin Odersky",
                        ""),
                new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course." +
                        "html?ID=22366",
                        LocalDate.of(2011, 3, 1),
                        LocalDate.of(2011, 4, 1),
                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                        "")
        )));

        Map<SectionType, Section> sections = RESUME.getSections();
        sections.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность," +
                " инициативность. Пурист кода и архитектуры."));
        sections.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по " +
                "Java Web и Enterprise технологиям"));
        sections.put(SectionType.ACHIEVEMENT, achievement);
        sections.put(SectionType.QUALIFICATION, qualification);
        sections.put(SectionType.EXPERIENCE, experience);
        sections.put(SectionType.EDUCATION, education);

        for (ContactType contactType : ContactType.values()) {
            System.out.println(contactType.getTitle() + " : " + RESUME.getContacts().get(contactType));
        }

        System.out.println();
        System.out.println(SectionType.PERSONAL.getTitle());
        System.out.println(((TextSection) sections.get(SectionType.PERSONAL)).getText());
        System.out.println();
        System.out.println(SectionType.OBJECTIVE.getTitle());
        System.out.println(((TextSection) sections.get(SectionType.OBJECTIVE)).getText());
        System.out.println();
        System.out.println(SectionType.ACHIEVEMENT.getTitle());

        List<String> listTextAchievement = ((ListSection) sections.get(SectionType.ACHIEVEMENT)).getListText();
        System.out.println();
        for (String text : listTextAchievement) {
            System.out.println(text);
            System.out.println();
        }

        List<String> listTextQualification = ((ListSection) sections.get(SectionType.QUALIFICATION)).getListText();
        System.out.println();
        for (String text : listTextQualification) {
            System.out.println(text);
            System.out.println();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
        List<Organization> listOrganization = ((OrganizationSection) sections.get(SectionType.EXPERIENCE)).getPost();
        for (Organization org : listOrganization) {
            System.out.println(org.getName());
            System.out.println(dtf.format((org.getDateStart())) + " - " + dtf.format((org.getDateFinish())));
            System.out.println(org.getPost());
            System.out.println(org.getText());
        }
        System.out.println();

        listOrganization = ((OrganizationSection) sections.get(SectionType.EDUCATION)).getPost();
        for (Organization org : listOrganization) {
            System.out.println(org.getName());
            System.out.println(dtf.format((org.getDateStart())) + " - " + dtf.format((org.getDateFinish())));
            System.out.println(org.getPost());
            System.out.println(org.getText());
        }
    }
}
