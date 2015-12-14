package com.kata.secret_santas;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by yannickgrenzinger on 03/11/2015.
 */
public class SecretSantas {

    public static final String GIVING_TO_SEPARATOR = " -> ";
    public static final String FIRSTNAME_LASTNAME_SEPARATOR = " ";

    public static Set<String> buildPairOfGiverReceiver(final List<String> persons) {
        Set<String> giverRecieverPairs = new HashSet<>();

        Queue<String> queueOfGivers = new LinkedList<>(sortByFamily(persons));
        List<String> personsWhoHaveReceiveGift = new ArrayList<>();
        while (!queueOfGivers.isEmpty()) {
            giverRecieverPairs.add(buildGiftMessage(persons, queueOfGivers, personsWhoHaveReceiveGift));
        }

        return giverRecieverPairs;
    }

    private static List<String> sortByFamily(List<String> persons) {
        return persons.stream().sorted(
                (person1, person2) -> hasAnotherPersonInTheSameFamily(persons, person1)
                        && hasAnotherPersonInTheSameFamily(persons, person2) ? -1 : 1)
                .collect(Collectors.toList());
    }

    private static boolean hasAnotherPersonInTheSameFamily(List<String> persons, String person1) {
        return persons.stream().filter(buildPredicateSameFamily(person1)).findAny().isPresent();
    }

    private static String buildGiftMessage(List<String> persons, Queue<String> queueOfGivers, List<String> personsWhoHaveReceiveGift) {
        final String giver = queueOfGivers.remove();
        StringBuilder giftMessage = new StringBuilder(personWithoutMail(giver) + GIVING_TO_SEPARATOR);
        final Predicate<String> isPersonFamilySameAsGiver = buildPredicateSameFamily(extractFamilyName(giver));
        persons.stream().filter(person -> !(person.equals(giver) || personsWhoHaveReceiveGift.contains(person)))
                .filter(isPersonFamilySameAsGiver)
                .findFirst()
                .ifPresent(person -> {
                    personsWhoHaveReceiveGift.add(person);
                    giftMessage.append(person);
                });
        return giftMessage.toString();
    }

    private static Predicate<String> buildPredicateSameFamily(final String person) {
        return otherPerson -> {
            String familyName = extractFamilyName(otherPerson);
            return !familyName.equals(person);
        };
    }

    private static String extractFamilyName(String person) {
        return person.split(FIRSTNAME_LASTNAME_SEPARATOR)[1];
    }

    private static String personWithoutMail(String person) {
        if (person.contains("@")) {
            return person.substring(0, person.indexOf("<")).trim();
        }
        return person;
    }
}
