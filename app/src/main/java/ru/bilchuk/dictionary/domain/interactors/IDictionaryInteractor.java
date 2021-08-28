package ru.bilchuk.dictionary.domain.interactors;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.bilchuk.dictionary.domain.models.DictionaryItem;

public interface IDictionaryInteractor {

    Completable add(@NonNull DictionaryItem dictionaryItem);
    Single<List<DictionaryItem>> getWords();
    Completable delete(long id);
}
