package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
                                "аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."),
                new Organization("RIT Center", null,
                        LocalDate.of(2012, 4, 1),
                        LocalDate.of(2014, 10, 1),
                        "Java архитектор",
                        "Организация процесса разработки системы ERP для разных окружений: релизная\n" +
                                "политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация\n" +
                                "Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД\n" +
                                "и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C\n" +
                                "(WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html).\n" +
                                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS\n" +
                                "Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring\n" +
                                "MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote\n" +
                                "scripting via ssh tunnels, PL/Python"),
                new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                        LocalDate.of(2010, 12, 1),
                        LocalDate.of(2012, 4, 1),
                        "Ведущий программист",
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC,\n" +
                                "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM.\n" +
                                "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов\n" +
                                "в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT),\n" +
                                "Highstock, Commet, HTML5."),
                new Organization("Yota", "https://www.yota.ru/",
                        LocalDate.of(2008, 6, 1),
                        LocalDate.of(2010, 12, 1),
                        "Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\"\n" +
                                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS,\n" +
                                "Maven2). Реализация администрирования, статистики и мониторинга фреймворка.\n" +
                                "Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"),
                new Organization("Enkata", "http://enkata.com/",
                        LocalDate.of(2007, 3, 1),
                        LocalDate.of(2008, 6, 1),
                        "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0,\n" +
                                "Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."),
                new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                        LocalDate.of(2005, 1, 1),
                        LocalDate.of(2007, 2, 1),
                        "Разработчик ПО",
                        "Разработка информационной модели, проектирование интерфейсов, реализация и\n" +
                                "отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."),
                new Organization("Alcatel", "http://www.alcatel.ru/",
                        LocalDate.of(1997, 9, 1),
                        LocalDate.of(2005, 1, 1),
                        "Инженер по аппаратному и программному тестированию",
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции\n" +
                                "Alcatel 1000 S12 (CHILL, ASM).")
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
                        ""),
                new Organization("Siemens AG", "http://www.siemens.ru/",
                        LocalDate.of(2005, 1, 1),
                        LocalDate.of(2005, 4, 1),
                        "3 месяца обучения мобильным IN сетям (Берлин)",
                        ""),
                new Organization("Alcatel", "http://www.alcatel.ru/",
                        LocalDate.of(1997, 9, 1),
                        LocalDate.of(1998, 3, 1),
                        "6 месяцев обучения цифровым телефонным сетям (Москва)",
                        ""),
                new Organization("Санкт-Петербургский национальный исследовательский университет\n" +
                        "информационных технологий, механики и оптики", "http://www.alcatel.ru/",
                        LocalDate.of(1993, 9, 1),
                        LocalDate.of(1996, 7, 1),
                        "Аспирантура (программист С, С++)",
                        ""),
                new Organization("", "http://www.alcatel.ru/",
                        LocalDate.of(1987, 9, 1),
                        LocalDate.of(1993, 7, 1),
                        "Инженер (программист Fortran, C)",
                        ""),
                new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                        LocalDate.of(1984, 9, 1),
                        LocalDate.of(1987, 6, 1),
                        "Закончил с отличием",
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
        for (SectionType sectionType : SectionType.values()) {
            System.out.println(sectionType.getTitle());
            System.out.println(RESUME.getSections().get(sectionType));
        }
    }
}
