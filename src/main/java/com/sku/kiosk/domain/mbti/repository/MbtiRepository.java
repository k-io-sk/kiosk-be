/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sku.kiosk.domain.mbti.entity.Mbti;

public interface MbtiRepository extends JpaRepository<Mbti, String> {

  @Query(
      """
    select sum(m.requestCount) from Mbti m
            where (:ei is null or m.ei = :ei)
                and (:sn is null or m.sn = :sn)
                    and (:tf is null or m.tf = :tf)
                        and (:jp is null or m.jp = :jp)
    """)
  Long sumRequestCountByMbti(
      @Param("ei") String ei,
      @Param("sn") String sn,
      @Param("tf") String tf,
      @Param("jp") String jp);
}
