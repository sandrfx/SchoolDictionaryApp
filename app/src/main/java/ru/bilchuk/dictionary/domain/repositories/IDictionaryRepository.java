package ru.bilchuk.dictionary.domain.repositories;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.bilchuk.dictionary.domain.repositories.models.DictionaryItemModel;

public interface IDictionaryRepository {

    Completable add(@NonNull DictionaryItemModel dictionaryItemModel);
    Single<List<DictionaryItemModel>> getWords();
    Completable delete(long id);
}
