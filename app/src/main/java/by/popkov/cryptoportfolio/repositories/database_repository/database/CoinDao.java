package by.popkov.cryptoportfolio.repositories.database_repository.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Observable;


@Dao
public interface CoinDao {
    @Query("SELECT * FROM coin")
    LiveData<List<CoinEntity>> getAllLiveData();

    @Query("SELECT * FROM coin")
    List<CoinEntity> getAll();

    @Query("SELECT * FROM coin")
    Observable<List<CoinEntity>> getAllObservable();

    @Query("SELECT * FROM coin WHERE id =:id")
    LiveData<CoinEntity> getCoinLiveData(String id);

    @Query("SELECT * FROM coin WHERE id =:id")
    CoinEntity getCoin(String id);

    @Query("SELECT * FROM coin WHERE id =:id")
    Observable<CoinEntity> getCoinObservable(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CoinEntity coinEntity);

    @Update
    void update(CoinEntity coinEntity);

    @Delete
    void delete(CoinEntity coinEntity);
}
