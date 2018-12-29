package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.ContactType;

import java.util.EnumMap;
import java.util.Map;

public class HtmlUtil {
    public static Map<ContactType, String> formatContacts(Map<ContactType, String> contacts) {
        Map<ContactType, String> result = new EnumMap<>(ContactType.class);
        contacts.forEach((key, value) -> result.put(key, formatContact(contacts, key)));
        return result;
    }

    public static String formatContact(Map<ContactType, String> contacts, ContactType type) {
        String contact = contacts.get(type);
        if (contact != null) {
            String typeLink = "";
            String iconLink;
            switch (type) {
                case PHONE:
                    typeLink = "tel:";
                    iconLink = "<img src='img/telephone.png' alt='phone logo'>";
                    break;
                case SKYPE:
                    typeLink = "skype:";
                    iconLink = "<img src='img/social-skype.png' alt='skype logo'>";
                    break;
                case EMAIL:
                    typeLink = "mailto:";
                    iconLink = "<img src='img/email.png' alt='mail logo'>";
                    break;
                case LINKEDIN:
                    iconLink = "<img src='img/social-linkedin.png' alt='linkedin logo'>";
                    break;
                case GITHUB:
                    iconLink = "<img src='img/social-github.png' alt='github logo'>";
                    break;
                case STACKOVERFLOW:
                    iconLink = "<img src='img/Stackoverflow.png' alt='stackoverflow logo'>";
                    break;
                default:
                    iconLink = "<img src='img/browsers.png' alt='url logo'>";
            }
            return "<a href='" + typeLink + contact + "'>" + iconLink + contact + "</a>";
        }
        return "";
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
