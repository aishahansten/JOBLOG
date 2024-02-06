package com.ssafy.joblog.domain.alarm.repository;

import com.ssafy.joblog.domain.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Integer> {

    List<Alarm> findByUserId(int userId);

    @Modifying
    @Query("UPDATE Alarm alarm SET alarm.isChecked = true WHERE alarm.id = :id")
    void markCheckedAlarm(@Param("id") int id);

    @Modifying
    @Query("UPDATE Alarm alarm SET alarm.isDelete = true WHERE alarm.id = :id")
    void markDeletedAlarm(@Param("id") int id);

}
