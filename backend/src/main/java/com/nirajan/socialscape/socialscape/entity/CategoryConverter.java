package com.nirajan.socialscape.socialscape.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/*
Use a custom JPA AttributeConverter to convert between the enum constant and its label string when saving/loading from the DB,
so you can keep neat enum constants and human-readable DB values.
 */
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        if (category == null) {
            return null;
        }
        return category.toString(); // returns label, e.g., "Business"
    }

    @Override
    public Category convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Category.fromLabel(dbData); // converts label to enum constant
    }
}

/*
In your entity, just annotate the field normally:

java
@Convert(converter = CategoryConverter.class)
@Column(name = "category")
private Category category;
 */
