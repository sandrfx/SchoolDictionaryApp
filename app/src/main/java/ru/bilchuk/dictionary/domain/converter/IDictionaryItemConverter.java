package ru.bilchuk.dictionary.domain.converter;

import java.util.List;

import ru.bilchuk.dictionary.domain.models.DictionaryItem;
import ru.bilchuk.dictionary.domain.repositories.models.DictionaryItemModel;

public interface IDictionaryItemConverter {

    DictionaryItemModel convert(DictionaryItem dictionaryItem);
    DictionaryItem reverse(DictionaryItemModel dictionaryItemModel);

    List<DictionaryItem> reverseList(List<DictionaryItemModel> dictionaryItemModel);
}
