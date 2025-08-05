package com.nirajan.socialscape.socialscape.entity;

public enum Category {
    BUSINESS("Business"),
    FOOD_DRINK("Food & Drink"),
    HEALTH("Health"),
    MUSIC("Music"),
    CHARITY_CAUSES("Charity & Causes"),
    COMMUNITY("Community"),
    FAMILY_EDUCATION("Family & Education"),
    FASHION("Fashion"),
    FILM_MEDIA("Film & Media"),
    HOME_LIFESTYLE("Home & Lifestyle"),
    SCIENCE_TECH("Science & Tech"),
    SPORTS_FITNESS("Sports & Fitness"),
    TRAVEL_OUTDOOR("Travel & Outdoor");

    // the BUSINESS enum constant has the label "Business"
    private final String label;

    // constructor that sets the label for each enum constant when it’s declared
    Category(String label) {
        this.label = label;
    }

    // returns the label string instead of the enum constant name
    @Override
    public String toString() {
        return this.label;
    }

    // Optional: to get enum from string value
    /*
    allows you to find and return the enum constant that matches a given label string.
    For example, if the database contains "Food & Drink", calling Category.fromLabel("Food & Drink") returns EventCategory.FOOD_DRINK.
    If no matching label is found, it throws an IllegalArgumentException.
     */

    public static Category fromLabel(String label) {
        for (Category c : values()) {
            if (c.label.equals(label)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unknown label: " + label);
    }
}

